package drocck.sp.beesandhoney.business.entities;

import java.util.List;

/**
 * Created by Connor on 2/10/2016.
 * A NucingTask is a concrete implementation of a task
 * where new bees are created.
 */

public class NucingTask extends Task {

    private static final String[] myStages = {"pre-split", "post-split", "post-queen-placed", "queen-checked"};

    private NucReport report; //report of a specific nucing yard.

    private List<Yard> yards; //yards to target.

    private NucYard nucYard; //yards that are targeted for nucing event.

    private int totalPostNucHiveCount; //number of total hives after nuc.

    private int totalPostNucQueenCount; //number of queens accepted after nuc.

    private boolean allYardsComplete = false; //Overall status complete/incomplete of all nucing yards.

    //The goal amount of Queens
    //or total Hives in which we can then determine for the user the number of queens needed to reach given(entered) goal etc.
    //This could also be done in DTO maybe.
    private int goalAmount;

    public NucingTask() {
        super(myStages);
    }

    public List<Yard> getYards() {
        return yards;
    }

    public void setYards(List<Yard> yards) {
        this.yards = yards;
    }

    public NucReport getReport(){
        return report;
    }

    public void setReport(NucReport nr){
        report = nr;
    }

    public NucYard getNucYard() {
        return nucYard;
    }

    public void setNucYard(NucYard nucYard) {
        this.nucYard = nucYard;
    }

    public int getTotalPostNucHiveCount() {
        return totalPostNucHiveCount;
    }

    public void setTotalPostNucHiveCount(int totalPostNucHiveCount) {
        this.totalPostNucHiveCount = totalPostNucHiveCount;
    }

    public int getTotalPostNucQueenCount() {
        return totalPostNucQueenCount;
    }

    public void setTotalPostNucQueenCount(int totalPostNucQueenCount) {
        this.totalPostNucQueenCount = totalPostNucQueenCount;
    }

    public boolean getAllYardsComplete() {
        return allYardsComplete;
    }

    public void setAllYardsComplete(boolean allYardsComplete) {
        this.allYardsComplete = allYardsComplete;
    }

    public int getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(int goalAmount) {
        this.goalAmount = goalAmount;
    }
}
