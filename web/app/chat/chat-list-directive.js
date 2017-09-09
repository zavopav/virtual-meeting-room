(function () {
    'use strict';
    var serviceUrl = 'http://localhost:8080/chat/';

    var directive = function ($http) {
        return {
            restrict: 'E',
            templateUrl: 'chat/chat-list.html',
            scope: {

            },
            link: function(scope) {
                $http.get(serviceUrl).success(function(data) {
                    console.log(data);
                    var chatList = $('#chat-list');
                    data.forEach(function(chat) {
                        chatList.append(
                            $('<li>').html('<a href="#!/chat/' + chat.id + '">' + chat.name + '</a>')
                        );
                    });
                });
            }
        };
    };

    angular.module('vmrApp.chat.chat-list-directive', [])
        .directive('chatList', ['$http', directive]);
}());