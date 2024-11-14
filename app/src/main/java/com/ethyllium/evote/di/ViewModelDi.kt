package com.ethyllium.evote.di

import com.ethyllium.evote.EVoteViewModel
import com.ethyllium.home.HomeViewModel
import com.ethyllium.invite.InviteViewModel
import com.ethyllium.login.LoginViewModel
import com.ethyllium.register.RegisterViewModel
import com.ethyllium.result.ResultViewModel
import com.ethyllium.root.RootViewModel
import com.ethyllium.upcoming.UpcomingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(loginReaction = it.get(), authService = get()) }
    viewModel { RegisterViewModel(registerReaction = it.get(), authService = get()) }
    viewModel { RootViewModel(rootReaction = it.get(), oAuthClient = it.get()) }
    viewModelOf(::HomeViewModel)
    viewModelOf(::ResultViewModel)
    viewModelOf(::UpcomingViewModel)
    viewModelOf(::InviteViewModel)
    viewModelOf(::EVoteViewModel)
}