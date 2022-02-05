package com.example.demoproj.Project.controllers


import com.example.demoproj.Project.dto.AuthorDTO
import com.example.demoproj.Project.service.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/author")
class AuthorController {
    @Autowired(required=true)
    private lateinit var authorService: AuthorService

    @GetMapping(
        value = ["/"]
    )
    fun getAllAuthors(): List<AuthorDTO>{
        return authorService.getAllAuthors()
    }

    @PostMapping(
        value=["/insert"]
    )
    fun insertAuthor(
        @RequestBody author: AuthorDTO
    ): String = authorService.insertAuthor(author)


    @DeleteMapping(
        value = ["/{id}"]
    )
    fun deleteNote(
        @PathVariable(name = "id") id: Int
    ) = authorService.deleteAuthor(id)


    @GetMapping(
        value = ["/searchBy/{author_name}&{page}&{size}"]
    )
    fun searchAuthor(
        @PathVariable(name = "author_name") author_name: String,
        @PathVariable(name = "page") page: Int,
        @PathVariable(name = "size") size: Int,
    ): ResponseEntity<Map<String, Any>>? = authorService.findByAuthor(author_name, page, size)

}
