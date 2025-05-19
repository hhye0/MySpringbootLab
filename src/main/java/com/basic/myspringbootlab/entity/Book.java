package com.basic.myspringbootlab.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column(unique = true, nullable = false)
    private String isbn;

    @Column
    private Integer price;

    @Column
    private LocalDate publishDate;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
    private BookDetail bookDetail;
}
