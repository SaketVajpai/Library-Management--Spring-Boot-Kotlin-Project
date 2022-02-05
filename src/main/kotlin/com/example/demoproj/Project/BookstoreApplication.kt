package com.library.kindle

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.client.RestTemplate
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
//@EnableSwagger2
@OpenAPIDefinition
@EnableCaching
@EnableJpaRepositories
class BookstoreApplication{

//    @Bean
//    fun restTemplate(builder: RestTemplateBuilder) : RestTemplate = builder.build()

}

fun main(args: Array<String>) {
    //SpringApplication.run(KindleApplication::class.java, *args)
    runApplication<BookstoreApplication>(*args)
}
