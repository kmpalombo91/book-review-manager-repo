package com.kmpalombo.book_review_manager_repo.repository;

import com.kmpalombo.book_review_manager_repo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
