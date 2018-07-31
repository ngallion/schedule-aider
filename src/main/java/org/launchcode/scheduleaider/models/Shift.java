package org.launchcode.scheduleaider.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Time;
import java.time.format.DateTimeFormatter;

@Entity
public class Shift {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Schedule schedule;

    private Date date;

    private Time startTime;

    private Time endTime;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public String getHumanReadableStartTime() {

        String humanReadableStartTime = this.startTime.toLocalTime().format(DateTimeFormatter.ofPattern("h:mm a"));

        return humanReadableStartTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getHumanReadableEndTime() {

        String humanReadableEndTime = this.endTime.toLocalTime().format(DateTimeFormatter.ofPattern("h:mm a"));

        return humanReadableEndTime;

    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
