// 广告分类控制器
app.controller('contentCategoryController', function ($scope, $controller, contentCategoryService) {

    // 第一个参数：继承的父类
    $controller('baseController', {$scope : $scope}); // 伪继承

    // 保存/更新
    $scope.save = function () {
        var object = null;
        // 更新
        if ($scope.entity.id != null) {
            object = contentCategoryService.update($scope.entity);
        } else { // 保存
            object = contentCategoryService.save($scope.entity);
        }
        object.success(function (response) {
            if (response.success) {
                alert(response.message);
                $scope.reloadList(); // 刷新
            } else {
                alert(response.message);
            }
        });
    };

    // 详情
    $scope.findOne = function (id) {
        contentCategoryService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    };

    // 删除
    $scope.deleteBatch = function () {
        if(confirm('确定要删除广告分类id为：' + $scope.selectIds + ' 的吗？')){
            contentCategoryService.deleteBatch($scope.selectIds).success(function (response) {
                if (response.success) {
                    alert(response.message);
                    $scope.reloadList(); // 刷新
                } else {
                    alert(response.message);
                }
            });
        }
    };

    // 初始查询条件
    $scope.searchEntity={};

    // 条件查询
    $scope.search = function (currentPage, pageNum) {
        contentCategoryService.search(currentPage, pageNum, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.records;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    }
});