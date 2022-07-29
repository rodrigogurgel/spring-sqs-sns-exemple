package br.com.rodrigogurgel.springsqssnsexemple.configurations

import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.AmazonSNSAsync
import com.amazonaws.services.sns.AmazonSNSAsyncClient
import com.amazonaws.services.sqs.AmazonSQSAsync
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate
import io.awspring.cloud.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AwsMessagingConfiguration {

    @Bean
    fun queueMessagingTemplate(
        amazonSQSAsync: AmazonSQSAsync
    ): QueueMessagingTemplate {
        return QueueMessagingTemplate(amazonSQSAsync)
    }

    @Bean
    fun notificationMessagingTemplate(
        amazonSNS: AmazonSNS
    ): NotificationMessagingTemplate {
        return NotificationMessagingTemplate(amazonSNS)
    }
}