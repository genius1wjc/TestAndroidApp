import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.myapplication.R;
import com.example.myapplication.activity.BroadcastActi;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
public class BroadcastActiTest {

    @Rule
    public ActivityTestRule<BroadcastActi> rule = new ActivityTestRule<>(BroadcastActi.class);

    @Test
    public void ensureToastShown() {

        onView(withId(R.id.button4)).perform(click());

        // Check if a toast is shown
        onView(withText(startsWith("Broadcast"))).
                inRoot(withDecorView(not(is(rule.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }
}