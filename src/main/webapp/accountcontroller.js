var app = angular.module("BankingSystem", []);

app.controller("AccountController", function ($scope, $http, $log, $location) {
    
    $scope.accountForm = {
        amount: 0,
    };

    function getUrlParameter(name) {
        name = name.replace(/[[]/, '\\[').replace(/[\]]/, '\\]');
        var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
        var results = regex.exec(location.search);
        return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
    }

    var accountId = getUrlParameter('accountId');
    $scope.customer = JSON.parse(localStorage.getItem('customer'));
    $scope.deposit = false;
    $scope.withdraw = false;

    _refreshAccountData();

    function _refreshAccountData() {
        $http({
            method: 'GET',
            url: 'http://localhost:8082/getAccount/' + accountId
        }).then(function successCallback(response) {
            $log.debug(response.data);
            $scope.account = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    $scope.backToPrevious = function () {
        var url = 'customer.html?id='+$scope.customer.id;
        window.location.href = url;
    };

    function _success(response) {
        _refreshAccountData();
    }
    
    function _error(response) {
        $scope.message = "Insufficient amount";
    }
    
    $scope.confirmDeposit = function(){
        $scope.deposit =true;
        $scope.withdraw = false;
    }
    
    $scope.confirmWithdraw = function(){
        $scope.withdraw =true;
        $scope.deposit = false;
    }
    
    function verifyAmount(input){
        return /^\d+(\.\d+)?$/.test(input);
    }
    
    $scope.updateAccount = function(){
        if(!verifyAmount($scope.accountForm.amount)){
            $scope.message = "Invalid Input.";
            return;
        }else{
            $scope.message = null;
        }
        
        if($scope.withdraw){
            withdrawMoney();
        }else if($scope.deposit){
            depositMoney();
        }else{
            return;
        }
    }
    
    $scope.cancel = function(){
        $scope.withdraw = false;
        $scope.deposit = false;
        $scope.accountForm.amount = 0;
    }
    
    function depositMoney(){
        var method = "";
        var url = "";
        method = "PATCH";
        url = '/deposit/'+accountId;

        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.accountForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    }
    
    function withdrawMoney(){
        var method = "";
        var url = "";
        method = "PATCH";
        url = '/withdraw/'+accountId;

        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.accountForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    }

});