package br.com.fioalpha.feature.character.data.restdatasource.model

import br.com.fioalpha.core.network.ApiService
import br.com.fioalpha.core.network.model.CharacterDataWrapperResponse

interface CharacterDataSource {
    suspend fun fetchCharacter(offset: Int = 0, limit: Int = 20): CharacterDataWrapperResponse
}

class CharacterDataSourceImp(
    private val apiService: ApiService
) : CharacterDataSource {
    override suspend fun fetchCharacter(offset: Int, limit: Int): CharacterDataWrapperResponse {
        return apiService.fetchCharacter(offset, limit)
    }
}
