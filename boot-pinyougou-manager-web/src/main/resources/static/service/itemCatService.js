// 品牌服务
app.service('itemCatService', function ($http) {
    // 查询品牌列表
    this.findAll = function () {
        return $http.get('/itemCats');
    };

    // 分页查询
    this.findPage = function (currentPage, pageNum) {
        return $http.get('/itemCat/page?currentPage=' + currentPage + '&pageNum=' + pageNum);
    };

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

    // 条件查询
    this.search = function (currentPage, pageNum, searchEntity) {
        return $http.post('/itemCat/page?currentPage=' + currentPage + '&pageNum=' + pageNum, searchEntity);
    };

    // 读取品牌列表
    this.selectBrandList = function () {
        return $http.get('/itemCat/list');
    };

    // 根据上级ID查询子类列表
    this.listByParentId = function (parentId) {
        return $http.get('/itemCat/list/' + parentId);
    };


});