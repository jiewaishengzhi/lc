# LeetCode 572 - 另一棵树的子树

## 题目描述

给你两棵二叉树 `root` 和 `subRoot`，判断 `subRoot` 是否是 `root` 的子树。

子树的含义是：

- `root` 中某个节点作为根节点
- 它下面整棵子树的结构和值
- 与 `subRoot` 完全相同

---

## 分析方法：DFS + 判断两棵树是否相同

### 核心思路

这题分成两层判断：

1. 先在 `root` 中寻找“可能作为子树起点的节点”
2. 对每个候选节点，判断“以它为根的树”是否和 `subRoot` 完全相同

所以代码里通常会拆成两个函数：

1. `isSubtree(root, subRoot)`
   负责在整棵 `root` 里不断往下找
2. `isSame(p, q)`
   负责判断两棵树是否完全相同

整体逻辑是：

```text
subRoot 是 root 的子树
=
当前 root 开始完全匹配
或者 root.left 中存在匹配
或者 root.right 中存在匹配
```

也就是：

```text
isSame(root, subRoot)
|| isSubtree(root.left, subRoot)
|| isSubtree(root.right, subRoot)
```

### 为什么要分两个函数

因为这题其实是两个子问题叠加：

1. “在哪个节点开始尝试匹配”
2. “从这个节点开始能不能完全匹配成功”

`isSubtree` 解决第一个问题，`isSame` 解决第二个问题。

---

## 示例推演

输入：

```text
root =         3
              / \
             4   5
            / \
           1   2

subRoot =      4
              / \
             1   2
```

### 第一步：从根节点 3 开始检查

调用：

```text
isSubtree(3, 4)
```

先看：

```text
isSame(3, 4)
```

因为 `3 != 4`，所以当前节点不能作为子树起点。

接着递归去左子树和右子树继续找：

```text
isSubtree(4, 4) || isSubtree(5, 4)
```

### 第二步：检查 root.left，也就是节点 4

调用：

```text
isSubtree(4, 4)
```

先执行：

```text
isSame(4, 4)
```

进入 `isSame` 之后：

```text
isSame(4, 4)
├─ 值相同，继续比较左右子树
├─ isSame(1, 1) -> true
└─ isSame(2, 2) -> true
```

所以 `isSame(4, 4)` 返回 `true`。

这意味着：

```text
以 root 中这个值为 4 的节点为根的整棵树
和 subRoot 完全相同
```

因此整题返回 `true`。

---

## 再看一个容易错的例子

```text
root =         3
              / \
             4   5
            / \
           1   2
              /
             0

subRoot =      4
              / \
             1   2
```

这里很多人会误判成 `true`，但实际是 `false`。

原因是：

- `root` 中虽然有一个值为 `4` 的节点
- 左右孩子看起来也像 `1` 和 `2`
- 但是 `root` 中这个 `2` 下面还有一个额外的 `0`

所以：

```text
isSame(4, 4)
```

在比较到右子树时会发现结构不同，最终返回 `false`。

这也说明本题要求的是：

**结构和值都完全一致，而不是只看一部分像不像。**

---

## 代码实现

```java
public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    if (subRoot == null) return true;
    if (root == null) return false;

    return isSame(root, subRoot)
            || isSubtree(root.left, subRoot)
            || isSubtree(root.right, subRoot);
}

private boolean isSame(TreeNode p, TreeNode q) {
    if (p == null && q == null) return true;
    if (p == null || q == null) return false;

    return p.val == q.val
            && isSame(p.left, q.left)
            && isSame(p.right, q.right);
}
```

---

## 详细执行过程

还是看这个例子：

```text
root =         3
              / \
             4   5
            / \
           1   2

subRoot =      4
              / \
             1   2
```

调用链可以写成：

```text
isSubtree(3, 4)
├─ isSame(3, 4) -> false
├─ isSubtree(4, 4)
│  ├─ isSame(4, 4)
│  │  ├─ isSame(1, 1) -> true
│  │  └─ isSame(2, 2) -> true
│  └─ 返回 true
└─ 因为左侧已经为 true，整题直接返回 true
```

这题的搜索方式本质上是：

```text
在 root 的每个节点都尝试“从这里开始能不能完整匹配 subRoot”
```

---

## 核心流程图

```text
isSubtree(root, subRoot)
        |
        v
subRoot == null ?
| yes -> 返回 true
| no
v
root == null ?
| yes -> 返回 false
| no
v
isSame(root, subRoot) ?
| yes -> 返回 true
| no
v
递归检查左子树 or 递归检查右子树
        |
        v
      返回结果
```

`isSame(p, q)` 的流程：

```text
isSame(p, q)
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
值不同 ?
| yes -> 返回 false
| no
v
比较左子树 && 比较右子树
        |
        v
      返回结果
```

---

## 复杂度分析

| 复杂度 | 值 | 说明 |
|--------|----|------|
| 时间 | O(m * n) | 最坏情况下，root 的很多节点都要和 subRoot 比较 |
| 空间 | O(h) | 主要是递归栈空间 |

说明：

- `m` 是 `root` 的节点数
- `n` 是 `subRoot` 的节点数
- `h` 是递归深度

---

## 关键点总结

1. `isSubtree` 的职责是“找起点”
2. `isSame` 的职责是“从当前起点判断是否完全相同”
3. 判断子树时，不能只看节点值，结构也必须完全一致
4. 递归搜索左右子树时，应该继续调用 `isSubtree`，而不是直接对左右孩子调用 `isSame`
