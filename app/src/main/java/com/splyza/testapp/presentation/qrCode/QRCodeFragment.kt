package com.splyza.testapp.presentation.qrCode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.splyza.testapp.R
import com.splyza.testapp.core.base.BaseFragment
import com.splyza.testapp.databinding.FragmentQrCodeBinding
import com.splyza.testapp.presentation.inviteMember.InviteMemberViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class QRCodeFragment :
    BaseFragment<FragmentQrCodeBinding, QRCodeViewModel>(FragmentQrCodeBinding::inflate),
    IQRCodeNavigator {


    override val viewModel: QRCodeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_inviteMemberFragment_to_homeFragment)
        }
    }


    override fun observe() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            qrCodeViewModel = viewModel
        }
        viewModel.setNavigator(this)
    }
}