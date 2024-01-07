package com.github.nenadjakic.cellidcalculators.service

import jakarta.validation.constraints.Min
import org.springframework.stereotype.Service

@Service
class NrService {

    fun getNCI(
        @Min(value = 0, message = "The gNodeBId value must be higher or equal then {value}.") gNodeBId: Int,
        gNodeBIdLength: Int = 22,
        cellId: Int
    ): Long {
        return (gNodeBId * Math.pow(2.0, ((36 - gNodeBIdLength).toDouble())).toLong() + cellId).toLong()
    }
}