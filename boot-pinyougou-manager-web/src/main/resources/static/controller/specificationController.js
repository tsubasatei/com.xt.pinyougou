// 品牌控制器
app.controller('specificationController', function ($scope, $controller, specificationService) {

    // 第一个参数：继承的父类
    $controller('baseController', {$scope : $scope}); // 伪继承

    // 查询品牌列表
    $scope.findAll = function() {
        specificationService.findAll().success(
            function (response) {
                $scope.list=response; // 给列表变量赋值
            }
        )
    };

    // 分页查询
    $scope.findPage = function (currentPage, pageNum) {
        specificationService.findPage(currentPage, pageNum).success(
            function (response) {
                $scope.list = response.records;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    };

    // 保存/更新
    $scope.save = function () {
        var object = null;
        // 更新
        if ($scope.entity.specification.id != null) {
            object = specificationService.update($scope.entity);
        } else { // 保存
            object = specificationService.save($scope.entity);
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
        specificationService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    };

    // 删除
    $scope.deleteBatch = function () {
        if(confirm('确定要删除规格id为：' + $scope.selectIds + ' 的吗？')){
            specificationService.deleteBatch($scope.selectIds).success(function (response) {
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
        specificationService.search(currentPage, pageNum, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.records;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    };

    // 增加规格选项行
    $scope.addTableRow = function () {
        $scope.entity.specificationOptions.push({});
    };

    // 删除规格选项行
    $scope.deleteTableRow = function (index) {
        $scope.entity.specificationOptions.splice(index, 1);
    }

});