// 品牌控制器
app.controller('itemCatController', function ($scope, $controller, itemCatService) {

    // 第一个参数：继承的父类
    $controller('baseController', {$scope : $scope}); // 伪继承

    // 查询品牌列表
    $scope.findAll = function() {
        itemCatService.findAll().success(
            function (response) {
                $scope.list=response; // 给列表变量赋值
            }
        )
    };

    // 分页查询
    $scope.findPage = function (currentPage, pageNum) {
        itemCatService.findPage(currentPage, pageNum).success(
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
        if ($scope.entity.id != null) {
            object = itemCatService.update($scope.entity);
        } else { // 保存
            object = itemCatService.save($scope.entity);
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
        itemCatService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    };

    // 删除
    $scope.deleteBatch = function () {
        if(confirm('确定要删除品牌id为：' + $scope.selectIds + ' 的吗？')){
            itemCatService.deleteBatch($scope.selectIds).success(function (response) {
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
        itemCatService.search(currentPage, pageNum, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.records;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    };

    // 查询子类列表
    $scope.listByParentId = function (parentId) {
        itemCatService.listByParentId(parentId).success(
            function (response) {
                $scope.list = response;
            }
        )
    };

    /**
     * 三级面包屑导航
     */
    $scope.grade = 1; // 当前分类级别 1

    // 设置分类级别
    $scope.setGrade = function (value) {
        $scope.grade = value;
    };

    // 读取列表
    $scope.selectList = function(pEntity) {
        //如果为1级
        if ($scope.grade == 1) {
            $scope.entity_1 = null;
            $scope.entity_2 = null;
        }
        //如果为2级
        if ($scope.grade == 2) {
            $scope.entity_1 = pEntity;
            $scope.entity_2 = null;
        }
        //如果为3级
        if ($scope.grade == 3) {
            $scope.entity_2 = pEntity;
        }
        //查询此级下级列表
        $scope.listByParentId(pEntity.id);
    }
});