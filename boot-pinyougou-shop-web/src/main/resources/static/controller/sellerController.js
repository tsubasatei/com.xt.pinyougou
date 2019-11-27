// 品牌控制器
app.controller('sellerController', function ($scope, sellerService) {

    // 保存
    $scope.add = function () {
        sellerService.add($scope.entity).success(function (response) {
            if (response.success) {
                // 注册成功。跳转登录页面
                location.href = '/login';

            } else {
                alert(response.message);
            }
        });
    };
});