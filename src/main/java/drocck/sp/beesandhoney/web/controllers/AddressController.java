package drocck.sp.beesandhoney.web.controllers;

import drocck.sp.beesandhoney.business.entities.Address;
import drocck.sp.beesandhoney.business.services.AddressService;
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
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ModelAttribute("address")
    public Address construct() {
        return new Address();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "address/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("address") Address address) {
        addressService.save(address);
        return "redirect:list";
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public String read(@PathVariable Long id, Model model) {
        model.addAttribute("address", addressService.findOne(id));
        return "address/read";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("address", addressService.findOne(id));
        return "address/update";
    }

    @RequestMapping(value = "/updateAddress/{id}", method = RequestMethod.POST)
    public String updateAddress(@PathVariable Long id, @ModelAttribute("address") Address address) {
        address.setId(id);
        addressService.update(address);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("address", addressService.findOne(id));
        return "address/delete";
    }

    @RequestMapping(value = "/confirmedDelete/{id}")
    public String delete(@PathVariable Long id) {
        addressService.delete(id);
        return "redirect:list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("addresses", addressService.findAll());
        return "address/list";
    }
}
