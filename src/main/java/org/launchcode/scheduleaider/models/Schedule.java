package org.launchcode.scheduleaider.models;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;

public class Schedule {

    private int id;
    private String name;
    private static int nextId = 0;
    private HashMap<DayOfWeek, ArrayList<Shift>> shifts;

    public Schedule() {
        this.id = nextId;
        nextId++;
        this.shifts.put(DayOfWeek.MONDAY, new ArrayList<Shift>());
        this.shifts.put(DayOfWeek.TUESDAY, new ArrayList<Shift>());
        this.shifts.put(DayOfWeek.WEDNESDAY, new ArrayList<Shift>());
        this.shifts.put(DayOfWeek.THURSDAY, new ArrayList<Shift>());
        this.shifts.put(DayOfWeek.FRIDAY, new ArrayList<Shift>());
        this.shifts.put(DayOfWeek.SATURDAY, new ArrayList<Shift>());
        this.shifts.put(DayOfWeek.SUNDAY, new ArrayList<Shift>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<DayOfWeek, ArrayList<Shift>> getShifts() {
        return shifts;
    }

    public void addShift(DayOfWeek dayOfWeek, Shift shift){
        this.shifts.get(dayOfWeek).add(shift);
    }
}
