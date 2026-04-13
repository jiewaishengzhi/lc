# LeetCode 116 - 填充每个节点的下一个右侧节点指针

## 题目描述

给你一棵完美二叉树的根节点 `root`。

完美二叉树的特点是：

- 每个父节点都有两个孩子
- 所有叶子节点都在同一层

要求把每个节点的 `next` 指针指向它右边相邻的节点；如果当前节点已经是这一层最右边的节点，那么它的 `next` 指向 `null`。

例如：

```text
        1
      /   \
     2     3
    / \   / \
   4   5 6   7
```

连接后应变成：

```text
1 -> null
2 -> 3 -> null
4 -> 5 -> 6 -> 7 -> null
```

---

## 分析方法

这份代码用了两种方法：

1. BFS 层序遍历
2. 利用完美二叉树性质，用已经建立好的 `next` 指针横向遍历

两种方法都能做出来，但第二种更体现 116 这题的特点。

---

## 方法一：BFS 层序遍历

### 分析思路

既然题目要求连接同一层的相邻节点，那么最直接的做法就是一层一层遍历。

做法如下：

1. 用队列做层序遍历
2. 每次处理一整层
3. 用变量 `pre` 记录当前层前一个节点
4. 当前节点出队后，如果 `pre != null`，就执行：

```java
pre.next = node;
```

这样就把前一个节点和当前节点连起来了。

当前层最后一个节点右边没有节点，所以它的 `next` 默认保持 `null`。

### 示例推演

看这棵树：

```text
        1
      /   \
     2     3
    / \   / \
   4   5 6   7
```

初始状态：

```text
queue = [1]
```

#### 第 1 层

```text
size = 1
pre = null
```

处理节点 `1`：

```text
pre 为空，不连接
pre = 1
把 2 和 3 入队
queue = [2, 3]
```

这一层结束后：

```text
1.next = null
```

#### 第 2 层

```text
queue = [2, 3]
size = 2
pre = null
```

处理节点 `2`：

```text
pre 为空，不连接
pre = 2
把 4 和 5 入队
queue = [3, 4, 5]
```

处理节点 `3`：

```text
pre = 2
执行 2.next = 3
pre = 3
把 6 和 7 入队
queue = [4, 5, 6, 7]
```

这一层结束后：

```text
3.next = null
```

#### 第 3 层

```text
queue = [4, 5, 6, 7]
size = 4
pre = null
```

处理 `4`：

```text
pre = 4
```

处理 `5`：

```text
4.next = 5
pre = 5
```

处理 `6`：

```text
5.next = 6
pre = 6
```

处理 `7`：

```text
6.next = 7
pre = 7
```

这一层结束后：

```text
7.next = null
```

最终连接结果正确。

### 代码实现

```java
public Node connect(Node root){
    if(root == null){
        return null;
    }

    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()){
        int size = queue.size();
        Node pre = null;

        for(int i = 0; i < size; i++){
            Node node = queue.poll();

            if(pre != null){
                pre.next = node;
            }
            pre = node;

            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
    }
    return root;
}
```

### 核心流程图

```text
connect(root)
    |
    v
root == null ?
| yes -> return null
| no
v
root 入队
    |
    v
队列非空 ?
| no -> return root
| yes
v
记录当前层节点数 size
pre = null
    |
    v
循环处理当前层节点
    |
    v
pre != null ?
| yes -> pre.next = 当前节点
| no
v
更新 pre = 当前节点
左右孩子入队
    |
    v
继续下一层
```

---

## 方法二：利用完美二叉树性质

### 分析思路

这题给的是完美二叉树，所以每个非叶子节点一定都有：

- `left`
- `right`

这就可以得到两条非常关键的连接规则：

1. 同一个父节点下面：

```java
node.left.next = node.right;
```

2. 跨父节点连接：

```java
node.right.next = node.next.left;
```

第二条的前提是：

```java
node.next != null
```

因为只有当前节点右边还有相邻节点时，才能把当前节点的右孩子连到“右侧相邻父节点的左孩子”。

整套做法是：

1. `leftMost` 指向当前层最左边节点
2. 用 `cur` 沿着当前层的 `next` 指针横向移动
3. 在移动过程中，把下一层的 `next` 全部建立起来
4. 当前层结束后，让 `leftMost = leftMost.left`，进入下一层

### 示例推演

还是看这棵树：

```text
        1
      /   \
     2     3
    / \   / \
   4   5 6   7
```

#### 第一轮

```text
leftMost = 1
cur = 1
```

连接同父节点的左右孩子：

```text
1.left.next = 1.right
2.next = 3
```

因为 `1.next == null`，所以不做跨父节点连接。

这一轮结束后：

```text
2 -> 3 -> null
```

然后进入下一层：

```text
leftMost = 2
```

#### 第二轮

当前层已经有：

```text
2 -> 3 -> null
```

先处理 `cur = 2`：

1. 连同父节点左右孩子：

```text
4.next = 5
```

2. 因为 `2.next = 3`，所以可以跨父节点连接：

```text
5.next = 6
```

然后 `cur = cur.next = 3`

处理 `cur = 3`：

1. 连同父节点左右孩子：

```text
6.next = 7
```

2. 因为 `3.next == null`，所以不再跨父节点连接

这一轮结束后：

```text
4 -> 5 -> 6 -> 7 -> null
```

然后进入下一层：

```text
leftMost = 4
```

因为 `4.left == null`，说明已经到叶子层，循环结束。

### 代码实现

```java
public Node connect2(Node root){
    if(root == null) return null;

    Node leftMost = root;

    while(leftMost.left != null){
        Node cur = leftMost;

        while(cur != null){
            cur.left.next = cur.right;

            if (cur.next != null) {
                cur.right.next = cur.next.left;
            }

            cur = cur.next;
        }

        leftMost = leftMost.left;
    }
    return root;
}
```

### 核心流程图

```text
connect2(root)
    |
    v
root == null ?
| yes -> return null
| no
v
leftMost = root
    |
    v
leftMost.left != null ?
| no -> return root
| yes
v
cur = leftMost
    |
    v
cur != null ?
| no -> leftMost = leftMost.left
| yes
v
cur.left.next = cur.right
    |
    v
cur.next != null ?
| yes -> cur.right.next = cur.next.left
| no
v
cur = cur.next
    |
    v
继续当前层
```

---

## 两种方法对比

| 方法 | 思路 | 时间复杂度 | 空间复杂度 |
|------|------|------------|------------|
| BFS | 层序遍历逐层连接 | O(n) | O(n) |
| 利用完美二叉树性质 | 横向扫当前层，建立下一层连接 | O(n) | O(1) |

---

## 关键点总结

1. 题目的关键前提是“完美二叉树”
2. BFS 解法最直观，逐层连即可
3. `connect2` 的核心是两条连接规则：
   - `left.next = right`
   - `right.next = node.next.left`
4. 第二种方法只适用于 116，不能直接照搬到 117

