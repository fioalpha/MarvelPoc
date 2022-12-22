package br.com.fioalpha.feature.character.domain

import br.com.fioalpha.core.network.model.CharacterDataWrapperResponse
import br.com.fioalpha.feature.character.domain.model.CharacterModel
import br.com.fioalpha.test.convertTo
import br.com.fioalpha.test.getFileMock
import org.junit.Test
import kotlin.test.assertEquals

internal val expectedValue = listOf(
    CharacterModel(
        id = 1011334,
        name = "3-D Man",
        description = "",
        image = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
    ),
    CharacterModel(
        id = 1017100,
        name = "A-Bomb (HAS)",
        description = "Rick Jones has been Hulk's best bud since day one, but now he's more " +
            "than a friend...he's a teammate! Transformed by a Gamma energy explosion," +
            " A-Bomb's thick, armored skin is just as strong and powerful as it is blue. " +
            "And when he curls into action, he uses it like a giant bowling ball of " +
            "destruction! ",
        image = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg"
    )
)

class TransformTest {

    private val charactersMock = "CharacterRequestTitleMock.json".getFileMock(
        this::class.java.classLoader
    ).convertTo<CharacterDataWrapperResponse>()

    @Test
    fun `when called running transform`() {
        charactersMock.transformTo()
            .run {
                assertEquals(expectedValue, this)
            }
    }
}
