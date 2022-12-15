package com.android.phonepemcround.domain

import java.util.*
import java.util.Collections.shuffle
import javax.inject.Inject

class Randomizer @Inject constructor() {

    fun randomize(input: String): String {
        val randomLength = randInt(5, 10)
        val sb = java.lang.StringBuilder()
        for (i in 0..randomLength) {
            val randomChar = randInt(0, 25) + 'a'.code
            sb.append(randomChar)
        }
        val final = input + sb.toString()
        return shuffle(final.toMutableList()).toString()
    }

    private fun randInt(min: Int, max: Int): Int {

        // NOTE: This will (intentionally) not run as written so that folks
        // copy-pasting have to think about how to initialize their
        // Random instance.  Initialization of the Random instance is outside
        // the main scope of the question, but some decent options are to have
        // a field that is initialized once and then re-used as needed or to
        // use ThreadLocalRandom (if using at least Java 1.7).
        //
        // In particular, do NOT do 'Random rand = new Random()' here or you
        // will get not very good / not very random results.

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return Random().nextInt(max - min + 1) + min
    }

}

