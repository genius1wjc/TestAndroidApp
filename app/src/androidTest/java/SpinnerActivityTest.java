import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.myapplication.R;
import com.example.myapplication.activity.SpinnerActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import utils.ListMatcher;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

@RunWith(AndroidJUnit4.class)
public class SpinnerActivityTest {

    @Rule
    public ActivityTestRule<SpinnerActivity> rule = new ActivityTestRule<>(SpinnerActivity.class);

    @Test
    public void clickSpinnerItem() throws InterruptedException {
        // Click on the Spinner to open the item selection
        onView(withId(R.id.spinner)).perform(click());

        // This is not needed, just for me to see the expanded spinner
        Thread.sleep(5000);

        // Click on the item “Personal”
        onData(allOf(is(instanceOf(String.class)), is("Personal"))).perform(click());
    }

    @Test
    public void ensureHasCorrectNumOfItems() {
        onView(withId(R.id.spinner)).check(matches(ListMatcher.withListSize(6)));
    }
}