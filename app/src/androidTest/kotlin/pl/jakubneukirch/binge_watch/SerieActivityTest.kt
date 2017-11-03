package pl.jakubneukirch.binge_watch

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.pressMenuKey
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.Toolbar
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pl.jakubneukirch.binge_watch.serie.SerieActivity
import kotlin.reflect.jvm.jvmName

@RunWith(AndroidJUnit4::class)
@LargeTest
class SerieActivityTest() {

    @Rule
    @JvmField
    public var rule: ActivityTestRule<SerieActivity> = ActivityTestRule(SerieActivity::class.java)

    @Test
    fun testFabClick() {
        onView(withId(R.id.fabSerie))
                .perform(click())
        onView(withId(android.support.design.R.id.snackbar_text))
                .check(matches(isDisplayed()))
    }

    @Test
    fun testBackPressed(){
        onView(isRoot()).perform(pressMenuKey())
        assert(rule.activity.isFinishing)
    }
}