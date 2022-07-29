package br.com.rodrigogurgel.springsqssnsexemple.controllers

import com.amazonaws.services.sns.AmazonSNSAsyncClient
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate
import io.awspring.cloud.messaging.core.QueueMessagingTemplate
import io.awspring.cloud.messaging.core.TopicMessageChannel
import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class AwsSqsController(
    private val queueMessagingTemplate: QueueMessagingTemplate,
    private val notificationMessagingTemplate : NotificationMessagingTemplate
) {
    @PostMapping("/sqs")
    fun createSqsMessage(@RequestBody payload: String) {
        val message = MessageBuilder.withPayload<Any>(payload)
            .build()
        queueMessagingTemplate.send("spring-cloud-test-queue", message)
    }

    @PostMapping("/sns")
    fun createSnsMessage(@RequestBody payload: String) {
        notificationMessagingTemplate.sendNotification("spring-cloud-test-topic", payload, null)
    }
}