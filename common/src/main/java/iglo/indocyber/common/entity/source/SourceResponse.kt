package iglo.indocyber.common.entity.source

data class SourceResponse(
    val sources: List<Source>,
    val status: String
)