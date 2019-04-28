(function(){
    /*
     * json拷贝
     * 不能正确复制undefined、NaN、函数等数据类型
     * 可以正确复制数组、普通对象、数字、字符串、布尔值数据类型
     */
    function jsonCopy(origin){
        let target=JSON.stringify(origin);
        target=JSON.parse(target);
        return target;
    }
})();