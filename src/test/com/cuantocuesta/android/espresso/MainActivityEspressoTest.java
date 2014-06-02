package com.cuantocuesta.android.espresso;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import com.cuantocuesta.android.MainActivity;
import com.example.R;
import com.example.robolectric.DeckardActivity;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;

@LargeTest
public class MainActivityEspressoTest extends ActivityInstrumentationTestCase2<MainActivity> {

    @SuppressWarnings("deprecation")
     public MainActivityEspressoTest() {
       // This constructor was deprecated - but we want to support lower API levels.
       super("com.cuantocuesta.android", MainActivity.class);
     }
    @Override
    public void setUp() throws Exception {
        super.setUp();
        // Espresso will not launch our activity for us, we must launch it via getActivity().
        getActivity();
    }

    public void testCheckText() {
        onView(withId(R.id.text))
            .check(matches(withText("Hello Espresso!")));
      }
}
