package com.example.messagingapp;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(MainActivity.class);
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void MainActivityLaunch() {
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void checkFAB(){
        onView(withId(R.id.fab)).perform(click());
        intended(hasComponent(NewMessage.class.getName()));
    }

    @Test
    public void onClickFAB(){
        onView(withId(R.id.fab)).perform(click());
        Intent i = new Intent();
        activityRule.launchActivity(i);
    }
}