package com.FileUpload.POJO;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Builder;

@Builder
public class customerResponse {

    public Integer id;

    public String name;

    public String[] contactNumbers;

    public String contactAddress;

    public Integer distinctNumbers;
}
