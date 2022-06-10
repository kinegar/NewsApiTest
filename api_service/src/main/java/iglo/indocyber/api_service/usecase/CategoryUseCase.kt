package iglo.indocyber.api_service.usecase

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class CategoryUseCase {
    operator fun invoke()= flow{
        val categoryList=
            arrayListOf(
                "business","entertainment","general","health","science","sports","technology"
            )
        emit(categoryList)
    }
}