/**
 * Created by Connor on 5/29/2016.
 */
(function(angular) {
    'use strict';
    function NavigationController($rootScope, $http, $location, $route) {
        var self = this;

        var authenticate = function(callback) {
            $http.get('user/').success(function(data) {
                //console.log(data);
                if (data.name) {
                    $rootScope.userName = data.name;
                    $rootScope.authenticated = true;
                } else {
                    $rootScope.authenticated = false;
                }
                callback && callback();
            }).error(function() {
                $rootScope.authenticated = false;
                callback && callback();
            });
        };

        authenticate();
        self.credentials = {};
        self.login = function() {
            $http.post('login', $.param(self.credentials), {
                headers : {
                    "content-type" : "application/x-www-form-urlencoded"
                }
            }).success(function(data) {
                authenticate(function() {
                    if ($rootScope.authenticated) {
                        $location.path("/");
                        self.error = false;
                    } else {
                        $location.path("/login");
                        self.error = true;
                    }
                });
            }).error(function(data) {
                $location.path("/login");
                self.error = true;
                $rootScope.authenticated = false;
            })
        };

        self.logout = function() {
            $http.post('logout', {}).success(function() {
                $rootScope.authenticated = false;
                $location.path("/");
            }).error(function(data) {
                $rootScope.authenticated = false;
            });
        }
    }

    angular.module('beeApp').component('navigation', {
        templateUrl: 'app/navigation/navigation.template.html',
        controller: NavigationController
    });
})(window.angular);