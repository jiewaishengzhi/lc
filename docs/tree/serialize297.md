# LeetCode 297 - 二叉树的序列化与反序列化

## 题目描述

设计一个算法，实现二叉树的**序列化**（树 → 字符串）和**反序列化**（字符串 → 树）。

```
示例：
    1                    "1,2,null,null,3,4,null,null,5,null,null"
   / \           ↔
  2   3
     / \
    4   5
```

---

## 分析方法

### 序列化思路

用**前序遍历**（根-左-右）将树转为字符串，`null` 用特殊标记表示。

### 反序列化思路

按前序顺序依次读取，递归重建：
- 遇到 `null` → 返回空节点
- 遇到数字 → 创建节点，递归构建左右子树

---

## 方法一：序列化（树 → 字符串）

### 示例推演

```
树：
    1
   / \
  2   3
     / \
    4   5

══════════════════════════════════════════════════════════════

serializeHelper(节点1, sb)
  ├── 访问1 → sb.append("1,")
  ├── serializeHelper(节点2, sb)
  │     ├── 访问2 → sb.append("2,")
  │     ├── serializeHelper(null, sb) → sb.append("null,")
  │     └── serializeHelper(null, sb) → sb.append("null,")
  └── serializeHelper(节点3, sb)
        ├── 访问3 → sb.append("3,")
        ├── serializeHelper(节点4, sb)
        │     ├── 访问4 → sb.append("4,")
        │     ├── serializeHelper(null, sb) → sb.append("null,")
        │     └── serializeHelper(null, sb) → sb.append("null,")
        └── serializeHelper(节点5, sb)
              ├── 访问5 → sb.append("5,")
              ├── serializeHelper(null, sb) → sb.append("null,")
              └── serializeHelper(null, sb) → sb.append("null,")

结果："1,2,null,null,3,4,null,null,5,null,null,"

══════════════════════════════════════════════════════════════
【遍历顺序与字符串对应关系】
══════════════════════════════════════════════════════════════

前序遍历：
    1
   / \
  2   3         1  2  null null  3  4  null null  5  null null
     / \         ↑  ↑   ↑    ↑   ↑  ↑   ↑    ↑   ↑   ↑    ↑
    4   5        根  左  左左 左右  根 右左 右左左 右左右 根 右左 右右
                 1   2  ─2的子树─  3   4 ─4的子树─   5 ─5的子树─

每个节点固定占3个位置（自身+左null+右null），叶子节点的左右都是null
```

### 代码

```java
// 序列化：树 → 字符串（前序遍历）
public String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    serializeHelper(root, sb);
    return sb.toString();
}

private void serializeHelper(TreeNode node, StringBuilder sb) {
    if (node == null) {
        sb.append("null,");  // 空节点用 null 标记
        return;
    }
    sb.append(node.val).append(",");   // 根
    serializeHelper(node.left, sb);     // 左
    serializeHelper(node.right, sb);    // 右
}
```

### 核心流程图

```
         ┌───────────────────┐
         │ serialize(node,sb)│
         └────────┬──────────┘
                  │
                  ▼
        ┌──────────────────┐     Yes
        │  node == null?   │────────→ sb.append("null,")
        └────────┬─────────┘          return
                 │ No
                 ▼
        ┌──────────────────────────┐
        │ sb.append(node.val+",")  │  ← 根
        └──────────┬───────────────┘
                   │
                   ▼
        ┌──────────────────────────┐
        │ serialize(left, sb)      │  ← 左
        └──────────┬───────────────┘
                   │
                   ▼
        ┌──────────────────────────┐
        │ serialize(right, sb)     │  ← 右
        └──────────────────────────┘
```

---

## 方法二：反序列化（字符串 → 树）

### 示例推演

```
输入："1,2,null,null,3,4,null,null,5,null,null"

split 后：["1","2","null","null","3","4","null","null","5","null","null"]
index 初始 = 0

══════════════════════════════════════════════════════════════

dfs(vals, index)
  │
  ├── index=0  val="1"  → 创建节点1
  │     │
  │     ├── index=1  val="2"  → 创建节点2
  │     │     │
  │     │     ├── index=2  val="null" → return null
  │     │     │
  │     │     └── index=3  val="null" → return null
  │     │
  │     │   节点2: left=null, right=null ✅
  │     │
  │     ├── index=4  val="3"  → 创建节点3
  │     │     │
  │     │     ├── index=5  val="4"  → 创建节点4
  │     │     │     │
  │     │     │     ├── index=6  val="null" → return null
  │     │     │     │
  │     │     │     └── index=7  val="null" → return null
  │     │     │
  │     │     │   节点4: left=null, right=null ✅
  │     │     │
  │     │     └── index=8  val="5"  → 创建节点5
  │     │           │
  │     │           ├── index=9  val="null" → return null
  │     │           │
  │     │           └── index=10 val="null" → return null
  │     │
  │     │         节点5: left=null, right=null ✅
  │     │
  │     │   节点3: left=节点4, right=节点5 ✅
  │     │
  │   节点1: left=节点2, right=节点3 ✅

══════════════════════════════════════════════════════════════
【index 指针移动过程】
══════════════════════════════════════════════════════════════

index: 0  1  2     3     4  5  6     7     8  9     10
val:   1  2  null  null  3  4  null  null  5  null  null
       ↑                                         创建节点1
          ↑                                      创建节点2
             ↑                                   null→return
                  ↑                              null→return
                       ↑                         创建节点3
                          ↑                      创建节点4
                             ↑                   null→return
                                  ↑              null→return
                                       ↑         创建节点5
                                          ↑      null→return
                                               ↑ null→return

每次读取一个值，index 自动后移，递归自然保证顺序正确
```

### 代码

```java
// 反序列化：字符串 → 树
public TreeNode deserialize(String data) {
    String[] vals = data.split(",");
    int[] index = {0};  // 用数组包装，以便递归中修改
    return dfs(vals, index);
}

private TreeNode dfs(String[] vals, int[] index) {
    String val = vals[index[0]++];

    if (val.equals("null")) {
        return null;
    }

    TreeNode node = new TreeNode(Integer.parseInt(val));
    node.left = dfs(vals, index);    // 先递归左子树
    node.right = dfs(vals, index);   // 再递归右子树
    return node;
}
```

### 核心流程图

```
         ┌──────────────────┐
         │  dfs(vals,index) │
         └────────┬─────────┘
                  │
                  ▼
        ┌──────────────────────┐
        │ val = vals[index++]  │
        └──────────┬───────────┘
                   │
                   ▼
        ┌──────────────────┐     Yes
        │ val == "null"?   │────────→ return null
        └────────┬─────────┘
                 │ No
                 ▼
        ┌──────────────────────────┐
        │ node = new TreeNode(val) │
        └──────────┬───────────────┘
                   │
                   ▼
        ┌──────────────────────────┐
        │ node.left = dfs(...)     │  ← 先递归左
        └──────────┬───────────────┘
                   │
                   ▼
        ┌──────────────────────────┐
        │ node.right = dfs(...)    │  ← 再递归右
        └──────────┬───────────────┘
                   │
                   ▼
        ┌──────────────────────────┐
        │      return node         │
        └──────────────────────────┘
```

---

## 序列化与反序列化的互逆关系

```
序列化（前序拆解）：

    1
   / \           "1,2,null,null,3,4,null,null,5,null,null"
  2   3
     / \
    4   5

反序列化（前序组装）：

"1,2,null,null,3,4,null,null,5,null,null"
                    →
                        1
                       / \
                      2   3
                         / \
                        4   5

两者都用前序遍历，保证顺序一致，完美互逆
```

---

## 复杂度分析

| 操作 | 时间 | 空间 |
|------|------|------|
| serialize | O(n) | O(n) 字符串 + O(h) 递归栈 |
| deserialize | O(n) | O(n) 数组 + O(h) 递归栈 |

---

## 关键点总结

1. **前序遍历**：先根再左右，序列化和反序列化都用前序，保证顺序一致
2. **null 必须序列化**：没有 null 无法区分树的结构（如 `[1,null,2]` vs `[1,2]`）
3. **index 用数组包装**：Java 值传递，`int` 修改后递归中看不到，必须用 `int[]`
4. **逗号分隔**：方便 split 解析
5. **互逆操作**：serialize 按前序拆解，deserialize 按前序组装
