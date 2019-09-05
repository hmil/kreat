package fr.hmil.kreat

import fr.hmil.kreat.internal.DriverManager

fun render(component: Component<*>): Unit {
    val driver = DriverManager.getDriver()
    val result = component.renderInternal(driver)
    driver.render(result)
}
