# LeetCode 429 - N 叉树的层序遍历

## 题目描述

给定一个 N 叉树的根节点 `root`，返回其节点值的层序遍历结果。

层序遍历的意思是：

- 第一层从左到右
- 第二层从左到右
- 第三层从左到右
- 每一层单独放进一个列表中

例如：

```text
        1
      / | \
     3  2  4
    / \
   5   6
```

返回结果为：

```text
[
  [1],
  [3, 2, 4],
  [5, 6]
]
```

---

## 分析方法

这题和二叉树的层序遍历本质一样，核心仍然是：

```text
BFS + 队列
```

区别只在于：

- 二叉树每个节点最多有两个孩子：`left` 和 `right`
- N 叉树每个节点有一个 `children` 列表

所以这题的处理方式是：

1. 如果 `root == null`，直接返回空结果
2. 创建队列，把根节点入队
3. 每次进入 `while` 循环时，先记录当前层节点个数 `size`
4. 用 `for` 循环只处理这一层的 `size` 个节点
5. 每处理一个节点：
   - 把节点值加入当前层列表
   - 再把它的所有孩子加入队列
6. 当前层处理完后，把这一层加入结果集

关键点是：

```text
每次 while 开始时，队列中存放的正好是一整层的节点
```

所以先记录 `queue.size()`，就能把每一层准确拆开。

---

## 示例推演

看这棵树：

```text
        1
      / | \
     3  2  4
    / \
   5   6
```

### 初始状态

```text
result = []
queue = [1]
```

---

### 第 1 轮 while

当前：

```text
queue = [1]
size = 1
level = []
```

处理这一层的第 1 个节点：

```text
取出 1
level = [1]
把 1 的所有孩子 3, 2, 4 加入队列
```

这一轮结束后：

```text
level = [1]
result = [[1]]
queue = [3, 2, 4]
```

---

### 第 2 轮 while

当前：

```text
queue = [3, 2, 4]
size = 3
level = []
```

处理第 1 个节点 `3`：

```text
取出 3
level = [3]
把 3 的孩子 5, 6 加入队列
queue = [2, 4, 5, 6]
```

处理第 2 个节点 `2`：

```text
取出 2
level = [3, 2]
2 没有孩子，不入队
queue = [4, 5, 6]
```

处理第 3 个节点 `4`：

```text
取出 4
level = [3, 2, 4]
4 没有孩子，不入队
queue = [5, 6]
```

这一轮结束后：

```text
level = [3, 2, 4]
result = [[1], [3, 2, 4]]
queue = [5, 6]
```

---

### 第 3 轮 while

当前：

```text
queue = [5, 6]
size = 2
level = []
```

处理第 1 个节点 `5`：

```text
取出 5
level = [5]
5 没有孩子
```

处理第 2 个节点 `6`：

```text
取出 6
level = [5, 6]
6 没有孩子
```

这一轮结束后：

```text
level = [5, 6]
result = [[1], [3, 2, 4], [5, 6]]
queue = []
```

队列为空，遍历结束。

最终答案：

```text
[[1], [3, 2, 4], [5, 6]]
```

---

## 代码实现

```java
public class levelOrder429 {
    class Node {
        public int val;
        public List<Node> children;
    }

    public List<List<Integer>> levelOrder(Node root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for(int i = 0; i < size; i++){
                Node node = queue.poll();
                level.add(node.val);

                if (node.children != null) {
                    for (Node child : node.children) {
                        queue.offer(child);
                    }
                }
            }

            result.add(level);
        }
        return result;
    }
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
记录当前层节点数 size
创建当前层列表 level
      |
      v
循环 size 次
      |
      v
节点出队
加入 level
遍历 children
把每个孩子入队
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
| 空间 | O(n) | 最坏情况下队列中会同时存放一整层节点 |

其中 `n` 是整棵 N 叉树的节点总数。

---

## 关键点总结

1. 这题本质上就是 N 叉树版本的层序遍历
2. 仍然使用 BFS 和队列
3. `queue.size()` 记录的是当前层节点数，不是整棵树剩余节点数
4. 当前层处理时，把所有孩子节点加入队列，留给下一层处理
5. 需要判断 `children != null`，避免空指针异常
