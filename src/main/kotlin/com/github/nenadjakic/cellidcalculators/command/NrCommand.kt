package com.github.nenadjakic.cellidcalculators.command

import com.github.nenadjakic.cellidcalculators.service.NrService
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption

@ShellComponent
@ShellCommandGroup("NR Calculator")
class NrCommand(private val nrService: NrService) {

    @ShellMethod(value = "Calculate NCI from gNodeBId, gNodeBIdLength and cellId.", key = ["to-nci", "get-nci"])
    fun toNCI(
        @ShellOption(value = ["-N", "--gnodebid"], help = "gNodeBId") gNodeBId: Int,
        @ShellOption(value = ["-L", "--length"], help = "gNodeBId Length", defaultValue = "22") gNodeBLength: Int,
        @ShellOption(value = ["-C", "--cellid"], help = "CellId") cellId: Int
    ): String {
        return nrService.getNCI(gNodeBId, gNodeBLength, cellId).toString()
    }
}