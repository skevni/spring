angular.module('market-app').controller('ordersController', function ($scope, $http, $localStorage) {
    const applicationPath = 'http://localhost:8193/market-order/api/v1/'

    $scope.loadOrders = function () {
        $http.defaults.headers.common.username = $localStorage.webMarketUser.username;
        $http({
            url: applicationPath + 'orders',
            method: 'GET'
        }).then(function (response) {
            console.log($scope.orders);
            $scope.orders = response.data;
        });
    };

    $scope.loadOrders();
});