package com.hwthird.hw3.Repositories;


import com.hwthird.hw3.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);

    List<Book> findByPublishedDateAfter(LocalDate date);

    List<Book> findByAuthorId(Long authorId);
}