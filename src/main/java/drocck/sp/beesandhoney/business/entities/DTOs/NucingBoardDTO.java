package drocck.sp.beesandhoney.business.entities.DTOs;

import drocck.sp.beesandhoney.business.entities.Shipment;
import drocck.sp.beesandhoney.business.entities.Yard;

import java.util.List;

/**
 * Created by Chai on 10/31/2015.
 */

public class NucingBoardDTO {

    private List<Yard> yards;

    private List<Shipment> shipments;

    private int goalAmountOfBees;
    private int numQueensNeedToReachGoal;
    private int queenOrderAmt;
    private boolean nuked = false;
    private boolean checked3WeeksLater = false;

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

    public void setGoalAmountOfBees(int goalAmountOfBees) {
        this.goalAmountOfBees = goalAmountOfBees;
    }

    public int getGoalAmountOfBees() {
        return goalAmountOfBees;
    }

    public void setNumQueensNeedToReachGoal(int numQueensNeedToReachGoal) {
        this.numQueensNeedToReachGoal = numQueensNeedToReachGoal;
    }

    public int getNumQueensNeedToReachGoal() {
        return numQueensNeedToReachGoal;
    }

    public void setQueenOrderAmt(int queenOrderAmt) {
        this.queenOrderAmt = queenOrderAmt;
    }

    public int getQueenOrderAmt() {
        return queenOrderAmt;
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
}
