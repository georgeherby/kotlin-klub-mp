package sample.domain

import kotlinx.serialization.*

@Serializable
class RatingResponse(
    var code: String? = null,
    var success: Boolean? = null,
    var content: Rating? = null
)