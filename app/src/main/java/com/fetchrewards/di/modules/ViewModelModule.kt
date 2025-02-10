package com.fetchrewards.di.modules

import com.fetchrewards.presentation.viewmodel.FetchRewardsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val modules =
        module {
            viewModel {
                FetchRewardsViewModel(get())
            }
        }
}
