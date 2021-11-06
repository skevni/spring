angular.module('market-app').controller('ordersController', function ($scope, $http, $localStorage) {
    const applicationPath = 'http://localhost:9000/order-service/api/v1/'

    $scope.loadOrders = function () {
        $http.defaults.headers.common.username = $localStorage.webUserStorage.username;
        $http({
            url: applicationPath + 'orders',
            method: 'GET'
        }).then(function (response) {
            $scope.orders = response.data;

        });
    };

    $scope.loadOrders();
});