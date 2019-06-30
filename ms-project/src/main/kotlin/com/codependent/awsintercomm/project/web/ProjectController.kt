package com.codependent.awsintercomm.project.web

import com.codependent.awsintercomm.project.dto.Project
import com.codependent.awsintercomm.project.dto.ProjectDetail
import com.codependent.awsintercomm.project.service.ProjectService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Controller("/projects")
class ProjectController(private val projectService: ProjectService) {

    @Get
    fun getProjects(): Flux<Project> {
        return projectService.getAll()
    }

    @Get("/{projectName}")
    fun getProject(projectName: String): Mono<ProjectDetail> {
        return projectService.get(projectName)
    }

}
