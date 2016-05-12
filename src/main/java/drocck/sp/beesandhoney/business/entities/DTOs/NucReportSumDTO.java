package drocck.sp.beesandhoney.business.entities.DTOs;

/**
 * Created by Connor on 4/12/2016.
 */
public class NucReportSumDTO {
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

    public void setBoxesAfterSupering(int boxesAfterSupering) {
        this.boxesAfterSupering = boxesAfterSupering;
    }

    public int getQueensNeeded() {
        return queensNeeded;
    }

    public void setQueensNeeded(int queensNeeded) {
        this.queensNeeded = queensNeeded;
    }

    public float getSplitRatio() {
        return splitRatio;
    }

    public void setSplitRatio(float splitRatio) {
        this.splitRatio = splitRatio;
    }

    public float getTotalRatio() {
        return totalRatio;
    }

    public void setTotalRatio(float totalRatio) {
        this.totalRatio = totalRatio;
    }
}
