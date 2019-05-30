//基本二叉搜索树实现
(function(){
    //树节点
    function Node(value){
        this.value=value;
        this.left=null;
        this.right=null;
    }

    //树结构
    function BST(){
        this.root=new Node(null);

        //中序遍历
        this.display=function(node){
            if(node===undefined){
                node=this.root;
            }
            if(node!==null){
                this.display(node.left);
                console.log(node.value);
                this.display(node.right);
            }
        };

        //查找最小值
        this.getMin=function(node){
            let parent;
            while(node.left!==null){
                parent=node;
                node=node.left;
            }
            return {
                node:node,
                parent:parent,
                key:"left"
            }
        };

        //查找
        this.search=function(value){
            let node=this.root;
            let parent=node;
            let key=null;
            while(node!==null){
                parent=node;
                if(node.value<value){
                    key="right";
                    node=node[key];
                }else if(node.value>value){
                    key="left";
                    node=node[key];
                }else{
                    break;
                }
            }
            return {
                parent:parent,
                key:key,
                node:node
            };
        };

        //插入
        this.insert1=function(value){
            if(this.root.value===null){
                this.root=new Node(value);
            }else{
                let node=this.root;
                let parent;
                while(true){
                    parent=node;
                    if(node.value>value){
                        node=node.left;
                        if(node===null){
                            parent.left=new Node(value);
                            break;
                        }
                    }else if(node.value<=value){
                        node=node.right;
                        if(node===null){
                            parent.right=new Node(value);
                            break;
                        }
                    }
                }
            }
        };
        this.insert=function(value){
            if(this.root.value===null){
                this.root=new Node(value);
            }else{
                let info=this.search(value);
                let node=info.node;
                let parent=info.parent;
                let key=info.key;
                if(node===null){
                    parent[key]=new Node(value);
                }else{
                    console.info("插入异常！值"+value+"已存在");
                }
            }
        };

        //删除
        this.delete=function(value){
            //找到node和parent
            let info=this.search(value);
            let node=info.node;
            let parent=info.parent;
            let key=info.key;
            //删除
            if(node){
                //叶子节点
                if(node.left===null && node.right===null){
                    parent[key]=null;
                }
                //有左子树
                if(node.left!==null && node.right===null){
                    parent[key]=node.left;
                }
                //有右子树
                if(node.left===null && node.right!==null){
                    parent[key]=node.right;
                }
                //左右子树都有
                if(node.left!==null && node.right!==null){
                    let minInfo=this.getMin(node);
                    let min=minInfo.node;
                    let minParent=minInfo.parent;
                    let minKey=minInfo.key;
                    node.value=min.value;
                    minParent[minKey]=null;
                }
            }else{
                return false;
            }
        };
    }

    let tree=new BST();
    tree.insert(7);
    tree.insert(5);
    tree.insert(6);
    tree.insert(3);
    tree.insert(4);
    tree.insert(1);
    tree.insert(2);
    tree.insert(9);
    tree.insert(8);
    tree.insert(10);
    tree.insert(7);
    console.log("start to loop tree:");
    tree.display();
})();