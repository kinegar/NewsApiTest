package iglo.indocyber.api_service.usecase

import iglo.indocyber.api_service.ConstantService.BASE_PAGE_SIZE
import iglo.indocyber.api_service.data_source.EverythingPager
import iglo.indocyber.api_service.service.EverythingService
import iglo.indocyber.common.app_response.AppResponse
import iglo.indocyber.common.entity.everything.EverythingResponse
import iglo.indocyber.common.entity.source.Source
import iglo.indocyber.common.entity.source.SourceResponse
import kotlinx.coroutines.flow.flow

class EverythingUseCase(private val everythingService: EverythingService) {
    operator fun invoke(source: Source, q: String?) =
        EverythingPager.createPager(BASE_PAGE_SIZE,everythingService,q,source.id).flow
}