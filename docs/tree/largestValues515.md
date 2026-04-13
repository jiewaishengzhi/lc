# LeetCode 515 - 在每个树行中找最大值

## 题目描述

给定一棵二叉树的根节点 `root`，返回一个数组，数组第 `i` 个元素表示第 `i` 层的最大值。

例如：

```text
        1
       / \
      3   2
     / \   \
    5   3   9
```

各层最大值分别是：

- 第 0 层：1
- 第 1 层：3
- 第 2 层：9

返回：

```text
[1, 3, 9]
```

---

## 分析方法

这份代码用了两种方法：

1. DFS 递归
2. BFS 层序遍历

两种方法都围绕同一件事：

```text
求出每一层的最大值
```

---

## 方法一：DFS 递归

### 分析思路

DFS 里通过 `depth` 来判断当前节点属于哪一层。

结果列表 `result` 的含义是：

```text
result.get(depth) 表示第 depth 层目前记录到的最大值
```

递归到某个节点时：

1. 如果这是第一次到达这一层，就直接把当前节点值加入结果
2. 如果这一层已经存在，就用：

```java
Math.max(result.get(depth), node.val)
```

更新这一层最大值

### 示例推演

看这棵树：

```text
        1
       / \
      3   2
     / \   \
    5   3   9
```

初始：

```text
result = []
```

#### 访问节点 1，depth = 0

第 0 层第一次出现：

```text
result = [1]
```

#### 访问节点 3，depth = 1

第 1 层第一次出现：

```text
result = [1, 3]
```

#### 访问节点 5，depth = 2

第 2 层第一次出现：

```text
result = [1, 3, 5]
```

#### 回到上一层，访问节点 3，depth = 2

第 2 层已存在，更新最大值：

```text
max(5, 3) = 5
result = [1, 3, 5]
```

#### 回到根，再访问节点 2，depth = 1

第 1 层已存在：

```text
max(3, 2) = 3
result = [1, 3, 5]
```

#### 访问节点 9，depth = 2

第 2 层更新：

```text
max(5, 9) = 9
result = [1, 3, 9]
```

最终结果：

```text
[1, 3, 9]
```

### 代码实现

```java
public List<Integer> largestValues515(TreeNode root){
    List<Integer> result = new ArrayList<>();
    dfs(root, 0, result);
    return result;
}

private void dfs(TreeNode node, int depth, List<Integer> result) {
    if(node == null) return;

    if(depth == result.size()){
        result.add(node.val);
    }else{
        result.set(depth, Math.max(result.get(depth), node.val));
    }

    dfs(node.left, depth + 1, result);
    dfs(node.right, depth + 1, result);
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
depth == result.size() ?
| yes -> 第一次到这一层，直接加入
| no  -> 用 max 更新这一层最大值
v
递归左子树
v
递归右子树
```

---

## 方法二：BFS 层序遍历

### 分析思路

BFS 更符合题意，因为题目本身就是“每层找最大值”。

每次处理一层时：

1. 记录当前层节点数 `size`
2. 用 `maxValue` 记录这一层的最大值
3. 遍历这一层所有节点，不断更新 `maxValue`
4. 当前层结束后把 `maxValue` 加入结果

### 示例推演

还是上面的树：

```text
        1
       / \
      3   2
     / \   \
    5   3   9
```

初始：

```text
queue = [1]
result = []
```

#### 第 1 层

```text
size = 1
maxValue = -∞
```

处理节点 `1`：

```text
maxValue = 1
queue = [3, 2]
```

当前层结束：

```text
result = [1]
```

#### 第 2 层

```text
size = 2
maxValue = -∞
```

处理 `3`：

```text
maxValue = 3
```

处理 `2`：

```text
maxValue = max(3, 2) = 3
queue = [5, 3, 9]
```

当前层结束：

```text
result = [1, 3]
```

#### 第 3 层

```text
size = 3
maxValue = -∞
```

处理 `5`：

```text
maxValue = 5
```

处理 `3`：

```text
maxValue = 5
```

处理 `9`：

```text
maxValue = 9
```

当前层结束：

```text
result = [1, 3, 9]
```

### 代码实现

```java
public List<Integer> largestValues2(TreeNode root){
    List<Integer> result = new ArrayList<>();

    if(root == null){
        return result;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while(!queue.isEmpty()){
        int size = queue.size();

        int maxValue = Integer.MIN_VALUE;

        for(int i = 0; i < size; i++){
            TreeNode node = queue.poll();
            maxValue = Math.max(maxValue, node.val);

            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
        result.add(maxValue);
    }
    return result;
}
```

### 核心流程图

```text
largestValues2(root)
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
maxValue = Integer.MIN_VALUE
      |
      v
循环处理当前层节点
      |
      v
更新 maxValue
左右孩子入队
      |
      v
当前层结束后加入 maxValue
      |
      v
继续下一层
```

---

## 两种方法对比

| 方法 | 思路 | 时间复杂度 | 空间复杂度 |
|------|------|------------|------------|
| DFS | 用 depth 维护每层最大值 | O(n) | O(h) |
| BFS | 层序遍历逐层找最大值 | O(n) | O(n) |

---

## 关键点总结

1. 题目本质是“每层统计一个值”
2. DFS 的关键是 `depth == result.size()`，表示第一次到这一层
3. BFS 的关键是 `size = queue.size()`
4. `maxValue` 不能初始化为 0，应该用 `Integer.MIN_VALUE`

