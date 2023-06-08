package com.eem.domain.model.tvshow

enum class Filter(val filter: String) {
    TOP_RATED("top_rated"),
    POPULAR("popular"),
    ON_TV("on_the_air"),
    AIRING_TODAY("airing_today");

    companion object {
        fun fromString(filterString: String): Filter? {
            return values().find { it.filter == filterString }
        }
    }
}
