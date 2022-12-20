package br.com.fioalpha.core.network.model

data class CharacterDataWrapperResponse(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val data: CharacterDataContainerResponse?,
    val etag: String?
)

data class CharacterDataContainerResponse(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val result: List<CharacterResponse?>
)

data class CharacterResponse(
    val id: Int?,
    val name: String?,
    val description: String?,
    val resourceId: String?,
    val urls: List<UrlResponse?>,
    val thumbnail: ImageResponse?,
    val comics: ComicListResponse?,
    val stories: StoryListResponse?,
    val events: EventListResponse?,
    val series: SeriesListResponse?

)

data class UrlResponse(
    val type: String?,
    val url: String?,
)

data class ImageResponse(
    val path: String?,
    val extension: String?,
)

data class ComicSummaryResponse(
    val resourceURI: String?,
    val name: String?,
)

data class ComicListResponse(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<ComicSummaryResponse?>?
)

data class StoryListResponse(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<StorySummaryResponse?>?,
)

data class StorySummaryResponse(
    val resourceURI: String?,
    val name: String?,
    val type: String?
)

data class EventListResponse(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<EventSummaryResponse?>?
)

data class EventSummaryResponse(
    val resourceURI: String?,
    val name: String?
)

data class SeriesListResponse(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<SeriesSummary?>?
)

data class SeriesSummary(
    val resourceURI: String?,
    val name: String?
)
