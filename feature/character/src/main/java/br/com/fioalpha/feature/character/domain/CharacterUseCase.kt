package br.com.fioalpha.feature.character.domain

import br.com.fioalpha.feature.character.data.CharacterRepository
import br.com.fioalpha.feature.character.domain.model.CharacterModel

interface CharacterUseCase {
    suspend fun execute(page: Int): List<CharacterModel>
}

class CharacterUseCaseImp(
    private val repository: CharacterRepository
): CharacterUseCase {
    override suspend fun execute(page: Int): List<CharacterModel> {
        return repository.fetchCharacters(page).transformTo()
    }
}
