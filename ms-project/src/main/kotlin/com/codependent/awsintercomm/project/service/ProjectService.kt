package com.codependent.awsintercomm.project.service

import com.codependent.awsintercomm.project.dto.Project
import com.codependent.awsintercomm.project.dto.ProjectDetail
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ProjectService {

    fun getAll() : Flux<Project>
    fun get(name: String): Mono<ProjectDetail>

}
