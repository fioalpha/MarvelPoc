package br.com.fioalpha.core.network.di

import br.com.fioalpha.core.network.ApiService
import br.com.fioalpha.core.network.RetroClient
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    factory <ApiService> {
        RetroClient.invoke(get(qualifier = named(BASE_URL)))
            .create(ApiService::class.java)
    }
}

const val BASE_URL = "BASE_URL"

val configModule = module {
    factory(qualifier = named(BASE_URL)) { "https://gateway.marvel.com/" }
}