package com.example.messagingapp;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class NewMessageTest {
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.SEND_SMS);
    @Rule
    public ActivityTestRule<NewMessage> activityRule = new ActivityTestRule<>(NewMessage.class);

    @Before
    public void setUp() throws Exception {
        SystemClock.sleep(2000);
    }

    @After
    public void tearDown() throws Exception {
        SystemClock.sleep(1000);
    }

    @Test
    public void emptyMessage(){
        onView(withId(R.id.phone_number)).perform(replaceText("8868003003"));
        onView(withId(R.id.sms_message)).perform(replaceText(""));
        onView(withId(R.id.btNewSend)).perform(click());
        onView(withText("Please enter a non-empty message")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    @Test
    public void emptyPhone(){
        onView(withId(R.id.phone_number)).perform(replaceText(""));
        onView(withId(R.id.sms_message)).perform(replaceText("Writing test using espresso and junit! yaay!!!"));
        onView(withId(R.id.btNewSend)).perform(click());
        onView(withText("Please enter a valid number")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    @Test
    public void invalidPhone1001(){
        onView(withId(R.id.phone_number)).perform(replaceText("wgfk"));
        onView(withId(R.id.sms_message)).perform(replaceText("bksdgs"));
        onView(withId(R.id.btNewSend)).perform(click());
        onView(withText("Please enter a valid number")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    @Test
    public void invalidPhone1002(){
        onView(withId(R.id.phone_number)).perform(replaceText("12345"));
        onView(withId(R.id.sms_message)).perform(replaceText("bksdgs"));
        onView(withId(R.id.btNewSend)).perform(click());
        onView(withText("Please enter a valid number")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    @Test
    public void invalidPhone1003(){
        onView(withId(R.id.phone_number)).perform(replaceText("1a4b5"));
        onView(withId(R.id.sms_message)).perform(replaceText("bksdgs"));
        onView(withId(R.id.btNewSend)).perform(click());
        onView(withText("Please enter a valid number")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    @Test
    public void newMessage(){
        onView(withId(R.id.phone_number)).perform(replaceText("8868003003"));
        onView(withId(R.id.sms_message)).perform(replaceText("Writing test using espresso and junit! yaay"));
        onView(withId(R.id.btNewSend)).perform(click());
        onView(withText("SMS Sent")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }
}
