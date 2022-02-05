package com.example.demoproj.Project.Repository

import com.example.demoproj.Project.entity.Author
import com.example.demoproj.Project.entity.Book
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface BookRepository: JpaRepository<Book, String> {
    fun findByTitle(title: String): Iterable<Book>
    fun findAllByAuthor(author: Author) : List<Book>
    fun findByCategory(genre: ArrayList<String>): Iterable<Book>
}