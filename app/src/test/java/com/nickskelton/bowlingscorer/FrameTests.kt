package com.nickskelton.bowlingscorer

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Test

class FrameTests {
    @Test
    fun strike() {
        //Given
        val strike = Strike(3, 5)
        //When

        //Then
        assertEquals(18, strike.frameValue)
    }

    @Test
    fun spare() {
        //Given
        val spare = Spare(4, 6)

        //When

        //Then
        assertEquals(16, spare.frameValue)
    }

    @Test
    fun normalFrame() {
        //Given
        val frame = NormalFrame(2, 1)

        //When

        //Then
        assertEquals(3, frame.frameValue)
    }
    @Test
    fun invalidNormalFrame() {
        //Given
        val frame = NormalFrame(2, 9)

        //When

        //Then
        assertFalse(frame.valid)
    }

    @Test
    fun invalidEndFrame_extra() {
        //Given
        val frame = EndFrame(2, 1, 3)

        //When

        //Then
        assertFalse(frame.valid)
    }

    @Test
    fun invalidEndFrame_start() {
        //Given
        val frame = EndFrame(9, 9, 3)

        //When

        //Then
        assertFalse(frame.valid)
    }
    @Test
    fun invalidEndFrame_end() {
        //Given
        val frame = EndFrame(10, 9, 3)

        //When

        //Then
        assertFalse(frame.valid)
    }

    @Test
    fun validEndFrame() {
        //Given
        val frame = EndFrame(10, 10, 10)

        //When

        //Then
        assertTrue(frame.valid)
    }

    @Test
    fun validEndFrame_endNothing() {
        //Given
        val frame = EndFrame(10, 0, 0)

        //When

        //Then
        assertTrue(frame.valid)
    }

    @Test
    fun validEndFrame_endSpare() {
        //Given
        val frame = EndFrame(10, 9, 1)

        //When

        //Then
        assertTrue(frame.valid)
    }

    @Test
    fun validEndFrame_SpareStrike() {
        //Given
        val frame = EndFrame(2, 8, 10)

        //When

        //Then
        assertTrue(frame.valid)
    }
}