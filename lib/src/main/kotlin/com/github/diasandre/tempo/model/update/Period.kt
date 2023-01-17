package com.github.diasandre.tempo.model.update

interface Period {
    operator fun component1(): Int = value
    operator fun component2(): UpdateOperation = updateOperation

    val value: Int
    val updateOperation: UpdateOperation
}