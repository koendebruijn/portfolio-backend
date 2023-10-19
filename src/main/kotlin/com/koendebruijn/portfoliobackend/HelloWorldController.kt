package com.koendebruijn.portfoliobackend

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {
    @GetMapping
    fun hello() = mapOf("message" to "Hello World")
}