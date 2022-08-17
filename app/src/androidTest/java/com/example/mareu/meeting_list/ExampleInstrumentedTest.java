package com.example.mareu.meeting_list;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.R;
import com.example.mareu.ui.MainActivity;
import com.example.mareu.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.hamcrest.Matchers;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static androidx.test.espresso.action.ViewActions.*;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static int ITEMS_COUNT = 3;

    private MainActivity mActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    @Test
    public void myMeetingList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(Matchers.allOf(ViewMatchers.withId(R.id.RView),isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myMeetingList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(Matchers.allOf(ViewMatchers.withId(R.id.RView),isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(Matchers.allOf(ViewMatchers.withId(R.id.RView),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(Matchers.allOf(ViewMatchers.withId(R.id.RView),isDisplayed())).check(withItemCount(ITEMS_COUNT-1));
    }

}