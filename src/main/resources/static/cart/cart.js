angular.module('market-app').controller('cartController', function ($scope, $http, $location) {
    const applicationPath = 'http://localhost:8189/api/v1/'

    $scope.getCart = function () {
        $http({
            url: applicationPath + 'cart',
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
            console.log($scope.cart);
        });
    }

    $scope.deleteProduct = function (product){
        console.log(product)
        $http({
            url: applicationPath + 'cart',
            method: 'DELETE',
            data: product
        }).then(function (response) {
            $scope.product = null;
        });
    }

    $scope.getCart();
});