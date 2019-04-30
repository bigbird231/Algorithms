(function(){
    //递归深拷贝
    function recursiveCopy(obj1,obj2={}){
        for(let i in obj1){
            if(obj1.hasOwnProperty(i)){
                if(Array.isArray(obj1[i])){
                    obj2[i]=[];
                    deepCopy(obj1[i],obj2[i]);
                }else if(typeof obj1[i]==="function"){
                    obj2[i]=obj1[i];
                }else if(obj1[i] instanceof Object){
                    obj2[i]={};
                    deepCopy(obj1[i],obj2[i]);
                }else{
                    obj2[i]=obj1[i];
                }
            }
        }
        return obj2;
    }
})();