package br.com.fioalpha.feature.character.presentation

import br.com.fioalpha.feature.character.domain.CharacterUseCase
import br.com.fioalpha.feature.character.domain.model.CharacterModel
import kotlin.test.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterViewModelTest {

    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: CharacterViewModel
    private val characterUseCase = mock<CharacterUseCase>()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = CharacterViewModel(characterUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        verifyNoMoreInteractions(characterUseCase)
    }

    @Test
    fun `when called start viewmodel Then return stateIdle`() = runTest {
        assertEquals(viewModel.bind().value, ViewState.Idle)
    }

    @Test
    fun `when called load interaction With without data Then return Empty state`() = runTest {
        whenever(characterUseCase.execute(1)).thenReturn(emptyList())
        viewModel.handleInteraction(ViewInteraction.LoadData)
        assertEquals(viewModel.bind().value, ViewState.Empty)

        verify(characterUseCase).execute(1)
    }

    @Test
    fun `when called load interaction With data Then return load data state`() = runTest {
        whenever(characterUseCase.execute(1)).thenReturn(
            listOf(
                CharacterModel(1, "Mock hero", "This data is mock", "pathimage")
            )
        )
        val values = mutableListOf<ViewState>()
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.bind().toList(values)
        }
        viewModel.handleInteraction(ViewInteraction.LoadData)

        assertEquals(ViewState.Idle, values.first())
        assertEquals(ViewState.Loading, values[1])
        assertEquals(
            ViewState.Success(
                listOf(
                    CharacterViewData(
                        name = "Mock hero",
                        id = 1,
                        description = "This data is mock",
                        image = "pathimage"
                    )
                )
            ),
            values[2]
        )

        collectJob.cancel()
        verify(characterUseCase).execute(1)
    }
}
