package br.com.rodrigogurgel.springsqssnsexemple.controllers

import io.awspring.cloud.messaging.core.NotificationMessagingTemplate
import io.awspring.cloud.messaging.core.QueueMessagingTemplate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class AwsSqsController(
    @Value("\${test.queue-name}")
    private val queueName: String,
    @Value("\${test.topic-name}")
    private val topicName: String,
    private val queueMessagingTemplate: QueueMessagingTemplate,
    private val notificationMessagingTemplate: NotificationMessagingTemplate
) {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(AwsSqsController::class.java)
    }

    @PostMapping("/sqs")
    fun createSqsMessage(@RequestBody payload: String) {
        val message = MessageBuilder.withPayload<Any>(payload)
            .build()
        logger.info("Send message \"{}\" to queue \"{}\"", payload, queueName)
        queueMessagingTemplate.send(queueName, message)
    }

    @PostMapping("/sns")
    fun createSnsMessage(@RequestBody payload: String) {
        val message = MessageBuilder.withPayload<Any>(payload)
            .build()
        logger.info("Send message \"{}\" to topic \"{}\"", payload, topicName)
        notificationMessagingTemplate.send(topicName, message)
    }
}