package iglo.indocyber.api_service.service


import iglo.indocyber.api_service.ConstantService.API_KEY
import iglo.indocyber.api_service.ConstantService.BASE_PAGE_SIZE
import iglo.indocyber.common.entity.everything.EverythingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EverythingService {
    @GET("everything")
    suspend fun getEverything(
        @Query("apiKey") apiKey:String = API_KEY,
        @Query("q") q:String?=null,
        @Query("sources") sources:String,
        @Query("pageSize") pageSize:Int,
        @Query("page") page:Int = 1
    ):Response<EverythingResponse>
}