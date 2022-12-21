package br.com.fioalpha.feature.character.domain

import br.com.fioalpha.core.network.model.CharacterDataWrapperResponse
import br.com.fioalpha.core.network.model.ImageResponse
import br.com.fioalpha.feature.character.domain.model.CharacterModel

internal fun CharacterDataWrapperResponse.transformTo(): List<CharacterModel> {
    return this.data?.results?.map {
        CharacterModel(
            id = it?.id?: 0,
            name = it?.name.orEmpty(),
            description = it?.description.orEmpty(),
            image = it?.thumbnail.transformTo()
        )
    }?: emptyList()
}

internal fun ImageResponse?.transformTo(): String {
    return this?.let {
        "${it.path}.${it.extension}"
    }?: ""
}