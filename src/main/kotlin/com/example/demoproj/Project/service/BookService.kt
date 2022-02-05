package com.example.demoproj.Project.service


import com.example.demoproj.Project.Repository.BookRepository
import com.example.demoproj.Project.dto.BookDTO
import com.example.demoproj.Project.entity.Book
import com.example.demoproj.Project.mappers.impl.BookMapperImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("Book service")
class BookService() {

    @Autowired
    lateinit var repository: BookRepository


    @Autowired
    lateinit var bookMapper: BookMapperImpl

    fun getBook(): List<BookDTO> {
        val bookDtolist: MutableList<BookDTO> = mutableListOf()
        val bookList = repository.findAll()
        for(book: Book in bookList){
            bookDtolist.add(bookMapper.toDTO(book))
        }
        return bookDtolist
    }


    fun insertBook(bookdto: BookDTO,category: ArrayList<String>): String {
        val book = bookMapper.toEntity(bookdto,category)
        repository.save(book)
        return "Book inserted successfully"
    }


    fun deleteBook(id: String): String {
        repository.deleteById(id)
        return "Book deleted successfully"
    }


    fun findByTitle(title: String): Iterable<BookDTO>{
        val bookDtolist: MutableList<BookDTO> = mutableListOf()
        val bookList = repository.findByTitle(title)
        for(book: Book in bookList){
            bookDtolist.add(bookMapper.toDTO(book))
        }

        return bookDtolist
    }

    fun findByCategory(genre: ArrayList<String>): Iterable<BookDTO>{
        println(genre)
        var temp = repository.findByCategory(genre)
        val bookList: MutableList<BookDTO> = mutableListOf()
        for(book: Book in temp){
            bookList.add(bookMapper.toDTO(book))
            println(book.title)
        }
        return bookList
    }

}