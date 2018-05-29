package com.nickskelton.bowlingscorer

import java.util.*

/**
 * An immutable class designed to calculate the score of an ongoing match.
 **
 * @param rolls consists of all positive Int's between 0 and 10 and should have a size of 21
 *              some games will consist of less than 21 rolls, these should be padded out with 0's
 *
 */
class Scorecard(rolls: IntArray) {
    val frames: Array<Frame> = calculate(rolls)
    val finalScore = frames.last().cumulativeTotal
    val isValid = frames.all { it.valid }

    private fun calculate(rolls: IntArray): Array<Frame> {
        val frames = Stack<Frame>()
        var workingFrame: Frame
        var i = 0
        while (i < rolls.size && frames.size < 10) {
            when {
                isLastFrame(frames) -> {
                    workingFrame = EndFrame(rolls[i], rolls[i + 1], rolls[i + 2])
                    i += 3
                }
                isStrike(rolls, i) -> {
                    workingFrame = Strike(rolls[i + 1], rolls[i + 2])
                    i++
                }
                isSpare(rolls, i) -> {
                    workingFrame = Spare(rolls[i], rolls[i + 2])
                    i += 2
                }
                else -> {
                    workingFrame = NormalFrame(rolls[i], rolls[i + 1])
                    i += 2
                }
            }
            if (!frames.empty())
                workingFrame.previous = frames.peek()
            frames.push(workingFrame)
        }
        return frames.toTypedArray()
    }

    private fun isLastFrame(frames: Stack<Frame>) =
            frames.size == 9

    private fun isSpare(rolls: IntArray, i: Int) =
            rolls[i] + rolls[i + 1] == 10

    private fun isStrike(rolls: IntArray, i: Int) =
            rolls[i] == 10
}