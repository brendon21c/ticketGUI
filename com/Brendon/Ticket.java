package com.Brendon;

/**
 * Created by Brendon on 4/9/16.
 */
public class Ticket {

    private int idNum;
    private String problem;
    private int severity;

    public int getIdNum() {
        return idNum;
    }

    public String getProblem() {
        return problem;
    }

    public int getSeverity() {
        return severity;
    }

    public Ticket(int ID, String issue, int severity) {

        this.idNum = ID;
        this.problem = issue;
        this.severity = severity;

    }

    @Override
    public String toString() {

        return idNum + ": " + problem +": " + severity + ": ";
    }
}
