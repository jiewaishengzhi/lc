package hot100.linklist;

import java.util.HashMap;
import java.util.Map;

/*
请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
实现 LRUCache 类：
LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，
则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
示例：
输入
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]

解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4
 */
public class LRUCache {
    private class Node{
        int key;
        int value;
        Node prev;
        Node next;
        Node(){}
        Node(int key,int value){
            this.key=key;
            this.value=value;
        }
    }
    private final int capacity;
    private final Map<Integer,Node> map;

    //虚拟头节点和虚拟尾节点  head后面是最近使用的节点  tail前面是最久未使用的节点
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        this.head = new Node();
        this.tail = new Node();

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node=map.get(key);
        if(node==null)return -1;

        //被访问过 移动到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node=map.get(key);
        if(node!=null){
            //key已存在 更新value 刷新未最近使用
            node.value=value;
            moveToHead(node);
            return;
        }
        //key不存在 创建新节点
        Node newNode=new Node(key,value);
        map.put(key,newNode);
        addToHead(newNode);

        if(map.size()>capacity){
            Node removed=removeTail();
            map.remove(removed.key);
        }
    }

    //把节点移动到头部
    private void moveToHead(Node node){
        removeNode(node);
        addToHead(node);
    }

    //把节点从当前位置删除
    private void removeNode(Node node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    //把节点插入到head后面
    private void addToHead(Node node){
        node.prev=head;
        node.next=head.next;

        head.next.prev=node;
        head.next=node;
    }

    //删除尾部真实节点(最久未使用的节点)
    private Node removeTail(){
        Node node=tail.prev;
        removeNode(node);
        return node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
