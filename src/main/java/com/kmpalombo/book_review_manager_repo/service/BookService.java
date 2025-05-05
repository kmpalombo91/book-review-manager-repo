package com.kmpalombo.book_review_manager_repo.service;

import com.kmpalombo.book_review_manager_repo.model.Book;
import com.kmpalombo.book_review_manager_repo.model.external_api.ExternalApiData;
import com.kmpalombo.book_review_manager_repo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // List all books
    public List<Book> getAllBooks() {
        List <Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            String uri = "https://api.potterdb.com/v1/books";
            RestTemplate restTemplate = new RestTemplate();
            ExternalApiData data = restTemplate.getForObject(uri, ExternalApiData.class);
            System.out.println("Data from external API: " + data);
            if (data != null) {
                data.getData().forEach(
                        book -> {
                            Book newBook = new Book();
                            // Assuming book has properties like title, author, etc.
                            // You need to set these properties based on the actual structure of the book object
                            newBook.setTitle(book.getAttributes().getTitle());
                            newBook.setAuthor(book.getAttributes().getAuthor());
                            newBook.setIsbn("");
                            newBook.setDescription(book.getAttributes().getSummary().substring(0, 252).concat("..."));
                            newBook.setPublisher("");
                            newBook.setPublicationDate(book.getAttributes().getRelease_date());
                            newBook.setGenre("Fiction");
                            newBook.setImageUrl(book.getAttributes().getCover());
                            System.out.println("New book created: " + newBook);
                            bookRepository.save(newBook);
                        }
                );
            }
        }
        return books;
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
