package com.splyza.testapp.presentation.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.splyza.testapp.R
import com.splyza.testapp.core.base.BaseFragment
import com.splyza.testapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate),
    IHomeNavigator {

    override val viewModel: HomeViewModel by viewModels()


    override fun observe() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            homeViewModel = viewModel
        }
        viewModel.setNavigator(this)
    }

    override fun setupUI() {
        super.setupUI()
        //  (requireActivity() as MainActivity?)?.viewModel?.isBackButtonShow?.value = false
        //  (requireActivity() as MainActivity?)?.viewModel?.titleText?.value = requireActivity().resources.getString(R.string.title_text_home)

    }

    override fun openInviteMemberScreen() {
        findNavController().navigate(R.id.action_homeFragment_to_inviteMemberFragment)
    }
}