package com.example.demo.business;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.SqlResultSetMapping;

public class TimespanPassengerReportDTO {
    public Long id;
    public Long count;

    public TimespanPassengerReportDTO(){}
    public TimespanPassengerReportDTO(Long id, Long count) {
        this.id = id;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
