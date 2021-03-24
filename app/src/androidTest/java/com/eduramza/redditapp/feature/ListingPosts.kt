package com.eduramza.redditapp.feature

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.eduramza.redditapp.MainActivity
import com.eduramza.redditapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@Suppress("DEPRECATION")
@RunWith(AndroidJUnit4::class)
class ListingPosts {

    val activityRule = ActivityTestRule(MainActivity::class.java)
        @Rule get

    @Test
    fun shouldDisplayTitleApp(){
        onView(withId(R.id.button_first)).check(matches(isDisplayed()))
    }

}