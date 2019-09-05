package fr.hmil.kreat.internal

interface Driver {
    fun createSurface(name: String): Surface
    fun createTextSurface(name: String, text: String): TextSurface
    fun render(s: Surface): Unit
}

expect object DriverManager {
    fun getDriver(): Driver
}