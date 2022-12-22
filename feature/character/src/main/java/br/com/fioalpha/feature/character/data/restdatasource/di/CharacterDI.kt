package br.com.fioalpha.feature.character.data.restdatasource.di

import br.com.fioalpha.core.network.di.networkModule
import br.com.fioalpha.feature.character.data.CharacterRepository
import br.com.fioalpha.feature.character.data.CharacterRepositoryImp
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val characterModule = module {
    loadKoinModules(networkModule)
    factory<CharacterRepository> { CharacterRepositoryImp(characterDataSource = get()) }
}
