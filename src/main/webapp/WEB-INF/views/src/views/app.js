// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
define([
    "views/account/add"
],function (addAccount) {
    angular.module('MOS', [
        "smartApp", "ui.router", "ug.validation"
    ]).controller("LeftPanelController",["$scope", "$rootScope", "$http", function($scope, $rootScope, $http){
        $http.get("/account/setting").then(function(data){
            if(data.data){
                $rootScope.account = data.data;
            }
        })
    }]).run(["$rootScope", function($rootScope){
        $rootScope.$on('$viewContentLoaded', function (event, next,  nextParams, fromState) {
            // 初始化全局控件
            pageSetUp();
        });
    }]).config(["$stateProvider", "$urlRouterProvider", function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise('/blank');

        $stateProvider

            .state('dashboard', {
                url: '/dashboard',
                templateUrl: 'views/dashboard.html',
                controller: ["$scope", function ($scope) {
                    console.log("dashboard")
                }]
            })

            .state('account_list', {
                url: '/account/list',
                templateUrl: 'views/account/list.html',
                controller: ["$scope", function ($scope) {
                    console.log("account_list")
                }]
            })

            .state('account_add', {
                url: '/account/add',
                templateUrl: 'views/account/add.html',
                controller: addAccount
            })

            .state('blank', {
                url: '/blank',
                templateUrl: 'views/blank.html',
                controller: ["$scope", function ($scope) {
                    console.log("blank");
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
