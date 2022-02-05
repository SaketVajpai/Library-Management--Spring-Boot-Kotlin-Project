package com.example.demoproj.Project.service

import com.example.demoproj.Project.Repository.AuthorRepository
import com.example.demoproj.Project.Repository.BookRepository
import com.example.demoproj.Project.dto.AuthorDTO
import com.example.demoproj.Project.entity.Book
import com.example.demoproj.Project.mappers.AuthorMapper
import com.example.demoproj.Project.mappers.impl.BookMapperImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class AuthorService(){
    @Autowired(required = true)
    private lateinit var authorRepository: AuthorRepository

    @Autowired(required = true)
    private lateinit var bookRepository: BookRepository

    @Autowired(required = true)
    private lateinit var authorMapper: AuthorMapper

    @Autowired(required = true)
    private lateinit var bookMapper: BookMapperImpl

    fun deleteAuthor(id: Int) = authorRepository.deleteById(id)

    fun getAllAuthors(): List<AuthorDTO>{
        val authorDtolist = mutableListOf<AuthorDTO>()
        authorRepository.findAll().forEach(){
            authorDtolist.add(authorMapper.toDTO(it))
        }
        return authorDtolist
    }

    fun insertAuthor(authorDTO: AuthorDTO): String {
        val author = authorMapper.toEntity(authorDTO)
        authorRepository.save(author)
        return "Author inserted successfully"
    }


    fun findByAuthor(author_name: String, page: Int , size: Int ): ResponseEntity<Map<String, Any>>? {
        return try {
            var authorDtoList: MutableList<String> = mutableListOf()
            var paging: Pageable = PageRequest.of(page - 1, size)
            var author = authorRepository.findAuthor(author_name)
            var books = bookRepository.findAllByAuthor(author)
            for(book: Book in books){
                authorDtoList.add(book.title)
            }
            val start: Int = paging.getOffset().toInt()
            val end: Int = Math.min(start + paging.getPageSize(), authorDtoList.size)
            val page: Page<String> = PageImpl<String>(authorDtoList.subList(start,end), paging, authorDtoList.size.toLong())
            var response: MutableMap<String, Any> = HashMap()
            response["books"] = page.content
            response["totalItems"] = page.totalElements
            response["totalPages"] = page.totalPages
            ResponseEntity(response, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}
