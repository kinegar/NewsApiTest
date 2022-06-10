package iglo.indocyber.newsapitest.fragment.article_detail

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import iglo.indocyber.common.mvvm.BaseFragment
import iglo.indocyber.newsapitest.R
import iglo.indocyber.newsapitest.databinding.ArticleDetailLayoutBinding
import iglo.indocyber.newsapitest.view_model.ArticleDetailViewModel

class ArticleDetailFragment: BaseFragment<ArticleDetailViewModel,ArticleDetailLayoutBinding>() {
    override val vm: ArticleDetailViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.article_detail_layout
    val args:ArticleDetailFragmentArgs by navArgs()
    override fun initBinding(binding: ArticleDetailLayoutBinding) {
        loadPage()
    }

    fun loadPage(){
        try {
            binding.webView.loadUrl(args.link)
        } catch (e: Exception){
            Log.e("ArticleDetailWebView","WebViewError",e)
        }
    }
}