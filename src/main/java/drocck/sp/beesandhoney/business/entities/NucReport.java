package drocck.sp.beesandhoney.business.entities;

import java.util.List;

/**
 * Created by Cai on 2/15/2016.
 */
public class NucReport {

    private List<Yard> yards;
    private int initialCount;
    private int postNuc;
    private int postCheck;

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

    public int getPostNuc() {
        return postNuc;
    }

    public void setPostNuc(int postNuc) {
        this.postNuc = postNuc;
    }

    public int getPostCheck() {
        return postCheck;
    }

    public void setPostCheck(int postCheck) {
        this.postCheck = postCheck;
    }
}
