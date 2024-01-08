package com.github.nenadjakic.cellidcalculators.service

import com.github.nenadjakic.cellidcalculators.DEFAULT_GNODEB_LENGTH
import com.github.nenadjakic.cellidcalculators.validation.GNodeBId
import com.github.nenadjakic.cellidcalculators.validation.Nci
import jakarta.validation.Valid
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import kotlin.math.pow

@Service
@Validated
class NrService {

    @GNodeBId
    class GNodeBDto(
        @Min(value = 22, message = "The gNodeB length value must be higher or equal then {value}.")
        @Max(
            value = 32,
            message = "The gNodeB length value must be lower or equal then {value}."
        ) val gNodeBIdLength: Int = DEFAULT_GNODEB_LENGTH, val gNodeBId: Int, val cellId: Int)

    @Nci
    class NciDto(@Min(value = 22, message = "The gNodeB length value must be higher or equal then {value}.")
                  @Max(
                      value = 32,
                      message = "The gNodeB length value must be lower or equal then {value}."
                  ) val gNodeBIdLength: Int = DEFAULT_GNODEB_LENGTH, val nci: Long)

    fun getNCI(
        @Valid @NotNull gNodeBIdDto: GNodeBDto
    ): Long {
        return (gNodeBIdDto.gNodeBId * 2.0.pow(((36 - gNodeBIdDto.gNodeBIdLength).toDouble()))
            .toLong() + gNodeBIdDto.cellId)
    }

    fun getGNodeBIdAndCellId(
        @Valid @NotNull nciDto: NciDto): Pair<Int, Int> {
        val mask = (2.0.pow(nciDto.gNodeBIdLength.toDouble()) - 1.0).toLong() *
                (2.0.pow(36 - nciDto.gNodeBIdLength.toDouble())).toLong()

        val gNodeBId = ((mask and nciDto.nci) / (2.0.pow(36.0 - nciDto.gNodeBIdLength)).toLong()).toInt()
        val cellId = ((mask.inv()) and nciDto.nci).toInt()

        return Pair(gNodeBId, cellId)
    }
}