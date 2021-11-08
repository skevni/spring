angular.module('market-app').controller('orderPayController', function ($scope, $http, $location, $localStorage, $routeParams) {
    const applicationPath = 'http://localhost:9000/core-service/api/v1/';
    const applicationPathOrder = 'http://localhost:9000/order-service/api/v1/';

    $scope.loadOrder = function () {
        $http({
            url: applicationPathOrder + 'orders/' + $routeParams.orderId,
            method: 'GET'
        }).then(function (response) {
            $scope.order = response.data;
            if ($scope.order.paid) {
                $scope.order.statusPay = "Оплачен";
            } else {
                $scope.order.statusPay = "Не оплачен";
            }
            if (!$scope.order.paid) {
                $scope.renderPaymentButtons();
            }
        });
    };

    $scope.renderPaymentButtons = function() {
        paypal.Buttons({
            createOrder: function(data, actions) {
                return fetch(applicationPath + 'paypal/create/' + $scope.order.id, {
                    method: 'post',
                    headers: {
                        'content-type': 'application/json'
                    }
                }).then(function(response) {
                    return response.text();
                });
            },

            onApprove: function(data, actions) {
                return fetch(applicationPath +'paypal/capture/' + data.orderID, {
                    method: 'post',
                    headers: {
                        'content-type': 'application/json'
                    }
                }).then(function successCallback(response) {
                    response.text().then(msg => {
                        alert(msg);
                    });
                    $location.go("/store");
                });
            },

            onCancel: function (data) {
                console.log("Order canceled: " + data);
            },

            onError: function (err) {
                console.log(err);
            }
        }).render('#paypal-buttons');
    }

    $scope.loadOrder();
});