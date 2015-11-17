package drocck.sp.beesandhoney.business.entities.DTOs;

import drocck.sp.beesandhoney.business.entities.Shipment;
import drocck.sp.beesandhoney.business.entities.Yard;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @author Robert Wilk
 *         Created on 10/24/2015.
 */
public class BeeBoardDTO {

    private DecimalFormat df = new DecimalFormat("0.00");

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

    public int getActiveShipments() {
        return shipments.size();
    }

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
            total += (y.getSingles() + y.getDoubles() + y.getSupers() + y.getDuds());
        return total;
    }

    public double getSinglesPercent() {
        return getPercentage(getTotalSingles(), getTotalHives());
    }

    public double getDoublesPercent(){
        return getPercentage(getTotalDoubles(), getTotalHives());
    }

    public double getSupersPercent(){
        return getPercentage(getTotalSupers(), getTotalHives());
    }

    public double getDudsPercent(){
        return getPercentage(getTotalDuds(), getTotalHives());
    }

    private double getPercentage(int num, int total) {
        return total > 0 ? getFormattedPercent(num, total) : 0.0;
    }

    private double getFormattedPercent(int num, int total) {
        return Double.parseDouble(df.format((num / total) * 100));
    }

    public Yard getOneYard(long id){
        Yard yard = new Yard();
        for (Yard y : yards){
            if(y.getId().equals(id)) {
                yard = y;
                break;
            }
        }
        return yard;    //Returns new yard if IDs don't match
    }
}
