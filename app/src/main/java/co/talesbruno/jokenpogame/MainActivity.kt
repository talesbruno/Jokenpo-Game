package co.talesbruno.jokenpogame

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.talesbruno.jokenpogame.presentation.MainViewModel
import co.talesbruno.jokenpogame.ui.theme.JokenpoGameTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainViewModel: MainViewModel by viewModels()

        setContent {
            JokenpoGameTheme {
                Home(mainViewModel = mainViewModel)
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Home(
    mainViewModel: MainViewModel
) {
    val stateGame by mainViewModel.gameState.collectAsState()

    Scaffold() {
        when (stateGame) {
            is co.talesbruno.jokenpogame.domain.util.Result.Loading -> Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = { mainViewModel.start(1) }) {
                    Text(text = "Pedra")
                }
                Button(onClick = { mainViewModel.start(2) }) {
                    Text(text = "Papel")
                }
                Button(onClick = { mainViewModel.start(3) }) {
                    Text(text = "Tesoura")
                }
            }
            is co.talesbruno.jokenpogame.domain.util.Result.Loss -> AlertDialog(onDismissRequest = { mainViewModel.resetGame() },
                title = { Text(text = "Que pena você perdeu!!!") },
                text = {
                    Text(
                        text = "${
                            when (stateGame.player) {
                                1 -> "Pedra"
                                2 -> "Papel"
                                else -> "Tesoura"
                            }
                        } VS ${
                            when (stateGame.bot) {
                                1 -> "Pedra"
                                2 -> "Papel"
                                else -> "Tesoura"
                            }
                        }"
                    )
                },
                confirmButton = {
                    IconButton(onClick = { mainViewModel.resetGame() }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                })
            is co.talesbruno.jokenpogame.domain.util.Result.Win -> AlertDialog(onDismissRequest = { mainViewModel.resetGame() },
                title = { Text(text = "Boa você ganhou!!!") },
                text = {
                    Text(
                        text = "${
                            when (stateGame.player) {
                                1 -> "Pedra"
                                2 -> "Papel"
                                else -> "Tesoura"
                            }
                        } VS ${
                            when (stateGame.bot) {
                                1 -> "Pedra"
                                2 -> "Papel"
                                else -> "Tesoura"
                            }
                        }"
                    )
                },
                confirmButton = {
                    IconButton(onClick = { mainViewModel.resetGame() }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                })
            is co.talesbruno.jokenpogame.domain.util.Result.Draw -> AlertDialog(onDismissRequest = { mainViewModel.resetGame() },
                title = { Text(text = "Empate tente novamente") },
                text = {
                    Text(
                        text = "${
                            when (stateGame.player) {
                                1 -> "Pedra"
                                2 -> "Papel"
                                else -> "Tesoura"
                            }
                        } VS ${
                            when (stateGame.bot) {
                                1 -> "Pedra"
                                2 -> "Papel"
                                else -> "Tesoura"
                            }
                        }"
                    )
                },
                confirmButton = {
                    IconButton(onClick = { mainViewModel.resetGame() }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JokenpoGameTheme {}
}