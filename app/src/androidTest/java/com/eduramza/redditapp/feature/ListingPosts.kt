package com.eduramza.redditapp.feature

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.eduramza.redditapp.MainActivity
import com.eduramza.redditapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListingPosts {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(
        MainActivity::class.java
    )

    @Test
    fun shouldDisplayListOfPostsApp(){
        onView(withId(R.id.tv_posted_by_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_elapsed_time)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_post_title)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayDetailsFragmentWhenClickInItem(){
        onView(withId(R.id.recyclerview_posts)).perform(
            RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()))

        onView(withId(R.id.button_second)).check(matches(isDisplayed()))
    }


}