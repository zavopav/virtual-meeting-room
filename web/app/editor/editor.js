(function () {
    'use strict';
    var editorId;
    var editor;

    angular.module('vmrApp.editor', ['ngRoute'])
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/editor', {
                templateUrl: 'editor/editor.html',
                controller: 'EditorCtrl'
            });
        }])
        .controller('EditorCtrl', ['$scope', '$http', '$routeParams', function ($scope, $http, $routeParams) {
            // editorId = $routeParams.editorId;
            editor = ace.edit("editor");
            editor.setTheme("ace/theme/sqlserver");
            editor.getSession().setMode("ace/mode/java");
        }]);
}());