package sephora.happyshop.ui.Activities;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import sephora.happyshop.R;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by fadel on 27/8/17.
 */
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testViewPagerDisplayed() {
        Espresso.onView(withId(R.id.viewPager))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testTabLayoutDisplayed() {
        Espresso.onView(withId(R.id.tabs))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testSwipe() {
        Espresso.onView(withId(R.id.viewPager))
                .perform(ViewActions.swipeLeft());
    }
}