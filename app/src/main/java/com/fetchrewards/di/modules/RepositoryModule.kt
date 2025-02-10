package com.fetchrewards.di.modules

import com.fetchrewards.data.remote.apis.FetchRewardsApis
import com.fetchrewards.data.repositoryimpl.DataRepositoryImpl
import com.fetchrewards.domain.repositories.DataRepository
import org.koin.dsl.module

object RepositoryModule {
    val modules =
        module {
            single<DataRepository> { DataRepositoryImpl(get() as FetchRewardsApis) }
        }
}
