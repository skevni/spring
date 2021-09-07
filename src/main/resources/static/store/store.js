angular.module('market-app').controller('storeController', function ($rootScope, $scope, $http, $location) {
    const applicationPath = 'http://localhost:8189/api/v1/'
    let page = 1;
    let totalPages = 0;
    let lastPage = false;

    $scope.getAllProducts = function (pageIndex = 1) {
        $http({
            url: applicationPath + 'products',
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            page = pageIndex;
            totalPages = response.data.totalPages;
            $scope.pageProducts = response.data;
            if (response.data.last) {
                lastPage = true;
            }
            $scope.paginationArray = $rootScope.generatePagesIndexes(1, $scope.pageProducts.totalPages);
            console.log($scope.paginationArray);
        })
    }

    $scope.deleteProduct = function (product) {
        $http({
            url: applicationPath + 'products/' + product.id,
            method: 'DELETE'
        }).then(function () {

            if (parseInt($scope.pageProducts.numberOfElements) === 1) {
                page -= 1;
            }
            $scope.getAllProducts(page);
        });
    }

    $scope.editProduct = function (productId) {
        $location.path('/edit_product/' + productId);
    }

    $scope.toCart = function (productId){
        $http({
            url: applicationPath + 'products/addToCart/' + productId,
            method: 'POST'
        }).then(function () {
            alert("Товар добавлен в корзину");
        });
    }
    $scope.getAllProducts();
});