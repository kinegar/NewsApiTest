package iglo.indocyber.api_service.service

import iglo.indocyber.api_service.ConstantService.API_KEY
import iglo.indocyber.common.entity.source.SourceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SourceService {
    @GET("top-headlines/sources")
    suspend fun getSource(
        @Query("apiKey") apiKey:String = API_KEY,
        @Query("category") category: String?
    ): Response<SourceResponse>
}