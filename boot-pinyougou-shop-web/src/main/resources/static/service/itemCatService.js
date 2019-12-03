// 品牌服务
app.service('itemCatService', function ($http) {

    // 根据上级ID查询子类列表
    this.listByParentId = function (parentId) {
        return $http.get('/itemCat/list/' + parentId);
    };

    // 详情
    this.findOne = function (id) {
        return $http.get('/itemCat/' + id);
    };

    // 全部列表
    this.findAll = function() {
        return $http.get('/itemCat/list');
    }

});