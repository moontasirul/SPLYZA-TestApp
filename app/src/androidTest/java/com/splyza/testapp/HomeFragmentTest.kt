package com.splyza.testapp

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.splyza.testapp.presentation.home.HomeFragment
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HomeFragmentTest {


    @Test
    fun testNav() {
        //Getting the NavController for test
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        //Launches the Fragment in isolation
        launchFragmentInContainer<HomeFragment>().onFragment { fragment ->
            //Setting the navigation graph for the NavController
            navController.setGraph(R.navigation.nav_graph)

            //Sets the NavigationController for the specified View
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        // Verify that performing a click changes the NavController’s state
        onView(ViewMatchers.withId(R.id.button_first))
            .perform(ViewActions.click())

        assertEquals(
            navController.currentDestination?.id,
            R.id.inviteMemberFragment
        )
    }
}