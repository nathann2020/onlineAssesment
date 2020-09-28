package com.workday.codingtask.di

import com.workday.codingtask.model.ImagesDataSource
import com.workday.codingtask.model.ImagesRepository
import com.workday.codingtask.viewmodel.ImagesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @CreatedBy Nathan N
 *
 *         Author Email: Nathan.nakhjavani@gmail.com
 *         Created on: 2020-09-27
 */
val appModule = module {
    single<ImagesDataSource> { ImagesRepository() }

    viewModel { ImagesViewModel(get()) }
}