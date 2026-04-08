# LeetCode 105 - 从前序与中序遍历序列构造二叉树

## 题目描述

给定两个整数数组 `preorder` 和 `inorder`，其中 `preorder` 是二叉树的**前序遍历**，`inorder` 是同一棵树的**中序遍历**，请构造二叉树并返回其根节点。

---

## 分析方法

### 核心原理

#### 前序遍历特点
```
[根节点, 左子树, 右子树]
```
- **第一个元素一定是根节点**
- 无法直接区分左右子树的边界

#### 中序遍历特点
```
[左子树, 根节点, 右子树]
```
- 根节点将数组分为左右两部分
- 左边是左子树，右边是右子树

#### 构造策略

1. 从 `preorder` 取出当前根节点（preIndex 位置的元素）
2. 在 `inorder` 中找到该根节点的位置
3. 根据位置将 `inorder` 分为左子树和右子树
4. 递归处理左子树和右子树

### 关键技巧

使用 **HashMap** 存储 `inorder` 的值到索引的映射，将查找操作从 O(n) 优化到 O(1)。

---

## 示例推演

### 输入数据

```
preorder = [3, 9, 20, 15, 7]
inorder  = [9, 3, 15, 20, 7]
```

### 构建索引映射

```
inorderIndexMap:
  9 → 0
  3 → 1
  15 → 2
  20 → 3
  7 → 4
```

### 详细执行过程

```
初始状态: preIndex = 0
         inLeft = 0, inRight = 4

══════════════════════════════════════════════════════════════
【第1次递归】build(preorder, 0, 4)
══════════════════════════════════════════════════════════════

inLeft=0, inRight=4 → 0 ≤ 4，继续

Step 1: 取根节点
        preorder[preIndex=0] = 3
        preIndex++ → preIndex = 1
        rootVal = 3

Step 2: 创建根节点
        TreeNode(3)

Step 3: 在中序中找根位置
        inorderIndexMap.get(3) = 1
        inRootIdx = 1

Step 4: 确定左右子树范围
        左子树: inorder[0, 0] → [9]
        右子树: inorder[2, 4] → [15, 20, 7]

        inorder: [9, 3, 15, 20, 7]
                  ↑  ↑   ↑   ↑  ↑
                  0  1   2   3  4
                  左 根  ──右子树──

Step 5: 递归构建左子树 → build(preorder, 0, 0)

══════════════════════════════════════════════════════════════
【第2次递归】build(preorder, 0, 0) ← 构建节点9
══════════════════════════════════════════════════════════════

inLeft=0, inRight=0 → 0 ≤ 0，继续

Step 1: 取根节点
        preorder[preIndex=1] = 9
        preIndex++ → preIndex = 2
        rootVal = 9

Step 2: 创建根节点
        TreeNode(9)

Step 3: 在中序中找根位置
        inorderIndexMap.get(9) = 0
        inRootIdx = 0

Step 4: 确定左右子树范围
        左子树: inorder[0, -1] → 空（inLeft > inRight）
        右子树: inorder[1, 0] → 空（inLeft > inRight）

Step 5: 递归左子树 → build(preorder, 0, -1) → 返回 null
Step 6: 递归右子树 → build(preorder, 1, 0) → 返回 null

返回: TreeNode(9) [无子节点]

        9
       / \
     null null

══════════════════════════════════════════════════════════════
【回到第1次递归】继续 Step 6
══════════════════════════════════════════════════════════════

root.left = TreeNode(9)

Step 6: 递归构建右子树 → build(preorder, 2, 4)

══════════════════════════════════════════════════════════════
【第3次递归】build(preorder, 2, 4) ← 构建节点20
══════════════════════════════════════════════════════════════

inLeft=2, inRight=4 → 2 ≤ 4，继续

Step 1: 取根节点
        preorder[preIndex=2] = 20
        preIndex++ → preIndex = 3
        rootVal = 20

Step 2: 创建根节点
        TreeNode(20)

Step 3: 在中序中找根位置
        inorderIndexMap.get(20) = 3
        inRootIdx = 3

Step 4: 确定左右子树范围
        左子树: inorder[2, 2] → [15]
        右子树: inorder[4, 4] → [7]

        inorder: [9, 3, 15, 20, 7]
                      ↑   ↑   ↑
                      2   3   4
                     左  根   右

Step 5: 递归左子树 → build(preorder, 2, 2)

══════════════════════════════════════════════════════════════
【第4次递归】build(preorder, 2, 2) ← 构建节点15
══════════════════════════════════════════════════════════════

inLeft=2, inRight=2 → 2 ≤ 2，继续

Step 1: 取根节点
        preorder[preIndex=3] = 15
        preIndex++ → preIndex = 4
        rootVal = 15

Step 2: 创建根节点
        TreeNode(15)

Step 3: 在中序中找根位置
        inorderIndexMap.get(15) = 2
        inRootIdx = 2

Step 4: 确定左右子树范围
        左子树: inorder[2, 1] → 空
        右子树: inorder[3, 2] → 空

返回: TreeNode(15) [叶子节点]

══════════════════════════════════════════════════════════════
【第5次递归】build(preorder, 4, 4) ← 构建节点7
══════════════════════════════════════════════════════════════

inLeft=4, inRight=4 → 4 ≤ 4，继续

Step 1: 取根节点
        preorder[preIndex=4] = 7
        preIndex++ → preIndex = 5
        rootVal = 7

Step 2: 创建根节点
        TreeNode(7)

Step 3: 在中序中找根位置
        inorderIndexMap.get(7) = 4
        inRootIdx = 4

Step 4: 左右子树范围都为空

返回: TreeNode(7) [叶子节点]

══════════════════════════════════════════════════════════════
【最终构建结果】
══════════════════════════════════════════════════════════════

        3
       / \
      9  20
        /  \
       15   7

preIndex 最终值 = 5（处理了5个节点）
```

---

## 代码实现

```java
public class buildTree105 {
    public Map<Integer, Integer> inorderIndexMap;
    private int preIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 构建 inorder 值→索引 的映射，O(1) 查找根位置
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return build(preorder, 0, inorder.length - 1);
    }

    /**
     * 递归构建子树
     * @param preorder  前序遍历数组
     * @param inLeft    当前子树在 inorder 中的左边界
     * @param inRight   当前子树在 inorder 中的右边界
     * @return 构建好的子树根节点
     */
    private TreeNode build(int[] preorder, int inLeft, int inRight) {
        // 递归终止条件：左边界超过右边界
        if (inLeft > inRight) {
            return null;
        }

        // 前序当前元素作为根
        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);

        // 在中序中找到根的位置
        int inRootIdx = inorderIndexMap.get(rootVal);

        // ⚠️ 顺序重要：先递归左子树，再递归右子树
        // 因为 preIndex 是按前序顺序递增的（根-左-右）
        root.left = build(preorder, inLeft, inRootIdx - 1);
        root.right = build(preorder, inRootIdx + 1, inRight);

        return root;
    }
}
```

---

## 核心流程图

```
┌─────────────────────────────────────────────────────────────┐
│                    buildTree(preorder, inorder)             │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│  Step 1: 构建 HashMap                                        │
│  inorderIndexMap.put(inorder[i], i)                         │
│  将中序数组值→索引映射，便于 O(1) 查找                         │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│  Step 2: 调用 build(preorder, 0, n-1)                       │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
         ┌────────────────────────────────────┐
         │                                    │
         ▼                                    ▼
   ┌──────────┐                      ┌──────────────┐
   │ inLeft   │                      │ inLeft ≤     │
   │ > inRight│                      │ inRight      │
   └──────────┘                      └──────────────┘
        │                                   │
        │ return null                       ▼
        │                    ┌──────────────────────────────┐
        │                    │ 取 preorder[preIndex++]      │
        │                    │ 作为当前根节点 rootVal        │
        │                    └──────────────────────────────┘
        │                                   │
        │                                   ▼
        │                    ┌──────────────────────────────┐
        │                    │ 在 HashMap 中查找 rootVal    │
        │                    │ 得到 inRootIdx               │
        │                    └──────────────────────────────┘
        │                                   │
        │                                   ▼
        │                    ┌──────────────────────────────┐
        │                    │ 递归构建左子树                │
        │                    │ build(inLeft, inRootIdx-1)   │
        │                    └──────────────────────────────┘
        │                                   │
        │                                   ▼
        │                    ┌──────────────────────────────┐
        │                    │ 递归构建右子树                │
        │                    │ build(inRootIdx+1, inRight)  │
        │                    └──────────────────────────────┘
        │                                   │
        │                                   ▼
        │                    ┌──────────────────────────────┐
        │                    │ return root                  │
        │                    └──────────────────────────────┘
        │                                   │
        └───────────────────────────────────┘
                              │
                              ▼
                    ┌─────────────────┐
                    │   返回根节点     │
                    └─────────────────┘
```

---

## 复杂度分析

| 复杂度 | 值 | 说明 |
|--------|-----|------|
| 时间 | O(n) | 每个节点处理一次，HashMap 查找 O(1) |
| 空间 | O(n) | HashMap 存储 + 递归栈深度（最坏 O(n)） |

---

## 关键点总结

1. **preIndex 必须在递归前递增**：保证前序遍历顺序正确
2. **先递归左子树**：因为前序是「根-左-右」
3. **HashMap 优化**：避免每次 O(n) 遍历找根位置
4. **边界条件**：`inLeft > inRight` 表示空子树
