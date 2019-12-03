// 商品控制器
// ?前要加# ，则是angularJS的地址路由的书写形式 http://localhost:9102/admin/goods_edit.html#?id=149187842867969
app.controller('goodsController', function ($scope, $controller, $location, goodsService, uploadService, itemCatService, typeTemplateService){

    // 第一个参数：继承的父类
    $controller('baseController', {$scope : $scope}); // 伪继承

    // 保存
    $scope.add = function () {
        $scope.entity.goodsDesc.introduction = editor.html();
        $scope.entity.goodsDesc.itemImages = JSON.stringify($scope.entity.goodsDesc.itemImages);
        $scope.entity.goodsDesc.customAttributeItems = JSON.stringify($scope.entity.goodsDesc.customAttributeItems);
        $scope.entity.goodsDesc.specificationItems = JSON.stringify($scope.entity.goodsDesc.specificationItems);
        for (var i = 0; i < $scope.entity.itemList.length; i++ ) {
            $scope.entity.itemList[i].spec = JSON.stringify($scope.entity.itemList[i].spec)
        }
        goodsService.add($scope.entity).success(function (response) {
            if (response.success) {
                alert(response.message);
                $scope.entity = {};
                editor.html(""); //清空富文本编辑器
            } else {
                alert(response.message);
            }
        }).error(function() {
            alert("上传发生错误");
        });

    };

    // 保存/更新
    $scope.save = function () {
        $scope.entity.goodsDesc.introduction = editor.html();
        $scope.entity.goodsDesc.itemImages = JSON.stringify($scope.entity.goodsDesc.itemImages);
        $scope.entity.goodsDesc.customAttributeItems = JSON.stringify($scope.entity.goodsDesc.customAttributeItems);
        if ($scope.entity.goods.isEnableSpec == 1) {
            $scope.entity.goodsDesc.specificationItems = JSON.stringify($scope.entity.goodsDesc.specificationItems);
            for (var i = 0; i < $scope.entity.itemList.length; i++ ) {
                $scope.entity.itemList[i].spec = JSON.stringify($scope.entity.itemList[i].spec)
            }
        } else {
            $scope.entity.goodsDesc.specificationItems = JSON.stringify([]);
            $scope.entity.itemList = [];
        }

        var obj;
        if ($scope.entity.goods.id != null) {
            obj = goodsService.update($scope.entity)
        } else {
            obj = goodsService.add($scope.entity)
        }
        obj.success(function (response) {
            if (response.success) {
                alert(response.message);
                location.href = "/admin/goods.html";
            } else {
                alert(response.message);
            }
        }).error(function() {
            alert("上传发生错误");
        });

    };

    // 上传图片
    $scope.uploadFile = function () {
        uploadService.uploadFile().success(
            function (response) {
                if (response.success) {
                    $scope.image_entity.url = response.message;
                } else {
                    alert(response.message);
                }
            }
        )
    };

    // 初始化
    $scope.entity={ goodsDesc:{itemImages:[], specificationItems: []} };

    // 将当前上传的图片实体存入图片列表
    $scope.add_image_entity = function () {
        $scope.entity.goodsDesc.itemImages.push($scope.image_entity);
    };

    // 移除图片
    $scope.remove_image_entity = function (index) {
        $scope.entity.goodsDesc.itemImages.splice(index, 1);
    }

    // 商品一级分类
    $scope.selectItemCat1List = function () {
        itemCatService.listByParentId(0).success(
            function (response) {
                $scope.itemCat1List = response;
            }
        )
    };

    // 商品二级分类
    $scope.$watch('entity.goods.category1Id', function (newValue, oldValue) {
        $scope.itemCat3List = [];
        $scope.entity.goods.typeTemplateId = '';
        itemCatService.listByParentId(newValue).success(
            function (response) {
                $scope.itemCat2List = response;
            }
        )
    });

    // 商品三级级分类
    $scope.$watch('entity.goods.category2Id', function (newValue, oldValue) {
        itemCatService.listByParentId(newValue).success(
            function (response) {
                $scope.itemCat3List = response;
            }
        )
    });

    // 读取模板ID
    $scope.$watch('entity.goods.category3Id', function (newValue, oldValue) {
        itemCatService.findOne(newValue).success(
            function (response) {
                $scope.entity.goods.typeTemplateId = response.typeId;
            }
        )
    });

    // 读取模板ID, 读取品牌列表, 扩展属性, 规格列表
    $scope.$watch('entity.goods.typeTemplateId', function (newValue, oldValue) {
        typeTemplateService.findOne(newValue).success(
            function (response) {
                $scope.typeTemplate = response; // 模板对象
                $scope.brandIds = JSON.parse($scope.typeTemplate.brandIds); // 品牌列表类型转换
                if ($location.search()['id'] == null) {
                    $scope.entity.goodsDesc.customAttributeItems = JSON.parse($scope.typeTemplate.customAttributeItems); // 扩展属性
                }

            }
        );

        // 查询规格列表
        typeTemplateService.findSpecList(newValue).success(
            function (response) {
                $scope.specList = response;
            }
        );

    });

    $scope.updateSpecAttribute = function($event, name, value){
        var object = $scope.searchObjectByKey($scope.entity.goodsDesc.specificationItems, 'attributeName', name);
        // 已有元素
        if (object != null) {
            if ($event.target.checked) {
                object.attributeValue.push(value);
            } else { // 取消勾选
                object.attributeValue.splice(object.attributeValue.indexOf(value), 1); // 移除选项

                if (object.attributeValue.length == 0) {
                    $scope.entity.goodsDesc.specificationItems.splice($scope.entity.goodsDesc.specificationItems.indexOf(object, 1));
                }
            }
        } else { // 还没有元素
            $scope.entity.goodsDesc.specificationItems.push({'attributeName': name, 'attributeValue': [value]});
        }

        $scope.createItemList();
    };

    // 创建 SKU 列表
    $scope.createItemList = function() {
        $scope.entity.itemList = [{spec: {}, price: 0, num: 99999, status: '0', isDefault: '0' }]; // 列表初始化
        var items = $scope.entity.goodsDesc.specificationItems;
        for (var i = 0; i < items.length; i++) {
            $scope.entity.itemList = addColumn($scope.entity.itemList, items[i].attributeName, items[i].attributeValue);
        }
    };

    // 添加列值
    var addColumn = function (list, columnName, columnValues) {
        var newList = []; // 新的集合
        for (var i = 0; i < list.length; i++) {
            var oldRow = list[i];

            for(var j = 0; j < columnValues.length; j++) {
                var newRow = JSON.parse(JSON.stringify(oldRow)); // 深克隆
                newRow.spec[columnName] = columnValues[j];
                newList.push(newRow);
            }
        }

        return newList;
    };

    // 初始查询条件
    $scope.searchEntity={};

    // 分页查询
    $scope.search = function (currentPage, pageNum) {
        goodsService.search(currentPage, pageNum, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.records;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    };

    // 审核状态
    $scope.status = ['未审核', '已审核', '审核未通过', '关闭'];

    //商品分类列表
    $scope.itemCatList = [];
    // 分类
    $scope.findAllItemCatList = function () {
        itemCatService.findAll().success(
            function (response) {
                for (var i = 0; i < response.length; i++) {
                    $scope.itemCatList[response[i].id] = response[i].name;
                }
            }
        )
    };
    
    // 查询实体
    $scope.findOne = function () {
        var id = $location.search()['id']; // 获取参数值
        if (id == null) {
            return ;
        }
        goodsService.findOne(id).success(
            function(response) {
                $scope.entity = response;
                editor.html($scope.entity.goodsDesc.introduction);  // 商品介绍
                $scope.entity.goodsDesc.itemImages = JSON.parse($scope.entity.goodsDesc.itemImages) // 商品图片列表
                $scope.entity.goodsDesc.customAttributeItems = JSON.parse($scope.entity.goodsDesc.customAttributeItems); // 商品扩展属性
                $scope.entity.goodsDesc.specificationItems = JSON.parse($scope.entity.goodsDesc.specificationItems); // 规格
                for (var i = 0; i < $scope.entity.itemList.length; i++) {
                    $scope.entity.itemList[i].spec = JSON.parse($scope.entity.itemList[i].spec);
                }
            }
        )
    };

    //根据规格名称和选项名称返回是否被勾选
    $scope.checkAttributeValue=function(specName,specValue){
        var obj = $scope.searchObjectByKey($scope.entity.goodsDesc.specificationItems, 'attributeName', specName);
        if (obj != null) {
            if (obj.attributeValue.indexOf(specValue) >= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    };

    // 上/下架
    $scope.updateMarketable = function (id, marketable) {
        goodsService.updateMarketable(id, marketable).success(
            function (response) {
                alert(response.message);
                if (response.success) {
                    $scope.reloadList();
                }
            }
        )
    }
});