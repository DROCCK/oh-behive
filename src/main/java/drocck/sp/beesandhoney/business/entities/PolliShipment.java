package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Robert on 3/5/2016.
 */
@Entity
public class PolliShipment {

    public enum Direction {
        IN, OUT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SHIP_DATE")
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "DIRECTION")
    private Direction direction;

    @Column(name = "DUD")
    private Integer dud;

    @Column(name = "WHERE_FROM")
    private String from;

    @Column(name = "COUNT")
    private Integer in;

    @Column(name = "WHERE_TO")
    private String to;

    @Column(name = "NOTES")
    private String notes;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Integer getIn() {
        return in;
    }

    public void setIn(Integer in) {
        this.in = in;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDud() {
        return dud;
    }

    public void setDud(Integer dud) {
        this.dud = dud;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
