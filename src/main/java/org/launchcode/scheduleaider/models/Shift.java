package org.launchcode.scheduleaider.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Shift {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Schedule schedule;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private int employeeId;

    public Shift() {
    }

    public int getId() {
        return id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public String getHumanReadableStartTime() {

        String humanReadableStartTime = this.startTime.format(DateTimeFormatter.ofPattern("h:mm a"));

        return humanReadableStartTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getHumanReadableEndTime() {

        String humanReadableEndTime = this.endTime.format(DateTimeFormatter.ofPattern("h:mm a"));

        return humanReadableEndTime;

    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
