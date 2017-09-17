#!/bin/sh

export AWS_PROFILE=vmr_chat

aws dynamodb create-table --table-name Chat --attribute-definitions AttributeName=RoomName,AttributeType=S AttributeName=Name,AttributeType=S --key-schema AttributeName=RoomName,KeyType=HASH AttributeName=Name,KeyType=RANGE --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 --endpoint-url http://localhost:8000

aws dynamodb batch-write-item --request-items file://Chat_items.json --endpoint-url http://localhost:8000
