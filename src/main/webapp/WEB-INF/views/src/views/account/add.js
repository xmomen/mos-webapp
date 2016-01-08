/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", function($scope){
        $scope.addAccountForm = {};
        $scope.addAccount = function(){
            if($scope.addAccountForm.valid()){
                console.log("3333")
            }
        }
    }];
});