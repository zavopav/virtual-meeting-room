package com.zonelab.vmr.chat;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import lombok.RequiredArgsConstructor;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.zonelab.vmr.chat.repository")
@RequiredArgsConstructor
public class DynamoDBConfiguration {
    private final GlobalProperties properties;

    @Bean
    public AmazonDynamoDB amazonDynamoDB(final AWSCredentialsProvider credentialsProvider, final EndpointConfiguration dynamoDBEndpoint) {
        return AmazonDynamoDBClient.builder()
                .withCredentials(credentialsProvider)
                .withEndpointConfiguration(dynamoDBEndpoint)
                .build();
    }

    @Bean
    public AWSCredentialsProvider credentialsProvider() {
        return new ProfileCredentialsProvider(properties.getAwsProfile());
    }

    @Bean
    public EndpointConfiguration dynamoDBEndpoint() {
        return new EndpointConfiguration(properties.getDynamodbEndpoint(), properties.getDynamodbRegion());
    }
}