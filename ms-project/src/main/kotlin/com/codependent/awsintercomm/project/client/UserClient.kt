package com.codependent.awsintercomm.project.client

import com.codependent.awsintercomm.project.client.dto.UserRequest
import com.codependent.awsintercomm.project.client.dto.UserResponse
import io.micronaut.http.annotation.Body
import reactor.core.publisher.Mono

interface UserClient {

    fun getUser(@Body request: UserRequest): Mono<UserResponse>

}
