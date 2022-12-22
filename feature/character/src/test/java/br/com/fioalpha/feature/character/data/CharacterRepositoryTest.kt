package br.com.fioalpha.feature.character.data

import br.com.fioalpha.core.network.model.CharacterDataWrapperResponse
import br.com.fioalpha.feature.character.data.restdatasource.model.CharacterDataSource
import br.com.fioalpha.test.convertTo
import br.com.fioalpha.test.getFileMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterRepositoryTest {
    private lateinit var repository: CharacterRepository
    private val remoteDataSource = mock<CharacterDataSource>()
    private val charactersMock = "CharactersRequestMock.json".getFileMock(
        this::class.java.classLoader
    ).convertTo<CharacterDataWrapperResponse>()

    @Before
    fun setup() {
        repository = CharacterRepositoryImp(remoteDataSource)
    }

    @Test
    fun `when called repository`() = runTest {
        whenever(remoteDataSource.fetchCharacter(10)).thenReturn(charactersMock)
        remoteDataSource.fetchCharacter(10).run {
            assert(this == charactersMock)
        }
    }
}
