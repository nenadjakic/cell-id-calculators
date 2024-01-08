package com.github.nenadjakic.cellidcalculators.validation

import jakarta.validation.Constraint
import kotlin.reflect.KClass


@Target(AnnotationTarget.CLASS)
@Retention(value = AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [GNodeBIdValidator::class])
annotation class GNodeBId (
    val message: String = "Incorrect gNodeBId or/and cellId value.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<*>> = []
)
