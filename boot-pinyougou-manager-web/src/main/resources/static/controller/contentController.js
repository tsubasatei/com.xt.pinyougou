// 广告分类控制器
app.controller('contentController', function ($scope, $controller, contentService, contentCategoryService, uploadService) {

    // 第一个参数：继承的父类
    $controller('baseController', {$scope : $scope}); // 伪继承

    $scope.status=["无效","有效"];

    // 查询列表
    $scope.findAll = function() {
        contentService.findAll().success(
            function (response) {
                $scope.list = response; // 给列表变量赋值
            }
        )
    };

    // 保存/更新
    $scope.save = function () {
        var object = null;
        // 更新
        if ($scope.entity.id != null) {
            object = contentService.update($scope.entity);
        } else { // 保存
            object = contentService.save($scope.entity);
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
        contentService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    };

    // 删除
    $scope.deleteBatch = function () {
        if(confirm('确定要删除广告id为：' + $scope.selectIds + ' 的吗？')){
            contentService.deleteBatch($scope.selectIds).success(function (response) {
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
        contentService.search(currentPage, pageNum, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.records;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    };

    // 查询列表
    $scope.findContentCategoryList = function() {
        contentCategoryService.findAll().success(
            function (response) {
                $scope.contentCategoryList = response; // 给列表变量赋值
            }
        )
    };

    // 上传文件
    $scope.uploadFile = function () {
        uploadService.uploadFile().success(
            function (response) {
                if (response.success) {
                    $scope.entity.pic = response.message;
                } else {
                    alert(response.message);
                }
            }
        ).error(function () {
            alert("上传发生错误");
        })
    }
});