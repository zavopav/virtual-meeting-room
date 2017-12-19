package com.zonelab.vmr.chat.controllers;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.ListTablesRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChatControllerTest {
    private static final Logger log = LoggerFactory.getLogger(ChatControllerTest.class);

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Before
    public void setup() {
    }

    @Test
    public void test() throws Exception {
        final ListTablesResult result = amazonDynamoDB.listTables(new ListTablesRequest());
        result.getTableNames().forEach(log::info);
    }
}