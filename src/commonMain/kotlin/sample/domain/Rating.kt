package sample.domain

import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    var id: Int? = null,
    var date: String? = null,
    var rating: Float? = null
)