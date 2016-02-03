package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Contract;
import drocck.sp.beesandhoney.business.services.ContractService;
import drocck.sp.beesandhoney.business.services.OrchardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Robert Wilk
 *         Created on 2/2/2016.
 */
@RestController
public class PollinationBoardRestController {

    @Autowired
    ContractService contractService;

    @Autowired
    OrchardService orchardService;
}
