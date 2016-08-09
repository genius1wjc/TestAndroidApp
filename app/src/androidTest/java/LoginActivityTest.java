import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.myapplication.R;
import com.example.myapplication.activity.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.startsWith;


@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {


    @Rule
    // Third parameter is set to false which means the activity is not started automatically
    public ActivityTestRule<LoginActivity> rule = new ActivityTestRule(LoginActivity.class, true, false);
    //public ActivityTestRule<LoginActivity> rule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void ensureTextChangesWork() {
        launchActivity();

        // Type text
        onView(withId(R.id.email)).perform(clearText(), typeText("jiechao378@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(clearText(), click(), typeText(" 1234"), closeSoftKeyboard());

        // Check that the text was changed.
        onView(withId(R.id.email)).check(matches(withText("jiechao378@gmail.com")))
                .check(matches(withText(startsWith("jiechao378"))));
        onView(withId(R.id.password)).check(matches(withText(containsString("12"))))
                .check(matches(withText(equalToIgnoringWhiteSpace("1234"))));
    }

    private void launchActivity() {
        Intent intent = new Intent();
        intent.putExtra("EXTRA", "Test");
        rule.launchActivity(intent);
    }
}