package drocck.sp.beesandhoney.business.entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Chai on 2/15/2016.
 *
 * Information for a nucing task related to a single yard.
 */
@Entity
public class NucReport {

    @Id
    @Column
    @GeneratedValue
    private Long id;

    @OneToOne
    private Yard yard; //Later this will be specifc nucing yard.

    // LAID OUT STAGE
    private Date dateLaidOut;

    // BEES PLACED STAGE
    private Date dateBeesPlaced;
    private int initialCount; // Parents in to yard

    // BEES SUPERED STAGE
    private Date dateBeesSupered;

    // BEES SPLIT STAGE
    private Date dateBeesSplit;
    private int oldQueensCount; // number of old queens found after nucing
    private int nucCount; //Number of hives of a specific nucingyard after nucing.

    // QUEENS PLACED STAGE
    private int queensPlaced; // the amount of queens placed

    // QUEENS CHECKED STAGE
    private int finalCount; // Total to be taken out of yard

    // percent yield
    // queen right percentage

    private String notes;


    // from the day the bees are split the queens placed and check are auto generated


    public Long getId() {
        return id;
    }

    public Yard getYard() {
        return yard;
    }

    public void setYard(Yard yard) {
        this.yard = yard;
    }

    public int getInitialCount() {
        return initialCount;
    }

    public void setInitialCount(int initialCount) {
        this.initialCount = initialCount;
    }

    public int getOldQueensCount() {
        return oldQueensCount;
    }

    public void setOldQueensCount(int oldQueensCount) {
        this.oldQueensCount = oldQueensCount;
    }

    public int getNucCount() {
        return nucCount;
    }

    public void setNucCount(int postNuc) {
        this.nucCount = postNuc;
    }

    public int getFinalCount() {
        return finalCount;
    }

    public void setFinalCount(int newQueenCount) {
        this.finalCount = newQueenCount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDateLaidOut() {
        return dateLaidOut;
    }

    public void setDateLaidOut(Date dateLaidOut) {
        this.dateLaidOut = dateLaidOut;
    }

    public Date getDateBeesPlaced() {
        return dateBeesPlaced;
    }

    public void setDateBeesPlaced(Date dateBeesPlaced) {
        this.dateBeesPlaced = dateBeesPlaced;
    }

    public Date getDateBeesSupered() {
        return dateBeesSupered;
    }

    public void setDateBeesSupered(Date dateBeesSupered) {
        this.dateBeesSupered = dateBeesSupered;
    }

    public Date getDateBeesSplit() {
        return dateBeesSplit;
    }

    public void setDateBeesSplit(Date dateBeesSplit) {
        this.dateBeesSplit = dateBeesSplit;
    }

    public int getQueensPlaced() {
        return queensPlaced;
    }

    public void setQueensPlaced(int queensPlaced) {
        this.queensPlaced = queensPlaced;
    }

    public float getSplitRatio() {
        float ratio = (nucCount + oldQueensCount) / initialCount;
        ratio = ((int)(100 * ratio)) / 100;
        return ratio;
    }
}

