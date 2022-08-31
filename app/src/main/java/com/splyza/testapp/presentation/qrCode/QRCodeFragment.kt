package com.splyza.testapp.presentation.qrCode

import android.graphics.Bitmap
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.splyza.testapp.R
import com.splyza.testapp.core.base.BaseFragment
import com.splyza.testapp.databinding.FragmentQrCodeBinding
import com.splyza.testapp.presentation.MainActivity
import com.splyza.testapp.utils.QRcodeManager
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class QRCodeFragment :
    BaseFragment<FragmentQrCodeBinding, QRCodeViewModel>(FragmentQrCodeBinding::inflate),
    IQRCodeNavigator {

    companion object {
        const val TAG_INVITATION_URL = "invitation_url"
    }

    override val viewModel: QRCodeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity?)?.viewModel?.isBackButtonShow?.value = true
        (activity as MainActivity?)?.viewModel?.titleText?.value =
            getString(R.string.title_text_my_qr_code)


        val qrCode = arguments?.getString(TAG_INVITATION_URL)
        val bitmap: Bitmap? = QRcodeManager.generateBarcode(
            qrCode, dpToPixel(
                500.0f
            ).toInt()
        )
        binding.ivQrCode.setImageBitmap(bitmap)
    }


    override fun observe() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            qrCodeViewModel = viewModel
        }
        viewModel.setNavigator(this)
    }


    /**
     * This method convert dp to pixel
     *
     * @param dp
     * @param context
     * @return
     */
    private fun dpToPixel(dp: Float): Float {
        val resources = requireContext().resources
        val metrics = resources.displayMetrics
        return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}