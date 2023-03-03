package co.talesbruno.jokenpogame.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlin.random.Random


@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _gameState =
        MutableStateFlow<co.talesbruno.jokenpogame.domain.util.Result>(co.talesbruno.jokenpogame.domain.util.Result.Loading)
    val gameState: StateFlow<co.talesbruno.jokenpogame.domain.util.Result> = _gameState

    fun start(games: Int) {
        val bot = Random.nextInt(3)
        if (games == bot) {
            _gameState.value = co.talesbruno.jokenpogame.domain.util.Result.Draw
        } else if ((games == 1) && (bot == 2)) {
            _gameState.value = co.talesbruno.jokenpogame.domain.util.Result.Loss
        } else if ((games == 1) && (bot == 3)) {
            _gameState.value = co.talesbruno.jokenpogame.domain.util.Result.Loss
        } else if ((games == 2) && (bot == 1)) {
            _gameState.value = co.talesbruno.jokenpogame.domain.util.Result.Win
        } else if ((games == 2) && (bot == 3)) {
            _gameState.value = co.talesbruno.jokenpogame.domain.util.Result.Loss
        } else if ((games == 3) && (bot == 1)) {
            _gameState.value = co.talesbruno.jokenpogame.domain.util.Result.Loss
        } else
            _gameState.value = co.talesbruno.jokenpogame.domain.util.Result.Win
    }

    fun resetGame() {
        _gameState.value = co.talesbruno.jokenpogame.domain.util.Result.Loading
    }
}