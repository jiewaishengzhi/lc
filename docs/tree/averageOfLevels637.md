# LeetCode 637 - 二叉树的层平均值

## 题目描述

给定一棵非空二叉树的根节点 `root`，返回一个数组，表示每一层节点值的平均值。

例如：

```text
        3
       / \
      9   20
         /  \
        15   7
```

第 0 层平均值：

```text
3 / 1 = 3.0
```

第 1 层平均值：

```text
(9 + 20) / 2 = 14.5
```

第 2 层平均值：

```text
(15 + 7) / 2 = 11.0
```

最终返回：

```text
[3.0, 14.5, 11.0]
```

---

## 分析方法

这份代码用了两种方法：

1. DFS 递归
2. BFS 层序遍历

两种方法都围绕同一个目标：

```text
统计每一层的“节点和”与“节点个数”
```

最后再做：

```text
平均值 = sum / count
```

---

## 方法一：DFS 递归

### 分析思路

DFS 不能天然按层遍历，但可以在递归时带上 `depth`，表示当前节点位于第几层。

代码里维护了两个列表：

- `sums.get(depth)`：第 `depth` 层节点值之和
- `counts.get(depth)`：第 `depth` 层节点个数

当递归到某个节点时：

1. 如果这是第一次到达该层，就新建这一层的数据
2. 如果这一层已经存在，就更新这一层的和与个数

递归结束后，再统一计算每层平均值。

### 示例推演

还是看：

```text
        3
       / \
      9   20
         /  \
        15   7
```

初始：

```text
sums = []
counts = []
```

#### 访问节点 3，depth = 0

这是第一次到达第 0 层：

```text
sums = [3]
counts = [1]
```

#### 访问节点 9，depth = 1

这是第一次到达第 1 层：

```text
sums = [3, 9]
counts = [1, 1]
```

#### 回到根，再访问节点 20，depth = 1

第 1 层已经存在：

```text
sums[1] = 9 + 20 = 29
counts[1] = 1 + 1 = 2
```

现在：

```text
sums = [3, 29]
counts = [1, 2]
```

#### 访问节点 15，depth = 2

第一次到达第 2 层：

```text
sums = [3, 29, 15]
counts = [1, 2, 1]
```

#### 访问节点 7，depth = 2

第 2 层已存在：

```text
sums[2] = 15 + 7 = 22
counts[2] = 1 + 1 = 2
```

最后得到：

```text
sums = [3, 29, 22]
counts = [1, 2, 2]
```

统一计算平均值：

```text
3 / 1 = 3.0
29 / 2 = 14.5
22 / 2 = 11.0
```

### 代码实现

```java
public List<Double> averageOfLevels(TreeNode root){
    List<Long> sums = new ArrayList<>();
    List<Integer> counts = new ArrayList<>();

    dfs(root, 0, sums, counts);

    List<Double> result = new ArrayList<>();
    for(int i = 0; i < sums.size(); i++){
        result.add((double)sums.get(i) / counts.get(i));
    }
    return result;
}

private void dfs(TreeNode node, int depth, List<Long> sums, List<Integer> counts){
    if(node == null) return;

    if(depth == sums.size()){
        sums.add((long)node.val);
        counts.add(1);
    }else{
        sums.set(depth, sums.get(depth) + node.val);
        counts.set(depth, counts.get(depth) + 1);
    }
    dfs(node.left, depth + 1, sums, counts);
    dfs(node.right, depth + 1, sums, counts);
}
```

### 核心流程图

```text
dfs(node, depth, sums, counts)
        |
        v
node == null ?
| yes -> return
| no
v
depth == sums.size() ?
| yes -> 初始化这一层的 sum 和 count
| no  -> 更新这一层的 sum 和 count
v
递归左子树
v
递归右子树
```

---

## 方法二：BFS 层序遍历

### 分析思路

这题从题意上看最直接的就是按层遍历。

BFS 中：

1. 每一轮 `while` 表示处理一层
2. 用 `size = queue.size()` 记录当前层节点个数
3. 用 `sum` 记录这一层所有节点值之和
4. 当前层处理完后，计算：

```text
sum / size
```

并加入结果

### 示例推演

还是同一棵树：

```text
        3
       / \
      9   20
         /  \
        15   7
```

初始：

```text
queue = [3]
result = []
```

#### 第 1 层

```text
size = 1
sum = 0
```

处理节点 `3`：

```text
sum = 3
queue = [9, 20]
```

当前层结束：

```text
result = [3.0]
```

#### 第 2 层

```text
size = 2
sum = 0
```

处理 `9`：

```text
sum = 9
```

处理 `20`：

```text
sum = 29
queue = [15, 7]
```

当前层结束：

```text
result = [3.0, 14.5]
```

#### 第 3 层

```text
size = 2
sum = 0
```

处理 `15`：

```text
sum = 15
```

处理 `7`：

```text
sum = 22
```

当前层结束：

```text
result = [3.0, 14.5, 11.0]
```

### 代码实现

```java
public List<Double> averageOfLevels2(TreeNode root){
    List<Double> result = new ArrayList<>();
    if(root == null){
        return result;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while(!queue.isEmpty()){
        int size = queue.size();
        double sum = 0;

        for(int i = 0; i < size; i++){
            TreeNode node = queue.poll();
            sum += node.val;

            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
        result.add(sum / size);
    }
    return result;
}
```

### 核心流程图

```text
averageOfLevels2(root)
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
记录当前层节点数 size
sum = 0
      |
      v
循环处理当前层节点
      |
      v
sum += node.val
左右孩子入队
      |
      v
当前层结束后加入 sum / size
      |
      v
继续下一层
```

---

## 两种方法对比

| 方法 | 思路 | 时间复杂度 | 空间复杂度 |
|------|------|------------|------------|
| DFS | 用 depth 统计每层的 sum 和 count | O(n) | O(h) |
| BFS | 层序遍历逐层统计平均值 | O(n) | O(n) |

---

## 关键点总结

1. 无论 DFS 还是 BFS，本质都是在统计每层的和与个数
2. DFS 的关键是 `depth`
3. BFS 的关键是 `size = queue.size()`
4. `sums` 用 `Long` 更稳，避免和过大时溢出

