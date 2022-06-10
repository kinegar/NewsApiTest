package iglo.indocyber.newsapitest.fragment.home

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import iglo.indocyber.common.app_response.AppResponse
import iglo.indocyber.common.ext.hide
import iglo.indocyber.common.ext.visible
import iglo.indocyber.common.mvvm.BaseFragment
import iglo.indocyber.newsapitest.R
import iglo.indocyber.newsapitest.databinding.HomeLayoutBinding
import iglo.indocyber.newsapitest.view_model.HomeViewModel

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, HomeLayoutBinding>() {
    override val vm: HomeViewModel by viewModels()
    override val layoutResourceId: Int= R.layout.home_layout
    val categoryAdapter = CategoryAdapter(::selectCategory,::getSelectedCategory)
    val sourceAdapter = SourceAdapter(::selectSource)


    override fun initBinding(binding: HomeLayoutBinding) {
        binding.recyclerCategory.adapter = categoryAdapter
        binding.recyclerSource.adapter = sourceAdapter
        binding.searchBar.addTextChangedListener {
            vm.searchValue.value = it.toString()
            if (vm.searchValue.value.isNullOrEmpty()){
                binding.recyclerCategory.visible()
            }else{
                binding.recyclerCategory.hide()
            }
        }
        binding.retryButton.setOnClickListener {
            vm.getSourceList()
        }
        observeLiveData()
    }



}