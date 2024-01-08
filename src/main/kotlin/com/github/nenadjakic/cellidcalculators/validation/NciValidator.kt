package com.github.nenadjakic.cellidcalculators.validation

import com.github.nenadjakic.cellidcalculators.service.NrService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import kotlin.math.pow

class NciValidator : ConstraintValidator<Nci, NrService.NciDto> {

    override fun isValid(dto: NrService.NciDto?, contect: ConstraintValidatorContext?): Boolean {
        return !(dto?.nci!! < 0 ||  dto.nci > (2.0.pow(36.0)).toLong() - 1L)
    }
}