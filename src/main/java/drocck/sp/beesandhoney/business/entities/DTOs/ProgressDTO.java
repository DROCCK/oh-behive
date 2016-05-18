package drocck.sp.beesandhoney.business.entities.DTOs;

import drocck.sp.beesandhoney.business.entities.Contract;

import java.util.List;

/**
 * Created by Robert Wilk on 5/10/2016.
 *
 * @author Robert Wilk
 */
public class ProgressDTO {

    private int needed;
    private int fulfilled;
    private double progress;

    public ProgressDTO(List<Contract> contracts) {
        for (Contract contract : contracts) {
            needed += contract.getAmount() == null ? 0 : contract.getAmount();
            fulfilled += contract.getOrchard() == null ?
                    0 : contract.getOrchard().getCount() == null ?
                    0 : contract.getOrchard().getCount();
        }
        progress = (double) fulfilled / (double) needed;
    }

    public void setNeeded(int needed) {
        this.needed = needed;
    }

    public int getNeeded() {
        return needed;
    }

    public void setFullfilled(int fulfilled) {
        this.fulfilled = fulfilled;
    }

    public int getFulfilled() {
        return fulfilled;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public double getProgress() {
        return progress;
    }
}
