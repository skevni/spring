angular.module('market-app').controller('userRegistrationController', function ($scope, $http, $routeParams, $location) {
    const applicationPath = 'http://localhost:9000/auth-service/'

    $scope.tryToRegister = function () {
        $http.post(applicationPath + 'registration', $scope.registration)
            .then(function successCallback() {
                $scope.registration = null;
                $location.path("/");
            }, function failureCallback(response) {
                alert(response.data.messages);
            });
    };

});