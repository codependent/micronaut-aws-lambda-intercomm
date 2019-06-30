package com.codependent.awsintercomm.project.service

import com.codependent.awsintercomm.project.client.UserClient
import com.codependent.awsintercomm.project.client.dto.UserRequest
import com.codependent.awsintercomm.project.dto.Person
import com.codependent.awsintercomm.project.dto.Project
import com.codependent.awsintercomm.project.dto.ProjectDetail
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono
import javax.inject.Singleton

@Singleton
class ProjectServiceImpl(private val userClient: UserClient) : ProjectService {

    private val projects = mutableListOf(
            Project("SpaceX", mutableListOf("1234D", "2345C")),
            Project("Ultra64", mutableListOf("1234D")))

    override fun getAll(): Flux<Project> {
        return projects.toFlux()
    }

    override fun get(name: String): Mono<ProjectDetail> {
        val project = projects.find { it.name == name }
        return if (project != null) {
            val users = mutableListOf<Person>()
            project.personIds.forEach {
                val user = userClient.getUser(UserRequest(it))
                val ur = user.block()
                if (ur != null) {
                    users.add(Person(ur.userId, ur.name))
                }
            }
            ProjectDetail(project.name, users).toMono()
        } else {
            Mono.empty()
        }
    }
}
