package iglo.indocyber.api_service.data_source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import iglo.indocyber.api_service.service.EverythingService
import iglo.indocyber.common.entity.everything.Article

class EverythingDataSource(
    val everythingService: EverythingService,
    val q: String?,
    val sources: String,
    val pageSize: Int
) : PagingSource<Int, Article>(){
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try{
            val result = everythingService.getEverything(
                q = q, sources = sources,page = params.key?:1,pageSize = params.loadSize
            )
            val page=params.key?: 1
            return if (result.isSuccessful){
                result.body()?.let {
                    val totalPage= it.totalResults/pageSize
                LoadResult.Page(data= it.articles, if(page ==1)null else page-1 , if (page <totalPage) page+1 else null )
                }?: LoadResult.Error(Exception("invalid Data"))
            }else{
                if(result.code()==426){
                    LoadResult.Page(arrayListOf(),if(page ==1)null else page-1,null)
                }
                LoadResult.Error(Exception("invalid Code"))
//                LoadResult.Page(Article,null,null)
            }
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}

object EverythingPager{
    fun createPager(
        pageSize: Int,
        everythingService: EverythingService,
        q: String?,
        sources: String
    ): Pager<Int,Article> = Pager(
        config = PagingConfig(pageSize),
        pagingSourceFactory = {
            EverythingDataSource(everythingService,q,sources,pageSize)
        }
    )
}