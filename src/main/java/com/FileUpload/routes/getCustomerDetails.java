package com.FileUpload.routes;

import com.FileUpload.POJO.customerResponse;
import com.FileUpload.helpers.AES;
import com.FileUpload.models.Customer;
import com.FileUpload.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class getCustomerDetails {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AES aesHelper;

    final static String secretKey = "shhhhh!!!!!";

    @GetMapping(value="/getCustomerDetails")
    public customerResponse getCustomerDetailsById(@RequestParam("Id") Integer customerId) throws IllegalAccessException {
        String message = "";

        Optional<Customer> customerData = customerRepository.findById(Long.valueOf(customerId.longValue()));

        if(!customerData.isPresent()){
            //No customer
            message = "User Not Found";
            throw new IllegalAccessException(message);
        }
        String[] Contacts;

        String encryptedContacts = aesHelper.decrypt(customerData.get().contactNumbers,secretKey);
        Contacts = encryptedContacts.split("[,]",0);

        for (int i =0;i<Contacts.length;i++) {
            Contacts[i] = aesHelper.encrypt(Contacts[i],secretKey);
        }
        return customerResponse.builder()
                .id(customerData.get().id)
                .contactNumbers(Contacts)
                .contactAddress(customerData.get().contactAddress)
                .distinctNumbers(customerData.get().distinctNumbers)
                .build();
    }

    @GetMapping(value="/getCustomerContacts")
    public String[] getContactsById(@RequestParam("Id") Integer customerId) throws IllegalAccessException {
        String[] Contacts;
        String message = "";
        Optional<Customer> customerData = customerRepository.findById(Long.valueOf(customerId.longValue()));

        if(customerData.isPresent()==false){
            //No customer
            message = "User Not Found";
            throw new IllegalAccessException(message);
        }
        String encryptedContacts = aesHelper.decrypt(customerData.get().contactNumbers,secretKey);
        Contacts = encryptedContacts.split("[,]",0);

        for (int i =0;i<Contacts.length;i++) {
            Contacts[i] = aesHelper.encrypt(Contacts[i],secretKey);
        }
        return Contacts;
    }

    @GetMapping(value="/getAllCustomers")
    public List<Customer> getAllCustomers() throws IllegalAccessException {
        String message = "";
        List<Customer> customerData = customerRepository.findAll();
        return customerData;
    }

    @GetMapping(value="/getAgentAllocations")
    public List<List<Customer>> getAgentAllocations(@RequestParam("perHeadAllocations") Integer perHeadAllocations) throws IllegalAccessException{
        String message= "";
        List<List<Customer>> allocations = new ArrayList<>();
        List<Customer> temp = new ArrayList<>();

        List<Customer> customerData = customerRepository.findAll();
        int currsize =0;
        for(int i=0;i<customerData.size();i++){
            temp.add(customerData.get(i));
            currsize = temp.size();
            if(currsize==perHeadAllocations){
                allocations.add(temp);
                temp = new ArrayList<>();
            }
        }
        return allocations;
    }

}
