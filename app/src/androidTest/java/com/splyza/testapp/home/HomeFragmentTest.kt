package com.splyza.testapp.home

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.splyza.testapp.DataBindingIdlingResource
import com.splyza.testapp.R
import com.splyza.testapp.launchFragmentInHiltContainer
import com.splyza.testapp.presentation.home.HomeFragment
import com.splyza.testapp.utils.EspressoIdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


//@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@MediumTest
@HiltAndroidTest
class HomeFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun init() {
        // Populate @Inject fields in test class
        hiltRule.inject()
    }


    /**
     * Idling resources tell Espresso that the app is idle or busy. This is needed when operations
     * are not scheduled in the main Looper (for example when executed on a different thread).
     */
    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }


    @Test
    fun testLaunchFragment() {
        // Create a mock NavController
        val navController = mock(NavController::class.java)
//        val fragmentScenario = FragmentScenario.launch(HomeFragment::class.java)
//        dataBindingIdlingResource.monitorFragment(fragmentScenario)
        launchFragmentInHiltContainer<HomeFragment>() {
            //    Setting the navigation graph for the NavController
            navController.setGraph(R.navigation.nav_graph)

            //Sets the NavigationController for the specified View
            Navigation.setViewNavController(requireView(), navController)


        }
        // Verify that performing a click changes the NavController’s state
        onView(ViewMatchers.withId(R.id.button_first))
            .perform(ViewActions.click())

        verify(navController).navigate(
            R.id.action_homeFragment_to_inviteMemberFragment
        )

    }
}