package iglo.indocyber.newsapitest.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import iglo.indocyber.api_service.usecase.CategoryUseCase
import iglo.indocyber.api_service.usecase.SourceUseCase
import iglo.indocyber.common.app_response.AppResponse
import iglo.indocyber.common.entity.source.Source
import iglo.indocyber.common.entity.source.SourceResponse
import iglo.indocyber.common.mvvm.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val categoryUseCase: CategoryUseCase,
    private val sourceUseCase: SourceUseCase
) : BaseViewModel(application) {
    val dataCategory = MutableLiveData<List<String>>()
    val dataSource = MutableLiveData<AppResponse<SourceResponse>>()
//    val selectedSource = MutableLiveData<List<Source>>()
    val selectedCategory = MutableLiveData<String>()
    val lastSelectedCategory = MutableLiveData<Int>()
    val searchValue = MutableLiveData<String>()

    init {
        getCategoryList()
        getSourceList()
    }

    fun getCategoryList(){
        viewModelScope.launch {
            categoryUseCase.invoke().collect {
                dataCategory.postValue(it)
            }
        }
    }
    fun getSourceList(){
        viewModelScope.launch {
            sourceUseCase.invoke(selectedCategory.value).collect {
                dataSource.postValue(it)
            }
        }
    }
    fun filter():List<Source>{
        return dataSource.value?.let {
            if (it is AppResponse.AppResponseSuccess){
                it.data?.sources.orEmpty().filter { it2->
                    it2.name.contains(searchValue.value.toString(),true)
                }
            }else arrayListOf()
        }?: run{
            arrayListOf()
        }
    }
}