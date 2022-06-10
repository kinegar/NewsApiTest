package iglo.indocyber.newsapitest.fragment.everything

import iglo.indocyber.common.entity.everything.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun EverythingFragment.observeLiveData() = with(vm){
    data.observe(this@observeLiveData){
        CoroutineScope(Dispatchers.IO).launch {
            adapter.submitData(it)
        }
    }
    selectedSource.observe(this@observeLiveData){
        getEverythingList()
    }


}

fun EverythingFragment.selectArticle(article: Article){
    vm.navigationtEvent.postValue(EverythingFragmentDirections.toArticleDetail(article.url))
}