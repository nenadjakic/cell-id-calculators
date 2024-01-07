package com.github.nenadjakic.cellidcalculators.configuration

import org.jline.utils.AttributedString
import org.jline.utils.AttributedStyle
import org.springframework.shell.jline.PromptProvider
import org.springframework.stereotype.Component

@Component
class CellIdCalculatorsPromptProvider : PromptProvider {
    override fun getPrompt(): AttributedString {
            return AttributedString("cell-calculator:>", AttributedStyle.DEFAULT.foreground(
                AttributedStyle.YELLOW))
    }
}