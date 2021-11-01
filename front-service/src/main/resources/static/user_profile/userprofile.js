angular.module('market-app').controller('userProfileController', function ($scope, $http) {
    const applicationPath = 'http://localhost:9000/auth-service/'

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