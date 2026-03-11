package com.somos.api.technician.application;

import com.somos.api.technician.domain.Technician;

import java.util.Date;
import java.util.List;

public interface TechnicianRepository {
    public List<Technician> getAll();
    public List<Technician> getAvailableTechnicians(Date startTime, Date endTime, int limit);
}

