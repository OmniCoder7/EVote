package com.ethyllium.data.di

import com.ethyllium.data.remote.createClient
import com.ethyllium.data.service.AuthServiceImpl
import com.ethyllium.data.service.DataStoreService
import com.ethyllium.data.util.CryptoStorage
import com.ethyllium.domain.service.AuthService
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single<AuthService> { AuthServiceImpl(get(), get()) }
    single { createClient() }
    factoryOf(::DataStoreService)
    factoryOf(::CryptoStorage)
}