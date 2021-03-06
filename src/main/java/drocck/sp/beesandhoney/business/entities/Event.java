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

    // For our uses we can be precise down to the date but not time.
    private boolean allDay = true;

    public Event() {

    }

    public Event(String t, Date d) {
        title = t;
        date = d;
    }

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

}