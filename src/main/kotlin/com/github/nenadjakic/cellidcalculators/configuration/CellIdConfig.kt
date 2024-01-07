package com.github.nenadjakic.cellidcalculators.configuration

import com.github.nenadjakic.cellidcalculators.CustomExceptionResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CellIdConfig {

    @Bean
    fun customExceptionResolver(): CustomExceptionResolver {
        return CustomExceptionResolver()
    }
}