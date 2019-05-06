(function(){
    //裴波那契数列
    function fibonacci(n){
        if(n===1){
            return 1;
        }
        if(n===2){
            return 1;
        }
        let result=fibonacci(n-1)+fibonacci(n-2);
        return result;
    }

    //尾递归优化版本
    function tailFibo(n,prev,next){
        if(n===0){
            return next;
        }
        if(next===undefined){
            prev=0;
            next=1;
        }else{
            let temp=next;
            next=temp+prev;
            prev=temp;
        }
        return tailFibo(n-1,prev,next);
    }
    
    //或
    function tailFibo1(n,prev,next){
        if(next===undefined){
            prev=0;
            next=1;
        }else{
            let temp=next;
            next=temp+prev;
            prev=temp;
        }
        if(n===0){
            return prev;
        }
        return tailFibo(n-1,prev,next);
    }
})();
