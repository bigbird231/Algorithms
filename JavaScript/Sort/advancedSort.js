(function(){
    //希尔排序
    //对插入排序的优化，设置一个间隔数组，由大间隔到小间隔对数组进行排序以提升效率。如果间隔为1，则退化为插入排序
    function shellSort(arr){
        let N=arr.length;
        let h=1;
        while(h<N/3){
            h=3*h+1;
        }
        while(h>=1){
            for(let i=h;i<N;i++){
                for(let j=i;j>=h && (arr[j]<arr[j-h]); j-=h){
                    let temp=arr[j];
                    arr[j]=arr[j-h];
                    arr[j-h]=temp;
                }
            }
            h=(h-1)/3;
        }
    }

    //快速排序
    //7.8ms
    //以一个基准值，将数组分成左右两组，分别存放小的值和大的值；递归重复步骤
    function quickSort(arr){
        if(arr.length<=1){
            return arr;
        }
        let small=[];
        let big=[];
        let pivot=arr[0];
        for(let i=1;i<arr.length;i++){
            if(arr[i]<pivot){
                small.push(arr[i]);
            }else{
                big.push(arr[i]);
            }
        }
        return quickSort(small).concat(pivot,quickSort(big));
    }
    //快速排序-优化写法
    //1.7ms
    //不额外用数组存放，直接在原数组中进行交换，并且使用经典的“左右互换”模式
    function quickSort1(arr,start,end){
        if(start===undefined){
            start=0;
        }
        if(end===undefined){
            end=arr.length-1;
        }
        if(start>=end){
            return ;
        }
        let pivot=start;
        let upper=end;
        let lower=start;
        let temp;
        while(lower<upper){
            while(arr[upper]>=arr[pivot] && lower<upper){
                upper--;
            }
            if(arr[upper]<arr[pivot]){
                temp=arr[pivot];
                arr[pivot]=arr[upper];
                arr[upper]=temp;
                pivot=upper;
            }
            while(arr[lower]<=arr[pivot] && lower<upper){
                lower++;
            }
            if(arr[lower]>arr[pivot]){
                temp=arr[pivot];
                arr[pivot]=arr[lower];
                arr[lower]=temp;
                pivot=lower;
            }
        }
        quickSort1(arr,start,pivot-1);
        quickSort1(arr,pivot+1,end);
    }

    //二分查找
    //二分查找基于有序数据，经常和排序算法一起使用
    function binarySearch(arr,data){
        let upper=arr.length-1;
        let lower=0;
        while(lower<=upper){
            let mid=Math.floor((upper+lower)/2);
            if(arr[mid]<data){
                lower=mid+1;
            }else if(arr[mid]>data){
                upper=mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    //let arr=[3,5,2,1,9,6];
    let arr=[9,3,93,9,65,94,50,90,12,65];
    let arr1=quickSort1(arr);
    console.log(arr);
    //console.log(binarySearch(arr1,90));
})();