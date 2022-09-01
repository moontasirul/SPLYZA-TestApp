package com.splyza.testapp.inviteMember

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import com.splyza.testapp.R
import com.splyza.testapp.launchFragmentInHiltContainer
import com.splyza.testapp.presentation.AppFragmentFactory
import com.splyza.testapp.presentation.inviteMember.InviteMemberFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import javax.inject.Inject

@ExperimentalCoroutinesApi
@LargeTest
@HiltAndroidTest
class InviteMemberFragmentTest {


    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: AppFragmentFactory

    @Before
    fun init() {
        // Populate @Inject fields in test class
        hiltRule.inject()
        // viewModel = mock(InviteMemberViewModel::class.java)

    }

    @Test
    fun fragmentVisibilityTest() {
        launchFragmentInHiltContainer<InviteMemberFragment>(fragmentFactory = fragmentFactory) {

        }
        onView(withId(R.id.invite_member_root_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }


    @Test
    fun clickShareButtonTest() {
        val navController = mock(NavController::class.java)
        val arguments = Bundle().also {
            it.putString("invitation_url", "https://www.google.com/manager")
        }
        launchFragmentInHiltContainer<InviteMemberFragment>(
            fragmentFactory = fragmentFactory,
            fragmentArgs = arguments
        ) {
            //navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.btn_qr_code_share)).perform(ViewActions.click())

        verify(navController).navigate(
            R.id.action_inviteMemberFragment_to_qrCodeFragment,
            arguments
        )
    }


    @Test
    fun ui_text_view_test() {
        launchFragmentInHiltContainer<InviteMemberFragment>(fragmentFactory = fragmentFactory)
        onView(withId(R.id.current_member_tv)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.current_supporters_tv)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.member_limit_tv)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.supporters_limit_tv)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}