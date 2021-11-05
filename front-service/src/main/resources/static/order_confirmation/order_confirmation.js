angular.module('market-app').controller('orderConfirmationController', function ($scope, $http, $location, $localStorage) {
    const applicationPath = 'http://localhost:9000/cart-service/api/v1/';
    const applicationPathOrder = 'http://localhost:9000/order-service/api/v1/';

    $scope.getCart = function () {
        $http({
            url: applicationPath + 'cart/' + $localStorage.webShopGuestCartId,
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.createOrder = function () {
        $http.defaults.headers.common.username = $localStorage.webUserStorage.username;
        console.log($scope.cart);
        $http({
            url: applicationPathOrder + 'orders',
            method: 'POST',
            data: $scope.cart
        }).then(function (response) {
            console.log(response.data);
            var orderId = response.data.value;
            console.log(orderId);
            $location.path('/order_pay/' + orderId);
        });
    };

    $scope.getCart();
});