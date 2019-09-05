package fr.hmil.kreat

import fr.hmil.kreat.internal.Driver
import fr.hmil.kreat.internal.Surface

@DslMarker
annotation class KreatDsl

@KreatDsl
abstract class AbstractUIBuilder {
    private val children = arrayListOf<Component<*>>()
    fun getChildren() = children
    val Component<*>.children get() = props.children.map { child(it) }
    fun <T : Component<*>> child(component: T): T {
        children.add(component)
        return component
    }

}


open class UIBuilder: AbstractUIBuilder() {
    operator fun String.unaryPlus() {
        text(this)
    }
}

open class Props(val children: List<Component<*>> = emptyList()) {

}

abstract class Component<P : Props>(val props: P) {

    open fun UIBuilder.render() { }

    open fun renderInternal(driver: Driver): Surface {
        val surface = driver.createSurface(this::class.simpleName ?: "View")
        surface.append(*(UIBuilder().apply { render() } .getChildren().map { it.renderInternal(driver) }) .toTypedArray())
        return surface
    }

    override fun toString(): String {
        return "<${this::class.simpleName}>" +
                indent("\n" +
                    UIBuilder().apply {
                        render()
                    }.getChildren().joinToString("\n") { it.toString() }
                ) +
                "\n</${this::class.simpleName}>"
    }

    private fun indent(s: String) = s.replace("\n", "\n    ")
}

class View<P : Props>(props: P) : Component<P>(props) {
    override fun UIBuilder.render() {
        children
    }
}

fun UIBuilder.view(init: UIBuilder.() -> Unit) = child(View(Props(children = UIBuilder().apply(init).getChildren())))

class TextProps(val text: String) : Props(emptyList()) {

}

class Text(props: TextProps) : Component<TextProps>(props) {

    override fun renderInternal(driver: Driver): Surface {
        val built = UIBuilder().apply { render() } .getChildren().map { it.renderInternal(driver) } .toTypedArray();
        val surface = driver.createTextSurface(this::class.simpleName ?: "View", props.text)
        surface.append(*built)
        return surface
    }

    override fun toString(): String {
        return props.text
    }
}

fun UIBuilder.text(text: String = "") = child(Text(TextProps(text)))
