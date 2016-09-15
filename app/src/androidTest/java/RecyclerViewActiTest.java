import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.myapplication.R;
import com.example.myapplication.activity.RecyclerViewActi;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;

/**
 * Created by jiechao on 9/14/16.
 */
@RunWith(AndroidJUnit4.class)
public class RecyclerViewActiTest {

    @Rule
    public ActivityTestRule<RecyclerViewActi> rule = new ActivityTestRule<>(RecyclerViewActi.class);

    @Test
    public void clickListItem() {
        // Click on the RecyclerView item at position 2
        onView(withId(R.id.cardList)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
    }
}
