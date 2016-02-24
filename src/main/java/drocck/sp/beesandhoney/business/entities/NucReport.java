package drocck.sp.beesandhoney.business.entities;

import java.util.List;

/**
 * Created by Chai on 2/15/2016.
 */
public class NucReport {

    private List<NucYard> nucYards; //Specific Nucing yards in the report.
    private int initialCount; //Number of hives of a specific nucing yard before nucing.
    private int postNucCount; //Number of hives of a specific nucingyard after nucing.
    private int postNucQueenCount; //Number of Queens accepted at specific nucing yard.
    private boolean postNucCheck = false; //Nucing yard status that a nucing yard has been checked after 3 weeks.

    public List<NucYard> getYards() {
        return nucYards;
    }

    public void setYards(List<NucYard> nYards) {
        this.nucYards = nYards;
    }

    public int getInitialCount() {
        return initialCount;
    }

    public void setInitialCount(int initialCount) {
        this.initialCount = initialCount;
    }

    public int getPostNucCount() {
        return postNucCount;
    }

    public void setPostNucCount(int postNuc) {
        this.postNucCount = postNuc;
    }

    public int getPostNucQueenCount() {
        return postNucQueenCount;
    }

    public void setPostNucQueenCount(int newQueenCount) {
        this.postNucQueenCount = newQueenCount;
    }

    public void setPostNucCheck(boolean postNucCheck) {
        this.postNucCheck = postNucCheck;
    }

    public boolean isPostNucCheck() {
        return postNucCheck;
    }
}
