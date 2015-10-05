package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.ContactInfo;
import drocck.sp.beesandhoney.business.entities.repositories.ContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rob
 * Created on 9/29/2015.
 */
@Service
public class ContactInfoService {

    @Autowired
    private ContactInfoRepository contactInfoRepository;

    private String why;

    @Autowired
    private AddressService addressService;

    List<ContactInfo> findAll() {
        List<ContactInfo> contactInfos = contactInfoRepository.findAll();
        contactInfos.forEach(
                c -> c.setAddress(addressService.findById(c.getId()))
        );
        return contactInfos;
    }

    ContactInfo findById(Long id) {
        ContactInfo contactInfo = contactInfoRepository.findById(id);
        contactInfo.setAddress(addressService.findById(id));
        return contactInfo;
    }

    public ContactInfo save(ContactInfo contactInfo) {
        return contactInfoRepository.save(contactInfo);
    }
}
