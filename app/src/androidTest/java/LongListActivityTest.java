import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.myapplication.R;
import com.example.myapplication.activity.LongListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.matcher.BundleMatchers.hasEntry;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

@RunWith(AndroidJUnit4.class)
public class LongListActivityTest {

    @Rule
    public ActivityTestRule<LongListActivity> rule = new ActivityTestRule<>(LongListActivity.class);

    @Test
    public void clickListItem() {
        onData(allOf(is(instanceOf(Map.class)), hasEntry(equalTo("STR"), is("item: 50"))))
                .perform(click());
    }

    @Test
    public void clickSecondItem() {
        // Directly specify the position in the adapter to click on
        onData(anything()) // We are using the position so don't need to specify a data matcher
                .inAdapterView(withId(R.id.lvItems)) // Specify the explicit id of the ListView
                .atPosition(2) // Explicitly specify the adapter item to use
                .perform(click()); // Standard ViewAction
    }
}