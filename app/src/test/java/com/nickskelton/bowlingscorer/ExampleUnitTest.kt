package com.nickskelton.bowlingscorer

import junit.framework.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun validEndFrame() {

        //Given
        val numbers: IntArray = intArrayOf(0, 1, 2, 3, 4, 5, 6, 1, 8, 1, 0, 1, 2, 3, 4, 5, 6, 1, 9, 1, 3)

        //When
        val scorecard = Scorecard(numbers)

        //Then
        assertEquals(66, scorecard.finalScore)
    }

    @Test
    fun perfect() {

        //Given
        val numbers: IntArray = intArrayOf(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10)

        //When
        val scorecard = Scorecard(numbers)

        //Then
        assertEquals(300, scorecard.finalScore)
    }

    @Test
    fun allOnes() {

        //Given
        val numbers: IntArray = intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0)

        //When
        val scorecard = Scorecard(numbers)

        //Then
        assertEquals(20, scorecard.finalScore)
    }

    @Test
    fun givenScorecard() {

        //Given
        val numbers: IntArray = intArrayOf(1, 4, 4, 5, 6, 4, 5, 5, 10, 0, 1, 7, 3, 6, 4, 10, 2, 8, 6)

        //When
        val scorecard = Scorecard(numbers)

        //Then
        assertEquals(133, scorecard.finalScore)
    }

    @Test
    fun allZero() {

        //Given
        val numbers: IntArray = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

        //When
        val scorecard = Scorecard(numbers)

        //Then
        assertEquals(0, scorecard.finalScore)
    }

    @Test
    fun twoThenTen() {

        //Given
        val numbers: IntArray = intArrayOf(2, 2, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

        //When
        val scorecard = Scorecard(numbers)

        //Then
        assertEquals(14, scorecard.finalScore)
    }

    @Test
    fun unfinished() {

        //Given
        val numbers: IntArray = intArrayOf(1, 4)

        //When
        val scorecard = Scorecard(numbers)

        //Then
        assertEquals(5, scorecard.finalScore)
    }

    @Test
    fun spares() {

        //Given
        val numbers: IntArray = intArrayOf(2, 8, 10, 10, 2, 1, 1, 9, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

        //When
        val scorecard = Scorecard(numbers)

        //Then
        assertEquals(78, scorecard.finalScore)
    }

}
