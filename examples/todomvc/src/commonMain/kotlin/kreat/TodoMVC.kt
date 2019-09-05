package fr.hmil.kreat.example

import fr.hmil.kreat.*

class App : Component<Props>(Props(emptyList())) {
    override fun UIBuilder.render() {
        view {
            text("roses are red")
            view {
                +"violets are blue"
                +"undefined is not a function"
            }
        }
    }
}