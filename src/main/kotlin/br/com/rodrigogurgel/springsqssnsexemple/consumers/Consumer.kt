package br.com.rodrigogurgel.springsqssnsexemple.consumers

import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy
import io.awspring.cloud.messaging.listener.annotation.SqsListener
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.messaging.Message
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

@Component
class Consumer {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(Consumer::class.java)
    }

    @SqsListener("\${test.queue-name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    fun receiveMessage(
        message: Message<String>,
        @Header("SenderId") senderId: String
    ) {
        logger.info(message.payload)
    }
}