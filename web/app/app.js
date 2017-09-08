(function () {
    'use strict';

    $(window).on('load', function () {
        var fixedHeight = $('.navbar').outerHeight(true) + $('.footer').outerHeight(true);
        $(window).resize(function(){
            $('#main-div').height($(window).height() - fixedHeight);
        });
        $(window).resize();
    });

    angular.module('wbdApp', [
        'ngRoute',
        'wbdApp.home',
        'wbdApp.editor',
        'wbdApp.chat',
        'wbdApp.chat.chat-list-directive',
        'wbdApp.about',
        'wbdApp.contact',
        'wbdApp.version'
    ]).config(['$locationProvider', '$routeProvider', function ($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('!');
        $routeProvider.otherwise({redirectTo: '/home'});
    }]);
}());