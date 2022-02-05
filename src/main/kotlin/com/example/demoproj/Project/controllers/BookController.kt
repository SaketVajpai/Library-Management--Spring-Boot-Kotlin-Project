package com.example.demoproj.Project.controllers



import com.example.demoproj.Project.dto.BookDTO
import com.example.demoproj.Project.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/books")
class BookController {

    @Autowired(required=true)
    private lateinit var service: BookService


    @GetMapping(
        value=["/"]
    )
    fun getBook(): List<BookDTO>{
        return service.getBook()
    }


    @PostMapping(
        value=["/insert"]
    )

    fun insertBook(
        @RequestBody book:BookDTO,
        @RequestParam(name = "category")category: ArrayList<String>
    ): String = service.insertBook(book,category)


    @DeleteMapping(
        value = ["/{id}"]
    )

    fun deleteNote(
        @PathVariable(name = "id") id: String
    ): String = service.deleteBook(id)



    @GetMapping(
        value = ["/search/{title}"]
    )
    fun searchBook(
        @PathVariable(name = "title") title: String
    ) = service.findByTitle(title)

    @GetMapping(
        value = ["/search"]
    )

    fun searchByCategory(
        @Param("genre")genre: ArrayList<String>
    ) = service.findByCategory(genre)

}