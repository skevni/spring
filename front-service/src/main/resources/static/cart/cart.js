angular.module('market-app').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const applicationPath = 'http://localhost:8191/market-cart/api/v1/'

    $scope.getCart = function () {
        $http.defaults.headers.common.username = $localStorage.webMarketUser.username;
        $http({
            url: applicationPath + 'cart/' + $localStorage.webShopGuestCartId,
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
            $localStorage.cartId = response.data.cartId;
        });
    }

    $scope.reduceItem = function (productId) {
        $http.defaults.headers.common.username = $localStorage.webMarketUser.username;
        $http({
            url: applicationPath + 'cart/' + $localStorage.webShopGuestCartId + '/reduce/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.getCart();
        });
    }

    $scope.increaseItem = function (productId) {
        $http.defaults.headers.common.username = $localStorage.webMarketUser.username;
        $http({
            url: applicationPath + 'cart/' + $localStorage.webShopGuestCartId + '/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.getCart();
        });
    }
    $scope.removeItem = function (productId) {
        $http.defaults.headers.common.username = $localStorage.webMarketUser.username;
        $http({
            url: applicationPath + 'cart/' + $localStorage.webShopGuestCartId + '/remove/' + productId,
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