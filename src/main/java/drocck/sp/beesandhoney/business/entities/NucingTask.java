package drocck.sp.beesandhoney.business.entities;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Connor on 2/10/2016.
 * A NucingTask is a concrete implementation of a task
 * where new bees are created.
 */

public class NucingTask extends Task {

    private static final String[] myStages = {"pre-split", "post-split", "post-queen-placed", "queen-checked"};
    private NucReport report;
    private Collection nucReports;

    public NucingTask() {
        super(myStages);
    }

    public void createReport(){
        report = new NucReport();
    }

    public void addReport(NucReport r){
        nucReports.add(r);
    }

    public void getReports(){
        if(nucReports.iterator().hasNext()){
            for(int i = 0; i < nucReports.size(); i++) {
                nucReports.iterator().next();
            }
        }
    }
}
