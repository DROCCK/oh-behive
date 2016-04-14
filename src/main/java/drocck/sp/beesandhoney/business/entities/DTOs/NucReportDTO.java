package drocck.sp.beesandhoney.business.entities.DTOs;

import drocck.sp.beesandhoney.business.entities.NucReport;

/**
 * Created by Connor on 3/12/2016.
 */
public class NucReportDTO {
    private long yardId;
    private String yardName;
    private int initialCount;
    private int countDuringSupering;
    private int superCount;
    private int oldQueensCount;
    private int nucCount;
    private int queensPlaced;
    private int finalCount;

    // calculated values
    private int boxesAfterSupering;
    private int queensNeeded;
    private float splitRatio;
    private float totalRatio;

    public NucReportDTO() {

    }

    public NucReportDTO(NucReport report) {
        yardId = report.getYard().getId();
        yardName = report.getYard().getYardName();
        initialCount = report.getInitialCount();
        countDuringSupering = report.getCountDuringSupering();
        superCount = report.getSuperCount();
        oldQueensCount = report.getOldQueensCount();
        nucCount = report.getNucCount();
        queensPlaced = report.getQueensPlaced();
        finalCount = report.getFinalCount();

        boxesAfterSupering = countDuringSupering * 2 + superCount;
        queensNeeded = nucCount - queensPlaced;
        if (countDuringSupering != 0) {
            splitRatio = (float)nucCount / countDuringSupering;
            totalRatio = (float)finalCount / countDuringSupering;
        } else {
            splitRatio = 0;
            totalRatio = 0;
        }
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

    public int getInitialCount() {
        return initialCount;
    }

    public void setInitialCount(int initalCount) {
        this.initialCount = initalCount;
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

    //Todo: Split Ratio: NucCount + OldQueensCount / countDuringSupering
    public float getSplitRatio() {
        return splitRatio;
    }

    public void setSplitRatio() {
        if (this.countDuringSupering != 0)
            this.splitRatio = (float) this.nucCount / (float) this.countDuringSupering;
        else this.splitRatio = 0;
    }

    public float getTotalRatio() {
        return totalRatio;
    }

    public void setTotalRatio() {
        if (this.countDuringSupering != 0) this.totalRatio = (float) this.finalCount / this.countDuringSupering;
        else this.totalRatio = 0;
    }

}
