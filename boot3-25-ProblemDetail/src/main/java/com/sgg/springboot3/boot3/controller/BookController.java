package com.sgg.springboot3.boot3.controller;

import com.sgg.springboot3.boot3.config.BookContainer;
import com.sgg.springboot3.boot3.entiry.BookRecord;
import com.sgg.springboot3.boot3.exception.BookNotFoundException;
import com.sgg.springboot3.boot3.exception.IsbnNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * ClassName: BookController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-07 22:35
 * @Version 1.0
 */
@RestController
public class BookController {
    //注入BookContainer 里面有一个List books
    @Autowired
    private BookContainer bookContainer;

    @GetMapping("/book")
    public BookRecord getBook(String isbn){
        List<BookRecord> books = bookContainer.getBooks();

        Optional<BookRecord> bookRecord = books.stream().filter(book ->
                book.isbn().equals(isbn)).findFirst();

        if (bookRecord.isEmpty()){
            throw new BookNotFoundException("ISNB: " + isbn + "->没有此图书");
        }

        return bookRecord.get();
    }

    @GetMapping("/book/isbn")
    public BookRecord getBookByIsbn(String isbn){
        List<BookRecord> books = bookContainer.getBooks();
        Optional<BookRecord> book = books.stream().filter(bookRecord ->
                bookRecord.isbn().equals(isbn)).findFirst();

        if (book.isEmpty()){
            throw new IsbnNotFoundException(HttpStatus.NOT_FOUND, "ISBN: 没有" + isbn + "此图书");
        }

        return book.get();
    }
}
