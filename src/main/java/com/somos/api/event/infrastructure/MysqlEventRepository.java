package com.somos.api.event.infrastructure;

import com.somos.api.event.application.EventRepository;
import com.somos.api.event.domain.Event;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class MysqlEventRepository implements EventRepository {
    SessionFactory sessionFactory;
    private Object Event;

    public MysqlEventRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Event> findById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Event.class, id));
        }
    }

    @Override
    public void save(Event event) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.merge(event);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
