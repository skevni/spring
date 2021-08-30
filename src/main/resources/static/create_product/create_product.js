angular.module('market-app').controller('createProductController', function ($scope, $http, $location) {
    const applicationPath = 'http://localhost:8189/api/v1/'

    $scope.createProduct = function () {
        console.log($scope.created_product );
        if ($scope.created_product == null) {
            alert('Форма не заполнена');
            return;
        }
        $http({
            url: applicationPath + 'products',
            data: $scope.created_product,
            method: 'POST'
        })
            .then (function successCallback(response) {
            $scope.created_product = null;
            alert('Продукт успешно создан')
            $location.path('/store');
        }, function failureCallback (response) {
            alert(response.data.messages);
        })
    }
});