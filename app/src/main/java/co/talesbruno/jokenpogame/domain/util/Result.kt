package co.talesbruno.jokenpogame.domain.util


sealed class Result {
    object Loading : Result()
    object Win : Result()
    object Loss : Result()
    object Draw : Result()
}