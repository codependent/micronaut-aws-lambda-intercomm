package com.codependent.awsintercomm.user

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.codependent.lambdaintercomm.user")
                .mainClass(Application.javaClass)
                .start()
    }
}
