// 品牌控制器
app.controller('goodsController', function ($scope, goodsService, uploadService) {

    // 保存
    $scope.add = function () {
        $scope.entity.goodsDesc.introduction = editor.html();
        $scope.entity.goodsDesc.itemImages = JSON.stringify($scope.entity.goodsDesc.itemImages);
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

    $scope.entity={ goodsDesc:{itemImages:[]}  };

    // 将当前上传的图片实体存入图片列表
    $scope.add_image_entity = function () {
        $scope.entity.goodsDesc.itemImages.push($scope.image_entity);
    };

    // 移除图片
    $scope.remove_image_entity = function (index) {
        $scope.entity.goodsDesc.itemImages.splice(index, 1);
    }
});