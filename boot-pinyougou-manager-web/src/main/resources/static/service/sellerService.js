// 品牌服务
app.service('sellerService', function ($http) {

    // 保存/更新
    this.add = function (entity) {
        return $http.post('/seller', entity);
    };

    // 分页查询
    this.findPage = function (currentPage, pageNum) {
        return $http.get('/seller/page?currentPage=' + currentPage + '&pageNum=' + pageNum);
    };

    // 更新
    this.update = function(entity) {
        return $http({
            method: 'PUT',
            url: '/seller',
            data: entity
        });
    };

    // 详情
    this.findOne = function (id) {
        return $http.get('/seller/' + id);
    };

    // 批量删除
    this.deleteBatch = function (ids) {
        return $http({
            method: 'DELETE',
            url: '/seller/deleteBatch/' + ids
        });
    };

    // 条件查询
    this.search = function (currentPage, pageNum, searchEntity) {
        return $http.post('/seller/page?currentPage=' + currentPage + '&pageNum=' + pageNum, searchEntity);
    };

    // 更新状态
    this.updateStatus = function(sellerId, status) {
        return $http.get('/seller/updateStatus/' + sellerId + '/' + status);
    }
});