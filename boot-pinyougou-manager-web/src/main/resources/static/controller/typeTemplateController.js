// 模板控制器
app.controller('typeTemplateController', function ($scope, $controller, typeTemplateService, brandService, specificationService) {

    // 第一个参数：继承的父类
    $controller('baseController', {$scope : $scope}); // 伪继承

    // 查询列表
    $scope.findAll = function() {
        typeTemplateService.findAll().success(
            function (response) {
                $scope.list=response; // 给列表变量赋值
            }
        )
    };

    // 分页查询
    $scope.findPage = function (currentPage, pageNum) {
        typeTemplateService.findPage(currentPage, pageNum).success(
            function (response) {
                $scope.list = response.records;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    };

    // 保存/更新
    $scope.save = function () {
        var object = null;

        /**
         * 1.4+ 以上的 jquery 版本对 json 格式要求更加严格.
         * 如果不是严格的 json 格式,就不能正常执行success回调函数.
         * 如果严格按照 json 格式,还要加上 JSON.stringify()
         */
        $scope.entity.customAttributeItems = JSON.stringify($scope.entity.customAttributeItems);
        $scope.entity.brandIds = JSON.stringify($scope.entity.brandIds);
        $scope.entity.specIds = JSON.stringify($scope.entity.specIds);
        // 更新
        if ($scope.entity.id != null) {
            object = typeTemplateService.update($scope.entity);
        } else { // 保存
            object = typeTemplateService.save($scope.entity);
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
        typeTemplateService.findOne(id).success(
            function (response) {
                $scope.entity = response;
                $scope.entity.customAttributeItems = JSON.parse($scope.entity.customAttributeItems);
                $scope.entity.brandIds = JSON.parse($scope.entity.brandIds);
                $scope.entity.specIds = JSON.parse($scope.entity.specIds);
            }
        );
    };

    // 删除
    $scope.deleteBatch = function () {
        if(confirm('确定要删除类型模板id为：' + $scope.selectIds + ' 的吗？')){
            typeTemplateService.deleteBatch($scope.selectIds).success(function (response) {
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
        typeTemplateService.search(currentPage, pageNum, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.records;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    };

    // 品牌列表
    //$scope.brandList = {data: [{id:1,text:'Apple'},{id:2,text:'小米'},{id:3,text:'华为'},{id:4,text:'三星'}]}

    $scope.brandList = {data: []};

    // 读取品牌列表
    $scope.findBrandList = function () {
        brandService.selectBrandList().success(
            function (response) {
                $scope.brandList.data = response;
            }
        )
    };

    // 规格列表
    $scope.specList = {data: []};
    // 读取规格列表
    $scope.findSpecList = function () {
        specificationService.selectSpecList().success(
            function (response) {
                $scope.specList.data = response;
            }
        )
    };

    // 增加扩展属性行
    $scope.addTableRow = function () {
        $scope.entity.customAttributeItems.push({});
    };

    // 删除扩展属性行
    $scope.deleteTableRow = function (index) {
        $scope.entity.customAttributeItems.splice(index, 1);
    };


});