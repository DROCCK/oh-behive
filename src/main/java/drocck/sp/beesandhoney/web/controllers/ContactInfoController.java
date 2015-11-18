package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.ContactInfo;
import drocck.sp.beesandhoney.business.services.ContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Robert Wilk
 *         Created on 10/6/2015.
 */
@Controller
@RequestMapping("contactInfo")
public class ContactInfoController {

    @Autowired
    private ContactInfoService contactInfoService;

    @ModelAttribute("contactInfo")
    public ContactInfo construct() {
        return new ContactInfo();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "contactInfo/create";
    }

    @RequestMapping(value = "contactInfo/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("contactInfo") ContactInfo contactInfo) {
        contactInfoService.save(contactInfo);
        return "redirect:list";
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public String read(@PathVariable Long id, Model model) {
        model.addAttribute("contactInfo", contactInfoService.findOne(id));
        return "contactInfo/read";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("contactInfo", contactInfoService.findOne(id));
        return "contactInfo/update";
    }

    @RequestMapping(value = "/updateContact/{id}", method = RequestMethod.POST)
    public String update(@PathVariable Long id, @ModelAttribute("contactInfo") ContactInfo contactInfo) {
        contactInfo.setId(id);
        contactInfoService.update(contactInfo);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("contactInfo", contactInfoService.findOne(id));
        return "contactInfo/delete";
    }

    @RequestMapping("/confirmedDelete/{id}")
    public String confirmedDelete(@PathVariable Long id) {
        contactInfoService.delete(id);
        return "redirect:list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("contactInfos", contactInfoService.findAll());
        return "contactInfo/list";
    }
}
