package com.example.mareu.meeting_list;

import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.R;
import com.example.mareu.ui.MainActivity;
import com.example.mareu.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class MeetingInstrumentedTest {

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
    public void test1myMeetingList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(Matchers.allOf(ViewMatchers.withId(R.id.RView),isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void test4myMeetingList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(Matchers.allOf(ViewMatchers.withId(R.id.RView),isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(Matchers.allOf(ViewMatchers.withId(R.id.RView),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(Matchers.allOf(ViewMatchers.withId(R.id.RView),isDisplayed())).check(withItemCount(ITEMS_COUNT-1));
    }

    @Test
    public void test2myMeetingFilter_shouldFilterByPlace() {
        onView(ViewMatchers.withId(R.id.spinner)).perform(click());
        onData(hasToString(startsWith("Location"))).perform(click());
        onView(withText("Filtre")).check(matches(isDisplayed()));
        onView(withId(22091969)).inRoot(isDialog()).perform(typeText("Peach"));
        onView(withText("OK")).perform(click());
        onView(Matchers.allOf(ViewMatchers.withId(R.id.RView),isDisplayed()))
                .check(withItemCount(1));
    }

    @Test
    public void test3myMeetingFilter_shouldFilterByDate() {
        onView(ViewMatchers.withId(R.id.spinner)).perform(click());
        onData(hasToString(startsWith("Date"))).perform(click());
        onView(withText("Filtre")).check(matches(isDisplayed()));
        onView(withId(22091969)).inRoot(isDialog()).perform(typeText("28/08/2022 16:00:00"));
        onView(withText("OK")).perform(click());
        onView(Matchers.allOf(ViewMatchers.withId(R.id.RView),isDisplayed()))
                .check(withItemCount(1));
    }

    @Test
    public void test5myMeetingAddMeeting_shouldSaveNewMeeting() {
        onView(Matchers.allOf(ViewMatchers.withId(R.id.addButton),isDisplayed()))
                .perform(ViewActions.click());
        onView(ViewMatchers.withId(R.id.subject)).perform(typeText("Reunion Z"));
        onView(ViewMatchers.withId(R.id.date)).perform(typeText("22/09/1969"));
        onView(ViewMatchers.withId(R.id.hours)).perform(typeText("10:00"));
        onView(ViewMatchers.withId(R.id.location)).perform(typeText("Paris"));
        onView(ViewMatchers.withId(R.id.saveButton))
                .perform(closeSoftKeyboard(), click());
        onView(Matchers.allOf(ViewMatchers.withText("Reunion Z"),isDisplayed()));

    }
}