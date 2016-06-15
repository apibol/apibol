'use strict';

angular.module('myApp.groups', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/groups', {
    templateUrl: 'groups/groups.html',
    controller: 'GroupsController'
  });
}])

.controller('GroupsController', [function() {

}]);