package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by cjeli_000
 * on 10/9/2015.
 */
@Entity
public class Inspection {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NUM_DOUBLES")
    private Integer numDoubles;

    @Column(name = "NUM_SINGLES")
    private Integer numSingles;

    @Column(name = "NUM_SUPERS")
    private Integer supers;

    @Column(name = "NUM_DUDS")
    private Integer duds;

    @Column(name = "VISIT_DATE")
    private Date visitDate = new Date(Calendar.getInstance().getTime().getTime());

    @Column(name = "IS_FED")
    private boolean isFed;

    @Column(name = "MEDICATION")
    private String medication;

    @Column(name = "NOTES")
    private String notes;

    @ManyToOne(fetch=FetchType.EAGER)
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

    public Integer getSupers() {
        return supers;
    }

    public void setSupers(Integer supers) {
        this.supers = supers;
    }

    public Integer getDuds() {
        return duds;
    }

    public void setDuds(Integer duds) {
        this.duds = duds;
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
