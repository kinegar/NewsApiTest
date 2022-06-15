package iglo.indocyber.common.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavDirections
import id.indocyber.ui.DialogData
import iglo.indocyber.common.R
import iglo.indocyber.common.app_response.AppResponse
import iglo.indocyber.common.ext.SingleLiveEvent

open class BaseViewModel(application: Application): AndroidViewModel(application) {
    val navigationtEvent = SingleLiveEvent<NavDirections>()
    var parent:BaseViewModel? = null




}