package com.github.nenadjakic.cellidcalculators

import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import org.springframework.shell.ParameterValidationException
import org.springframework.shell.command.CommandExceptionResolver
import org.springframework.shell.command.CommandHandlingResult
import java.util.stream.Collectors


class CustomExceptionResolver : CommandExceptionResolver {
    override fun resolve(ex: Exception): CommandHandlingResult {
        if (ex is ParameterValidationException) return CommandHandlingResult.of(
            ex.constraintViolations
                .stream()
                .map { obj: ConstraintViolation<Any?> -> obj.message.replaceFirstChar { it.uppercase() } }
                .collect(Collectors.joining("\n"))
                    + '\n'
        ) else if (ex is ConstraintViolationException) {
            return CommandHandlingResult.of(
                ex.constraintViolations
                    .stream()
                    .map { obj: ConstraintViolation<*> -> obj.message.replaceFirstChar { it.uppercase() } }
                    .collect(Collectors.joining("\n"))
                        + '\n')
        }

        return CommandHandlingResult.of(ex.message + '\n')
    }
}