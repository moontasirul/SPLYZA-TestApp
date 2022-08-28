package com.splyza.testapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.splyza.testapp.R
import com.splyza.testapp.core.base.BaseFragment
import com.splyza.testapp.databinding.FragmentHomeBinding
import com.splyza.testapp.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate),
    IHomeNavigator {

    override val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.viewModel?.isBackButtonShow?.value = false
        (activity as MainActivity?)?.viewModel?.titleText?.value =
            requireActivity().resources.getString(R.string.title_text_home)

    }

    override fun observe() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            homeViewModel = viewModel
        }
        viewModel.setNavigator(this)
    }

    override fun openInviteMemberScreen() {
        findNavController().navigate(R.id.action_homeFragment_to_inviteMemberFragment)
    }
}