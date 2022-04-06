package org.example.algorithm

import java.util.*

enum class AlgorithmEvent {
    Started,
    Finished
}

abstract class AlgorithmObserver : Observer {
    class InvalidNotification :
        java.lang.Exception("AlgorithmObservers must only be notified by AlgorithmObservable and must contain an AlgorithmEvent")

    override fun update(o: Observable?, arg: Any?) {
        if (o !is AlgorithmObservable || arg !is AlgorithmEvent)
            throw InvalidNotification()
    }
}

open class AlgorithmObservable : Observable() {
    class InvalidArgument : java.lang.Exception("Notification argument must be of type AlgorithmEvent")
    class InvalidObserver : java.lang.Exception("Observer must be of type AlgorithmObserver")

    override fun notifyObservers(arg: Any?) {
        setChanged()
        if (arg is AlgorithmEvent)
            super.notifyObservers(arg)
        else
            throw InvalidArgument()
    }

    override fun addObserver(o: Observer?) {
        if (o is AlgorithmObserver)
            super.addObserver(o)
        else
            throw InvalidObserver()
    }
}