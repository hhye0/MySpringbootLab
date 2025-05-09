package com.basic.Mmyspringbootlab.repository;

import com.basic.Mmyspringbootlab.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

//    도서 등록 테스트 ( testCreateBook() )
    @Test
    @Rollback(value = false)
    void testCreateBook(){
        //given
        Book book = new Book();
        book.setTitle("JPA 프로그래밍");
        book.setAuthor("박둘리");
        book.setIsbn("9788956746432");
        book.setPrice(35000);
        book.setPublishDate(LocalDate.of(2025,4,30));
        //when
        Book addBook = bookRepository.save(book);
        //then
        assertThat(addBook).isNotNull();
        assertThat(addBook.getTitle()).isEqualTo("JPA 프로그래밍");
    }
//    ISBN으로 도서 조회 테스트 ( testFindByIsbn() )
//    저자명으로 도서 목록 조회 테스트 ( testFindByAuthor() )
//    도서 정보 수정 테스트 ( testUpdateBook() )
//    도서 삭제 테스트 ( testDeleteBook() )

}