import kotlinx.coroutines.*
import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.random.Random

val r = Random(0)

suspend fun printResult(i: Int){
    val sh = r.nextInt(0, 1000)
    delay(1000L+sh)
    println("Coroutine output $i, $sh")
}

fun main() {
    var beg = 0L
    var end = 0L
    beg = System.currentTimeMillis()
    /*runBlocking {
        for (i in 1..100000) {
            val j = launch {
                printResult(i)
            }
        }
    }*/


    /*for (i in 1..100000) {
        val j = thread {
            val sh = r.nextInt(0, 1000)
            sleep(1000L+sh)
            println("Thread output $i, $sh")
        }
    }*/



    /*GlobalScope.launch{

    }
     */

    runBlocking{
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
    }
    end = System.currentTimeMillis()
    val d = end-beg
    println("Finishing program in $d ms ...")
}