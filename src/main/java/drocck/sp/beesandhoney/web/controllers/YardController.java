package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Address;
import drocck.sp.beesandhoney.business.entities.ContactInfo;
import drocck.sp.beesandhoney.business.entities.Person;
import drocck.sp.beesandhoney.business.entities.Yard;
import drocck.sp.beesandhoney.business.services.AddressService;
import drocck.sp.beesandhoney.business.services.ContactInfoService;
import drocck.sp.beesandhoney.business.services.PersonService;
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

import java.util.Iterator;
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
    private PersonService personService;

    @Autowired
    private ContactInfoService contactInfoService;

    @Autowired
    private AddressService addressService;

    @Autowired
    public YardController(YardService yardService, PersonService personService, ContactInfoService contactInfoService, AddressService addressService) {
        super();
        this.yardService = yardService;
        this.personService = personService;
        this.contactInfoService = contactInfoService;
        this.addressService = addressService;
        initData();
    }

    // for test uses only
    private void initData() {
        Address a1 = new Address();
        a1.setId(1l);
        a1.setStreet("80 Geraldson Drive");
        Address a2 = new Address();
        a2.setId(2l);
        a2.setStreet("6000 J St");

        ContactInfo c1 = new ContactInfo();
        c1.setId(1l);
        c1.setAddress(a1);
        ContactInfo c2 = new ContactInfo();
        c2.setId(2l);
        c2.setAddress(a2);

        Person p1 = new Person();
        p1.setName("Jackie Chan");
        p1.setContactInfo(c1);
        Person p2 = new Person();
        p2.setName("John Doe");
        p2.setContactInfo(c2);

        personService.save(p1);
        personService.save(p2);

        //Test Data
        Yard y1 = new Yard();
        Yard y2 = new Yard();

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
        y1.setOwner(p1);
        y2.setOwner(p1);
        y1.setRentReceiver(p1);
        y2.setRentReceiver(p2);
        this.yardService.add(y1);
        this.yardService.add(y2);
    }
    /** Models **/

    @ModelAttribute("allYards")
    public List<Yard> populateYards(){
        List<Yard> allYards = yardService.findAll();
//        Iterator<Yard> itr = allYards.iterator();
//        while (itr.hasNext()) {
//            logger.info(itr.next().getOwnerName());
//        }
        return allYards;
    }

    @ModelAttribute("yard")
    public Yard createModel() {
        return new Yard();
    }

    /** Request Mapping **/

    @RequestMapping({"/" ,"/index"})
    public String index() {
        return "/yard/index";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String yardSubmit(Yard yard, Model model) {
        model.addAttribute("yard", yard);
        yardService.add(yard);
        return index();
    }
}
