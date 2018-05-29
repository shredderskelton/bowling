package com.nickskelton.bowlingscorer

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BowlingTest {

    @get:Rule
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    val robot: BowlingRobot = BowlingRobot()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mActivityRule.launchActivity(Intent())
    }

    @Test
    fun singleEntry() {
        robot.enterNumber(2)
                .pressRoll()
                .assertCumulativeScoreFor(0, 2)
    }

    @Test
    fun doubleEntry() {
        robot.enterNumbers(2, 2)
                .assertCumulativeScoreFor(0, 4)
    }

    @Test
    fun strike() {
        robot.enterNumbers(2, 2, 10, 2, 2)
                .assertCumulativeScoreFor(0, 4)
                .assertCumulativeScoreFor(1, 18)
                .assertCumulativeScoreFor(2, 22)
    }

    @Test
    fun spare() {
        robot.enterNumbers(2, 2, 8, 2, 2)
                .assertCumulativeScoreFor(0, 4)
                .assertCumulativeScoreFor(1, 16)
                .assertCumulativeScoreFor(2, 18)
    }

    @Test
    fun perfect() {
        robot.enterNumbers(10,10,10,10,10, 10,10,10,10,10, 10,10)
                .assertCumulativeScoreFor(0, 30)
                .assertCumulativeScoreFor(1, 60)
                .assertCumulativeScoreFor(2, 90)
                .assertCumulativeScoreFor(3, 120)
                .assertCumulativeScoreFor(4, 150)
                .assertCumulativeScoreFor(5, 180)
                .assertCumulativeScoreFor(6, 210)
                .assertCumulativeScoreFor(7, 240)
                .assertCumulativeScoreFor(8, 270)
                .assertCumulativeScoreFor(9, 300)
    }

    @Test
    fun given() {
        robot.enterNumbers(1,4, 4,5, 6,4, 5,5, 10, 0,1, 7,3, 6,4, 10, 2,8,6)
                .assertCumulativeScoreFor(0, 5)
                .assertCumulativeScoreFor(1, 14)
                .assertCumulativeScoreFor(2, 29)
                .assertCumulativeScoreFor(3, 49)
                .assertCumulativeScoreFor(4, 60)
                .assertCumulativeScoreFor(5, 61)
                .assertCumulativeScoreFor(6, 77)
                .assertCumulativeScoreFor(7, 97)
                .assertCumulativeScoreFor(8, 117)
                .assertCumulativeScoreFor(9, 133)
    }
}
