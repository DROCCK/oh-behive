package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.services.YardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Connor on 9/26/2015.
 */
@Controller
public class YardController {

    @Autowired
    private YardService yardService;

    public YardController() {
        super();
    }

    /** Models **/

    @ModelAttribute("allYards")
    public List<Yard> populateYards(){
        return yardService.findAll();
    }

    /** Request Mapping **/

    @RequestMapping("/yard/create")
    public String create() {
        return "yard/create";
    }

    @RequestMapping({"/yard","/yard/" ,"/yard/index"})
    public String index() {
        return "/yard/index";
    }

    @RequestMapping(value="/yard/create", params={"save"})
    public String saveYard(final Yard yard, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return create();
        }
        this.yardService.add(yard);
        model.clear();
        return create();
    }
}
