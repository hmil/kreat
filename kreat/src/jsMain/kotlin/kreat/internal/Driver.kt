package fr.hmil.kreat.internal

import org.w3c.dom.Element
import kotlin.browser.document

open class JSDomSurface(val element: Element) : Surface {
    override fun append(vararg children: Surface) {
        (children as Array<JSDomSurface>).forEach {
            element.appendChild(it.element)
        }
    }
}

class JSDomTextSurface(element: Element, var text: String) : JSDomSurface(element), TextSurface {

    init {
        element.innerHTML = text
    }

    override fun setText(text: String) {
        element.innerHTML = text
    }
}

class JSDriver : Driver {

    var currentRoot: JSDomSurface? = null

    override fun render(s: Surface) {
        s as JSDomSurface
        val rootElement = currentRoot?.element
        if (rootElement != null) {
            document.body?.removeChild(rootElement)
        }
        document.body?.appendChild(s.element)
        currentRoot = s
    }

    override fun createSurface(name: String): Surface {
        return JSDomSurface(document.createElement(name))
    }

    override fun createTextSurface(name: String, text: String): JSDomTextSurface {
        return JSDomTextSurface(document.createElement(name), text)
    }
}

actual object DriverManager {

    val driver = JSDriver()

    actual fun getDriver(): Driver {
        return driver
    }
}