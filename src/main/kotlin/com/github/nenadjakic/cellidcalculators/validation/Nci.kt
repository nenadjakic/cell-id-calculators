package com.github.nenadjakic.cellidcalculators.validation

import jakarta.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(value = AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [NciValidator::class])
annotation class Nci (
    val message: String = "NCI must be between 0 and 68719476736.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<*>> = []
)