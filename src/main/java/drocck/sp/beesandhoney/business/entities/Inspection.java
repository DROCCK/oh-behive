package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by cjeli_000 on 10/9/2015.
 */
@Entity
public class Inspection {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;

    @Column(name = "NUM_DOUBLES")
    private Integer numDoubles = null;

    @Column(name = "NUM_SINGLES")
    private Integer numSingles = null;

    @Column(name = "VISIT_DATE")
    private Date visitDate = new Date(Calendar.getInstance().getTime().getTime());;

    @Column(name = "IS_FED")
    private boolean isFed = false;

    @Column(name = "MEDICATION")
    private String medication = null;

    @Column(name = "NOTES")
    private String notes = null;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "DROP_SITE")
    private DropSite dropSite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumDoubles() {
        return numDoubles;
    }

    public void setNumDoubles(Integer numDoubles) {
        this.numDoubles = numDoubles;
    }

    public Integer getNumSingles() {
        return numSingles;
    }

    public void setNumSingles(Integer numSingles) {
        this.numSingles = numSingles;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public boolean isFed() {
        return isFed;
    }

    public void setIsFed(boolean isFed) {
        this.isFed = isFed;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public DropSite getDropSite() {
        return dropSite;
    }

    public void setDropSite(DropSite dropSite) {
        this.dropSite = dropSite;
    }
}
