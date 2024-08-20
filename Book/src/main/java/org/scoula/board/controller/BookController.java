package org.scoula.board.controller;

import org.scoula.board.dto.BookDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<BookDTO> bookList = new ArrayList<>(); // 메모리 내 저장소 예시

    // 모든 도서 조회
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookList;
    }

    // 특정 ID를 가진 도서 조회
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id) {
        BookDTO book = bookList.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (book == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        return ResponseEntity.ok(book); // 200 OK
    }

    // 새 도서 추가
    @PostMapping
    public BookDTO addBook(@RequestBody BookDTO newBook) {
        newBook.setId(bookList.size() + 1);
        bookList.add(newBook);
        return newBook;
    }

    // 특정 ID를 가진 도서 정보 업데이트
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Integer id, @RequestBody BookDTO updatedBook) {
        for (BookDTO book : bookList) {
            if (book.getId().equals(id)) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setAvailable(updatedBook.getAvailable());
                return ResponseEntity.ok(book); // 200 OK
            }
        }
        return ResponseEntity.notFound().build(); // 404 Not Found
    }

    // 특정 ID를 가진 도서 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        boolean removed = bookList.removeIf(book -> book.getId().equals(id));
        if (removed) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build(); // 404 Not Found
    }
}
