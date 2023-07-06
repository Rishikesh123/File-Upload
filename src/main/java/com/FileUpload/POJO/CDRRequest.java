package com.FileUpload.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public @Data @NoArgsConstructor @AllArgsConstructor class CDRRequest {
    private int page = 0;
    private int size = 10;
    private String sortBy = "timestamp";
    private String sortDirection = "asc";
    private String caller;
    private String receiver;
}
