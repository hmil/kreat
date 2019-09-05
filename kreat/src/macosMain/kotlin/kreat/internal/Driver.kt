package fr.hmil.kreat.internal

import fr.hmil.kreat.make_window

open class NativeSurface(val name: String) : Surface {
    val children = mutableListOf<NativeSurface>()

    override fun append(vararg children: Surface) {
        this.children.addAll(children as Array<NativeSurface>)
    }

    open fun render(): String {
        return "<${name}>" +
                indent("\n" +
                        children.joinToString("\n") { it.render() }
                ) +
                "\n</${name}>"
    }

    private fun indent(s: String) = s.replace("\n", "\n    ")
}

class NativeTextSurface(name: String, var text: String) : NativeSurface(name), TextSurface {
    override fun setText(text: String) {
        this.text = text;
    }
}

class NativeDriver : Driver {
    override fun createSurface(name: String): Surface {
        return NativeSurface(name)
    }

    override fun createTextSurface(name: String, text: String): TextSurface {
        return NativeTextSurface(name, text)
    }

    override fun render(s: Surface) {
        s as NativeSurface
        make_window()
    }

}

actual object DriverManager {

    val driver = NativeDriver()

    actual fun getDriver(): Driver {
        return driver
    }
}