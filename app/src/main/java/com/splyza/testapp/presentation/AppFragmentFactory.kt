package com.splyza.testapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.splyza.testapp.presentation.home.HomeFragment
import com.splyza.testapp.presentation.inviteMember.InviteMemberFragment
import com.splyza.testapp.presentation.qrCode.QRCodeFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AppFragmentFactory
@Inject
constructor(

) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {

            HomeFragment::class.java.name -> {
                val fragment = HomeFragment()
                fragment
            }

            InviteMemberFragment::class.java.name -> {
                val fragment = InviteMemberFragment()
                fragment
            }

            QRCodeFragment::class.java.name -> {
                val fragment = QRCodeFragment()
                fragment
            }

            else -> super.instantiate(classLoader, className)
        }
    }
}