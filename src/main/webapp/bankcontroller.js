var app = angular.module("BankingSystem", ['ui.router']);

app.controller("BankingController", function ($scope, $http, $log, $window, $state) {

    $scope.customers = [];
    $scope.customerForm = {
        id: -1,
        name: "",
    };

    _refreshCustomerData();


    $scope.submitCustomer = function () {
        if (customerExists($scope.customerForm.name)) {
            // Customer with the same name already exists, return early
            alert("Customer with the same name already exists.");
            return;
        }

        var method = "";
        var url = "";
        if ($scope.customerForm.id == -1) {
            //Id is absent in form data, it is create new customer operation
            method = "POST";
            url = '/newCustomer';
        }

        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.customerForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };



    $scope.viewCustomer = function (customer) {
        var url = 'customer.html?id=' + customer.id;
        window.location.href = url;
    };

    function _refreshCustomerData() {
        $http({
            method: 'GET',
            url: 'http://localhost:8082/getAllCustomers'
        }).then(function successCallback(response) {
            $scope.customers = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    function _success(response) {
        _refreshCustomerData();
        _clearFormData();
    }

    function _error(response) {
        console.log(response.statusText);
    }

    //Clear the form
    function _clearFormData() {
        $scope.customerForm.name = "";

    }
    ;

    function customerExists(name) {
        var lowerCaseName = name.toLowerCase(); // Convert provided name to lowercase

        for (var i = 0; i < $scope.customers.length; i++) {
            var customerName = $scope.customers[i].name.toLowerCase(); // Convert customer name to lowercase
            if (customerName === lowerCaseName) {
                return true; // Customer with the same name found
            }
        }
        return false; // Customer with the same name not found
    }
});

//            app.config(function($stateProvider, $urlRouterProvider, $locationProvider) {
//                // Define state for 'viewcustomer'
//                $stateProvider.state('customer', {
//                    url: '/customer/:id', // Define URL with parameter
//                    templateUrl: '../customer.html',
//                    controller: 'CustomerController' 
//                });
//
//                // Set default route
//                $urlRouterProvider.otherwise('/'); // Set default route to handle undefined routes
//                
//                $locationProvider.html5Mode(true);
//            });