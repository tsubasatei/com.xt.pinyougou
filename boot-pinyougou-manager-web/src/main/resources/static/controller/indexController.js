app.controller('indexController', function ($scope, indexService) {
    // 显示当前用户名
    $scope.showLoginName = function () {
       indexService.loginName().success(
           function (response) {
               $scope.loginName = response.loginName;
           }
       );
   }
});