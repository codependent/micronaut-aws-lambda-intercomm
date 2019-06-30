package com.codependent.awsintercomm.user.web

import com.codependent.awsintercomm.user.dto.User
import com.codependent.awsintercomm.user.service.UserService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Controller("/users")
class UserController(private val userService: UserService) {

    @Get
    fun getAll(): Flux<User> {
        return userService.getAll()
    }

    @Get("/{userId}")
    fun get(userId: String): Mono<User> {
        return userService.get(userId)
    }

}
