package drocck.sp.beesandhoney.business.entities.DTOs;

import drocck.sp.beesandhoney.business.entities.PolliShipment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 3/20/2016.
 */
public class PolliShipmentCreateDTO {
    private List<String> directions = new ArrayList<>();

    public List<String> getDirections() {
        return directions;
    }

    public void setDirections(List<String> directions) {
        this.directions = directions;
    }
}
