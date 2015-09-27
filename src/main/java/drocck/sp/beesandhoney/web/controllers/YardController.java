package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.services.YardService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Connor on 9/26/2015.
 */
@Controller
@RequestMapping("/yard")
public class YardController {
    private static final Log logger = LogFactory.getLog(YardController.class);

    @Autowired
    private YardService yardService;

    @Autowired
    public YardController(YardService yardService) {
        super();
        this.yardService = yardService;
        initData();
    }

    // for test uses only
    private void initData() {
        //Test Data
        Yard y1 = new Yard();
        Yard y2 = new Yard();
        y1.setId(0);
        y2.setId(1);
        y1.setName("Watson");
        y2.setName("Moss");
        y1.setStatus("in use");
        y2.setStatus("potential yard");
        y1.setAccessLocation("To the left");
        y2.setAccessLocation("Behind the barn");
        y1.setAddress("123 main st");
        y2.setAddress("80 1st st");
        y1.setZip("12345");
        y2.setZip("95658");
        y1.setMaxHives(80);
        y2.setMaxHives(160);
        y1.setCombo("12345");
        y2.setCombo("MHF Key");
        this.yardService.add(y1);
        this.yardService.add(y2);
    }
    /** Models **/

    @ModelAttribute("allYards")
    public @ResponseBody List<Yard> populateYards(){
        return yardService.findAll();
    }

    @ModelAttribute("yard")
    public Yard createModel() {
        return new Yard();
    }

    /** Request Mapping **/

    @RequestMapping("/create")
    public String create() {
        return "yard/create";

    }

    @RequestMapping({"/" ,"/index"})
    public String index() {
        return "/yard/index";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String yardSubmit(@ModelAttribute Yard yard, Model model) {
        model.addAttribute("yard", yard);
        return index();
    }
}
