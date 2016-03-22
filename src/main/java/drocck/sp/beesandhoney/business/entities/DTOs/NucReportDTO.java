package drocck.sp.beesandhoney.business.entities.DTOs;

import drocck.sp.beesandhoney.business.entities.NucReport;

/**
 * Created by Connor on 3/12/2016.
 */
public class NucReportDTO {
    private long yardId;
    private String yardName;
    private int initalCount;
    private int countDuringSupering;
    private int superCount;
    private int oldQueensCount;
    private int nucCount;
    private int queensPlaced;
    private int finalCount;

    // calculated values
    private int boxesAfterSupering;
    private int queensNeeded;

    public NucReportDTO() {

    }

    public NucReportDTO(NucReport report) {
        yardId = report.getYard().getId();
        yardName = report.getYard().getYardName();
        initalCount = report.getInitialCount();
        countDuringSupering = report.getCountDuringSupering();
        superCount = report.getSuperCount();
        oldQueensCount = report.getOldQueensCount();
        nucCount = report.getNucCount();
        queensPlaced = report.getQueensPlaced();
        finalCount = report.getFinalCount();

        boxesAfterSupering = countDuringSupering * 2 + superCount;
        queensNeeded = nucCount - queensPlaced;
    }

    public long getYardId() {
        return yardId;
    }

    public void setYardId(long yardId) {
        this.yardId = yardId;
    }

    public String getYardName() {
        return yardName;
    }

    public void setYardName(String yardName) {
        this.yardName = yardName;
    }

    public int getInitalCount() {
        return initalCount;
    }

    public void setInitalCount(int initalCount) {
        this.initalCount = initalCount;
    }

    public int getCountDuringSupering() {
        return countDuringSupering;
    }

    public void setCountDuringSupering(int countDuringSupering) {
        this.countDuringSupering = countDuringSupering;
    }

    public int getSuperCount() {
        return superCount;
    }

    public void setSuperCount(int superCount) {
        this.superCount = superCount;
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

    public void setNucCount(int nucCount) {
        this.nucCount = nucCount;
    }

    public int getQueensPlaced() {
        return queensPlaced;
    }

    public void setQueensPlaced(int queensPlaced) {
        this.queensPlaced = queensPlaced;
    }

    public int getFinalCount() {
        return finalCount;
    }

    public void setFinalCount(int finalCount) {
        this.finalCount = finalCount;
    }

    public int getBoxesAfterSupering() {
        return boxesAfterSupering;
    }

    public void setBoxesAfterSupering() {
        this.boxesAfterSupering = superCount + 2 * countDuringSupering;
    }

    public int getQueensNeeded() {
        return queensNeeded;
    }

    public void setQueensNeeded() {
        this.queensNeeded = nucCount - queensPlaced;
    }
}
