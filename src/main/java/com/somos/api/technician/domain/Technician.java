package com.somos.api.technician.domain;

import com.somos.api.event.domain.Event;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "technicians")
@NoArgsConstructor
public class Technician {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Column(name = "name", nullable = false)
    String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "technician")
    List<Event> events = new ArrayList<>();

    public Technician(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Technician(String name){
        this.name = name;
    }

    public int getEventsCount(){
        return 0;
    }

}
