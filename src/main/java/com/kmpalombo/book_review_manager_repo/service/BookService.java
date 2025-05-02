package com.kmpalombo.book_review_manager_repo.service;

import com.kmpalombo.book_review_manager_repo.model.Book;
import com.kmpalombo.book_review_manager_repo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // List all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Find a book by ID
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    // Save a new book
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Update an existing book
    public Book updateBook(Long id, Book book) {
        Book newBook = getBookById(id);
        if (newBook != null) {
            newBook.setTitle(book.getTitle());
            newBook.setAuthor(book.getAuthor());
            newBook.setIsbn(book.getIsbn());
            newBook.setDescription(book.getDescription());
            newBook.setPublisher(book.getPublisher());
            newBook.setPublicationDate(book.getPublicationDate());
            newBook.setGenre(book.getGenre());
            newBook.setImageUrl(book.getImageUrl());
            return bookRepository.save(book);
        }
        return null;
    }

    // Delete a book
    public String deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            return null; // or throw an exception if you prefer
        }
        bookRepository.deleteById(id);
        return String.valueOf(id);
    }
}
