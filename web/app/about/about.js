(function () {
    'use strict';

    angular.module('wbdApp.about', ['ngRoute'])
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/about', {
                templateUrl: 'about/about.html'
            });
        }]);
}());