package com.splyza.testapp.presentation.inviteMember

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
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
    IInviteMemberNavigator, AdapterView.OnItemSelectedListener {

    override val viewModel: InviteMemberViewModel by viewModels()
    var teamMember = arrayOf("Coach", "Player Coach", "Player", "Supporter")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        (activity as MainActivity?)?.viewModel?.isBackButtonShow?.value = true
        (activity as MainActivity?)?.viewModel?.titleText?.value =
            requireActivity().resources.getString(R.string.title_text_invite_member)

        val aa = ArrayAdapter(requireActivity(), R.layout.spinner_item_selected, teamMember)
        aa.setDropDownViewResource(R.layout.spinner_center_aligned)

        with(binding.invitePermissionSp)
        {
            adapter = aa
            setSelection(0, false)
            onItemSelectedListener = this@InviteMemberFragment
            view.textAlignment = View.TEXT_ALIGNMENT_TEXT_START

        }
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


    override fun onNothingSelected(parent: AdapterView<*>?) {
        showToast(message = "Nothing selected")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        showToast(message = "Position:${position} and team member: ${teamMember[position]}")
    }

    private fun showToast(
        context: Context = requireActivity(),
        message: String,
        duration: Int = Toast.LENGTH_LONG
    ) {
        Toast.makeText(context, message, duration).show()
    }
}