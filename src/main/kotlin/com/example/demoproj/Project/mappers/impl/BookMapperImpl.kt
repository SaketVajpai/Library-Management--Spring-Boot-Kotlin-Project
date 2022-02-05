package com.example.demoproj.Project.mappers.impl


import com.example.demoproj.Project.Repository.AuthorRepository
import com.example.demoproj.Project.dto.BookDTO
import com.example.demoproj.Project.entity.Book
import com.example.demoproj.Project.mappers.AuthorMapper
import com.example.demoproj.Project.mappers.BookMapper
import com.example.demoproj.Project.service.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BookMapperImpl(
    val authorMapper: AuthorMapper,
) : BookMapper {

    @Autowired
    lateinit var repository: AuthorRepository

    @Autowired
    lateinit var service: AuthorService

    override fun toDTO(book: Book): BookDTO {
        if (book == null)
            return book

        val bookDto = BookDTO()
        bookDto.apply {
            isbn = book.isbn
            title = book.title
            pages = book.pages
            author = authorMapper.toDTO(book.author)
            created = book.created
            category = book.category
        }
        return bookDto
    }


    override fun toEntity(bookDto: BookDTO,cat: ArrayList<String>): Book {
        val book = Book()
        book.apply {
            isbn = bookDto.isbn
            title = bookDto.title
            pages = bookDto.pages
            val exist: Boolean = repository.existsById(bookDto.author.id)

            if (!exist)
            {
                var temp: String = service.insertAuthor(bookDto.author)
            }
            author = bookDto.author.let { authorMapper.toEntity(it)}!!
            created = bookDto.created
            var temp: ArrayList<String> = arrayListOf()
            for (s:String in cat) {
                println(s)
                temp.add(s)
            }
            category = cat
        }
        return book
    }

}
