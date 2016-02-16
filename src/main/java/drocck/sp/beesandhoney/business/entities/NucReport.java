package drocck.sp.beesandhoney.business.entities;

import java.util.List;

/**
 * Created by Chai on 2/15/2016.
 */
public class NucReport {

    private List<Yard> yards;
    private int initialCount;
    private int postNucCount;;
    private int postCheckCount;
    private boolean postCheck;

    public NucReport(){

    }

    public NucReport(int ic, int pnc, int pcc, boolean pc){
        setInitialCount(ic);
        setPostCheckCount(pnc);
        setPostCheckCount(pcc);
        setPostCheck(pc);
    }

    public List<Yard> getYards() {
        return yards;
    }

    public void setYards(List<Yard> yards) {
        this.yards = yards;
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

    public int getPostCheckCount() {
        return postCheckCount;
    }

    public void setPostCheckCount(int postCheck) {
        this.postCheckCount = postCheck;
    }

    public void setPostCheck(boolean postCheck) {
        this.postCheck = postCheck;
    }

    public boolean isPostCheck() {
        return postCheck;
    }
}
