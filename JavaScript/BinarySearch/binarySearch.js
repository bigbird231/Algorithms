(function(){
    function binarySearch(arr,target){
        var lower,upper,middle,result;
        lower=0;
        upper=arr.length-1;
        while(lower<=upper){
            middle=parseInt((lower+upper)/2);
            if(arr[middle]<target){
                lower=middle+1;
            }else if(arr[middle]>target){
                upper=middle-1;
            }else{
                upper=middle;
                break;
            }
        }
        if(arr[upper]===target){
            result=middle;
        }else{
            result=-1;
        }
        return result;
    }


    let arr1=[];
    let arr2=[1,2,3];
    //-1
    console.log(binarySearch(arr1,2));
    //1
    console.log(binarySearch(arr2,2));
})();