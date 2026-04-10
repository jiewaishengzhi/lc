# LeetCode 208 - 实现 Trie（前缀树）

## 题目描述

实现一个 Trie（前缀树），包含 `insert`、`search`、`startsWith` 三个操作。

```
示例：
insert("apple")
search("apple")   → true
search("app")     → false（"app"没有被完整插入过）
startsWith("app") → true（存在以"app"开头的单词）
insert("app")
search("app")     → true
```

---

## 分析方法

### Trie 核心概念

Trie 是一棵**多叉树**，每条边代表一个字符，从根到某节点的路径就是一个**前缀**。

### 节点结构

```java
class TrieNode {
    TrieNode[] children = new TrieNode[26]; // 26个字母槽位
    boolean isEnd = false;                   // 标记是否是单词结尾
}
```

**关键理解**：
- `children[i]` 的**索引**代表字母（索引 0='a', 1='b', ..., 25='z'）
- `children[i] != null` → 该字母存在，指向下一个节点
- `children[i] == null` → 该字母不存在，死胡同
- **节点不存字符**，字符由数组的索引隐式表示

```
一个 TrieNode 的内部结构：

┌─────────────────────────────────────────────────────┐
│  children[0]  ('a')  →  null 或 下一个TrieNode       │
│  children[1]  ('b')  →  null 或 下一个TrieNode       │
│  children[2]  ('c')  →  null 或 下一个TrieNode       │
│  ...                                                 │
│  children[25] ('z')  →  null 或 下一个TrieNode       │
│                                                      │
│  isEnd: false / true                                 │
└─────────────────────────────────────────────────────┘

插入 "car" 后：
root → children[2]('c') → children[0]('a') → children[17]('r') → isEnd=true
```

---

## 示例推演

```
══════════════════════════════════════════════════════════════
【初始状态】
══════════════════════════════════════════════════════════════

root: children[0..25] 全部 null, isEnd=false

══════════════════════════════════════════════════════════════
【insert("apple")】
══════════════════════════════════════════════════════════════

node = root

处理 'a' (idx=0)：
  children[0] == null → 创建新节点A
  node = 新节点A

处理 'p' (idx=15)：
  children[15] == null → 创建新节点B
  node = 新节点B

处理 'p' (idx=15)：
  children[15] == null → 创建新节点C
  node = 新节点C

处理 'l' (idx=11)：
  children[11] == null → 创建新节点D
  node = 新节点D

处理 'e' (idx=4)：
  children[4] == null → 创建新节点E
  node = 新节点E

标记：E.isEnd = true

树结构：
root → [a] → [p] → [p] → [l] → [e✓]

══════════════════════════════════════════════════════════════
【search("apple")】
══════════════════════════════════════════════════════════════

调用 find("apple")：

node = root
'a' → children[0] 非空 → node = 节点A → 继续
'p' → children[15] 非空 → node = 节点B → 继续
'p' → children[15] 非空 → node = 节点C → 继续
'l' → children[11] 非空 → node = 节点D → 继续
'e' → children[4] 非空 → node = 节点E → 继续

遍历结束 → return 节点E

search 结果：node!=null 且 node.isEnd=true → true ✅

══════════════════════════════════════════════════════════════
【search("app")】
══════════════════════════════════════════════════════════════

调用 find("app")：

node = root
'a' → children[0] 非空 → 继续
'p' → children[15] 非空 → 继续
'p' → children[15] 非空 → 继续

遍历结束 → return 节点C（第二个p节点）

search 结果：node!=null 但 node.isEnd=false → false ❌
（"app" 只是路径经过，没有被标记为完整单词）

══════════════════════════════════════════════════════════════
【startsWith("app")】
══════════════════════════════════════════════════════════════

调用 find("app")：

同上，return 节点C

startsWith 结果：node!=null → true ✅
（只看路径是否存在，不检查 isEnd）

══════════════════════════════════════════════════════════════
【insert("app")】
══════════════════════════════════════════════════════════════

node = root
'a' → children[0] 已存在（节点A）→ 复用
'p' → children[15] 已存在（节点B）→ 复用
'p' → children[15] 已存在（节点C）→ 复用

标记：节点C.isEnd = true（在已有路径上打标记）

树结构：
root → [a] → [p] → [p✓] → [l] → [e✓]
                   ↑ 新增 isEnd 标记（不影响后续路径）

══════════════════════════════════════════════════════════════
【search("app")】（再次查找）
══════════════════════════════════════════════════════════════

find("app") → 节点C
node.isEnd = true → true ✅

══════════════════════════════════════════════════════════════
【find("apx")】← 不存在的路径
══════════════════════════════════════════════════════════════

node = root
'a' → children[0] 非空 → 继续
'p' → children[15] 非空 → 继续
'x' → children[23] == null → return null

search 结果：node==null → false ❌
startsWith 结果：node==null → false ❌
```

---

## 代码实现

```java
public class Trie208 {

    // 内部节点类
    private class TrieNode {
        // 每个槽位对应一个字母，非空表示该字母存在
        // 索引 0='a', 1='b', ..., 25='z'（由 c-'a' 计算）
        TrieNode[] children = new TrieNode[26];
        // 标记从根到当前节点是否构成一个完整单词
        boolean isEnd = false;
    }

    private TrieNode root;

    public Trie208() {
        root = new TrieNode();
    }

    // 插入单词
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';           // 字符转索引
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode(); // 不存在则创建
            }
            node = node.children[idx];   // 存在则复用
        }
        node.isEnd = true; // 标记为完整单词
    }

    // 搜索单词（必须完整匹配）
    public boolean search(String word) {
        TrieNode node = find(word);
        return node != null && node.isEnd; // 路径存在 且 是完整单词
    }

    // 查找前缀（只要路径存在即可）
    public boolean startWith(String prefix) {
        return find(prefix) != null; // 路径存在就行，不检查 isEnd
    }

    /**
     * 公共方法：沿路径查找，返回最后到达的节点
     * @param word 要查找的单词或前缀
     * @return 最后到达的节点，路径中断则返回 null
     */
    public TrieNode find(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                return null; // 路径断了，不存在
            }
            node = node.children[idx];
        }
        return node;
    }
}
```

---

## 核心流程图

```
═════════════════════════════════════════════
              insert(word)
═════════════════════════════════════════════
        ┌───────────┐
        │ node=root │
        └─────┬─────┘
              │
              ▼
   ┌──────────────────────┐
   │ 遍历 word 每个字符 c  │◄──────────┐
   └──────────┬───────────┘            │
              │                        │
              ▼                        │
   ┌──────────────────────┐            │
   │ idx = c - 'a'        │            │
   │ children[idx]==null? │            │
   └──────┬───────┬───────┘            │
         Yes      No                   │
          │       │                    │
          ▼       ▼                    │
   ┌──────────┐ ┌───────────┐          │
   │创建新节点│ │复用现有节点│          │
   └────┬─────┘ └─────┬─────┘          │
        └──────┬──────┘                │
               │                       │
               ▼                       │
      ┌─────────────────┐              │
      │还有下一个字符？  │──Yes────────┘
      └────────┬────────┘
            No  │
               ▼
      ┌─────────────────┐
      │ node.isEnd=true │
      └─────────────────┘

═════════════════════════════════════════════
    find(word) — search 和 startsWith 的底层
═════════════════════════════════════════════
        ┌───────────┐
        │ node=root │
        └─────┬─────┘
              │
              ▼
   ┌──────────────────────┐
   │ 遍历 word 每个字符 c  │◄──────────┐
   └──────────┬───────────┘            │
              │                        │
              ▼                        │
   ┌──────────────────────┐            │
   │ idx = c - 'a'        │            │
   │ children[idx]==null? │──Yes──→ return null
   └──────────┬───────────┘            │
              │ No                     │
              ▼                        │
   ┌──────────────────────┐            │
   │ node=children[idx]   │            │
   └──────────┬───────────┘            │
              │                        │
              ▼                        │
      ┌─────────────────┐              │
      │还有下一个字符？  │──Yes────────┘
      └────────┬────────┘
            No  │
               ▼
      ┌─────────────────┐
      │  return node    │
      └─────────────────┘

═════════════════════════════════════════════
    search(word) vs startsWith(prefix)
═════════════════════════════════════════════

  find(word)
     │
     ▼
  node == null? ──Yes──→ false
     │ No
     ▼
  ┌──────────────────┐
  │ search:          │
  │ return isEnd     │ ← 多检查 isEnd
  │                  │
  │ startsWith:      │
  │ return true      │ ← 不检查 isEnd
  └──────────────────┘
```

---

## 复杂度分析

| 操作 | 时间 | 空间 |
|------|------|------|
| insert | O(L) | O(L) 最坏新建 L 个节点 |
| search | O(L) | O(1) |
| startsWith | O(L) | O(1) |

> L = 单词/前缀长度

---

## 关键点总结

1. **节点不存字符**：字符由数组索引隐式表达（`children[0]` = 'a'）
2. **isEnd 区分前缀和单词**：`search` 检查 isEnd，`startsWith` 不检查
3. **复用节点**：插入 "apple" 后再插 "app"，共享 a→p→p 路径
4. **find 公共方法**：`search` 和 `startsWith` 复用同一个路径查找逻辑
5. **空间换时间**：每个节点固定 26 个引用，保证查找 O(L)
