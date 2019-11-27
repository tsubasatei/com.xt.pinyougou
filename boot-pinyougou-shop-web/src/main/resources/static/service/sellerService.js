// 品牌服务
app.service('sellerService', function ($http) {

    // 保存/更新
    this.add = function (entity) {
        return $http.post('/seller', entity);
    };
});