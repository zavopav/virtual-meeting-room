#!/bin/sh

export AWS_PROFILE=vmr_chat

aws dynamodb create-table --table-name Message --attribute-definitions AttributeName=ChatId,AttributeType=S AttributeName=Id,AttributeType=S --key-schema AttributeName=ChatId,KeyType=HASH AttributeName=Id,KeyType=RANGE --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 --endpoint-url http://localhost:8000

aws dynamodb batch-write-item --request-items file://Message_items.json --endpoint-url http://localhost:8000
