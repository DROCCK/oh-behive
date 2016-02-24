package drocck.sp.beesandhoney.business.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Collection;
import java.sql.Date;
import java.util.List;
import java.util.Vector;

/**
 * Created by Connor on 2/10/2016.
 * A NucingTask is a concrete implementation of a task
 * where new bees are created.
 */

@Entity
public class NucingTask extends Task {

    private static final String[] myStages = {"pre-split", "post-split", "post-queen-placed", "queen-checked"};

    @OneToMany
    private List<NucReport> report; //report of a specific nucing yard.
    //private Collection nucReports; //collection of reports.

    @OneToMany
    private List<Event> event = new Vector<>();

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

    public NucingTask(int count, Date d) {
        super(myStages);
        goalAmount = count;
        long dayInMilli = 1000 * 60 * 60 * 24;
        event.add(new Event("Make " + count + " queenless nucs", new Date(d.getTime() - 3 * dayInMilli)));
        event.add(new Event("Place " + count + " queens", new Date(d.getTime())));
        event.add(new Event("Queen check " + count + " nucs", new Date(d.getTime() + 21 * dayInMilli)));

    }

    public List<NucReport> getReport(){
        return report;
    }

    public void setReport(List<NucReport> nr){
        report = nr;
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

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }

    public int getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(int goalAmount) {
        this.goalAmount = goalAmount;
    }
}
