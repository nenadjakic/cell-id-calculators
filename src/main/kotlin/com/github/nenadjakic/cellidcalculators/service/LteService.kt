package com.github.nenadjakic.cellidcalculators.service

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Service
@Validated
class LteService {

    fun getECI(
        @Min(
            value = 0,
            message = "The eNodeBId value must be higher or equal then {value}."
        ) @Max(
            value = 2097151,
            message = "The eNodeBId value must be lower or equal than {value}."
        ) eNodeBId: Int, @Min(
            value = 0,
            message = "The cellId value must be higher or equal then {value}."
        ) @Max(
            value = 511,
            message = "The cellId value must be lower or equal than {value}."
        ) cellId: Int
    ): Int {
        return (256 * eNodeBId) + cellId
    }

    fun getENodBIdAndCellId(
        @Min(
            value = 0,
            message = "The ECI value must be higher or equal then {value}."
        ) @Max(
            value = 268435703,
            message = "The ECI value must be lower or equal than {value}."
        ) eci: Long
    ): Pair<Int, Int> {
        val hex = ("0".repeat(8) + eci.toString(16)).takeLast(8);

        val cellId = hex.takeLast(2).toInt(16)
        val eNodeBId = hex.take(hex.length - 2).toInt(16)
        return Pair(eNodeBId, cellId)
    }
}