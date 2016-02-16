package drocck.sp.beesandhoney.business.entities;

/**
 * Created by Connor on 2/10/2016.
 * A NucingTask is a concrete implementation of a task
 * where new bees are created.
 */

public class NucingTask extends Task {

    private static final String[] myStages = {"pre-split", "post-split", "post-queen-placed", "queen-checked"};
    private NucReport report = new NucReport();

    public NucingTask() {
        super(myStages);
    }
}
