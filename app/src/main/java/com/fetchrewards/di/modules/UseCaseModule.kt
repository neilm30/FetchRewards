package com.fetchrewards.di.modules

import com.fetchrewards.data.usecasesimpl.DataUseCaseImpl
import com.fetchrewards.domain.repositories.DataRepository
import com.fetchrewards.domain.usecases.DataRepositoryUseCase
import org.koin.dsl.module

object UseCaseModule {
    val modules =
        module {
            single<DataRepositoryUseCase> { DataUseCaseImpl(get() as DataRepository) }
        }
}
