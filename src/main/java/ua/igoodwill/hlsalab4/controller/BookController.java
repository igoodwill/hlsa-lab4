package ua.igoodwill.hlsalab4.controller;

import org.springframework.web.bind.annotation.*;
import ua.igoodwill.hlsalab4.model.Book;
import ua.igoodwill.hlsalab4.service.BookService;

import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable UUID id) {
        return bookService.getBook(id);
    }

    @PostMapping
    public UUID createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }
}
