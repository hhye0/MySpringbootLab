package com.basic.myspringbootlab.controller;

import com.basic.myspringbootlab.entity.Book;
import com.basic.myspringbootlab.exception.BusinessException;
import com.basic.myspringbootlab.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookRepository bookRepository;

    //POST /api/books: 새 도서 등록
    @PostMapping
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    //GET /api/books: 모든 도서 조회
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    //GET /api/books/{id}: ID로 특정 도서 조회
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        ResponseEntity<Book> responseEntity = optionalBook.map(book -> ResponseEntity.ok(book))
                .orElse(new ResponseEntity("Book Not Found", HttpStatus.NOT_FOUND));
        return responseEntity;
    }

    //GET /api/books/isbn/{isbn}: ISBN으로 도서 조회
    @GetMapping("/isbn/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        Book existBook = optionalBook.orElseThrow(() -> new BusinessException("Book NotFound", HttpStatus.NOT_FOUND));
        return existBook;
    }

    //PATCH /api/books/{id}: 도서 정보 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetail) {
        Book existBook = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
        existBook.setPrice(bookDetail.getPrice());

        Book updatedBook = bookRepository.save(existBook);
        return ResponseEntity.ok(updatedBook);
    }

    //DELETE /api/books/{id}: 도서 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}