package com.splyza.testapp.inviteMember

import androidx.navigation.NavController
import androidx.test.filters.LargeTest
import com.splyza.testapp.launchFragmentInHiltContainer
import com.splyza.testapp.presentation.inviteMember.InviteMemberFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@LargeTest
@HiltAndroidTest
class InviteMemberFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        // Populate @Inject fields in test class
        hiltRule.inject()
    }

    @Test
    fun clickBackTOHomeFragment() {
        val navController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<InviteMemberFragment>() {
            //Navigation.setViewNavController(view!!, navController)
        }

        //  pressBack()
        // verify(navController).popBackStack()
    }
}