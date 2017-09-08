(function () {
    'use strict';

    angular.module('vmrApp.about', ['ngRoute'])
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/about', {
                templateUrl: 'about/about.html'
            });
        }]);
}());