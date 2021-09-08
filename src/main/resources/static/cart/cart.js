angular.module('market-app').controller('cartController', function ($scope, $http, $location) {
    const applicationPath = 'http://localhost:8189/api/v1/'

    $scope.getCart = function () {
        $http({
            url: applicationPath + 'cart',
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.reduceItem = function (productId) {
        $http({
            url: applicationPath + 'cart/reduce/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.getCart();
        });
    }

    $scope.increaseItem = function (productId) {
        $http({
            url: applicationPath + 'cart/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.getCart();
        });
    }
    $scope.removeItem = function (productId){
        $http({
            url: applicationPath + 'cart/remove/' + productId,
            method: 'GET'
        })
        .then(function (response) {
            $scope.getCart();
        });
    }

    $scope.сheckOut = function (){
        $http({
            url: applicationPath + 'cart/remove/' + productId,
            method: 'GET'
        })
            .then(function (response) {
                $scope.getCart();
            });
    }

    $scope.disabledCheckOut = function (){
        $http({
            url: applicationPath + 'cart/remove/' + productId,
            method: 'GET'
        })
            .then(function (response) {
                $scope.getCart();
            });
    }

    $scope.getCart();
});