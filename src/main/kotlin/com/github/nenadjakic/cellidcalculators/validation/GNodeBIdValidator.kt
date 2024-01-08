package com.github.nenadjakic.cellidcalculators.validation

import com.github.nenadjakic.cellidcalculators.service.NrService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import kotlin.math.pow

class GNodeBIdValidator : ConstraintValidator<GNodeBId, NrService.GNodeBDto> {

    override fun isValid(dto: NrService.GNodeBDto?, context: ConstraintValidatorContext?): Boolean {
        val maxGNodeBId = (2.0.pow(dto?.gNodeBIdLength!!.toDouble())).toLong() - 1L
        val maxCellId = (2.0.pow(36.0 - dto.gNodeBIdLength.toDouble())).toLong() - 1L

        if (dto.gNodeBId < 0 || dto.gNodeBId > maxGNodeBId) {
            context?.disableDefaultConstraintViolation()
            context?.buildConstraintViolationWithTemplate(
                String.format(
                    "GNodeBId for chosen length %d must be between 0 and %d.",
                    dto.gNodeBIdLength,
                    maxGNodeBId,
                )
            )!!
                .addConstraintViolation()
            return false
        } else if (dto.cellId < 0 || dto.cellId > maxCellId) {
            context?.disableDefaultConstraintViolation()
            context?.buildConstraintViolationWithTemplate(
                String.format(
                    "CellId for chosen length %d must be between 0 and %d.",
                    dto.gNodeBIdLength,
                    maxCellId,
                )
            )!!
                .addConstraintViolation()
            return false
        } else {
            return true
        }
    }
}