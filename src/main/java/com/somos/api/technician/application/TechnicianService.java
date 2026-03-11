package com.somos.api.technician.application;

import com.somos.api.event.application.EventRepository;
import com.somos.api.event.domain.Event;
import com.somos.api.technician.domain.Technician;

import java.util.Date;
import java.util.List;

import static com.somos.api.shared.infrastructure.utils.StringUtils.convertIsoToDate;

public class TechnicianService {
    public final TechnicianRepository technicianRepository;
    public final EventRepository eventRepository;
    public static String START_LABORAL_TIME = "08:00";
    public static String END_LABORAL_TIME = "18:00";
    public TechnicianService(TechnicianRepository technicianRepository, EventRepository eventRepository) {
        this.technicianRepository = technicianRepository;
        this.eventRepository = eventRepository;
    }

    public Technician getAppointment(String installationId, String startTime, String endTime){
        // Validate startTime and endTime are in the same date
        if(!startTime.substring(0,10).equals(endTime.substring(0,10))){
            throw new RuntimeException("start_time and end_time should be on the same date");
        }

        // Get the technician most available for the specific startTime and endTime
        Date startTimeJava = convertIsoToDate(startTime);
        Date endTimeJava = convertIsoToDate(endTime);
        List<Technician> techniciantList = technicianRepository.getAvailableTechnicians(startTimeJava, endTimeJava, 1);
        techniciantList.stream().forEach(technicianReport -> System.out.println("Tecnico Id: %s".formatted(technicianReport.getId())));

        if (techniciantList.size() == 0){
            throw new RuntimeException("Technicians are not available to assign the installation");
        }
        var technician = techniciantList.get(0);

        // Create the event type travel
        Event event = new Event("installation", startTimeJava, endTimeJava, installationId, technician);
        eventRepository.save(event);

        return technician;
    }
}

/*
*
select technician_id, count(1) as events_qty
from events
where start_time BETWEEN '2025-03-10 08:00' and '2025-03-10 18:00'
group by technician_id
;

select *
from technicians t
inner join events e on (t.id = e.technician_id)
where STR_TO_DATE('2025-03-10T10:00:00.000Z', '%Y-%m-%dT%H:%i:%s')
;
* */