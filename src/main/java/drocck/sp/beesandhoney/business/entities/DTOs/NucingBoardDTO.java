package drocck.sp.beesandhoney.business.entities.DTOs;

import drocck.sp.beesandhoney.business.entities.Shipment;
import drocck.sp.beesandhoney.business.entities.Yard;

import java.util.Date;
import java.util.List;

/**
 * Created by Chai on 10/31/2015.
 */

public class NucingBoardDTO {

    private List<Yard> yards;
    private List<Shipment> shipments;

    private int goalAmountOfBeeHives; //Goal amount to set
    private int numQueensNeeded = 2016; //Amount of Queens needed to achieve goal amount of hives
    //Number of queens needed = (hive goal amount) - (current/active hive count)

    private int numQueensAccepted; //total number of queens acceptted from event/yards?
    private int preNucingTotalHives; //total yard bee hives count before nucing (get from beeboard)
    private int postNucingTotalHives; //total yard bee hives count after nucing

    private boolean nuked = false; //yard has been nuked. (goes in yard maybe)
    private boolean checked3WeeksLater = false; //Queens checked 3 weeks later status (check by ???)

    /* (Uneeded maybe if stored in event)
    private Date nucDate;
    private Date queenDate;
    private Date checkDate;
    */

    /* (Notes)
    Yards that will be visited for nucing. (Done on left side)
    (List next yards, List next yards to place a queens, list yards to check queens.

    How many hives were there before nucing. (get a copy of total from beeboard)
    How many hives are there before queens are checked. (same as before)
    How many hives are there when nucing is done. (re-calculate total)
    How many hives accepted the new queens. (calculate on nucing board)

    the date the nucs were made. (Created in event)
    the date new queens needed to be put into hive. (Created in event)
    the date to visit the yard to check queens. (Created in event)

    Optional: Security information/landowner POC., tick treatment, who queens are from, how many hives have old queens in them.

    *****************************************************************

    Going to need input for updating status and recalculating other stats.
     */

    public List<Yard> getYards() {
        return yards;
    }

    public void setYards(List<Yard> yards) {
        this.yards = yards;
    }

    public List<Shipment> getShipments() {
        return shipments;
    }

    public void setShipments(List<Shipment> shipments) {
        this.shipments = shipments;
    }

    public void setGoalAmountOfBees(int goalAmountOfBeeHives) {
        this.goalAmountOfBeeHives = goalAmountOfBeeHives;
    }

    public int getGoalAmountOfBees() {
        return goalAmountOfBeeHives;
    }

    public void setNumQueensNeed(int numQueensNeeded) {
        this.numQueensNeeded = numQueensNeeded;
    }

    public int getNumQueensNeeded() {
        return numQueensNeeded;
    }

    public void setNuked(boolean nuked) {
        this.nuked = nuked;
    }

    public boolean isNuked() {
        return nuked;
    }

    public void setChecked3WeeksLater(boolean checked3WeeksLater) {
        this.checked3WeeksLater = checked3WeeksLater;
    }

    public boolean isChecked3WeeksLater() {
        return checked3WeeksLater;
    }

    public void setGoalAmountOfBeeHives(int goalAmountOfBeeHives) {
        this.goalAmountOfBeeHives = goalAmountOfBeeHives;
    }

    public int getGoalAmountOfBeeHives() {
        return goalAmountOfBeeHives;
    }

    public void setNumQueensAccepted(int numQueensAccepted) {
        this.numQueensAccepted = numQueensAccepted;
    }

    public int getNumQueensAccepted() {
        return numQueensAccepted;
    }

    public void setPreNucingTotalHives(int preNucingTotalHives) {
        this.preNucingTotalHives = preNucingTotalHives;
    }

    public int getPreNucingTotalHives() {
        return preNucingTotalHives;
    }

    public void setPostNucingTotalHives(int postNucingTotalHives) {
        this.postNucingTotalHives = postNucingTotalHives;
    }

    public int getPostNucingTotalHives() {
        return postNucingTotalHives;
    }
}
