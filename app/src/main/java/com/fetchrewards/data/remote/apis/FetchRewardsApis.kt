package com.fetchrewards.data.remote.apis

import com.fetchrewards.data.remote.dto.FetchRewardsDto
import retrofit2.http.GET

interface FetchRewardsApis {
    @GET("hiring.json")
    suspend fun fetchData(): List<FetchRewardsDto>
}
