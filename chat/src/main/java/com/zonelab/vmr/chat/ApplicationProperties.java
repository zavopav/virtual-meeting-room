package com.zonelab.vmr.chat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Component
@ConfigurationProperties
@Getter
@Setter
@ToString
public class ApplicationProperties {
    private boolean debug;
    @NotNull
    private String dynamodbEndpoint;
    @NotNull
    private String dynamodbRegion;
    @NotNull
    private String awsProfile;
}
