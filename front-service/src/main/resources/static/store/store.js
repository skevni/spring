angular.module('market-app').controller('storeController', function ($rootScope, $scope, $http, $location, $localStorage) {
    const applicationPath = 'http://localhost:9000/core-service/api/v1/';
    const applicationPathCart = 'http://localhost:9000/cart-service/api/v1/';

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

    $scope.addToCart = function (productId) {
        // $http.defaults.headers.common.username = $localStorage.webUserStorage.username;
        $http({
            url: applicationPathCart + 'cart/' + $localStorage.webShopGuestCartId + '/add/' + productId,
            method: 'GET'
        }).then(function () {
        });
    }
    $scope.productInfo = function (productId) {
        $location.path('/products/' + productId + '/info');
    }

    $scope.getAllProducts();
});