package com.codependent.awsintercomm.project.client

import com.codependent.awsintercomm.project.client.dto.UserRequest
import com.codependent.awsintercomm.project.client.dto.UserResponse
import io.micronaut.context.annotation.Requires
import io.micronaut.context.env.Environment
import io.micronaut.function.client.FunctionClient
import io.micronaut.http.annotation.Body
import io.micronaut.retry.annotation.Retryable
import reactor.core.publisher.Mono
import javax.inject.Named


@FunctionClient
@Requires(notEnv = [Environment.TEST])
interface UserClientAwsImpl : UserClient {

    @Named("ms-user")
    @Retryable(attempts = "\${ms-user.retry.attempts:3}", delay = "\${ms-user.retry.delay:1s}")
    override fun getUser(@Body request: UserRequest): Mono<UserResponse>
}
