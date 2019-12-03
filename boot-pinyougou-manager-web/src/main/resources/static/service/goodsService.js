// 商品服务
app.service('goodsService', function ($http) {

    // 保存
    this.add = function (entity) {
        return $http.post('/goods', entity);
    };

    // 更新
    this.update = function (entity) {
        return $http({
            method: 'PUT',
            url: '/goods',
            data: entity
        });
    };

    // 分页查询
    this.search = function(currentPage, pageNum, searchEntity) {
        return $http.post('/goods/page?currentPage=' + currentPage + '&pageNum=' + pageNum, searchEntity);
    };

    // 查询实体
    this.findOne = function (id) {
        return $http.get('/goods/' + id);
    };

    // 更新状态
    this.updateStatus = function (ids, status) {
        return $http.get('/goods/updateStatus/' + ids + '/' + status);
    };

    // 批量删除
    this.deleteBatch = function (ids) {
        return $http({
            method: 'DELETE',
            url: '/goods/deleteBatch/' + ids
        });
    };
});