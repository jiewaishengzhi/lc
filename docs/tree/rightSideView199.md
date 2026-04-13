# LeetCode 199 - 二叉树的右视图

## 题目描述

给定一棵二叉树的根节点 `root`，想象自己站在这棵树的右侧，按照从上到下的顺序，返回从右侧所能看到的节点值。

题目的本质是：

```text
每一层最右边的那个节点
```

例如：

```text
        1
       / \
      2   3
       \   \
        5   4
```

从右侧看过去，看到的是：

```text
[1, 3, 4]
```

---

## 分析方法

这份代码用了两种方法：

1. BFS 层序遍历
2. DFS 递归，按“根 -> 右 -> 左”的顺序遍历

这两种方法都能抓住“每层最右边节点”这个核心。

---

## 方法一：BFS 层序遍历

### 分析思路

既然题目要找“每一层最右边的节点”，最直接的办法就是一层一层遍历。

层序遍历时：

1. 每次进入 `while` 循环，先记录当前层节点数 `levelSize`
2. 用 `for` 循环处理这一层所有节点
3. 当处理到这一层的最后一个节点时，也就是：

```java
if (i == levelSize - 1)
```

这个节点就是当前层最右边的节点，把它加入结果即可。

### 示例推演

看这棵树：

```text
        1
       / \
      2   3
       \   \
        5   4
```

初始状态：

```text
result = []
queue = [1]
```

#### 第 1 轮 while

当前层：

```text
queue = [1]
levelSize = 1
```

处理第 1 个节点：

```text
node = 1
i = 0
levelSize - 1 = 0
```

因为 `i == levelSize - 1`，说明 `1` 是这一层最后一个节点，也就是最右边节点。

```text
result = [1]
```

然后把 `1` 的左右孩子入队：

```text
queue = [2, 3]
```

#### 第 2 轮 while

当前层：

```text
queue = [2, 3]
levelSize = 2
```

处理第 1 个节点 `2`：

```text
i = 0，不是这一层最后一个节点
不加入 result
```

`2` 有右孩子 `5`，入队：

```text
queue = [3, 5]
```

处理第 2 个节点 `3`：

```text
i = 1
levelSize - 1 = 1
```

说明 `3` 是这一层最后一个节点，把它加入结果：

```text
result = [1, 3]
```

`3` 有右孩子 `4`，入队：

```text
queue = [5, 4]
```

#### 第 3 轮 while

当前层：

```text
queue = [5, 4]
levelSize = 2
```

处理第 1 个节点 `5`：

```text
i = 0，不加入结果
```

处理第 2 个节点 `4`：

```text
i = 1，是这一层最后一个节点
加入 result
```

结果变成：

```text
result = [1, 3, 4]
```

队列为空，遍历结束。

最终答案：

```text
[1, 3, 4]
```

### 代码实现

```java
public List<Integer> rightSideView(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if(root == null) return result;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while(!queue.isEmpty()){
        int levelSize = queue.size();
        for(int i = 0; i < levelSize; i++){
            TreeNode node = queue.poll();
            if(i == levelSize - 1){
                result.add(node.val);
            }
            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
    }
    return result;
}
```

### 核心流程图

```text
rightSideView(root)
      |
      v
root == null ?
| yes -> 返回 []
| no
v
root 入队
      |
      v
队列非空 ?
| no -> 返回 result
| yes
v
记录当前层节点数 levelSize
      |
      v
循环处理当前层每个节点
      |
      v
当前节点是否为这一层最后一个 ?
| yes -> 加入 result
| no
v
左右孩子入队
      |
      v
继续下一层
```

---

## 方法二：DFS 递归

### 分析思路

除了按层遍历，还可以换个角度：

如果递归时总是优先访问右子树，那么每一层第一次访问到的节点，一定是这一层最右边的节点。

所以 DFS 的核心是：

1. 按 `根 -> 右 -> 左` 的顺序递归
2. 用 `depth` 表示当前节点所在层数
3. 当第一次到达某一层时，把这个节点加入结果

如何判断“第一次到达某一层”？

看：

```java
if (result.size() == depth)
```

这说明当前层还没有记录过节点，而因为我们是先走右边，所以当前节点就是这一层最右边节点。

### 示例推演

还是看这棵树：

```text
        1
       / \
      2   3
       \   \
        5   4
```

调用开始：

```text
dfs(1, 0, result)
result = []
```

#### 访问节点 1，depth = 0

此时：

```text
result.size() = 0
depth = 0
```

满足：

```text
result.size() == depth
```

说明第 0 层还没记录过，加入 `1`：

```text
result = [1]
```

然后先递归右子树：

```text
dfs(3, 1, result)
```

#### 访问节点 3，depth = 1

此时：

```text
result.size() = 1
depth = 1
```

说明第 1 层还没记录过，加入 `3`：

```text
result = [1, 3]
```

继续先递归右子树：

```text
dfs(4, 2, result)
```

#### 访问节点 4，depth = 2

此时：

```text
result.size() = 2
depth = 2
```

说明第 2 层还没记录过，加入 `4`：

```text
result = [1, 3, 4]
```

然后递归 `4` 的右、左子树，都是空，返回。

接着回到节点 `3`，再递归它的左子树，也是空，返回。

回到节点 `1`，现在开始递归左子树：

```text
dfs(2, 1, result)
```

#### 访问节点 2，depth = 1

此时：

```text
result.size() = 3
depth = 1
```

不满足 `result.size() == depth`，说明第 1 层已经记录过了，而且记录的是更靠右的节点 `3`。

所以 `2` 不加入结果。

继续递归右子树：

```text
dfs(5, 2, result)
```

#### 访问节点 5，depth = 2

同理：

```text
result.size() = 3
depth = 2
```

第 2 层已经记录过节点 `4`，所以 `5` 也不加入结果。

最终结果保持为：

```text
[1, 3, 4]
```

### 代码实现

```java
public List<Integer> rightSideView2(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    dfs(root, 0, result);
    return result;
}

private void dfs(TreeNode node, int depth, List<Integer> result) {
    if(node == null) return;

    if(result.size() == depth){
        result.add(node.val);
    }
    dfs(node.right, depth + 1, result);
    dfs(node.left, depth + 1, result);
}
```

### 核心流程图

```text
dfs(node, depth, result)
        |
        v
node == null ?
| yes -> return
| no
v
result.size() == depth ?
| yes -> 说明这一层第一次到达，加入当前节点
| no
v
先递归右子树
        |
        v
再递归左子树
```

---

## 两种方法对比

| 方法 | 思路 | 时间复杂度 | 空间复杂度 |
|------|------|------------|------------|
| BFS | 一层一层遍历，取每层最后一个节点 | O(n) | O(n) |
| DFS | 根右左遍历，每层第一次访问的节点就是右视图 | O(n) | O(h) |

说明：

- `n` 是节点总数
- `h` 是树的高度

---

## 关键点总结

1. 右视图的本质就是“每一层最右边的节点”
2. BFS 解法里，直接取每层最后一个节点最直观
3. DFS 解法里，关键是按“根 -> 右 -> 左”递归
4. `result.size() == depth` 表示当前层第一次被访问到
5. 因为右子树优先，所以第一次访问到的节点一定是这一层最右边的节点
