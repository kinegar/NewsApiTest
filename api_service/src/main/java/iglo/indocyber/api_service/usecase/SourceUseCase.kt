package iglo.indocyber.api_service.usecase

import iglo.indocyber.api_service.service.SourceService
import iglo.indocyber.common.app_response.AppResponse
import iglo.indocyber.common.entity.source.SourceResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class SourceUseCase(private val sourceService: SourceService) {
    operator fun invoke(category: String?) = flow {
        emit(AppResponse.loading())
        val data = sourceService.getSource(category = category)
        if (data.isSuccessful) {
            data.body()?.let {
                emit(AppResponse.success(it))
            } ?: run {
                emit(
                    AppResponse.failure<SourceResponse>(
                        Exception("Data dari backend null")
                    )
                )
            }
        } else {
            emit(AppResponse.failure(Exception(data.message())))
        }
    }
}