package com.codependent.awsintercomm.project.client

import com.amazonaws.regions.Regions
import com.amazonaws.services.lambda.AWSLambdaClientBuilder
import com.amazonaws.services.lambda.model.InvokeRequest
import com.codependent.awsintercomm.project.client.dto.UserRequest
import com.codependent.awsintercomm.project.client.dto.UserResponse
import com.codependent.awsintercomm.project.dto.Person
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.micronaut.context.annotation.Primary
import reactor.core.publisher.Mono
import javax.inject.Singleton


@Primary
@Singleton
class UserClientAwsSdkImpl : UserClient {

    private val objectMapper = jacksonObjectMapper()

    override fun getUser(request: UserRequest): Mono<UserResponse> {
        val region = Regions.fromName("eu-west-3")
        val builder = AWSLambdaClientBuilder.standard()
                .withRegion(region)
        val client = builder.build()
        val req = InvokeRequest()
                .withFunctionName("ms-user")
                .withPayload("""{
                                      "path": "/users/${request.userId}",
                                      "httpMethod": "GET",
                                      "headers":  null,
                                      "multiValueHeaders": {
                                        "Host": [
                                          "8mvkrus5e1.execute-api.eu-west-3.amazonaws.com"
                                        ]
                                      }
                                    }""")
        val result = client.invoke(req)
        println(result.statusCode)
        val responsePayLoad = String(result.payload.array())
        val json = objectMapper.readValue(responsePayLoad, Map::class.java) as Map<String, String>
        val person = objectMapper.readValue(json["body"], Person::class.java)
        return Mono.just(UserResponse(person.id, person.name))
    }


}

fun main() {
    val objectMapper = jacksonObjectMapper()
    val json = """
        { "statusCode": 200, "multiValueHeaders": { "Content-Type": [ "application/json" ] }, "body": "{\"id\":\"2345C\",\"name\":\"Lewis\"}", "isBase64Encoded": false }
    """.trimIndent()
    val readValue = objectMapper.readValue(json, Map::class.java) as Map<String, String>
    println(readValue["body"])
    val theBody = objectMapper.readValue(readValue["body"], Person::class.java)
    println(theBody)
}
