package com.carParts.model.dto;

import com.carParts.model.entity.Part;

import java.util.List;

public class SearchFormDTO {

    public String searchTerm;

    public SearchFormDTO() {
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
