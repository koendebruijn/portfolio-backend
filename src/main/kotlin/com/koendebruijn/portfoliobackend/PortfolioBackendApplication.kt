package com.koendebruijn.portfoliobackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PortfolioBackendApplication

fun main(args: Array<String>) {
    runApplication<PortfolioBackendApplication>(*args)
}
