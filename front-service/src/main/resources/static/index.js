(function () {
    angular.module('market-app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run)

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: "start/start.html",
                controller: "startController"
            })
            .when('/store', {
                templateUrl: "store/store.html",
                controller: "storeController"
            })
            .when('/edit_product/:productId', {
                templateUrl: "edit_product/edit_product.html",
                controller: "editProductController"
            })
            .when('/create_product', {
                templateUrl: "create_product/create_product.html",
                controller: "createProductController"
            })
            .when('/products/:productId/info', {
                templateUrl: "product_info/product_info.html",
                controller: "productInfoController"
            })
            .when('/cart', {
                templateUrl: "cart/cart.html",
                controller: "cartController"
            })
            .when('/order_confirmation', {
                templateUrl: "order_confirmation/order_confirmation.html",
                controller: "orderConfirmationController"
            })
            .when('/user_profile', {
                templateUrl: "user_profile/userprofile.html",
                controller: "userProfileController"
            })
            .when('/registration', {
                templateUrl: "registration/registration.html",
                controller: "userRegistrationController"
            })
            .when('/users', {
                templateUrl: "user_list/user_list.html",
                controller: "usersController"
            })
            .when('/orders', {
                templateUrl: "orders/orders.html",
                controller: "ordersController"
            })
            .when('/statistics', {
                templateUrl: "statistics/statistics.html",
                controller: "statisticsController"
            })
            .otherwise({
                redirectTo: '/'
            });

    }

    function run($rootScope, $http, $localStorage) {
        const applicationPathCart = 'http://localhost:9000/cart-service/'

        if ($localStorage.webUserStorage) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.webUserStorage.token;
        }
        if (!$localStorage.webShopGuestCartId) {
            $http.get(applicationPathCart + 'api/v1/cart/generate')
                .then(function successCallback(response) {
                    $localStorage.webShopGuestCartId = response.data.value;
                });
        }
    }

})();

angular.module('market-app').controller('indexController', function ($rootScope, $scope, $http, $localStorage, $location) {
    const applicationPathAuth = 'http://localhost:9000/auth-service/';
    const applicationPathCart = 'http://localhost:9000/cart-service/';

    $scope.tryToAuth = function () {
        $http({
            url: applicationPathAuth + "auth",
            data: $scope.user,
            method: "POST"
        }).then(function successCallback(response) {
            if (response.data.token) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                $localStorage.webUserStorage = {username: $scope.user.username, token: response.data.token};
                $http.defaults.headers.common.username = $scope.user.username;
                $scope.user.username = null;
                $scope.user.password = null

                $http.get(applicationPathCart + 'api/v1/cart/' + $localStorage.webShopGuestCartId + '/merge')
                    .then(function successCallback(response) {
                    });
            }
        }).then(function errorCallback(response) {
        });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
        $location.path("/");
    };

    $scope.clearUser = function () {
        delete $localStorage.webUserStorage;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        return $localStorage.webUserStorage ? true : false;
    };
    // TODO: сделать доступ только, если пользователь имеет право добавлять продукты
    $rootScope.isUserPrivileged = function () {
        return $rootScope.isUserLoggedIn();
    };
    // TODO: сделать доступ только для админа или суперадмина
    $rootScope.isAdmin = function () {
        return $rootScope.isUserLoggedIn();
    };

    $scope.redirectToRegistration = function () {
        $location.path("/registration");
    };

    $rootScope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i <= endPage; i++) {
            arr.push(i);
        }
        return arr;
    };
});