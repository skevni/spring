angular.module('market-app').controller('userProfileController', function ($scope, $http, $location) {
    const applicationPath = 'http://localhost:8189/'

    $scope.getUserProfile = function () {
        $http({
            url: applicationPath + 'userProfile',
            method: 'GET'
        }).then(function (response) {
            $scope.userProfile = response.data;
            console.log($scope.userProfile);
        })
    }

    $scope.getUserProfile();
});