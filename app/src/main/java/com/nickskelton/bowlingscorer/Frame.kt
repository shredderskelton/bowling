package com.nickskelton.bowlingscorer

abstract class Frame {
    abstract val frameValue: Int
    abstract val firstRollText: String
    abstract val secondRollText: String
    abstract val valid: Boolean

    var previous: Frame? = null
    var cumulativeTotal: Int = 0
        get() {
            return frameValue + (previous?.cumulativeTotal ?: 0)
        }

    val cumulativeTotalText: String by lazy {
        cumulativeTotal.toString()
    }
}

class Strike(nextBonusRoll: Int,
             nextNextBonusRoll: Int) : Frame() {
    override val valid: Boolean = true
    override val firstRollText: String = "X"
    override val secondRollText: String = ""
    override val frameValue: Int = 10 + nextBonusRoll + nextNextBonusRoll
}

class Spare(firstRoll: Int,
            nextBonusRoll: Int) : Frame() {
    override val valid: Boolean = true
    override val firstRollText: String = firstRoll.toString()
    override val secondRollText: String = "/"
    override val frameValue: Int = 10 + nextBonusRoll
}

class NormalFrame(firstRoll: Int,
                  secondRoll: Int) : Frame() {
    override val valid: Boolean = firstRoll + secondRoll < 10
    override val firstRollText: String = firstRoll.toString()
    override val secondRollText: String = secondRoll.toString()
    override val frameValue: Int = firstRoll + secondRoll
}

class EndFrame(firstRoll: Int,
               secondRoll: Int,
               thirdRoll: Int) : Frame() {

    // validate the third throw
    override val valid: Boolean =

            if (firstRoll == 10) {
                // first throw is a strike
                if (secondRoll == 10) {
                    // second throw is a strike
                    // third throw is whatever
                    true
                } else {
                    // second throw is NOT a strike
                    // third throw is at best a spare
                    secondRoll + thirdRoll <= 10
                }
            } else {
                // first throw is NOT a strike
                if (firstRoll + secondRoll == 10) {
                    // second throw is a spare
                    // third throw is whatever
                    true
                } else {
                    // second throw is NOT a spare
                    // third throw must be 0
                    thirdRoll == 0
                }
            }

    override val firstRollText: String = firstRoll.toString()
    override val secondRollText: String = secondRoll.toString()
    override val frameValue: Int = firstRoll + secondRoll + thirdRoll
    val thirdRollText: String = thirdRoll.toString()
}