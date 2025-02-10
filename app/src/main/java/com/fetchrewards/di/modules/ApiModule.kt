package com.fetchrewards.di.modules

import com.fetchrewards.data.remote.apis.FetchRewardsApis
import org.koin.dsl.module
import retrofit2.Retrofit

object ApiModule {
    val modules =
        module {
            single<FetchRewardsApis> { (get() as Retrofit).create(FetchRewardsApis::class.java) }
        }
}
