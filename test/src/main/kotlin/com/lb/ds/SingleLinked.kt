package com.lb.ds

class SingleLinked<T> {
    /*
    * 带头结点的单链表初始化，空链表初始化时有头结点，并且头结点的next=null*/

    class Entry<T>( var data:T?= null, var next:Entry<T>? = null){
    }

    var head:Entry<T> = Entry()

    private var firstNode = head.next

/*
* 应包含以下基本运算操作：
*  1. 求链表长度
*  2. 头插法和尾插法建立链表
*  3. 按序号查找和按值查找
*  4. 中间插入操作
*  5. 删除
*/
    //    找到表尾元素
    fun findLast(): Entry<T>{
        var node = firstNode
        while (node?.next != null){
            node = node.next
        }
        return node!!
}

    //    求链表长度
    fun length():Int{
        var node = firstNode
        var j = 0

        while (node != null){
            node = node.next
            j++

        }
        return j

    }
    //    头插法
    fun createFromHead(data:T){
        var node = Entry(data,firstNode)
        firstNode = node
    }
    //    尾插法
    fun createFromTail(data:T){
        if (firstNode == null){
            firstNode = Entry(data)
            return
        }


        var lastNode = findLast()

        lastNode.next = Entry(data)
    }


    //    按序号查找

    fun findN(i:Int): Entry<T>? {

        var size = length()

        if (size== 0){
            return null
    }

//        序号小于等于0返回首元节点，大于链表长度返回最后一个节点

        if (i<1){
            return firstNode
        }

        if (i>=size-1){
            return findLast()
        }

//        经过上面条件分支执行后  只有0<i<size-1时  才会进入以下流程 while顺链查找 直到j=i时出循环就是第i个元素

        var j = 0
        var node = firstNode

        while ( j<i){
            node = node!!.next
            j++
        }
        return node
    }

    //    按值查找

    fun findI(data:T):Int{
        var node = firstNode
        var j = 0
        while (node?.data!= data&&node?.next!=null){
            node = node.next
            j++
        }
        if (node?.data==data){
            return j
        }else{
            return -1
        }
    }
    //    中间插入操作
    fun insert(i:Int,data: T){

        //    1.如果序号小于等于0直接头插
        if(i<1){
            createFromHead(data)
        }
        //    2.如果序号大于等于链表长度直接尾插
        if(i>= length()){
            createFromTail(data)
        }
        //    3. 0<i<size 时 才是中间插入

        // 首先找到第i-1个节点
        var precursorNode = findN(i-1)

        // 创建要插入的节点对象,并让next指向原先的第i个节点
        var newNode = Entry<T>(data,precursorNode?.next)

        // 补全挂链操作让新节点称为前驱节点的next

        precursorNode?.next = newNode

    }

    //    按序号删除
    fun delete(i: Int){
        var precursorNode = findN(i-1)

        precursorNode?.next = precursorNode?.next?.next
    }

    //    按值删除第一个
    fun delete(data: T){
        if (firstNode ==null){
            return
        }
        var node = head
        while (node.next?.data!=data&&node.next!=null){
            node = node.next!!
        }

        // 出来循环有2种可能
            // 1.找到前驱节点  node.next.data = data
            // 2.data无效 么有这个节点，当然也么有前驱节点 此时 node.next= NULL
            // (重点理解一下程序执行过程中，不可能出现node.data=data 情况，如果node是最后一个节点，那就说明么有找到，如果可以找到，node最多是尾节点的前驱节点     )

        if (node.next == null){
            return
        }

        node.next = node.next?.next
    }

    //  按值删除最后一个
        // 跟删除第一个的差别主要是 找到第一个前驱节点记录下来
        // 接着找 如果还能找到 就更改前驱节点，循环找完整条链表
        // 对这个最终的前驱节点进行操作，
        // 需要注意根本么有data这个节点时找不到前驱节点的操作的操作


    fun deleteLast(data: T){
        if(firstNode == null){
            return
        }
        var node = head
        var precursorNode:Entry<T>? = null
        while (node.next!=null){
            if (node.next?.data ==data){
                precursorNode = node
            }
            node = node.next!!
        }
        if (precursorNode == null){
            return
        }
        precursorNode.next = precursorNode.next?.next

    }

    fun clear(){
        firstNode = null
    }



}

fun dayinfengexian(i: Int){
    var j = 0
    while (j<i){
        println("***--------------***")
        j++
    }
}


fun main(args: Array<String>) {

    // 测试一个整型链表

    var iSingleLinked = SingleLinked<Int>()

    for (i in 0..99){
        iSingleLinked.createFromTail(i)
    }

    println(iSingleLinked.length())
    println(iSingleLinked.findLast()?.data)
    println(iSingleLinked.findI(10))
    println(iSingleLinked.findN(10)?.data)

    dayinfengexian(1)


    iSingleLinked.createFromHead(8888)
    println(iSingleLinked.findI(0))
    println(iSingleLinked.findI(10))
    println(iSingleLinked.findN(10)?.data)

    dayinfengexian(2)

    iSingleLinked.createFromTail(6666)
    println(iSingleLinked.findLast()?.data)
    println(iSingleLinked.findI(6666))
    println(iSingleLinked.findN(100)?.data)
    println(iSingleLinked.findN(101)?.data)
    println(iSingleLinked.findN(102)?.data)


    dayinfengexian(3)




}