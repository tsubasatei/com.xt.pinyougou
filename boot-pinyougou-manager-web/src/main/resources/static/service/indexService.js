app.service('indexService', function ($http) {
    this.loginName = function () {
        return $http.get('/getLoginName');
    }
});