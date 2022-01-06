fun main() {
    val firstListener = FirstListener()
    val secondListener = SecondListener()
    val observable = ObservableClass()
    observable.addListener(firstListener)
    observable.addListener(secondListener)
    observable.text = "Hello"
    observable.text = "World"
    observable.removeListener(firstListener)
    observable.text = "Removed First Listener"
}