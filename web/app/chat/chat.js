(function () {
    'use strict';
    var chatServiceUrl = 'http://localhost:8080/chat/';
    var chatWsServiceUrl = 'http://localhost:8080/chat-ws/';
    var stompClient = null;
    var chatId = null;

    function onConnected() {
        $('#chat-form-msg').prop('disabled', false);
        $('#chat-form-btn').prop('disabled', false);
        // load chat messages
        stompClient.send('/ws/messages', {}, JSON.stringify({'chatId': chatId}));
    }

    function onDisconnected() {
        $('#chat-form-msg').prop('disabled', true);
        $('#chat-form-btn').prop('disabled', true);
    }

    function onMessage(message) {
        $('#chat-messages').append($('<li>').text(message.content));
    }

    function connect() {
        if (stompClient === null) {
            var socket = new SockJS(chatWsServiceUrl);
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/messages', function (data) {
                    JSON.parse(data.body).forEach(onMessage);
                });
                stompClient.subscribe('/topic/add', function (data) {
                    onMessage(JSON.parse(data.body));
                });
                onConnected();
            });
        } else {
            onConnected();
        }
    }

    function disconnect() {
        if (stompClient !== null) {
            stompClient.disconnect(function () {
                console.log('Disconnected');
                onDisconnected();
            });
            stompClient = null;
        } else {
            onDisconnected();
        }
    }

    function send() {
        var msg = $('#chat-form-msg');
        stompClient.send('/ws/add', {}, JSON.stringify({
            'chatId': chatId,
            'content': msg.val(),
            'author': 'Pavel'
        }));
        msg.val('');
    }

    angular.module('vmrApp.chat', ['ngRoute'])
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/chat/:chatId', {
                templateUrl: 'chat/chat.html',
                controller: 'ChatCtrl'
            });
        }])
        .controller('ChatCtrl', ['$scope', '$http', '$routeParams', function ($scope, $http, $routeParams) {
            chatId = $routeParams.chatId;
            connect();
            $http.get(chatServiceUrl + chatId).success(function (data) {
                $('#chat-name').text(data.name);
            });
            $('#chat-form').on('submit', function (e) {
                e.preventDefault();
            });
            $('#chat-form-btn').click(send);
            $scope.$on("$destroy", disconnect);
        }]);
}());