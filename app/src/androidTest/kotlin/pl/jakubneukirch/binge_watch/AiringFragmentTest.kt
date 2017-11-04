package pl.jakubneukirch.binge_watch

import android.content.ComponentName
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v4.view.ViewPager
import android.support.v7.widget.AppCompatImageButton
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import junit.extensions.ActiveTestSuite
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pl.jakubneukirch.binge_watch.airing.AiringFragment
import pl.jakubneukirch.binge_watch.airing.mvp.view.SerieRecyclerAdapter
import pl.jakubneukirch.binge_watch.serie.SerieActivity
import pl.jakubneukirch.binge_watch.test.TestActivity

@RunWith(AndroidJUnit4::class)
class AiringFragmentTest {

    @Rule
    @JvmField
    public val rule: IntentsTestRule<TestActivity> = IntentsTestRule(TestActivity::class.java)

    lateinit var fragment: AiringFragment

    @Before
    fun setup() {
        fragment = AiringFragment()
        rule.activity.supportFragmentManager.beginTransaction().add(R.id.testFragmentFrame, fragment).commit()
    }

    @Test
    fun testFragmentDisplayed() {
        onView(withId(R.id.testFragmentFrame)).check(matches(isDisplayed()))
    }

    @Test
    fun testOpenSerieActivity() {
        onView(isRoot())
                .perform(waitFor(1000))
        onView(
                allOf(
                        isDescendantOfA(
                                nthChildOf(withId(R.id.airingRecyclerView), 0)
                        ),
                        withId(R.id.buttonDetails)
                )
        )
                .perform(click())


        intended(hasComponent(SerieActivity::class.java.name))
    }

    @Test
    fun testExapand(){
        onView(isRoot())
                .perform(waitFor(1000))
        onView(
                allOf(
                        isDescendantOfA(
                                nthChildOf(withId(R.id.airingRecyclerView), 0)
                        ),
                        withId(R.id.expandButton)
                )
        )
                .perform(click())

        onView(
                allOf(
                        isDescendantOfA(
                                nthChildOf(withId(R.id.airingRecyclerView), 0)
                        ),
                        withId(R.id.itemDesc)
                )
        )
                .check(matches(isDisplayed()))
    }

    @Test
    fun testHideOtherCard(){
        onView(isRoot())
                .perform(waitFor(1000))

        onView(
                allOf(
                        isDescendantOfA(
                                nthChildOf(withId(R.id.airingRecyclerView), 0)
                        ),
                        withId(R.id.expandButton)
                )
        )
                .perform(click())


        onView(
                withId(R.id.airingRecyclerView)
        )
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))

        onView(
                withId(R.id.airingRecyclerView)
        )
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, clickId(R.id.expandButton)))

        onView(isRoot())
                .perform(waitFor(2000))

        onView(
                withId(R.id.airingRecyclerView)
        )
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))

        onView(
                allOf(
                        isDescendantOfA(
                                nthChildOf(withId(R.id.airingRecyclerView), 1)
                        ),
                        withId(R.id.itemDesc)
                )
        )
                .check(matches(not(isDisplayed())))

        onView(isRoot())
                .perform(waitFor(5000))
    }

    companion object {

        fun clickId(id: Int):ViewAction{
            return object : ViewAction{
                override fun getDescription(): String {
                    return "click item withId $id"
                }

                override fun getConstraints(): Matcher<View> {
                    return withId(id)
                }

                override fun perform(uiController: UiController?, view: View?) {
                    var button: AppCompatImageButton? = view?.findViewById(id) as AppCompatImageButton
                    button?.callOnClick()
                }
            }
        }

        fun waitFor(delay: Long): ViewAction {
            return object : ViewAction {
                override fun getDescription(): String {
                    return "Wait for $delay ms"
                }

                override fun getConstraints(): Matcher<View> {
                    return isRoot()
                }

                override fun perform(uiController: UiController?, view: View?) {
                    uiController?.loopMainThreadForAtLeast(delay)
                }
            }
        }

        fun nthChildOf(parentMatcher: Matcher<View>, position: Int): Matcher<View> {
            return object : TypeSafeMatcher<View>() {
                override fun describeTo(description: Description?) {
                    description?.appendText("position $position")
                }

                override fun matchesSafely(item: View?): Boolean {
                    if ((item?.parent !is RecyclerView)) {
                        return parentMatcher.matches(item?.parent)
                    }
                    var recycler = item?.parent as RecyclerView
                    return parentMatcher.matches(item?.parent) && recycler?.getChildAt(position).equals(item)
                }
            }
        }
    }
}