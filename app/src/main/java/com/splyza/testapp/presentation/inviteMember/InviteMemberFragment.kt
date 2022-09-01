package com.splyza.testapp.presentation.inviteMember


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.splyza.testapp.R
import com.splyza.testapp.core.base.BaseFragment
import com.splyza.testapp.databinding.FragmentInviteMemberBinding
import com.splyza.testapp.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class InviteMemberFragment :
    BaseFragment<FragmentInviteMemberBinding, InviteMemberViewModel>(FragmentInviteMemberBinding::inflate),
    IInviteMemberNavigator, AdapterView.OnItemSelectedListener {

    companion object {
        const val TAG_INVITATION_URL = "invitation_url"
    }


    override val viewModel: InviteMemberViewModel by viewModels()

    override fun setupUI() {
        super.setupUI()
        (activity as MainActivity?)?.viewModel?.isBackButtonShow?.value = true
        (activity as MainActivity?)?.viewModel?.titleText?.value =
            requireActivity().resources.getString(R.string.title_text_invite_member)



        viewModel.checkSupporterLimit()
        viewModel.permissionListHideShow()

        // initialize an array adapter
        val aa: ArrayAdapter<Any> = object : ArrayAdapter<Any>(
            requireActivity(),
            R.layout.spinner_item_selected,
            viewModel.teamMember.toArray()
        ) {
//            override fun isEnabled(position: Int): Boolean {
//                return position != 1
//            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view1 = super.getDropDownView(position, convertView, parent)
                val tv = view1 as TextView

                if (position != 0 && position != 1 && position != 2 || !viewModel.isMemberFull) {
                    if (position == 3 && !viewModel.isMemberFull && viewModel.isSupporterFull) {
                        tv.setTextColor(Color.GRAY)
                    } else {
                        tv.setTextColor(
                            requireActivity().resources.getColor(
                                R.color.teal_700,
                                null
                            )
                        )
                    }
                } else {
                    tv.setTextColor(Color.GRAY)
                }



                return view1
            }
        }

        aa.setDropDownViewResource(R.layout.spinner_center_aligned)

        with(binding.invitePermissionSp)
        {
            adapter = aa
            setSelection(0, false)
            viewModel.prepareUserRole(selectedItem.toString())
            lifecycleScope.launch {
                viewModel.setInvitationRole(viewModel.prepareUserRole(selectedItem.toString()))
            }
            onItemSelectedListener = this@InviteMemberFragment
        }


        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
                viewModel.currentMembers.value = ""
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }


    override fun observe() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            inviteMemberViewModel = viewModel
        }
        viewModel.setNavigator(this)

    }


    override fun onOpenShareQRCode() {
        val bundle = Bundle()
        bundle.putString(TAG_INVITATION_URL, viewModel.invitationURL.value)
        findNavController().navigate(R.id.action_inviteMemberFragment_to_qrCodeFragment, bundle)
    }


    override fun onNothingSelected(parent: AdapterView<*>?) {
        showToast(message = "Nothing selected")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        if (position != 0 && position != 1 && position != 2 || !viewModel.isMemberFull) {
            if (position == 3 && !viewModel.isMemberFull && viewModel.isSupporterFull) {
                showToast(message = "Team member: ${viewModel.teamMember[position]}")
            } else {
                // viewModel.prepareUserRole(viewModel.teamMember[position])
                lifecycleScope.launch {
                    viewModel.setInvitationRole(viewModel.prepareUserRole(viewModel.teamMember[position]))
                }
            }
        } else {
            showToast(message = "Team member: ${viewModel.teamMember[position]}")
        }
    }

    private fun showToast(
        context: Context = requireActivity(),

        message: String,
        duration: Int = Toast.LENGTH_LONG
    ) {
        Toast.makeText(context, message, duration).show()
    }


    override fun copyLink() {
        val clipboardManager =
            binding.btnCopyLink.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText(TAG_INVITATION_URL, viewModel.invitationURL.value)
        clipboardManager.setPrimaryClip(clipData)

        Toast.makeText(
            binding.btnCopyLink.context,
            clipboardManager.primaryClip?.getItemAt(0)?.text,
            Toast.LENGTH_LONG
        ).show()
    }
}