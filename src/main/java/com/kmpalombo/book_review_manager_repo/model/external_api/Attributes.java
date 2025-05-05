package com.kmpalombo.book_review_manager_repo.model.external_api;

import lombok.Data;

@Data
public class Attributes {
    private String title;
    private String author;
    private String summary;
    private String release_date;
    private String cover;
}
