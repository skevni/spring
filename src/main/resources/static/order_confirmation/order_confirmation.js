angular.module('market-app').controller('orderConfirmationController', function ($scope, $http, $location, $localStorage) {
    const applicationPath = 'http://localhost:8189/api/v1/'

    $scope.getCart = function () {
        $http({
            url: applicationPath + 'cart/' + $localStorage.webShopGuestCartId,
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.createOrder = function () {
        console.log($scope.cart);
        $http({
            url: applicationPath + 'orders',
            method: 'POST',
            data: $scope.cart
        }).then(function (response) {
            alert('Ваш заказ успешно сформирован');
            $location.path('/');
        });
    };

    $scope.getCart();
});