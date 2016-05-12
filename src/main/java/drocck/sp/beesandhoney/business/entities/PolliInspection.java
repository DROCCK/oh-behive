package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Robert Wilk
 * on 5/9/2016.
 */
@Entity
public class PolliInspection implements Serializable {

    public enum Purpose {
        FEED, INSPECT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORCHARD")
    private Orchard orchard;

    @Column(name = "INSPECT_DATE")
    private Date date;

    @Column(name = "PURPOSE")
    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Column(name = "NOTES")
    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public Orchard getOrchard() {
        return orchard;
    }

    public void setOrchard(Orchard orchard) {
        this.orchard = orchard;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
