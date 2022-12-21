package br.com.fioalpha.feature.character.domain

import br.com.fioalpha.core.network.model.CharacterDataWrapperResponse
import br.com.fioalpha.feature.character.data.CharacterRepository
import br.com.fioalpha.test.convertTo
import br.com.fioalpha.test.getFileMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

class CharacterUseCaseTest {

    private lateinit var useCase: CharacterUseCase
    private val repository = mock<CharacterRepository>()
    private val charactersMock =  "CharacterRequestTitleMock.json".getFileMock(this::class.java.classLoader)
        .convertTo<CharacterDataWrapperResponse>()

    @Before
    fun setUp() {
        useCase = CharacterUseCaseImp(repository)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when called fetch data With data okay Then return data results`() = runTest {
        whenever(repository.fetchCharacters(1)).thenReturn(charactersMock)
        useCase.execute(1).run {
            assertEquals(expectedValue, this)
        }
        verify(repository).fetchCharacters(1)
    }
}