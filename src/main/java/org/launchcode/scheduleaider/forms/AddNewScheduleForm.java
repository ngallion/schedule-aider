package org.launchcode.scheduleaider.forms;

public class AddNewScheduleForm {
    private String name;
    private int weeksAhead;

    public AddNewScheduleForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeeksAhead() {
        return weeksAhead;
    }

    public void setWeeksAhead(int weeksAhead) {
        this.weeksAhead = weeksAhead;
    }
}
