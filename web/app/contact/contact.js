(function () {
    'use strict';

    angular.module('wbdApp.contact', ['ngRoute'])
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/contact', {
                templateUrl: 'contact/contact.html'
            });
        }]);
}());