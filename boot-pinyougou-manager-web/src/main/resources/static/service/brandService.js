// 品牌服务
app.service('brandService', function ($http) {
    // 查询品牌列表
    this.findAll = function () {
        return $http.get('/brands');
    };

    // 分页查询
    this.findPage = function (currentPage, pageNum) {
        return $http.get('/brand/page?currentPage=' + currentPage + '&pageNum=' + pageNum);
    };

    // 保存/更新
    this.save = function (entity) {
        return $http.post('/brand', entity);
    };

    // 更新
    this.update = function(entity) {
        return $http({
            method: 'PUT',
            url: '/brand',
            data: entity
        });
    };

    // 详情
    this.findOne = function (id) {
        return $http.get('/brand/' + id);
    };

    // 批量删除
    this.deleteBatch = function (ids) {
        return $http({
            method: 'DELETE',
            url: '/brand/deleteBatch/' + ids
        });
    };

    // 条件查询
    this.search = function (currentPage, pageNum, searchEntity) {
        return $http.post('/brand/page?currentPage=' + currentPage + '&pageNum=' + pageNum, searchEntity);
    };

    // 读取品牌列表
    this.selectBrandList = function () {
        return $http.get('/brand/list');
    }
});