// 品牌服务
app.service('itemCatService', function ($http) {

    // 保存/更新
    this.save = function (entity) {
        return $http.post('/itemCat', entity);
    };

    // 更新
    this.update = function(entity) {
        return $http({
            method: 'PUT',
            url: '/itemCat',
            data: entity
        });
    };

    // 详情
    this.findOne = function (id) {
        return $http.get('/itemCat/' + id);
    };

    // 批量删除
    this.deleteBatch = function (ids) {
        return $http({
            method: 'DELETE',
            url: '/itemCat/deleteBatch/' + ids
        });
    };

    // 根据上级ID查询子类列表
    this.listByParentId = function (parentId) {
        return $http.get('/itemCat/list/' + parentId);
    };

    // 删除
    this.delete = function (id) {
        return $http({
            method: 'DELETE',
            url: '/itemCat/' + id
        });
    }

});