package drocck.sp.beesandhoney.business.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by David on 9/29/2015.
 */

public class Dropsite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Double longitude = null;
    private Double latitude = null;
    private String notes = null;
    private Long dropId = null;
    private Integer numDoublesDropped = null;
    private Integer numSinglesDropped = null;
    private Integer numVisits = null;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getDropId() {
        return dropId;
    }

    public void setDropId(Long dropId) {
        this.dropId = dropId;
    }

    public int getNumDoublesDropped() {
        return numDoublesDropped;
    }

    public void setNumDoublesDropped(int numDoublesDropped) {
        this.numDoublesDropped = numDoublesDropped;
    }

    public int getNumSinglesDropped() {
        return numSinglesDropped;
    }

    public void setNumSinglesDropped(int numSinglesDropped) {
        this.numSinglesDropped = numSinglesDropped;
    }

    public int getNumVisits() {
        return numVisits;
    }

    public void setNumVisits(int numVisits) {
        this.numVisits = numVisits;
    }

    public int getNumHivesDropped(){
       return getNumSinglesDropped()+getNumDoublesDropped();
    }
}
