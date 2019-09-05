package fr.hmil.kreat

import kotlin.browser.document

actual object Fiddle {
    actual fun render(text: String) {
        val container = document.createElement("div")
        document.body?.appendChild(container)
        container.innerHTML = text
    }
}