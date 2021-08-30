(function () {
    angular.module('market-app', ['ngRoute'])
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
            .when('/cart', {
                templateUrl: "cart/cart.html",
                controller: "cartController"
            })
            .otherwise({
                redirectTo: '/'
            });

    }

    function run($rootScope, $http) {

    }
})();

angular.module('market-app').controller('indexController', function ($rootScope, $scope, $http) {
    const applicationPath = 'http://localhost:8189/api/v1/'
});