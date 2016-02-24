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
//    private NucYard nucYard; //yards that are targeted for nucing event.
    private int queensOrdered;
    private int totalPostNucHiveCount; //number of total hives after nuc.
    private int totalPostNucQueenCount; //number of queens accepted after nuc.
    private boolean allYardsComplete = false; //Overall status complete/incomplete of all nucing yards.

    public NucingTask() {
        super(myStages);
    }

    public NucingTask(int count, Date d) {
        super(myStages);
        queensOrdered = count;
        long dayInMilli = 1000 * 60 * 60 * 24;
        event.add(new Event("Make " + count + " queenless nucs", new Date(d.getTime() - 3 * dayInMilli)));
        event.add(new Event("Place " + count + " queens", new Date(d.getTime())));
        event.add(new Event("Queen check " + count + " nucs", new Date(d.getTime() + 21 * dayInMilli)));

    }

//    public List<Yard> getYards() {
//        return yards;
//    }

//    public void setYards(List<Yard> yards) {
//        this.yards = yards;
//    }

    public List<NucReport> getReport(){
        return report;
    }

    public void setReport(List<NucReport> nr){
        report = nr;
    }

//    public NucYard getNucYard() {
//        return nucYard;
//    }

//    public void setNucYard(NucYard nucYard) {
//        this.nucYard = nucYard;
//    }


    public int getQueensOrdered() {
        return queensOrdered;
    }

    public void setQueensOrdered(int queensOrdered) {
        this.queensOrdered = queensOrdered;
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

    /*
    *******Possibly going to service class, if we decide on sending every method/object/class to the service/repository/database*******
    public void createReport(){ //do something to allow the creation of reports.
        report = new NucReport();
    }
    public void addReport(NucReport r){ //add reports to collection.
        nucReports.add(r);
    }
    public NucReport getReports(){ //method to get reports from collection.
        NucReport temp = new NucReport();
        if(nucReports.iterator().hasNext()){
            temp =  (NucReport) nucReports.iterator().next();
        }
        return temp;
    }
    */
}
