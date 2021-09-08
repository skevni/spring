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

    $scope.checkOut = function (){
        $location.path("/order_confirmation");
    }

    $scope.disabledCheckOut = function (){
        alert("Для оформления заказа необходимо войти в учетную запись");
    }

    $scope.getCart();
});