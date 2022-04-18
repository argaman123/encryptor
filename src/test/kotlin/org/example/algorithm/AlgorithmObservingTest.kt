package org.example.algorithm

import org.example.utils.SystemIOMock
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.util.*

class AlgorithmObserverTester : AlgorithmObserver() {
    companion object {
        const val RECEIVED_UPDATE = "update received"
    }

    override fun update(o: Observable?, arg: Any?) {
        super.update(o, arg)
        println(RECEIVED_UPDATE)
    }
}


internal class AlgorithmObserverTest {
    companion object {
        private var systemIOMock = SystemIOMock()

        @JvmStatic
        @BeforeAll
        fun enableIOMock() {
            systemIOMock.enable()
        }

        @JvmStatic
        @AfterAll
        fun disableIOMock() {
            systemIOMock.disable()
        }
    }

    @BeforeEach
    private fun setup() {
        systemIOMock.enable()
    }

    private val observer = AlgorithmObserverTester()

    @Test
    fun update() {
        observer.update(AlgorithmObservable(), AlgorithmEvent.Started)
        assertTrue(systemIOMock.consumeOutput().contains(AlgorithmObserverTester.RECEIVED_UPDATE))
    }

    @Test
    fun updateBadObservable() {
        assertThrows(AlgorithmObserver.InvalidNotification::class.java, fun() {
            observer.update(Observable(), AlgorithmEvent.Started)
        })
    }

    @Test
    fun updateBadArg() {
        assertThrows(AlgorithmObserver.InvalidNotification::class.java, fun() {
            observer.update(AlgorithmObservable(), Object())
        })
    }
}

internal class AlgorithmObservableTest {
    companion object {
        private var systemIOMock = SystemIOMock()

        @JvmStatic
        @BeforeAll
        fun enableIOMock() {
            systemIOMock.enable()
        }

        @JvmStatic
        @AfterAll
        fun disableIOMock() {
            systemIOMock.disable()
        }
    }

    @BeforeEach
    private fun setup() {
        systemIOMock.enable()
    }

    @Test
    fun notifyObservers() {
        val observable = AlgorithmObservable()
        val observer = AlgorithmObserverTester()
        observable.addObserver(observer)
        observable.notifyObservers(AlgorithmEvent.Started)
        assertTrue(systemIOMock.consumeOutput().contains(AlgorithmObserverTester.RECEIVED_UPDATE))
    }

    @Test
    fun notifyObserversBadArg() {
        val observable = AlgorithmObservable()
        val observer = AlgorithmObserverTester()
        observable.addObserver(observer)
        assertThrows(AlgorithmObservable.InvalidArgument::class.java, fun() {
            observable.notifyObservers(Object())
        })
    }

    @Test
    fun addObserverBadObserver() {
        class ObserverInstance : Observer {
            override fun update(o: Observable?, arg: Any?) {}
        }

        val observable = AlgorithmObservable()
        assertThrows(AlgorithmObservable.InvalidObserver::class.java, fun() {
            observable.addObserver(ObserverInstance())
        })
    }

}