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

    @Enumerated(EnumType.STRING)
    @Column(name = "DIRECTION")
    private Direction direction;

    @Column(name = "IN")
    private Integer in;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "DUD")
    private Integer dud;

    @Column(name = "NOTES")
    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
