(()=>{
    function integerSort(input){
        let l,sort,result,i,max;
        max=Math.max.apply(this,input)+1;
        sort=[];
        result=[];
        for(i=0;i<max;i++){
            sort.push(0);
        }
        for(i=0,l=input.length;i<l;i++){
            sort[input[i]]=1;
        }
        for(i=0;i<max;i++){
            if(sort[i]===1){
                result.push(i);
            }
        }
        return result;
    }

    let input=[12,3,1,7,18,9,2,15];
    integerSort(input);
})();