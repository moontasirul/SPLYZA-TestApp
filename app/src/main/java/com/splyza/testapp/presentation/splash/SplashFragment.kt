package com.splyza.testapp.presentation.splash

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.splyza.testapp.R
import com.splyza.testapp.core.base.BaseFragment
import com.splyza.testapp.databinding.FragmentSplashBinding
import com.splyza.testapp.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class SplashFragment @Inject constructor() :
    BaseFragment<FragmentSplashBinding, SplashViewModel>(FragmentSplashBinding::inflate),
    ISplashNavigator {

    override val viewModel: SplashViewModel by viewModels()

    override fun observe() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            splashViewModel = viewModel
        }
        viewModel.setNavigator(this)
    }

    override fun setupUI() {
        super.setupUI()
        if (activity is MainActivity) {
            (requireActivity() as MainActivity?)?.viewModel?.isToolbarShow?.value = false
            (requireActivity() as MainActivity?)?.viewModel?.isBackButtonShow?.value = false
        }
        viewModel.splashCount()
    }

    override fun openHomeScreen() {
        findNavController()
            .navigate(
                R.id.action_splashFragment_to_homeFragment,
                null,
                NavOptions.Builder()
                    .setPopUpTo(
                        R.id.splashFragment,
                        true
                    ).build()
            )
    }
}