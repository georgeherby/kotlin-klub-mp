package sample

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.Url
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.UnstableDefault
import sample.domain.RatingResponse

internal expect val ApplicationDispatcher: CoroutineDispatcher

class ApplicationApi {
    @UnstableDefault
    private val client = HttpClient() {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json.nonstrict)
        }
    }

    var address = Url("http://localhost:8070/jobs/android_rating")

    @UnstableDefault
    fun rating(callback: (RatingResponse) -> Unit) {
        GlobalScope.apply {
            launch(ApplicationDispatcher) {
                val response = client.get<RatingResponse>() {
                    url(this@ApplicationApi.address.toString())
                }
                callback(response)
            }
        }
    }
}