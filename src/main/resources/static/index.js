angular.module('market-app', []).controller('indexController', function ($scope, $http) {
    const applicationPath = 'http://localhost:8189/'
    var page = 1;
    var totalPages = 0;
    var lastPage = false;

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
            // надо это обрабатыать при удалении, но там numberOfElements не меняется
            // оставил пока здесь
            if (parseInt(response.data.numberOfElements) === 1) {
                page -= 1;
            }

        })
    }
    $scope.deleteProduct = function (product) {
        $http({
            url: applicationPath + 'products/delete/' + product.id,
            method: 'GET'
        }).then(function () {
            $scope.getAllProducts(page);
        })
    }
    $scope.previousPage = function () {
        $http.get(applicationPath + 'products')
            .then(function () {
                if (page - 1 < 1) {
                    page = 1
                } else {
                    page -= 1;
                }
                lastPage = false;
                $scope.getAllProducts(page);
            })
    }
    $scope.nextPage = function () {
        $http({
            url: applicationPath + 'products',
            method: 'GET'
        }).then(function () {
            if (!lastPage) {
                if (page + 1 < totalPages) {
                    page += 1;
                } else {
                    page = totalPages;
                }
                $scope.getAllProducts(page);
            }
        })
    }

    $scope.getAllProducts();
});