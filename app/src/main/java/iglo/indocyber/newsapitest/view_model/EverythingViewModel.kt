package iglo.indocyber.newsapitest.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import iglo.indocyber.api_service.usecase.EverythingUseCase
import iglo.indocyber.common.app_response.AppResponse
import iglo.indocyber.common.entity.everything.Article
import iglo.indocyber.common.entity.everything.EverythingResponse
import iglo.indocyber.common.entity.source.Source
import iglo.indocyber.common.ext.SingleLiveEvent
import iglo.indocyber.common.mvvm.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EverythingViewModel @Inject constructor(
    application: Application,
    private val everythingUseCase: EverythingUseCase
) : BaseViewModel(application) {
    val data = MutableLiveData<PagingData<Article>>()
    val selectedSource = SingleLiveEvent<Source>()
    val searchBar = MutableLiveData<String>()

    fun getEverythingList() {
        viewModelScope.launch {
            selectedSource.value?.let {
                everythingUseCase.invoke(
                    it,
                    searchBar.value
                ).collect {
                    data.postValue(it)
                }
            }
        }
    }
}