package com.codependent.awsintercomm.user.service

import com.codependent.awsintercomm.user.dto.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono
import javax.inject.Singleton

@Singleton
class UserServiceImpl : UserService {

    private val users = mutableListOf(
            User("1234D", "Joe"),
            User("2345C", "Lewis"))

    override fun get(userId: String): Mono<User> {
        val first = users.stream().filter { it.id == userId }.findFirst()
        return if (first.isPresent) {
            first.get().toMono()
        } else {
            Mono.empty()
        }
    }

    override fun getAll(): Flux<User> {
        return users.toFlux()
    }
}
