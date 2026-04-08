# LeetCode 100 - 相同的树

## 题目描述

给你两棵二叉树 `p` 和 `q`，判断它们是否是同一棵树。

这里的“相同”需要同时满足两点：

1. 对应位置的节点值相同
2. 树的结构完全相同

---

## 方法一：递归比较

### 分析思路

这题最直接的做法就是同时递归比较两棵树的对应节点。

对于任意一对节点 `p` 和 `q`：

1. 如果 `p` 和 `q` 都是 `null`，说明这一位置相同，返回 `true`
2. 如果只有一个是 `null`，说明结构不同，返回 `false`
3. 如果两个都不为 `null`，但值不同，返回 `false`
4. 如果当前值相同，则继续递归比较：
   - `p.left` 和 `q.left`
   - `p.right` 和 `q.right`

也就是说，当前节点想相同，必须满足：

```text
当前值相同 && 左子树相同 && 右子树相同
```

### 示例推演

输入：

```text
p =        1
          / \
         2   3

q =        1
          / \
         2   3
```

递归执行过程：

```text
isSameTree(1, 1)
├─ 当前节点都不为空，且 1 == 1，继续比较左右子树
├─ isSameTree(2, 2)
│  ├─ 当前节点都不为空，且 2 == 2
│  ├─ isSameTree(null, null) -> true
│  └─ isSameTree(null, null) -> true
│  返回 true
└─ isSameTree(3, 3)
   ├─ 当前节点都不为空，且 3 == 3
   ├─ isSameTree(null, null) -> true
   └─ isSameTree(null, null) -> true
   返回 true

最终结果：true && true -> true
```

再看一个不相同的例子：

```text
p =        1
          / \
         2   3

q =        1
          / \
         2   4
```

执行到右子树时：

```text
isSameTree(3, 4)
├─ 当前节点都不为空
└─ 3 != 4 -> false
```

所以整题返回 `false`。

### 代码实现

```java
public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
        return true;
    }
    if (p == null || q == null) {
        return false;
    }
    if (p.val != q.val) {
        return false;
    }
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
}
```

### 核心流程图

```text
isSameTree(p, q)
        |
        v
都为 null ?
| yes -> 返回 true
| no
v
一个为 null ?
| yes -> 返回 false
| no
v
节点值不同 ?
| yes -> 返回 false
| no
v
递归比较左子树 && 递归比较右子树
        |
        v
      返回结果
```

---

## 方法二：迭代 + 队列

### 分析思路

递归本质上是在“成对比较节点”，这个过程也可以改成迭代。

做法是把待比较的节点成对放进队列里：

1. 初始时把 `p` 和 `q` 入队
2. 每次从队列中取出两个节点 `node1` 和 `node2`
3. 对这两个节点做和递归同样的判断：
   - 都为 `null`：这一对相同，继续比较下一对
   - 一个为 `null`：返回 `false`
   - 值不同：返回 `false`
4. 如果这一对节点相同，就把它们的左右孩子按相同顺序继续入队：
   - `node1.left` 和 `node2.left`
   - `node1.right` 和 `node2.right`

最后如果队列检查完还没出现不一致，说明两棵树相同。

### 示例推演

输入：

```text
p =        1
          / \
         2   3

q =        1
          / \
         2   3
```

初始状态：

```text
queue = [1, 1]
```

第 1 轮：

```text
取出 1 和 1
值相同
把左孩子入队：2, 2
把右孩子入队：3, 3

queue = [2, 2, 3, 3]
```

第 2 轮：

```text
取出 2 和 2
值相同
入队 null, null, null, null

queue = [3, 3, null, null, null, null]
```

第 3 轮：

```text
取出 3 和 3
值相同
入队 null, null, null, null

queue = [null, null, null, null, null, null, null, null]
```

后续几轮：

```text
每次取出的都是 null, null
说明这些位置相同，直接 continue
```

最终队列为空，返回 `true`。

### 代码实现

```java
public boolean isSameTree2(TreeNode p, TreeNode q) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(p);
    queue.offer(q);

    while (!queue.isEmpty()) {
        TreeNode node1 = queue.poll();
        TreeNode node2 = queue.poll();

        if (node1 == null && node2 == null) {
            continue;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        if (node1.val != node2.val) {
            return false;
        }

        queue.offer(node1.left);
        queue.offer(node2.left);
        queue.offer(node1.right);
        queue.offer(node2.right);
    }
    return true;
}
```

### 核心流程图

```text
isSameTree2(p, q)
        |
        v
  p 和 q 入队
        |
        v
队列非空 ?
| no -> 返回 true
| yes
v
取出两个节点 node1, node2
        |
        v
都为 null ?
| yes -> continue
| no
v
一个为 null ?
| yes -> 返回 false
| no
v
值不同 ?
| yes -> 返回 false
| no
v
左右孩子成对入队
        |
        v
回到 while 循环
```

---

## 两种方法对比

| 方法 | 思路 | 时间复杂度 | 空间复杂度 |
|------|------|------------|------------|
| 递归 | DFS 同步比较 | O(n) | O(h) |
| 迭代 | 队列成对比较 | O(n) | O(n) |

说明：

- `n` 是节点总数
- `h` 是树的高度

---

## 关键点总结

1. 这题比较的不是“值集合”，而是“对应位置是否完全一致”
2. 递归写法最自然，直接比较左右对应节点
3. 迭代写法里，节点必须成对入队、成对出队
4. 当取出的是 `null, null` 时，只能说明这一位置相同，所以要 `continue`，不能提前 `return true`
