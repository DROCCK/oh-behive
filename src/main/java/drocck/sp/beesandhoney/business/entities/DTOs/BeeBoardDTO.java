package drocck.sp.beesandhoney.business.entities.DTOs;

import drocck.sp.beesandhoney.business.entities.Shipment;
import drocck.sp.beesandhoney.business.entities.Yard;

import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 10/24/2015.
 */
public class BeeBoardDTO {
    private List<Yard> yards;

    private List<Shipment> shipments;

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

    public int getActiveShipments() { return shipments.size(); }

    public int getTotalSingles() {
        int singles = 0;
        for (Yard y : yards)
            singles += y.getSingles();
        return singles;
    }

    public int getTotalDoubles() {
        int doubles = 0;
        for (Yard y : yards)
            doubles += y.getDoubles();
        return doubles;
    }

    public int getTotalSupers() {
        int supers = 0;
        for(Yard y : yards){
            supers += y.getSupers();
        }
        return supers;
    }

    public int getTotalDuds() {
        int duds = 0;
        for(Yard y : yards){
            duds += y.getDuds();
        }
        return duds;
    }

    public int getTotalHives() {
        int total = 0;
        for (Yard y : yards)
            total += (y.getSingles() + y.getDoubles() + y.getSupers() - y.getDuds());
        return total;
    }

    public double getSinglesPercent() {
        double percent = 0;
        if(getTotalHives() != 0) {
            percent = (this.getTotalSingles() / this.getTotalHives()) * 100;
        }
        return percent;
    }

    public double getDoublesPercent(){
        double percent = 0;
        if(getTotalHives() != 0) {
            percent = (this.getTotalDoubles() / this.getTotalHives()) * 100;
        }
        return percent;
    }

    public double getSupersPercent(){
        double percent = 0;
        if(getTotalHives() != 0) {
            percent = (this.getTotalSupers() / this.getTotalHives()) * 100;
        }
        return percent;
    }

    public double getDudsPercent(){
        double percent = 0;
        if(getTotalHives() != 0) {
            percent = (this.getTotalDuds() / this.getTotalHives()) * 100;
        }
        return percent;
    }
}
