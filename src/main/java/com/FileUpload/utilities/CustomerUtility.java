package com.FileUpload.utilities;

import com.FileUpload.constants.Details;
import com.FileUpload.helpers.AES;
import com.FileUpload.models.Customer;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Configuration
public class CustomerUtility {
    public String[] handleCustomerContacts(AES aesHelper, Optional<Customer> customerData) {
        String encryptedContacts = aesHelper.decrypt(customerData.get().customerContact, Details.SECRET_KEY.getValue());
        String[] Contacts = encryptedContacts.split("[,]",0);

        for (int i =0;i<Contacts.length;i++) {
            Contacts[i] = aesHelper.encrypt(Contacts[i], Details.SECRET_KEY.getValue());
        }
        return Contacts;
    }

    public String[] handleUnencyptedCustomerContacts(AES aesHelper, Optional<Customer> customerData) {
        String encryptedContacts = aesHelper.decrypt(customerData.get().customerContact, Details.SECRET_KEY.getValue());
        String[] Contacts = encryptedContacts.split("[,]",0);
        return Contacts;
    }

}
