package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Connor on 2/10/2016.
 *
 * An event is a representation of a calender event
 * that contains a date, and a title.
 */

@Entity
public class Event {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String title;

    @Column(nullable = false)
    private Date date;

//    //The nucing task for event. I'm assuming this will be automatic and not something a user will need to choose options for.
//    private NucingTask task;

    //The goal amount of Queens
    //or total Hives in which we can then determine for the user the number of queens needed to reach given(entered) goal etc.
    //This could also be done in DTO maybe.
    private int goalAmount;

    // For our uses we can be precise down to the date but not time.
    private boolean allDay = true;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAllDay() {
        return allDay;
    }

//    public NucingTask getTask() {
//        return task;
//    }

    public void setTask(NucingTask task) {
        this.task = task;
    }

    public int getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(int goalAmount) {
        this.goalAmount = goalAmount;
    }
}