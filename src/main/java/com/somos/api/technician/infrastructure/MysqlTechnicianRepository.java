package com.somos.api.technician.infrastructure;

import com.somos.api.technician.application.TechnicianRepository;
import com.somos.api.technician.domain.Technician;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class MysqlTechnicianRepository implements TechnicianRepository {
    public final SessionFactory sessionFactory;

    public MysqlTechnicianRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Technician> getAll() {
        return List.of();
    }

    @Override
    public List<Technician> getAvailableTechnicians(Date startTime, Date endTime, int limit) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            String sql = """
            select t.id, t.name
            from technicians t
            left outer join events ev on (ev.technician_id = t.id and date(ev.start_time) = DATE(:start_date) )
            WHERE NOT EXISTS (
                select 1
                from events e
                where e.technician_id = t.id
                and :start_time < e.end_time
                and :end_time > e.start_time
            )
            group by t.id
            ORDER BY COALESCE(sum(TIMESTAMPDIFF(MINUTE, ev.start_time, ev.end_time)),0) ASC
            limit :limit_val
            """;

            @SuppressWarnings("unchecked")
            List<Object[]> rows = session.createNativeQuery(sql)
                    .setParameter("start_date", (startTime))
                    .setParameter("start_time", (startTime))
                    .setParameter("end_time", (endTime))
                    .setParameter("limit_val", limit)
                    .getResultList();

            List<Technician> list = rows.stream()
                    .map(row -> new Technician(
                            ((Number) row[0]).longValue(),
                            (String) row[1]
                    ))
                    .toList();

            tx.commit();
            return list;

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
