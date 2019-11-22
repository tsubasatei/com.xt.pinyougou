// 品牌服务
app.service('specificationService', function ($http) {
    // 查询品牌列表
    this.findAll = function () {
        return $http.get('/specifications');
    };

    // 分页查询
    this.findPage = function (currentPage, pageNum) {
        return $http.get('/specification/page?currentPage=' + currentPage + '&pageNum=' + pageNum);
    };

    // 保存/更新
    this.save = function (entity) {
        return $http.post('/specification', entity);
    };

    // 更新
    this.update = function(entity) {
        return $http({
            method: 'PUT',
            url: '/specification',
            data: entity
        });
    };

    // 详情
    this.findOne = function (id) {
        return $http.get('/specification/' + id);
    };

    // 批量删除
    this.deleteBatch = function (ids) {
        return $http({
            method: 'DELETE',
            url: '/specification/deleteBatch/' + ids
        });
    };

    // 条件查询
    this.search = function (currentPage, pageNum, searchEntity) {
        return $http.post('/specification/page?currentPage=' + currentPage + '&pageNum=' + pageNum, searchEntity);
    }
});