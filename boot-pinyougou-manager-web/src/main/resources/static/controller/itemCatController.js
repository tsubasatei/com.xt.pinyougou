// 品牌控制器
app.controller('itemCatController', function ($scope, $controller, itemCatService, typeTemplateService) {

    // 第一个参数：继承的父类
    $controller('baseController', {$scope : $scope}); // 伪继承

    // 刷新
    $scope.refresh = function() {
        $scope.listByParentId($scope.parentId);
    };

    // 保存/更新
    $scope.save = function () {
        var object = null;
        // 更新
        if ($scope.entity.id != null) {
            object = itemCatService.update($scope.entity);
        } else { // 保存
            $scope.entity.parentId = $scope.parentId;  //赋予上级ID
            object = itemCatService.save($scope.entity);
        }
        object.success(function (response) {
            if (response.success) {
                alert(response.message);
                $scope.listByParentId($scope.parentId); // 刷新
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
    $scope.delete = function (id) {
        itemCatService.delete(id).success(function (response) {
            if (response.success) {
                alert(response.message);
                $scope.refresh(); // 刷新
            } else {
                alert(response.message);
            }
        });
    };

    // 批量删除
    $scope.deleteBatch = function () {
        if(confirm('确定要删除商品分类id为：' + $scope.selectIds + ' 的吗？')){
            itemCatService.deleteBatch($scope.selectIds).success(function (response) {
                if (response.success) {
                    alert(response.message);
                    $scope.refresh(); // 刷新
                } else {
                    alert(response.message);
                }
            });
        }
    };
    
    // 查询子类列表
    $scope.listByParentId = function (parentId) {
        $scope.parentId = parentId; //记住上级ID
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
        $scope.selectIds = [];
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
    };

    // 父 ID
    $scope.parentId = 0;

    $scope.typeTemplateList = [];
    // 读取类型模板列表
    $scope.findTypeTemplateList = function () {
        typeTemplateService.findTypeTemplateList().success(
            function (response) {
                $scope.typeTemplateList = response;
            }
        )
    }
});