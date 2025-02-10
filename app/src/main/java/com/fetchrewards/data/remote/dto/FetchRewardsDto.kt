package com.fetchrewards.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FetchRewardsDto(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "listId") val listId: Int? = null,
    @Json(name = "name") val name: String? = null,
) {
    fun hasValidData() = id != null && listId != null && !name.isNullOrEmpty()
}
