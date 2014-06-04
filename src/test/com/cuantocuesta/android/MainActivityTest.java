package com.cuantocuesta.android;

import android.app.Activity;

import com.cuantocuesta.android.activities.LauncherActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void testSomething() throws Exception {
        Activity activity = Robolectric.buildActivity(LauncherActivity.class).create().get();
        assertTrue(activity != null);
    }
}
