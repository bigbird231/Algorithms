(()=>{
    var input=[-1,6,-5,4,-3,7,-8,20,-3,12,-5];
    var algorithm1,algorithm2,start;

    /*
     * O(n^3)
     */
    algorithm1=function(){
        var i,j,k,l,sum,sumStorage,count,temp,max;
        sumStorage=[];
        count=0;
        for(i=0,l=input.length;i<l;i++){
            for(j=l-i;j>0;j--){
                sum=0;
                temp=[];
                for(k=0;k<j;k++){
                    sum+=input[i+k];
                    temp.push(input[i+k]);
                    //console.log(input[i+k]);
                }
                //console.log(count+++":"+sum);
                sumStorage.push({
                    data:sum,
                    array:temp
                });
            }
        }
        return sumStorage;
    };

    //正序找子集，逆序找子集
    //O(n^2)
    algorithm2=function(){
        var i, j, l,leftSumStorage,rightSumStorage,sum,max,maxIndex,subInput,temp;
        leftSumStorage=[];
        for(i=0,l=input.length;i<l;i++){
            sum=0;
            for(j=i;j<l;j++){
                sum+=input[j];
            }
            leftSumStorage.push(sum);
        }
        i=0;
        max=leftSumStorage[i];
        for(l=leftSumStorage.length;i<l;i++){
            if(max<leftSumStorage[i]){
                max=leftSumStorage[i];
                maxIndex=i;
            }
        }
        subInput=input.slice(maxIndex);
        rightSumStorage=[];
        for(i=subInput.length-1;i>-1;i--){
            sum=0;
            temp=[];
            for(j=i;j>-1;j--){
                sum+=subInput[j];
                temp.push(subInput[j]);
            }
            rightSumStorage.push({
                data:sum,
                array:temp
            });
        }
        return rightSumStorage;
    };


    start=function(){
        var i, l,sum,max;
        //sum=algorithm1();
        sum=algorithm2();
        console.log(sum);
        max=sum[0];
        for(i=1,l=sum.length;i<l;i++){
            if(max.data<sum[i].data){
                max=sum[i];
            }
        }
        console.log(max);
    };

    start();
})();