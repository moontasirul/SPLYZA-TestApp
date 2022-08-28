package com.splyza.testapp.presentation.inviteMember

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.splyza.testapp.R
import com.splyza.testapp.core.base.BaseFragment
import com.splyza.testapp.databinding.FragmentInviteMemberBinding
import com.splyza.testapp.presentation.MainActivity
import com.splyza.testapp.presentation.home.HomeViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class InviteMemberFragment :
    BaseFragment<FragmentInviteMemberBinding, InviteMemberViewModel>(FragmentInviteMemberBinding::inflate),
    IInviteMemberNavigator {

    override val viewModel: InviteMemberViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        (activity as MainActivity?)?.viewModel?.isBackButtonShow?.value = true
        (activity as MainActivity?)?.viewModel?.titleText?.value =
            requireActivity().resources.getString(R.string.title_text_invite_member)

    }


    override fun observe() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            inviteMemberViewModel = viewModel
        }
        viewModel.setNavigator(this)
    }


    override fun onOpenShareQRCode() {
        findNavController().navigate(R.id.action_inviteMemberFragment_to_qrCodeFragment)
    }
}