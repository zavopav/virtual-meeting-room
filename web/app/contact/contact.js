(function () {
    'use strict';

    angular.module('vmrApp.contact', ['ngRoute'])
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/contact', {
                templateUrl: 'contact/contact.html'
            });
        }]);
}());