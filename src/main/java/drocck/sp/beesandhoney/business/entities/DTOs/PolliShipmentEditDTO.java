package drocck.sp.beesandhoney.business.entities.DTOs;

import drocck.sp.beesandhoney.business.entities.PolliShipment;

/**
 * Created by Robert Wilk
 * on 4/1/2016.
 */
public class PolliShipmentEditDTO {

    private PolliShipmentCreateDTO polliShipmentCreateDTO;
    private PolliShipment shipment;

    public PolliShipmentEditDTO(PolliShipmentCreateDTO polliShipmentCreateDTO, PolliShipment shipment) {
        this.polliShipmentCreateDTO = polliShipmentCreateDTO;
        this.shipment = shipment;
    }

    public PolliShipment getShipment() {
        return shipment;
    }

    public void setShipment(PolliShipment shipment) {
        this.shipment = shipment;
    }

    public PolliShipmentCreateDTO getPolliShipmentCreateDTO() {
        return polliShipmentCreateDTO;
    }

    public void setPolliShipmentCreateDTO(PolliShipmentCreateDTO polliShipmentCreateDTO) {
        this.polliShipmentCreateDTO = polliShipmentCreateDTO;
    }
}
