package br.com.fioalpha.feature.character.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fioalpha.feature.character.domain.CharacterUseCase
import br.com.fioalpha.feature.character.domain.model.CharacterModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val characterUseCase: CharacterUseCase
) : ViewModel() {
    private val states: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Idle)
    private val interactions = Channel<ViewInteraction>(Channel.UNLIMITED)

    init {
        viewModelScope.launch {
            interactions.consumeAsFlow().collect {
                when (it) {
                    ViewInteraction.LoadData -> {
                        states.emit(ViewState.Loading)
                        val result = characterUseCase.execute(1).transformTo()
                        states.emit(
                            if (result.isEmpty()) {
                                ViewState.Empty
                            } else {
                                ViewState.Success(result)
                            }
                        )
                    }
                    else -> {}
                }
            }
        }
    }

    fun bind() = states.asStateFlow()

    fun handleInteraction(interaction: ViewInteraction) = viewModelScope.launch {
        interactions.send(interaction)
    }
}

sealed interface ViewState {
    object Idle : ViewState
    object Empty : ViewState
    object Loading : ViewState
    data class Success(val data: List<CharacterViewData>) : ViewState
}

sealed interface ViewInteraction {
    object LoadData : ViewInteraction
}

fun List<CharacterModel>.transformTo() = this.map { it.transformTo() }

fun CharacterModel.transformTo() = CharacterViewData(
    name = this.name,
    description = this.description,
    id = this.id,
    image = this.image
)

data class CharacterViewData(
    val name: String,
    val description: String,
    val id: Int,
    val image: String
)
