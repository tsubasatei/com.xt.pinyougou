// 广告分类服务
app.service('contentCategoryService', function ($http) {
    // 查询列表
    this.findAll = function () {
        return $http.get('/contentCategory/list');
    };

    // 保存/更新
    this.save = function (entity) {
        return $http.post('/contentCategory', entity);
    };

    // 更新
    this.update = function(entity) {
        return $http({
            method: 'PUT',
            url: '/contentCategory',
            data: entity
        });
    };

    // 详情
    this.findOne = function (id) {
        return $http.get('/contentCategory/' + id);
    };

    // 批量删除
    this.deleteBatch = function (ids) {
        return $http({
            method: 'DELETE',
            url: '/contentCategory/deleteBatch/' + ids
        });
    };

    // 条件查询
    this.search = function (currentPage, pageNum, searchEntity) {
        return $http.post('/contentCategory/page?currentPage=' + currentPage + '&pageNum=' + pageNum, searchEntity);
    };
});