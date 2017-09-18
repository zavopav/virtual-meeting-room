#!/bin/sh
if [ -z "$1" ] 
then
	echo "Usage: create-table.sh <table_name>"
	exit 1
fi
export AWS_PROFILE=vmr_chat
TABLE_NAME=$1
DATA_DIR=../data
TABLE_JSON=${DATA_DIR}/${TABLE_NAME}_table.json
ITEMS_JSON=${DATA_DIR}/${TABLE_NAME}_items.json

if [ -f ${TABLE_JSON} ]
then
	echo "Deleting ${TABLE_NAME}"
	aws dynamodb delete-table --table-name ${TABLE_NAME} --endpoint-url http://localhost:8000
	echo "Creating ${TABLE_NAME} from ${TABLE_JSON}"
	aws dynamodb create-table --cli-input-json file://${TABLE_JSON} --endpoint-url http://localhost:8000
else
	echo "File '${TABLE_JSON}' not found"
	exit 1
fi

if [ -f ${ITEMS_JSON} ]
then
	echo "Inserting items into ${TABLE_NAME} from ${ITEMS_JSON}"
	aws dynamodb batch-write-item --request-items file://${ITEMS_JSON} --endpoint-url http://localhost:8000
fi
