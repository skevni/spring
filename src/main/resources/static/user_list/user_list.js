angular.module('market-app').controller('usersController', function ($scope, $http, $routeParams, $location, $rootScope) {
    const applicationPath = 'http://localhost:8189/'

    $scope.editUser = function (user_id) {
        $http({
            url: applicationPath + 'users/' + user_id,
            method: 'PUT'
            })
            .then(function successCallback(response){
                $location.path("/users");
            }, function failureCallback (response){
                alert(response.data.messages);
            });
    };

    $scope.deleteUser = function () {
        $http({
            url: applicationPath + 'users',
            method: 'DELETE',
            data: $scope.pu
            })
            .then(function successCallback(response){
                $location.path("/users");
            }, function failureCallback (response){
                alert(response.data.messages);
            });
    };

    $scope.getAllUsers = function (pageIndex = 1) {
        $http({
            url: applicationPath + 'users',
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            page = pageIndex;
            totalPages = response.data.totalPages;

            $scope.pageUsers = response.data;
            $scope.paginationArray = $rootScope.generatePagesIndexes(1, $scope.pageUsers.totalPages);
            console.log(response.data);
        });
    };

    $scope.getAllUsers();
});