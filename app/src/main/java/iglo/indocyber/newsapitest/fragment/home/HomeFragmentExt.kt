package iglo.indocyber.newsapitest.fragment.home

import iglo.indocyber.common.app_response.AppResponse
import iglo.indocyber.common.entity.source.Source
import iglo.indocyber.common.ext.hide
import iglo.indocyber.common.ext.visible

fun HomeFragment.observeLiveData() = with(vm) {
    dataCategory.observe(this@observeLiveData) {
        categoryAdapter.submitData(it)
        binding.searchBar.text?.clear()
    }
    dataSource.observe(this@observeLiveData) {
        if (it is AppResponse.AppResponseSuccess) {
            sourceAdapter.submitData(it.data.sources)
            binding.compat2.visible()
            binding.compatLoading.hide()
            binding.compatError.hide()
            if (sourceAdapter.itemCount==0){
                binding.compatEmpty.visible()
            }else{
                binding.compatEmpty.hide()
            }
        }else if(it is AppResponse.AppResponseError){
            binding.compat2.hide()
            binding.compatEmpty.hide()
            binding.compatLoading.hide()
            binding.compatError.visible()
        }else if (it is AppResponse.AppResponseLoading){
            binding.compat2.hide()
            binding.compatEmpty.hide()
            binding.compatLoading.visible()
            binding.compatError.hide()
        }
    }
    selectedCategory.observe(this@observeLiveData) {
        vm.getSourceList()
    }
    searchValue.observe(this@observeLiveData){
        val data = vm.filter()
        if (data.isEmpty()&&vm.dataSource.value is AppResponse.AppResponseSuccess){
            binding.compatEmpty.visible()
        }else{
            binding.compatEmpty.hide()
        }
        sourceAdapter.submitData(data)
    }

}

fun HomeFragment.selectCategory(category: String, position: Int) {
    if (vm.selectedCategory.value == category) {
        vm.selectedCategory.value = null
    } else {
        vm.selectedCategory.value = category
    }
    vm.lastSelectedCategory.value?.let {
        if(it!=position){
            categoryAdapter.notifyItemChanged(it)
        }
    }
    categoryAdapter.notifyItemChanged(position)
    vm.lastSelectedCategory.value=position
}

fun HomeFragment.getSelectedCategory() = vm.selectedCategory.value

fun HomeFragment.selectSource(source: Source){
    vm.navigationtEvent.postValue(HomeFragmentDirections.toEverything(source))
}


