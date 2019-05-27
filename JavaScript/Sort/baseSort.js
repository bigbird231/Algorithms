(function(){
    //冒泡排序
    //每次冒泡找出一个最大值
    function bubleSort(arr){
        let numElements=arr.length;
        let temp;
        for(let outer=numElements;outer>=2;--outer){
            for(let inner=0;inner<=outer-1;++inner){
                if(arr[inner]>arr[inner+1]){
                    temp=arr[inner];
                    arr[inner]=arr[inner+1];
                    arr[inner+1]=temp;
                }
            }
        }
    }

    //选择排序
    //每次查找右侧的最小值，构建左侧的有序数组
    function selectSort(arr){
        for(let i=0;i<arr.length;i++){
            let min=arr[i];
            let minIndex=i;
            for(let j=i+1;j<arr.length;j++){
                if(arr[j]<min){
                    min=arr[j];
                    minIndex=j;
                }
            }
            arr[minIndex]=arr[i];
            arr[i]=min;
        }
    }

    //插入排序
    //左侧视为有序数组，每次向这个左侧有序数组中插入当前值
    function insertSort(arr){
        for(let i=1;i<arr.length;i++){
            for(let j=i;j>0;j--){
                if(arr[j]<arr[j-1]){
                    let temp=arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=temp;
                }else{
                    break;
                }
            }
        }
    }
    //插入排序-优化写法
    function insertSort1(arr){
        let temp,inner;
        for(let outer=1;outer<=arr.length-1;++outer){
            temp=arr[outer];
            inner=outer;
            while(inner>0 && (arr[inner-1]>=temp)){
                arr[inner]=arr[inner-1];
                --inner;
            }
            arr[inner]=temp;
        }
    }

    /*let testArray=[3,5,2,1,9,6];
     selectSort(testArray);
     console.log(testArray);*/

    //测试类
    let Test=(function(){
        function getRandomArray(length){
            let arr=[];
            for(let i=0;i<length;i++){
                arr.push(Math.floor(Math.random()*100));
            }
            return arr;
        }

        function testAvgRunTime(func,length){
            let amount=0;
            let times=100;
            for(let i=0;i<times;i++){
                let arr=getRandomArray(length);
                let start=new Date().getTime();
                func(arr);
                let end=new Date().getTime();
                amount+=end-start;
            }
            console.log(amount/times);
        }
        
        function testFuncCorrect(funcs){
            let length=1000;
            let arrs=[];
            let originArr=getRandomArray(length);
            for(let i=0;i<funcs.length;i++){
                let arr=[];
                for(let j=0;j<originArr.length;j++){
                    arr.push(originArr[j]);
                }
                arrs.push(arr);
                funcs[i].call(null,arrs[i]);
            }
            for(let i=0;i<arrs.length;i++){
                for(let j=i+1;j<arrs.length;j++){
                    console.log(i,j,arrs[i].toString()===arrs[j].toString());
                }
            }
        }

        return {
            getRandomArray:getRandomArray,
            testAvgRunTime:testAvgRunTime,
            testFuncCorrect:testFuncCorrect
        };
    })();

    //47.88ms
    //Test.testAvgRunTime(insertSort,10000);
    //38.92ms
    //Test.testAvgRunTime(insertSort1,10000);
    //38.71
    //Test.testAvgRunTime(selectSort,10000);
    //288.07
    //Test.testAvgRunTime(bubleSort,10000);

    //验证算法正确性
    //Test.testFuncCorrect([bubleSort,selectSort,insertSort,insertSort1]);
})();