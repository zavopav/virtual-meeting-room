#!/bin/sh

dynamodb_home=..
dynamodb_data=${dynamodb_home}/data
dynamodb_log=${dynamodb_home}/logs/dynamodb.log
pid=${dynamodb_home}/logs/pid
RETVAL=0

stop() {
    if [ -f "${pid}" ]
    then
	kill -2 `cat ${pid}`
	rm ${pid}
	echo "Stopped DynamoDB."
	RETVAL=$?
    else
	echo "DynamoDB is not running."
    fi
}
start() {
    if [ -f "${pid}" ]
    then
	echo "DynamoDB is already running."
    else
	nohup java -Djava.library.path=${dynamodb_home}/DynamoDBLocal_lib -jar ${dynamodb_home}/DynamoDBLocal.jar -sharedDb -dbPath ${dynamodb_data} </dev/null >${dynamodb_log} 2>&1 &
	echo $! > ${pid}
	echo "Started DynamoDB."
	RETVAL=$?
    fi
}

case "$1" in
    start)
	start
	;;
    stop)
	stop
	;;
    restart)
	stop
	start
	;;
    *)
	echo "Usage: dynamodb.sh {start|stop|restart}"
	exit 1
esac

exit $RETVAL
