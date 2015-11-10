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
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ModelAttribute("address")
    public Address construct() {
        return new Address();
    }

    @RequestMapping(value = "/address/create", method = RequestMethod.GET)
    public String create() {
        return "address/create";
    }

    @RequestMapping(value = "/address/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("address") Address address) {
        addressService.save(address);
        return "redirect:/address/list";
    }

    @RequestMapping(value = "/address/read/{id}", method = RequestMethod.GET)
    public String read(@PathVariable Long id, Model model) {
        model.addAttribute("address", addressService.findOne(id));
        return "address/read";
    }

    @RequestMapping(value = "/address/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("address", addressService.findOne(id));
        return "address/update";
    }

    @RequestMapping(value = "/address/updateAddress/{id}", method = RequestMethod.POST)
    public String updateAddress(@PathVariable Long id, @ModelAttribute("address") Address address) {
        address.setId(id);
        addressService.update(address);
        return "redirect:/address/list";
    }

    @RequestMapping(value = "/address/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("address", addressService.findOne(id));
        return "/address/delete";
    }

    @RequestMapping(value = "/address/confirmedDelete/{id}")
    public String delete(@PathVariable Long id) {
        addressService.delete(id);
        return "redirect:/address/list";
    }

    @RequestMapping("/address/list")
    public String list(Model model) {
        model.addAttribute("addresses", addressService.findAll());
        return "address/list";
    }
}
