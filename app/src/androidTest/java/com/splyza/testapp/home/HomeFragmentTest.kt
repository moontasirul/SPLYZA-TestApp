package com.splyza.testapp.home

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.splyza.testapp.R
import com.splyza.testapp.launchFragmentInHiltContainer
import com.splyza.testapp.presentation.home.AppFragmentFactory
import com.splyza.testapp.presentation.home.HomeFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import javax.inject.Inject


//@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@MediumTest
@HiltAndroidTest
class HomeFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: AppFragmentFactory


    @Before
    fun init() {
        // Populate @Inject fields in test class
        hiltRule.inject()
    }

    @Test
    fun testLaunchFragment() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<HomeFragment>(fragmentFactory = fragmentFactory) {
            //  navController.setGraph(R.navigation.nav_graph)
            //Sets the NavigationController for the specified View
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(ViewMatchers.withId(R.id.button_first)).perform(ViewActions.click())

        verify(navController).navigate(
            R.id.action_homeFragment_to_inviteMemberFragment
        )

    }



}