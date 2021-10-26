angular.module('market-app').controller('userProfileController', function ($scope, $http) {
    const applicationPath = 'http://localhost:8189/'

    $scope.getUserProfile = function () {
        $http({
            url: applicationPath + 'user_profile',
            method: 'GET'
        }).then(function (response) {
            $scope.userProfile = response.data;
        })
    }

    $scope.getUserProfile();
});