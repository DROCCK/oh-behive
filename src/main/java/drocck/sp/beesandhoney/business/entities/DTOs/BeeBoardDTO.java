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

    public int getTotalHives() {
        int total = 0;
        for (Yard y : yards)
            total += (y.getSingles() + y.getDoubles());
        return total;
    }
}
