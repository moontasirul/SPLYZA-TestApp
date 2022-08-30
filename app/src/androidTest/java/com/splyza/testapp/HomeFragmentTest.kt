package com.splyza.testapp

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.splyza.testapp.presentation.home.HomeFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    private lateinit var scenario: FragmentScenario<HomeFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer<HomeFragment>()
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun getViewModel() {
    }


    @Test
    fun observe() {
    }

    @Test
    fun openInviteMemberScreen() {
        onView(withId(R.id.button_first)).perform(click())
    }
}