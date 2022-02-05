package com.example.demoproj.Project.mappers

import com.example.demoproj.Project.dto.AuthorDTO
import com.example.demoproj.Project.entity.Author


interface AuthorMapper {
    fun toDTO(author: Author): AuthorDTO

    fun toEntity(authorDTO: AuthorDTO): Author
}