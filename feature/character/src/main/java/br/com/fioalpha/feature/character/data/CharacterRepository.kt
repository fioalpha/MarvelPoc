package br.com.fioalpha.feature.character.data

import br.com.fioalpha.core.network.model.CharacterDataWrapperResponse
import br.com.fioalpha.feature.character.data.restdatasource.model.CharacterDataSource

interface CharacterRepository {
    suspend fun fetchCharacters(offset: Int): CharacterDataWrapperResponse
}

class CharacterRepositoryImp(
    private val characterDataSource: CharacterDataSource
) : CharacterRepository {
    override suspend fun fetchCharacters(offset: Int): CharacterDataWrapperResponse {
        return characterDataSource.fetchCharacter(offset)
    }
}
