package com.example.demoproj.Project.Repository

import com.example.demoproj.Project.entity.Author
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository: JpaRepository<Author,Int>{
    fun findAuthor(author: String): Author
}