package com.codependent.awsintercomm.project

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.codependent.awsintercomm.project")
                .mainClass(Application.javaClass)
                .start()
    }
}
