package com.example.demoproj.Project.mappers

import com.example.demoproj.Project.dto.BookDTO
import com.example.demoproj.Project.entity.Book


interface BookMapper {
    fun toDTO(book: Book): BookDTO

    fun toEntity(bookDTO: BookDTO, category: ArrayList<String>): Book
}