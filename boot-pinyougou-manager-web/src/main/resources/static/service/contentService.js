// 广告服务
app.service('contentService', function ($http) {
    // 查询列表
    this.findAll = function () {
        return $http.get('/content/list');
    };

    // 保存/更新
    this.save = function (entity) {
        return $http.post('/content', entity);
    };

    // 更新
    this.update = function(entity) {
        return $http({
            method: 'PUT',
            url: '/content',
            data: entity
        });
    };

    // 详情
    this.findOne = function (id) {
        return $http.get('/content/' + id);
    };

    // 批量删除
    this.deleteBatch = function (ids) {
        return $http({
            method: 'DELETE',
            url: '/content/deleteBatch/' + ids
        });
    };

    // 条件查询
    this.search = function (currentPage, pageNum, searchEntity) {
        return $http.post('/content/page?currentPage=' + currentPage + '&pageNum=' + pageNum, searchEntity);
    };
});