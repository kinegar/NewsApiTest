package iglo.indocyber.newsapitest.fragment.everything

import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import iglo.indocyber.common.ext.hide
import iglo.indocyber.common.ext.visible
import iglo.indocyber.common.mvvm.BaseFragment
import iglo.indocyber.newsapitest.R
import iglo.indocyber.newsapitest.databinding.EverythingLayoutBinding
import iglo.indocyber.newsapitest.view_model.EverythingViewModel

@AndroidEntryPoint
class EverythingFragment: BaseFragment<EverythingViewModel, EverythingLayoutBinding>() {
    override val vm: EverythingViewModel by viewModels()
    override val layoutResourceId: Int= R.layout.everything_layout
    val adapter = EverythingPagingAdapter(::selectArticle)
    val loadStateAdapter = EverythingPagingStateAdapter { vm.getEverythingList() }
    val args:EverythingFragmentArgs by navArgs()

    override fun initBinding(binding: EverythingLayoutBinding) {
        adapter.addLoadStateListener {
            if (adapter.itemCount == 0){
                if (it.refresh is LoadState.Error){
                    binding.emptyConstraint.hide()
                    binding.errorConstraint.visible()
                    binding.searchBar.hide()
                    binding.recycler.hide()
                    binding.loadingConstraint.hide()
                }else if (it.refresh is LoadState.Loading){
                    binding.emptyConstraint.hide()
                    binding.errorConstraint.hide()
                    binding.searchBar.hide()
                    binding.recycler.hide()
                    binding.loadingConstraint.visible()
                }else if (it.refresh is LoadState.NotLoading){
                    binding.emptyConstraint.visible()
                    binding.errorConstraint.hide()
                    binding.searchBar.visible()
                    binding.recycler.hide()
                    binding.loadingConstraint.hide()
                }
            }else{
                binding.emptyConstraint.hide()
                binding.errorConstraint.hide()
                binding.searchBar.visible()
                binding.recycler.visible()
                binding.loadingConstraint.hide()
            }
        }
        binding.recycler.adapter=adapter.withLoadStateFooter(loadStateAdapter)
        binding.retryButton.setOnClickListener {
            vm.getEverythingList()
            adapter.loadStateFlow
        }
        vm.selectedSource.postValue(args.source)
        observeLiveData()
        binding.searchBar.setOnCloseListener {
            vm.searchBar.value = null
            vm.getEverythingList()
            false
        }
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                vm.searchBar.value = query
                vm.getEverythingList()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }
}