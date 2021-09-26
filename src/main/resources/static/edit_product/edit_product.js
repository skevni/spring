angular.module('market-app').controller('editProductController', function ($scope, $http, $routeParams, $location) {
    const applicationPath = 'http://localhost:8189/api/v1/'

    $scope.prepareProductForUpdate = function () {
        $http.get(applicationPath + 'products/' + $routeParams.productId)
            .then(function successCallback(response){
                $scope.updated_product = response.data;
            }, function failureCallback (response){
                alert(response.data.messages);
                $location.path('/store');
            });
    }

    $scope.updateProduct = function(){
        $http({
            url: applicationPath + 'products/',
            data: $scope.updated_product,
            method: 'PUT'
        }).then(function successCallback(response){
            $scope.updateProduct = null;
            alert('Продукт успешно обновлен');
            $location.path('/store');
        }, function failureCallback (response) {
            alert(response.data.messages)
        })
    }

    $scope.prepareProductForUpdate();
});