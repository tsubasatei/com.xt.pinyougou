// 品牌服务
app.service('goodsService', function ($http) {

    // 保存
    this.add = function (entity) {
        return $http.post('/goods', entity);
    };
});