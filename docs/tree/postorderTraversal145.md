# LeetCode 145 - 二叉树的后序遍历

## 题目描述

给定一棵二叉树的根节点 `root`，返回它的后序遍历结果。

后序遍历顺序是：

```text
左 -> 右 -> 中
```

例如：

```text
    1
     \
      2
     /
    3

后序遍历结果： [3, 2, 1]
```

---

## 分析方法

后序遍历和前序、中序最大的不同是：

- 前序是先处理当前节点
- 中序是左边处理完再处理当前节点
- 后序是左右子树都处理完，最后才处理当前节点

所以这题的本质是：

```text
只有当一个节点的左子树和右子树都已经访问完，才能把它加入结果
```

这份代码用了两种方法：

1. 递归
2. 双栈迭代

按照 `tree.md` 的要求，下面分别展开分析、示例推演、代码和核心流程图。

---

## 方法一：递归

### 分析思路

递归是最自然的写法，因为后序遍历本身就符合“先处理子问题，再处理当前节点”的结构。

对于任意节点 `root`：

1. 先递归遍历左子树
2. 再递归遍历右子树
3. 最后把当前节点值加入结果

也就是：

```text
dfs(root.left)
dfs(root.right)
result.add(root.val)
```

### 示例推演

看一个稍微完整一点的例子：

```text
        1
       / \
      2   3
     / \   \
    4   5   6
```

后序遍历应该是：

```text
[4, 5, 2, 6, 3, 1]
```

详细执行过程：

```text
dfs(1)
├─ 先递归左子树 -> dfs(2)
│  ├─ 先递归左子树 -> dfs(4)
│  │  ├─ dfs(null) -> return
│  │  ├─ dfs(null) -> return
│  │  └─ result.add(4)
│  │     result = [4]
│  ├─ 再递归右子树 -> dfs(5)
│  │  ├─ dfs(null) -> return
│  │  ├─ dfs(null) -> return
│  │  └─ result.add(5)
│  │     result = [4, 5]
│  └─ 左右子树都处理完，加入 2
│     result.add(2)
│     result = [4, 5, 2]
├─ 再递归右子树 -> dfs(3)
│  ├─ dfs(null) -> return
│  ├─ dfs(6)
│  │  ├─ dfs(null) -> return
│  │  ├─ dfs(null) -> return
│  │  └─ result.add(6)
│  │     result = [4, 5, 2, 6]
│  └─ 左右子树都处理完，加入 3
│     result.add(3)
│     result = [4, 5, 2, 6, 3]
└─ 最后加入根节点 1
   result.add(1)
   result = [4, 5, 2, 6, 3, 1]
```

可以看到，每个节点都是“最后”才加入结果，这就是后序遍历的核心。

### 代码实现

```java
public List<Integer> postorderTraversal(TreeNode root){
    List<Integer> result = new ArrayList<>();
    dfs(root, result);
    return result;
}

private void dfs(TreeNode root, List<Integer> result){
    if(root == null){
        return;
    }
    dfs(root.left, result);
    dfs(root.right, result);
    result.add(root.val);
}
```

### 核心流程图

```text
dfs(root)
   |
   v
root == null ?
| yes -> return
| no
v
dfs(root.left)
   |
   v
dfs(root.right)
   |
   v
result.add(root.val)
```

---

## 方法二：双栈迭代

### 分析思路

后序遍历顺序是：

```text
左 -> 右 -> 中
```

它不太适合像前序那样直接“弹出一个节点就加入结果”，因为根节点必须最后处理。

双栈法的核心思想是：

1. 先用第一个栈按 `中 -> 右 -> 左` 的顺序访问节点
2. 再利用第二个栈把顺序反转
3. 最终得到 `左 -> 右 -> 中`

这份代码里的做法是：

1. `stack1` 负责遍历
2. `stack2` 负责接收 `stack1` 弹出的节点
3. 在 `stack1` 中：
   - 当前节点先弹出
   - 压入 `stack2`
   - 再把左孩子压入 `stack1`
   - 再把右孩子压入 `stack1`

因为栈是后进先出，所以：

```text
stack1 实际访问顺序 = 中 -> 右 -> 左
```

然后 `stack2` 再弹出来时，顺序就变成：

```text
左 -> 右 -> 中
```

### 示例推演

用一个更复杂的例子：

```text
          1
        /   \
       2     3
      / \   / \
     4   5 6   7
        / \
       8   9
```

正确的后序遍历结果应该是：

```text
[4, 8, 9, 5, 2, 6, 7, 3, 1]
```

下面详细看两个栈的变化。

初始状态：

```text
stack1 = [1]
stack2 = []
result = []
```

其中，栈顶写在最右边。

#### 第 1 轮

`stack1` 弹出 `1`，压入 `stack2`

```text
stack1 = []
stack2 = [1]
```

把 `1` 的左孩子 `2` 压入 `stack1`，再把右孩子 `3` 压入 `stack1`

```text
stack1 = [2, 3]
stack2 = [1]
```

#### 第 2 轮

`stack1` 弹出 `3`，压入 `stack2`

```text
stack1 = [2]
stack2 = [1, 3]
```

压入 `3` 的左孩子 `6`，再压入右孩子 `7`

```text
stack1 = [2, 6, 7]
stack2 = [1, 3]
```

#### 第 3 轮

弹出 `7`，压入 `stack2`

```text
stack1 = [2, 6]
stack2 = [1, 3, 7]
```

`7` 没有子节点。

#### 第 4 轮

弹出 `6`，压入 `stack2`

```text
stack1 = [2]
stack2 = [1, 3, 7, 6]
```

`6` 没有子节点。

#### 第 5 轮

弹出 `2`，压入 `stack2`

```text
stack1 = []
stack2 = [1, 3, 7, 6, 2]
```

压入 `2` 的左孩子 `4`，再压入右孩子 `5`

```text
stack1 = [4, 5]
stack2 = [1, 3, 7, 6, 2]
```

#### 第 6 轮

弹出 `5`，压入 `stack2`

```text
stack1 = [4]
stack2 = [1, 3, 7, 6, 2, 5]
```

压入 `5` 的左孩子 `8`，再压入右孩子 `9`

```text
stack1 = [4, 8, 9]
stack2 = [1, 3, 7, 6, 2, 5]
```

#### 第 7 轮

弹出 `9`，压入 `stack2`

```text
stack1 = [4, 8]
stack2 = [1, 3, 7, 6, 2, 5, 9]
```

#### 第 8 轮

弹出 `8`，压入 `stack2`

```text
stack1 = [4]
stack2 = [1, 3, 7, 6, 2, 5, 9, 8]
```

#### 第 9 轮

弹出 `4`，压入 `stack2`

```text
stack1 = []
stack2 = [1, 3, 7, 6, 2, 5, 9, 8, 4]
```

此时 `stack1` 为空，第一阶段结束。

这时候 `stack2` 中从底到顶的顺序是：

```text
1, 3, 7, 6, 2, 5, 9, 8, 4
```

也就是 `中 -> 右 -> 左`。

接下来开始第二阶段：不断弹出 `stack2`，加入 `result`。

#### stack2 出栈过程

```text
弹出 4 -> result = [4]
弹出 8 -> result = [4, 8]
弹出 9 -> result = [4, 8, 9]
弹出 5 -> result = [4, 8, 9, 5]
弹出 2 -> result = [4, 8, 9, 5, 2]
弹出 6 -> result = [4, 8, 9, 5, 2, 6]
弹出 7 -> result = [4, 8, 9, 5, 2, 6, 7]
弹出 3 -> result = [4, 8, 9, 5, 2, 6, 7, 3]
弹出 1 -> result = [4, 8, 9, 5, 2, 6, 7, 3, 1]
```

最终结果：

```text
[4, 8, 9, 5, 2, 6, 7, 3, 1]
```

和后序遍历完全一致。

### 代码实现

```java
public List<Integer> postorderTraveral(TreeNode root){
    List<Integer> result = new ArrayList<>();
    if(root == null) return result;

    Stack<TreeNode> stack1 = new Stack<>();
    Stack<TreeNode> stack2 = new Stack<>();

    stack1.push(root);
    while(!stack1.isEmpty()){
        TreeNode node = stack1.pop();
        stack2.push(node);

        if(node.left != null){
            stack1.push(node.left);
        }
        if(node.right != null){
            stack1.push(node.right);
        }
    }

    while(!stack2.isEmpty()){
        result.add(stack2.pop().val);
    }
    return result;
}
```

### 核心流程图

```text
postorderTraveral(root)
        |
        v
root == null ?
| yes -> 返回 []
| no
v
root 压入 stack1
        |
        v
stack1 非空 ?
| no -> 进入 stack2 出栈阶段
| yes
v
弹出 stack1 栈顶 node
压入 stack2
左孩子入 stack1
右孩子入 stack1
        |
        v
继续 while

stack2 非空 ?
| no -> 返回 result
| yes
v
弹出 stack2 栈顶
加入 result
        |
        v
继续 while
```

---

## 两种方法对比

| 方法 | 思路 | 时间复杂度 | 空间复杂度 |
|------|------|------------|------------|
| 递归 | 左右子树处理完再加入当前节点 | O(n) | O(h) |
| 双栈迭代 | 先得到 `中右左`，再反转成 `左右中` | O(n) | O(n) |

说明：

- `n` 是节点总数
- `h` 是树的高度

---

## 关键点总结

1. 后序遍历顺序一定是 `左 -> 右 -> 中`
2. 递归写法最自然，当前节点一定最后加入结果
3. 双栈法的本质是先构造 `中 -> 右 -> 左`，再反转得到后序
4. 在双栈法里，`stack1` 必须先压左孩子、再压右孩子，这样弹出时才是 `右 -> 左`
5. `stack2` 最后弹出的顺序，才是真正的后序遍历顺序
