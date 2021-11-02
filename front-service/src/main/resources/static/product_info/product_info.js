angular.module('market-app').controller('productInfoController', function ($scope, $http, $routeParams, $location, $rootScope) {
    const applicationPath = 'http://localhost:9000/core-service/api/v1/'

    $scope.getProduct = function () {
        $http({
            url: applicationPath + 'products/' + $routeParams.productId + '/info',
            method: 'GET'
        })
            .then(function successCallback(response) {
                $scope.product = response.data;

            }, function failureCallback(response) {
                alert(response.data.messages);
                $location.path('/products');
            });
    };

    $scope.getComment = function () {
        $http({
            url: applicationPath + 'products/' + $routeParams.productId + '/comments',
            method: 'GET'
        })
            .then(function successCallback(response) {
                $scope.comments = response.data
            }, function failureCallback(response) {
                alert(response.data.messages);
                $location.path('/products');
            });
    };

    $scope.createComment = function () {
        if ($scope.created_comment == null) {
            alert('Форма не заполнена');
            return;
        }
        $scope.created_comment.product_id = $routeParams.productId;
        $http({
            url: applicationPath + 'products/comment',
            data: $scope.created_comment,
            method: 'POST'
        })
            .then(function successCallback(response) {
                $scope.created_comment = null;
                alert('Комментарий успешно добавлен');
                $scope.getComment();
            }, function failureCallback(response) {
                alert(response.data);
            })
    }

    $scope.getUsersPurchase = function () {
        $http({
            url: applicationPath + 'products/' + $routeParams.productId + '/purchase',
            method: 'GET'
        })
            .then(function successCallback(response) {
                $scope.isBought = response.data.value;
            }, function failureCallback(response) {
                return false;
            });
    };

    $scope.isUserBoughtProduct = function (){
        return $scope.isBought !== "false";
    }

    $scope.getUsersPurchase();
    $scope.getProduct();
    $scope.getComment();

});