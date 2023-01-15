package kt.date

import kt.date.model.Configuration

interface DateHandler<RESULT> {

    fun validateAndBuildConfiguration(): Configuration<*>
    fun run(): RESULT
}