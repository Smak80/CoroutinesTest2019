import kotlinx.coroutines.*
import java.lang.Thread.sleep
import java.util.*
import kotlin.concurrent.thread
import kotlin.random.Random

val r = Random(Date().time)
val delta = 10000

//Функция не используется.
/*suspend fun printResult(i: Int){
    val s = 1000L+r.nextInt(0, delta)
    delay(s)
    println("Coroutine output $i, $s")
}*/

fun main() {
    var beg = 0L
    var end = 0L
    beg = System.currentTimeMillis()

    runBlocking {
        for (i in 1..100000) {
            val j = launch {
                val s = 1000L + r.nextInt(0, delta)
                delay(s)
            }
        }
    }

    end = System.currentTimeMillis()
    val cd = end - beg

    val t = mutableListOf<Thread>()
    beg = System.currentTimeMillis()
    for (i in 1..100000) {
        t.add(thread {
            val s = 1000L + r.nextInt(0, delta)
            sleep(s)
        })
    }
    for (th in t) {
        th.join()
    }

    end = System.currentTimeMillis()
    val td = end - beg
    println("Finished coruotines in $cd ms ...")
    println("Finished threads in $td ms ...")

    /*runBlocking{
        val d = mutableListOf<Deferred<Int>>()
        for (i in 1..10){
            d.add(async{
                delay(1000+r.nextLong(0, 1000))
                val k = r.nextInt(-100, 100)
                println("Random term $i = $k")
                k
            })
        }
        var s = 0
        for (e in d){
            val v = e.await()
            s += v
            println("Got $v")
        }
        println("runBlocking output $s")
    }*/
}