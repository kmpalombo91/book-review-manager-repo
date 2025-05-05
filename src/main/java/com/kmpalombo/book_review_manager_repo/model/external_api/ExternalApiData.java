package com.kmpalombo.book_review_manager_repo.model.external_api;

import lombok.Data;

import java.util.List;

@Data
public class ExternalApiData {
    private List<BookData> data;
}