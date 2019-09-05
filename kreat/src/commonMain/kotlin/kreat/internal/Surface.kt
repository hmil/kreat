package fr.hmil.kreat.internal

interface Surface {
    fun append(vararg children: Surface): Unit
}

interface TextSurface : Surface {
    fun setText(text: String): Unit
}