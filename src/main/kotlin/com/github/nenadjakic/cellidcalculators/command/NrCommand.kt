package com.github.nenadjakic.cellidcalculators.command

import com.github.nenadjakic.cellidcalculators.DEFAULT_GNODEB_LENGTH
import com.github.nenadjakic.cellidcalculators.INFO_FOREGROUND_COLOR
import com.github.nenadjakic.cellidcalculators.service.NrService
import org.jline.utils.AttributedString
import org.jline.utils.AttributedStringBuilder
import org.jline.utils.AttributedStyle
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
        @ShellOption(value = ["-L", "--length"], help = "gNodeBId Length", defaultValue = DEFAULT_GNODEB_LENGTH.toString()) gNodeBLength: Int,
        @ShellOption(value = ["-C", "--cellid"], help = "CellId") cellId: Int
    ): String {
        val nci = nrService.getNCI(NrService.GNodeBDto(gNodeBLength, gNodeBId, cellId))

        return AttributedStringBuilder()
            .append(
                AttributedString(
                    "NCI: ",
                    AttributedStyle.DEFAULT.bold().foreground(INFO_FOREGROUND_COLOR)
                )
            ).append(
                AttributedString(
                    nci.toString(), AttributedStyle.DEFAULT.foreground(
                        INFO_FOREGROUND_COLOR
                    )
                )
            )
            .toAnsi()
    }

    @ShellMethod(value = "Extract NCI value to gNodeBId and cellId.",
        key = ["get-gnodebid-cellid", "from-nci", "extract-nci"])
    fun fromNci(
        @ShellOption(value = ["-N", "--nci"], help = "NCI") nci: Long,
        @ShellOption(value = ["-L", "--length"], help = "gNodeBId Length", defaultValue = DEFAULT_GNODEB_LENGTH.toString()) gNodeBLength: Int
    ): String {
        val x = nrService.getGNodeBIdAndCellId(NrService.NciDto(gNodeBLength, nci))
        return AttributedStringBuilder()
            .append(
                AttributedString(
                    "gNodeBLength: ",
                    AttributedStyle.DEFAULT.bold().foreground(INFO_FOREGROUND_COLOR)
                )
            ).append(
                AttributedString(
                    gNodeBLength.toString(), AttributedStyle.DEFAULT.foreground(
                        INFO_FOREGROUND_COLOR
                    )
                )
            )
            .append(", ")
            .append(
                AttributedString(
                    "gNodeBId: ",
                    AttributedStyle.DEFAULT.bold().foreground(INFO_FOREGROUND_COLOR)
                )
            ).append(
                AttributedString(
                    x.first.toString(), AttributedStyle.DEFAULT.foreground(
                        INFO_FOREGROUND_COLOR
                    )
                )
            )
            .append(", ")
            .append(
                AttributedString(
                    "cellId: ",
                    AttributedStyle.DEFAULT.bold().foreground(INFO_FOREGROUND_COLOR)
                )
            ).append(
                AttributedString(
                    x.second.toString(), AttributedStyle.DEFAULT.foreground(
                        INFO_FOREGROUND_COLOR
                    )
                )
            )
            .toAnsi()
    }
}