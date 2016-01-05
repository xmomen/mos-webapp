// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
define(function () {
    angular.module('MOS', [
        "smartApp", "ui.router"
    ]).config(["$stateProvider", "$urlRouterProvider", function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise('/blank');

        $stateProvider

            .state('dashboard', {
                url: '/dashboard',
                templateUrl: 'views/dashboard.html',
                controller: ["$scope", function ($scope) {
                    console.log("dashboard")
                }]
            })

            .state('blank', {
                url: '/blank',
                templateUrl: 'views/blank.html',
                controller: ["$scope", function ($scope) {
                    console.log("blank")
                }]
            })

            .state('messageBox', {
                url: '/message',
                templateUrl: 'views/messageBox/all.html',
                controller: ["$scope", function ($scope) {
                    console.log("messageBox")
                }]
            })

    }]);
});
