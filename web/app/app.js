(function () {
    'use strict';

    $(window).on('load', function () {
        var fixedHeight = $('.navbar').outerHeight(true) + $('.footer').outerHeight(true);
        $(window).resize(function(){
            $('#main-div').height($(window).height() - fixedHeight);
        });
        $(window).resize();
    });

    angular.module('vmrApp', [
        'ngRoute',
        'vmrApp.home',
        'vmrApp.editor',
        'vmrApp.chat',
        'vmrApp.chat.chat-list-directive',
        'vmrApp.about',
        'vmrApp.contact',
        'vmrApp.version'
    ]).config(['$locationProvider', '$routeProvider', function ($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('!');
        $routeProvider.otherwise({redirectTo: '/home'});
    }]);
}());