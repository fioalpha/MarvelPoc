package br.com.fioalpha.feature.character.domain

import br.com.fioalpha.core.network.model.CharacterDataWrapperResponse
import br.com.fioalpha.core.network.model.ImageResponse

interface CharacterUseCase {
    suspend fun execute(page: Int): List<CharacterModel>
}

data class CharacterModel(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
)
