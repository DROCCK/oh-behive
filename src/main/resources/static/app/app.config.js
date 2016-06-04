/**
 * Created by Connor on 5/29/2016.
 */
angular.module("beeApp", [
    'ngRoute',
    'navigation'
]).config(function($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
        templateUrl: 'login',
        // controller: 'index',
        // controllerAs: 'controller'
    }).when('/login', {
        templateUrl: 'login',
        // controller: 'navigation',
        // controllerAs: 'controller'
    }).when('/beeboard', {
        templateUrl: 'dashboard/beeboard',
        // controller: 'navigation',
        // controllerAs: 'controller'
    }).when('/nucing', {
        templateUrl: 'dashboard/nucing',
        // controller: 'navigation',
        // controllerAs: 'controller'
    }).when('/pollination', {
        templateUrl: 'dashboard/pollination',
        // controller: 'navigation',
        // controllerAs: 'controller'
    }).when('/help', {
        templateUrl: 'help',
        // controller: 'navigation',
        // controllerAs: 'controller'
    }).otherwise('/');
});