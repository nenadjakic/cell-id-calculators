package com.github.nenadjakic.cellidcalculators.command

import com.github.nenadjakic.cellidcalculators.service.LteService
import org.jline.utils.AttributedString
import org.jline.utils.AttributedStyle
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption

@ShellComponent
@ShellCommandGroup("LTE Calculator")
class LteCommand(private val lteService: LteService) {

    @ShellMethod(value = "Calculate ECI from eNodeBId and cellId.", key = ["to-eci", "get-eci"])
    fun toEci(
        @ShellOption(value = ["-N", "--enodebid"], help = "eNodeBId") eNodeBId: Int,
        @ShellOption(value = ["-C", "--cellid"], help = "CellId") cellId: Int
    ): String {
        val eci = lteService.getECI(eNodeBId, cellId)
        return AttributedString(
            "ECI: ",
            AttributedStyle.DEFAULT.bold().foreground(INFO_FOREGROUND_COLOR)
        ).toAnsi() + AttributedString(
            eci.toString(), AttributedStyle.DEFAULT.foreground(
                INFO_FOREGROUND_COLOR
            )
        ).toAnsi()
    }

    @ShellMethod(
        value = "Extract ECI value to eNodeBId and CellId.",
        key = ["get-enodebid-cellid", "from-eci", "extract-eci"]
    )
    fun fromEci(@ShellOption(value = ["-E", "--eci"], help = "ECI") eci: Long): String {
        val x = lteService.getENodBIdAndCellId(eci)
        return x.first.toString() + "-" + x.second.toString()
    }
}