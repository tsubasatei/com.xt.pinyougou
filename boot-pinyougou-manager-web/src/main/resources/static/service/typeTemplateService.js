// 模板服务
app.service('typeTemplateService', function ($http) {
    // 查询列表
    this.findTypeTemplateList = function () {
        return $http.get('/typeTemplate/findOptionList');
    };

    // 分页查询
    this.findPage = function (currentPage, pageNum) {
        return $http.get('/typeTemplate/page?currentPage=' + currentPage + '&pageNum=' + pageNum);
    };

    // 保存/更新
    this.save = function (entity) {
        return $http.post('/typeTemplate', entity);
    };

    // 更新
    this.update = function(entity) {
        return $http({
            method: 'PUT',
            url: '/typeTemplate',
            data: entity
        });
    };

    // 详情
    this.findOne = function (id) {
        return $http.get('/typeTemplate/' + id);
    };

    // 批量删除
    this.deleteBatch = function (ids) {
        return $http({
            method: 'DELETE',
            url: '/typeTemplate/deleteBatch/' + ids
        });
    };

    // 条件查询
    this.search = function (currentPage, pageNum, searchEntity) {
        return $http.post('/typeTemplate/page?currentPage=' + currentPage + '&pageNum=' + pageNum, searchEntity);
    };

    // 查询规格列表
    this.findSpecList = function (id) {
        return $http.get('/typeTemplate/findSpecList/' + id);
    };
});