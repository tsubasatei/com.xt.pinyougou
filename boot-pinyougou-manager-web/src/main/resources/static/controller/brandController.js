// 品牌控制器
app.controller('brandController', function ($scope, $http, brandService) {
    // 查询品牌列表
    $scope.findAll = function() {
        brandService.findAll().success(
            function (response) {
                $scope.list=response; // 给列表变量赋值
            }
        )
    };

    /**
     * 分页控件配置
     * 	currentPage: 当前页
     * 	totalItems: 总记录数
     * 	itemsPerPage: 每页的记录数
     * 	perPageOptions: 分页选项
     * 	onChange: 当页码变更后自动触发的事件
     */
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function(){
            $scope.reloadList();//重新加载
        }
    };

    // 刷新列表
    $scope.reloadList = function () {
        $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage)
    };

    // 分页查询
    $scope.findPage = function (currentPage, pageNum) {
        brandService.findPage(currentPage, pageNum).success(
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
            object = brandService.update($scope.entity);
        } else { // 保存
            object = brandService.save($scope.entity);
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
        brandService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    };

    // 选中的 ID 集合
    $scope.selectIds = [];

    // 更新复选框 selectIds
    $scope.updateSelection = function ($event, id) {
        if ($event.target.checked) {
            $scope.selectIds.push(id); //push向集合添加元素
        } else {
            var index = $scope.selectIds.indexOf(id); //查找 id 值的位置
            $scope.selectIds.splice(index, 1);  // 参数1：移除的位置； 参数2：移除的个数
        }
    };

    // 删除
    $scope.deleteBatch = function () {
        if(confirm('确定要删除品牌id为：' + $scope.selectIds + ' 的吗？')){
            brandService.deleteBatch($scope.selectIds).success(function (response) {
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
        brandService.search(currentPage, pageNum, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.records;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    }
});