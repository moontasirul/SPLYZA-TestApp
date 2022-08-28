package com.splyza.testapp.presentation.qrCode

import com.splyza.testapp.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class QRCodeViewModel @Inject constructor() : BaseViewModel<IQRCodeNavigator>() {

}