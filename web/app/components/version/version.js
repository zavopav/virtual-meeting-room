(function () {
    'use strict';

    angular.module('wbdApp.version', [
        'wbdApp.version.interpolate-filter',
        'wbdApp.version.version-directive'
    ]).value('version', '0.1');
}());