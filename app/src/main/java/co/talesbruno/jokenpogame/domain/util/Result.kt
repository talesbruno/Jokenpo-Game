package co.talesbruno.jokenpogame.domain.util


sealed class Result(
    val player: Int? = null,
    val bot: Int? = null,
) {
    object Loading : Result()
    class Win(player: Int, game: Int) : Result(player, game)
    class Loss(player: Int, game: Int) : Result(player, game)
    class Draw(player: Int, game: Int) : Result(player, game)
}
