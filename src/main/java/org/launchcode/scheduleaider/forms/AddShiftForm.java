package org.launchcode.scheduleaider.forms;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AddShiftForm {

    private String date;
    private String startTime;
    private String endTime;
    private String employeeId;
    private int scheduleId;

    public AddShiftForm() {
    }

    public AddShiftForm(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getDateOfTypeDate(){
        return Date.valueOf(LocalDate.parse(this.date));
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Time getStartTimeOfTypeTime(){
        return Time.valueOf(LocalTime.parse(this.startTime));
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Time getEndTimeOfTypeTime(){
        return Time.valueOf(LocalTime.parse(this.endTime));
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
