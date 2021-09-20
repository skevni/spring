angular.module('market-app').controller('ordersController', function ($scope, $http) {
    const applicationPath = 'http://localhost:8189/api/v1/'

    $scope.loadOrders = function () {

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