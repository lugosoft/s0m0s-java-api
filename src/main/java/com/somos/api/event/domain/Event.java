package com.somos.api.event.domain;

import com.somos.api.technician.domain.Technician;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "events")
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    Long id;

    @Column(name="type", nullable = false)
    String type;

    @Column(name="start_time", nullable = false)
    Date start_time;

    @Column(name="end_time", nullable = false)
    Date end_time;

    @Column(name="installation_id", nullable = true)
    String installation_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="technician_id")
    Technician technician;

    public Event (String type, Date start_time, Date end_time, String installation_id, Technician technician){
        this.type = type;
        this.start_time = start_time;
        this.end_time = end_time;
        this.installation_id = installation_id;
        this.technician = technician;
    }
}
