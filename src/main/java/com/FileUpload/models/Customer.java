package com.FileUpload.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Customer")
public @Data @NoArgsConstructor @AllArgsConstructor class Customer {

    @Id
    @Column(name = "id")
    public Integer id;

    @Column(name = "name")
    public String name;

    @Column(name = "contactNumbers")
    public String contactNumbers;

    @Column(name = "contactAddress")
    public String contactAddress;

    @Column(name= "distinctNumbers" )
    public Integer distinctNumbers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(String contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public Integer getDistinctNumbers() {
        return distinctNumbers;
    }

    public void setDistinctNumbers(int distinctNumbers) {
        this.distinctNumbers = distinctNumbers;
    }
}
