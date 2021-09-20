angular.module('market-app').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const applicationPath = 'http://localhost:8189/api/v1/'

    $scope.getCart = function () {
        let cartId = $localStorage.cartId ? $localStorage.cartId : "getNewCartId";
        $http({
            url: applicationPath + 'cart/' + cartId,
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
            $localStorage.cartId = response.data.cartId;
            console.log($scope.cart);
        });
    }

    $scope.reduceItem = function (productId) {
        let cartId = $localStorage.cartId ? $localStorage.cartId : "getNewCartId";
        $http({
            url: applicationPath + cartId + '/cart/reduce/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.getCart();
        });
    }

    $scope.increaseItem = function (productId) {
        let cartId = $localStorage.cartId ? $localStorage.cartId : "getNewCartId";
        $http({
            url: applicationPath + cartId + '/cart/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.getCart();
        });
    }
    $scope.removeItem = function (productId) {
        let cartId = $localStorage.cartId ? $localStorage.cartId : "getNewCartId";
        $http({
            url: applicationPath + cartId + '/cart/remove/' + productId,
            method: 'GET'
        })
            .then(function (response) {
                $scope.getCart();
            });
    }

    $scope.checkOut = function () {
        $location.path("/order_confirmation");
    }

    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись");
    }

    $scope.getCart();
});