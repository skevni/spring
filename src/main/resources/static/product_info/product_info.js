angular.module('market-app').controller('productInfoController', function ($scope, $http, $routeParams, $location, $rootScope) {
    const applicationPath = 'http://localhost:8189/api/v1/'

    $scope.getProduct = function () {
        $http({
            url: applicationPath + 'products/' + $routeParams.productId + '/info',
            method: 'GET'
        })
            .then(function successCallback(response) {
                $scope.product = response.data;
                console.log(response.data);
            }, function failureCallback(response) {
                alert(response.data.messages);
                $location.path('/products');
            });
    };

    $scope.getComment = function () {
        $http({
            url: applicationPath + 'products/' + $routeParams.productId + '/comments',
            method: 'GET'
        })
            .then(function successCallback(response) {
                $scope.comments = response.data;
            }, function failureCallback(response) {
                alert(response.data.messages);
                $location.path('/products');
            });
    };

    $scope.getProduct();
});