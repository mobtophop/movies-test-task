package com.example.moviestesttask.core

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract fun run(params: Params): Type

    class None
}

abstract class SuspendUseCase<out Type, in Params> where Type : Any? {
    abstract suspend fun run(params: Params): Type
}

fun none() = UseCase.None()