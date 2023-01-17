package com.github.diasandre.tempo

import com.github.diasandre.tempo.model.Configuration

interface DateHandler<RESULT> {

    fun validateAndBuildConfiguration(): Configuration<*>
    fun run(): RESULT
}