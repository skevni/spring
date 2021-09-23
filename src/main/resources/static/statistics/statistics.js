angular.module('market-app').controller('statisticsController', function ($scope, $http, $routeParams, $location, $rootScope) {
    const applicationPath = 'http://localhost:8189/'

    $scope.getStatistics = function () {
        $http({
            url: applicationPath + 'statistics/',
            method: 'GET'
        })
            .then(function successCallback(response) {
                $scope.statistics = response.data;
            }, function failureCallback(response) {
                alert(response.data.messages);
            });
    };

    $scope.getStatistics();
});