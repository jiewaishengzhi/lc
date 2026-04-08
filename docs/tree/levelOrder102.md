# LeetCode 102 - 二叉树的层序遍历

## 题目描述

给你二叉树的根节点 `root`，返回其节点值的层序遍历结果。

也就是：

- 第一层从左到右
- 第二层从左到右
- 第三层从左到右

最终返回一个二维列表，每一层作为一个子列表。

例如：

```text
root = [3,9,20,null,null,15,7]

返回：
[
  [3],
  [9,20],
  [15,7]
]
```

---

## 分析方法：BFS 层序遍历

### 核心思路

层序遍历最适合用队列来做。

原因是队列先进先出的特性，天然符合“按层从左到右处理节点”。

整体做法：

1. 如果根节点为空，直接返回空列表
2. 创建队列，把根节点入队
3. 当队列不为空时，开始处理当前层
4. 先记录当前层的节点数 `levelSize = queue.size()`
5. 循环 `levelSize` 次，把这一层的节点依次出队
6. 把这些节点的值加入当前层列表 `level`
7. 把它们的左右孩子加入队列，留给下一层处理
8. 当前层处理结束后，把 `level` 放入结果集

关键点是：

```text
每次 while 循环开始时，队列里存的正好是一整层的节点
```

所以只要先记住这一层的节点个数，就可以把每层准确分开。

---

## 示例推演

输入：

```text
        3
       / \
      9  20
        /  \
       15   7
```

初始状态：

```text
result = []
queue = [3]
```

### 第 1 轮 while

当前：

```text
queue = [3]
levelSize = 1
level = []
```

处理这一层的 1 个节点：

```text
弹出 3
level = [3]
把 3 的左孩子 9 入队
把 3 的右孩子 20 入队
```

本轮结束：

```text
level = [3]
result = [[3]]
queue = [9, 20]
```

### 第 2 轮 while

当前：

```text
queue = [9, 20]
levelSize = 2
level = []
```

处理第 1 个节点：

```text
弹出 9
level = [9]
9 没有左右孩子，不入队
```

处理第 2 个节点：

```text
弹出 20
level = [9, 20]
把 20 的左孩子 15 入队
把 20 的右孩子 7 入队
```

本轮结束：

```text
level = [9, 20]
result = [[3], [9, 20]]
queue = [15, 7]
```

### 第 3 轮 while

当前：

```text
queue = [15, 7]
levelSize = 2
level = []
```

处理第 1 个节点：

```text
弹出 15
level = [15]
无孩子
```

处理第 2 个节点：

```text
弹出 7
level = [15, 7]
无孩子
```

本轮结束：

```text
level = [15, 7]
result = [[3], [9, 20], [15, 7]]
queue = []
```

队列为空，遍历结束。

---

## 代码实现

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> level = new ArrayList<>();

        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            level.add(node.val);

            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        result.add(level);
    }

    return result;
}
```

---

## 核心流程图

```text
levelOrder(root)
      |
      v
root == null ?
| yes -> 返回 []
| no
v
根节点入队
      |
      v
队列非空 ?
| no -> 返回 result
| yes
v
记录当前层节点数 levelSize
创建当前层列表 level
      |
      v
循环 levelSize 次
      |
      v
节点出队
加入 level
左孩子非空则入队
右孩子非空则入队
      |
      v
当前层结束后加入 result
      |
      v
回到 while 循环
```

---

## 复杂度分析

| 复杂度 | 值 | 说明 |
|--------|----|------|
| 时间 | O(n) | 每个节点只会入队和出队一次 |
| 空间 | O(n) | 最坏情况下队列需要存一整层节点 |

---

## 关键点总结

1. 层序遍历最适合用 BFS 和队列
2. `queue.size()` 必须在每一层开始前先记录下来
3. 这个 `levelSize` 表示“当前层有多少个节点”，不能在 for 循环里动态取
4. 每处理完一层，就把这一层的结果加入 `result`
