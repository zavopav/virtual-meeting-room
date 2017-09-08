(function () {
    'use strict';

    angular.module('vmrApp.version', [
        'vmrApp.version.interpolate-filter',
        'vmrApp.version.version-directive'
    ]).value('version', '0.1');
}());