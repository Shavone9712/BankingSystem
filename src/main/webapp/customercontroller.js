var app = angular.module("BankingSystem", []);

app.controller("CustomerController", function ($scope, $http, $log, $location, $window) {

    function getUrlParameter(name) {
        name = name.replace(/[[]/, '\\[').replace(/[\]]/, '\\]');
        var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
        var results = regex.exec(location.search);
        return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
    }

    var customerId = getUrlParameter('id');

    _refreshCustomerData();

    function _refreshCustomerData() {
        $http({
            method: 'GET',
            url: 'http://localhost:8082/getCustomer/' + customerId
        }).then(function successCallback(response) {
            $scope.customer = response.data;
            $log.debug("resload");
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    $scope.backToPrevious = function () {
        var url = 'bankingsystem.html';
        window.location.href = url;
    };

    $scope.addAccount = function (customer) {
        $http({
            method: 'POST',
            url: '/newAccount/' + customer.id
        }).then(_success, function errorCallback(response) {
            console.log(response.statusText);
        });
    }
    
    function _success(response) {
        $log.debug(response);
        _refreshCustomerData();
    }

    $scope.updateAccount = function (account, customer) {
        localStorage.setItem('customer', JSON.stringify(customer));

        var url = 'account.html?accountId=' + account.id;
        window.location.href = url;
    }
    
    $scope.closeAccount = function (account, customer) {
        var confirmation = $window.confirm('Are you sure you want to close the account '+account.id+' for ' + customer.name + '?');
        if (confirmation) {
            $http({
                method: 'PATCH',
                url: '/closeAccount/' + account.id
            }).then(_success, function errorCallback(response) {
                console.log(response.data);
            });
        }
    }
});