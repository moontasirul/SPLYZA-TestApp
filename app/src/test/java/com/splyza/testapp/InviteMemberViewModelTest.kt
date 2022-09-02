package com.splyza.testapp

import com.imranhsn.monstarlab.MainCoroutineRule
import com.splyza.testapp.data.model.TeamResponse
import com.splyza.testapp.domain.useCase.TeamInvitationInfo
import com.splyza.testapp.presentation.inviteMember.InviteMemberViewModel
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
class InviteMemberViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var team: TeamInvitationInfo
    private lateinit var viewModel: InviteMemberViewModel
    private var teamInfo = mockk<TeamResponse>()

    @Before
    fun setup() {
        team = mockk<TeamInvitationInfo>()
        viewModel = InviteMemberViewModel(team)
    }

}