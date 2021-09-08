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
            .otherwise({
                redirectTo: '/'
            });

    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.webUserStorage) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.webUserStorage.token;
        }
    }
})();

angular.module('market-app').controller('indexController', function ($rootScope, $scope, $http, $localStorage, $location) {
    const applicationPath = 'http://localhost:8189/'

    $scope.tryToAuth = function () {
        $http({
            url: applicationPath + "auth",
            data: $scope.user,
            method: "POST"
        }).then(function successCallback(response) {
            console.log(response.data);
            if (response.data.token) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                $localStorage.webUserStorage = {username: $scope.user.username, token: response.data.token};

                $scope.user.username = null;
                $scope.user.password = null;
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

    $scope.isUserLoggedIn = function () {
        if ($localStorage.webUserStorage) {
            return true;
        } else {
            return false;
        }
    };
    // TODO: сделать доступ только, если пользователь имеет право добавлять продукты
    $scope.isUserPrivileged = function(){
        return $scope.isUserLoggedIn();
    };
    // TODO: сделать доступ только для админа или суперадмина
    $scope.isAdmin = function(){
        return $scope.isUserLoggedIn();
    };

    $scope.redirectToRegistration = function(){
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