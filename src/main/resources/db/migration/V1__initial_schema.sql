create table technicians (
         id bigint auto_increment PRIMARY KEY,
         name VARCHAR(235)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table events (
        id bigint auto_increment PRIMARY KEY,
        type VARCHAR(20) NOT NULL,
        start_time DATETIME NOT NULL,
        end_time DATETIME NOT NULL,
        installation_id VARCHAR(100),
        technician_id bigint NOT NULL,
        CONSTRAINT fk_events_technician FOREIGN KEY (technician_id) REFERENCES technicians(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;