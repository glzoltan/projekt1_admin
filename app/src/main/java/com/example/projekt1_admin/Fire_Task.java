package com.example.projekt1_admin;

public class Fire_Task {
    private String ID;
    private String TASK;
    private String GROUP;
    private String STATUS;
    public Fire_Task(String ID, String GROUP, String TASK, String STATUS) {
        this.ID = ID;
        this.TASK = TASK;
        this.GROUP = GROUP;
        this.STATUS = STATUS;
    }

    public String getID() {
        return ID;
    }

    public String getTASK() {
        return TASK;
    }

    public String getGROUP() {
        return GROUP;
    }

    public String getSTATUS() {
        return STATUS;
    }
}
