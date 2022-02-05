package com.example.demoproj.Project.mappers.impl



import com.example.demoproj.Project.dto.AuthorDTO
import com.example.demoproj.Project.entity.Author
import com.example.demoproj.Project.mappers.AuthorMapper
import org.springframework.stereotype.Component

@Component
class AuthorMapperImpl : AuthorMapper {



    override fun toDTO(author: Author): AuthorDTO {
        if (author == null) {
            return AuthorDTO()
        }

        val authorDto = AuthorDTO()
        authorDto.apply {
            id = author.id
            author_name = author.author_name
            bookCount = author.bookCount
        }
        return authorDto
    }

    override fun toEntity(authorDto: AuthorDTO): Author {
        val author = Author()
        println(authorDto.id)
        author.apply {
            id = authorDto.id
            author_name = authorDto.author_name
            bookCount = authorDto.bookCount+1
        }
        return author
    }
}
