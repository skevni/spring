angular.module('market-app').controller('orderConfirmationController', function ($scope, $http, $location, $localStorage) {
    const applicationPath = 'http://localhost:8191/market-cart/api/v1/';
    const applicationPathOrder = 'http://localhost:8193/market-order/api/v1/'

    $scope.getCart = function () {
        $http({
            url: applicationPath + 'cart/' + $localStorage.webShopGuestCartId,
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.createOrder = function () {
        $http.defaults.headers.common.username = $localStorage.webMarketUser.username;
        console.log($scope.cart);
        $http({
            url: applicationPathOrder + 'orders',
            method: 'POST',
            data: $scope.cart
        }).then(function (response) {
            alert('Ваш заказ успешно сформирован');
            $location.path('/');
        });
    };

    $scope.getCart();
});