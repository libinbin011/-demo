class SingleLinked<T> {
    /*
    * 带头结点的单链表初始化，空链表初始化时有头结点，并且头结点的next=null*/

    class Entry<T>(var data: T, var next: Entry<T>? = null)

    var first: Entry<T>? = null
        private set
    val second
        get() = first?.next
    val third
        get() = second?.next
    val last: Entry<T>?
        get() {
            var node = first
            while (node?.next != null)
                node = node.next
            return node
        }

    var length = 0
        private set

    /*
    * 应包含以下基本运算操作：
    *  1. 求链表长度
    *  2. 头插法和尾插法建立链表
    *  3. 按序号查找和按值查找
    *  4. 中间插入操作
    *  5. 删除
    */

    //    头插法
    fun insertFromHead(data: T) {
        length++
        val node = Entry(data, first)
        first = node
    }

    //    尾插法
    fun insertFromTail(data: T) {
        length++
        val node = Entry(data)
        if(first == null)
        {
            first = node
            return
        }
        last?.next = node
    }


    //    按序号查找

    fun findN(i: Int): Entry<T>? {
//        序号小于等于0返回首元节点，大于链表长度返回最后一个节点
        if (i < 1)
            return first

        if (i >= length - 1)
            return last

//        经过上面条件分支执行后  只有0<i<size-1时  才会进入以下流程 while顺链查找 直到j=i时出循环就是第i个元素

        var j = 0
        var node = first

        while (j < i) {
            if(node == null) break
            node = node.next
            j++
        }
        return node
    }

    //    按值查找

    fun findI(data: T): Int {
        var node = first
        var j = 0
        while (node != null) {
            if(node.data == data)
                return j
            node = node.next
            j++
        }
        return -1
    }

    //    中间插入操作
    fun insert(i: Int, data: T) {
        length++
        //    1.如果序号小于等于0直接头插
        if (i < 1) {
            insertFromHead(data)
            return
        }
        //    2.如果序号大于等于链表长度直接尾插
        if (i >= length) {
            insertFromTail(data)
            return
        }
        //    3. 0<i<size 时 才是中间插入

        // 首先找到第i-1个节点
        val precursorNode = findN(i - 1)!!
        // 创建要插入的节点对象,并让next指向原先的第i个节点
        val newNode = Entry(data, precursorNode.next)

        // 补全挂链操作让新节点称为前驱节点的next

        precursorNode.next = newNode

    }

    //    按序号删除
    fun deleteN(i: Int) {
        if(i < 0 || i >= length)
            return
        length--
        if(i == 0) {
            first = second
            return
        }
        val precursorNode = findN(i - 1)!!
        precursorNode.next = precursorNode.next?.next
    }

    //    按值删除第一个
    fun delete(data: T) {
//        val node: Entry<T> = first ?: return
//        if (node.data == data) {
//            length--
//            first = second
//            return
//        }

        var cur: Entry<T>? = first ?: return
        var pre: Entry<T>? = null
        while (cur != null) {
            if(cur.data == data)
                break
            pre = cur
            cur = cur.next
        }

        // 出来循环有2种可能
        // 1.找到前驱节点  node.next.data = data
        // 2.data无效 么有这个节点，当然也么有前驱节点 此时 node.next= NULL
        // (重点理解一下程序执行过程中，不可能出现node.data=data 情况，如果node是最后一个节点，那就说明么有找到，如果可以找到，node最多是尾节点的前驱节点     )
        if (cur == null)        //未找到
            return
        length--
        if (pre == null) {      //第一个结点
            first = second
            return
        }
        pre.next = cur.next
    }

    //  按值删除最后一个
    // 跟删除第一个的差别主要是 找到第一个前驱节点记录下来
    // 接着找 如果还能找到 就更改前驱节点，循环找完整条链表
    // 对这个最终的前驱节点进行操作，
    // 需要注意根本么有data这个节点时找不到前驱节点的操作的操作


    fun deleteLast(data: T) {
//        val node = first ?: return
//        if(node.data == data) {
//            length--
//            first = second
//            return
//        }
//
//        var cur = first
//        var pre: Entry<T>? = null
//        while (cur != null) {
//            if (cur.next?.data == data)
//                pre = cur
//            cur = cur.next
//        }
//        if (pre == null)
//            return
//        length--
//        pre.next = pre.next?.next
    }

    fun clear() {
        first = null
    }


}

fun dayinfengexian(i: Int) {
    var j = 0
    while (j < i) {
        println("***--------------***")
        j++
    }
}


fun main(args: Array<String>) {

    // 测试一个整型链表

    val iSingleLinked = SingleLinked<Int>()

    for (i in 0..99) {
        iSingleLinked.insertFromTail(i)
    }

    println(iSingleLinked.length)
    println(iSingleLinked.last?.data)
    println(iSingleLinked.findI(10))
    println(iSingleLinked.findN(10)?.data)

    dayinfengexian(1)


    iSingleLinked.insertFromHead(8888)
    println(iSingleLinked.findI(0))
    println(iSingleLinked.findI(10))
    println(iSingleLinked.findN(10)?.data)

    dayinfengexian(2)

    iSingleLinked.insertFromTail(6666)
    println(iSingleLinked.last?.data)
    println(iSingleLinked.findI(6666))
    println(iSingleLinked.findN(100)?.data)
    println(iSingleLinked.findN(101)?.data)
    println(iSingleLinked.findN(102)?.data)


    dayinfengexian(3)


}


//fun main(args: Array<String>) {
//    val link = SingleLinked<Int>()
//    link.insertFromHead(1)
//    link.insertFromHead(2)
//    println(link.head.next?.data)
//}