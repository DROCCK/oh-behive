package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by Connor
 * on 10/9/2015.
 */
@Entity
public class Inspection {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DOUBLES")
    private Integer doubles;

    @Column(name = "SINGLES")
    private Integer singles;

    @Column(name = "SUPERS")
    private Integer supers;

    @Column(name = "DUDS")
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
    @JoinColumn(name = "INSPECTED_YARD")
    private Yard yard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDoubles() {
        return doubles;
    }

    public void setDoubles(Integer doubles) {
        this.doubles = doubles;
    }

    public Integer getSingles() {
        return singles;
    }

    public void setSingles(Integer singles) {
        this.singles = singles;
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

    public Yard getYard() {
        return yard;
    }

    public void setYard(Yard yard) {
        this.yard = yard;
    }
}
