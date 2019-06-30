package com.codependent.awsintercomm.user.service

import com.codependent.awsintercomm.user.dto.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserService {

    fun getAll() : Flux<User>
    fun get(userId: String): Mono<User>

}
