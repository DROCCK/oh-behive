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

    @Autowired
    private AddressService addressService;

    public List<ContactInfo> findAll() {
        List<ContactInfo> contactInfos = contactInfoRepository.findAll();
        contactInfos.forEach(
                c -> c.setAddress(addressService.findById(c.getId()))
        );
        return contactInfos;
    }

    public ContactInfo findById(Long id) {
        ContactInfo contactInfo = contactInfoRepository.findById(id);
        contactInfo.setAddress(addressService.findById(id));
        return contactInfo;
    }

    public ContactInfo save(ContactInfo contactInfo) {
        contactInfo.setAddress(addressService.save(contactInfo.getAddress()));
        return contactInfoRepository.save(contactInfo);
    }

    public ContactInfo update(ContactInfo contactInfo) {
        ContactInfo c = contactInfoRepository.findById(contactInfo.getId());
        c.setEmail(contactInfo.getEmail());
        c.setPhone(contactInfo.getPhone());
        contactInfo.getAddress().setId(contactInfo.getId());
        c.setAddress(addressService.update(contactInfo.getAddress()));
        return contactInfoRepository.save(c);
    }

    public void delete(Long id) {
        contactInfoRepository.delete(id);
    }

    public void delete(ContactInfo contactInfo) {
        contactInfoRepository.delete(contactInfo);
    }
}
