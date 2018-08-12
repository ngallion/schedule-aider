package org.launchcode.scheduleaider.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private Date startDate;

    @OneToMany
    @JoinColumn(name = "schedule_id")
    private List<Shift> shifts = new ArrayList<>();

    public Schedule() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<Shift> getShifts() {
        return shifts;
    }
}
