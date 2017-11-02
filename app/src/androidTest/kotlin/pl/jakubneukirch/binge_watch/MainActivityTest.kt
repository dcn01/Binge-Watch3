package pl.jakubneukirch.binge_watch

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import pl.jakubneukirch.binge_watch.api.MovieDBInterface
import pl.jakubneukirch.binge_watch.main.MainActivity
import pl.jakubneukirch.binge_watch.main.dagger.DaggerMainComponent
import pl.jakubneukirch.binge_watch.main.mvp.view.MainPagerAdapter
import javax.inject.Inject

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest() {

    @Rule
    @JvmField
    public var rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Inject
    lateinit var apiInt: MovieDBInterface

    @Before
    fun setup(){
    }

    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        assertEquals("pl.jakubneukirch.binge_watch", appContext.packageName)
    }

    @Test
    fun testFavoritesClick(){
        onView(withId(R.id.favoritesNav)).perform(click())
        onView(withId(R.id.mainPager)).check(matches(currentChildEqualTo(MainPagerAdapter.FAVORITES_FRAGMENT_NAV)))
    }

    @Test
    fun testAiringClick(){
        onView(withId(R.id.airingNav)).perform(click())
        onView(withId(R.id.mainPager)).check(matches(currentChildEqualTo(MainPagerAdapter.AIRING_FRAGMENT_NAV)))
    }

    @Test
    fun testSimilarClick(){
        onView(withId(R.id.similarNav)).perform(click())
        onView(withId(R.id.mainPager)).check(matches(currentChildEqualTo(MainPagerAdapter.SIMILAR_FRAGMENT_NAV)))
    }

    companion object {

        fun currentChildEqualTo(position: Int): Matcher<View>{
            return object: TypeSafeMatcher<View>(){
                override fun describeTo(description: Description?) {
                    description?.appendText("position: $position of parent ")
                }

                override fun matchesSafely(item: View?): Boolean {
                    if(item !is ViewPager) return false
                    var view: ViewPager = item as ViewPager
                    return view.currentItem == position
                }
            }
        }
    }
}
