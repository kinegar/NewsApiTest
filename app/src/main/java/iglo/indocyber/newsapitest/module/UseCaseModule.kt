package iglo.indocyber.newsapitest.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import iglo.indocyber.api_service.service.EverythingService
import iglo.indocyber.api_service.service.SourceService
import iglo.indocyber.api_service.usecase.CategoryUseCase
import iglo.indocyber.api_service.usecase.EverythingUseCase
import iglo.indocyber.api_service.usecase.SourceUseCase

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    fun provideCategoryUseCase() = CategoryUseCase()

    @Provides
    fun provideSourceUseCase(sourceService: SourceService) = SourceUseCase(sourceService)

    @Provides
    fun provideEverythingUseCase(everythingService: EverythingService) = EverythingUseCase(everythingService)
}