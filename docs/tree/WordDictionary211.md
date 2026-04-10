# LeetCode 211 - 添加与搜索单词

## 题目描述

设计一个数据结构，支持：
- `addWord(word)` — 添加单词
- `search(word)` — 搜索单词，支持 `.` 通配符（匹配任意一个字母）

```
示例：
addWord("bad")
addWord("dad")
addWord("mad")
search("pad") → false
search("bad") → true
search(".ad") → true（. 匹配 b/d/m）
search("b..") → true（两个 . 匹配任意字母）
```

---

## 分析方法

本质是 **LeetCode 208 Trie** 的升级版。区别仅在于 `search`：
- 普通字符 → 和 208 一样，走对应的路
- 遇到 `.` → 要**遍历所有非空子节点**，逐个尝试（DFS 回溯）

---

## 示例推演

```
══════════════════════════════════════════════════════════════
【addWord("bad")】
══════════════════════════════════════════════════════════════

root → [b] → [a] → [d✓]

══════════════════════════════════════════════════════════════
【addWord("dad")】
══════════════════════════════════════════════════════════════

root → [b] → [a] → [d✓]
    → [d] → [a] → [d✓]

══════════════════════════════════════════════════════════════
【addWord("mad")】
══════════════════════════════════════════════════════════════

root → [b] → [a] → [d✓]
    → [d] → [a] → [d✓]
    → [m] → [a] → [d✓]

完整结构：
         root
        / | \
       b  d  m       ← children[1], [3], [12] 非空
       |  |  |
       a  a  a
       |  |  |
       d✓ d✓ d✓

══════════════════════════════════════════════════════════════
【search("pad")】
══════════════════════════════════════════════════════════════

dfs(word="pad", index=0, node=root)

index=0, c='p':
  idx = 'p'-'a' = 15
  node.children[15] == null → return false ❌

结果：false

══════════════════════════════════════════════════════════════
【search("bad")】← 无通配符，和208完全一样
══════════════════════════════════════════════════════════════

dfs("bad", 0, root)

index=0, c='b':
  idx=1, children[1] 非空 → dfs("bad", 1, 节点b)

  index=1, c='a':
    idx=0, children[0] 非空 → dfs("bad", 2, 节点a)

    index=2, c='d':
      idx=3, children[3] 非空 → dfs("bad", 3, 节点d)

      index=3 == word.length():
        return isEnd → true ✅

结果：true

══════════════════════════════════════════════════════════════
【search(".ad")】← 有通配符，关键！
══════════════════════════════════════════════════════════════

dfs(".ad", 0, root)

index=0, c='.':
  遍历 root 所有非空子节点：children[1](b), children[3](d), children[12](m)

  ── 尝试 i=1 (b):
     dfs(".ad", 1, 节点b)

     index=1, c='a':
       idx=0, children[0] 非空 → dfs(".ad", 2, 节点a)

       index=2, c='d':
         idx=3, children[3] 非空 → dfs(".ad", 3, 节点d)

         index=3 == word.length():
           return isEnd → true ✅

  → 第一条路就成功了！return true，不再尝试 d 和 m

结果：true

══════════════════════════════════════════════════════════════
【search("b..")】
══════════════════════════════════════════════════════════════

dfs("b..", 0, root)

index=0, c='b':
  idx=1, children[1] 非空 → dfs("b..", 1, 节点b)

  index=1, c='.':
    遍历节点b的所有非空子节点：只有 children[0](a)

    ── 尝试 i=0 (a):
       dfs("b..", 2, 节点a)

       index=2, c='.':
         遍历节点a的所有非空子节点：只有 children[3](d)

         ── 尝试 i=3 (d):
            dfs("b..", 3, 节点d)

            index=3 == word.length():
              return isEnd → true ✅

结果：true

══════════════════════════════════════════════════════════════
【search(".e")] ← 不存在的情况
══════════════════════════════════════════════════════════════

dfs(".e", 0, root)

index=0, c='.':
  遍历 root 所有非空子节点：b, d, m

  ── 尝试 b:
     index=1, c='e': children[4]==null → return false ❌

  ── 尝试 d:
     index=1, c='e': children[4]==null → return false ❌

  ── 尝试 m:
     index=1, c='e': children[4]==null → return false ❌

  全部失败 → return false ❌

结果：false

══════════════════════════════════════════════════════════════
【search("..d")] ← 多条路成功的情况
══════════════════════════════════════════════════════════════

dfs("..d", 0, root)

index=0, c='.':
  遍历 root 非空子节点：b, d, m

  ── 尝试 b:
     index=1, c='.':
       遍历节点b非空子节点：只有 a
       ── 尝试 a:
          index=2, c='d': children[3] 非空 → dfs("..d", 3, 节点d)
          index=3 == length → isEnd=true → true ✅

  → 找到了！"bad" 匹配 "..d" → return true
  （"dad", "mad" 也匹配，但找到一条就够了）

结果：true
```

---

## 代码实现

```java
public class WordDictionary211 {

    private class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private TrieNode root;

    public WordDictionary211() {
        root = new TrieNode();
    }

    // 添加单词（和 LeetCode 208 的 insert 完全一样）
    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    // 搜索单词（支持 . 通配符）
    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    /**
     * DFS：从 node 出发，匹配 word 从 index 开始的后缀
     * @param word  要搜索的单词
     * @param index 当前匹配到 word 的第几个字符
     * @param node  当前所在的 Trie 节点
     * @return 是否匹配成功
     */
    private boolean dfs(String word, int index, TrieNode node) {
        // 匹配完了所有字符，检查是否是完整单词
        if (index == word.length()) {
            return node.isEnd;
        }

        char c = word.charAt(index);

        if (c == '.') {
            // '.' 通配符：尝试所有非空子节点
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    // 递归尝试每条路，有一条成功就返回 true
                    if (dfs(word, index + 1, node.children[i])) {
                        return true;
                    }
                }
            }
            // 所有路都走不通
            return false;
        } else {
            // 普通字符：走对应的路
            int idx = c - 'a';
            if (node.children[idx] == null) {
                return false; // 路径不存在
            }
            return dfs(word, index + 1, node.children[idx]);
        }
    }
}
```

---

## 核心流程图

```
═════════════════════════════════════════════
            search(word)
═════════════════════════════════════════════
              │
              ▼
       ┌──────────────┐
       │ dfs(word,idx,│
       │     node)    │
       └──────┬───────┘
              │
              ▼
     ┌──────────────────┐
     │ idx == word长度?  │──Yes──→ return isEnd
     └────────┬─────────┘
              │ No
              ▼
     ┌──────────────────┐
     │ c = word[idx]    │
     └────────┬─────────┘
              │
        ┌─────┴──────┐
        │            │
     c == '.'      普通字符
        │            │
        ▼            ▼
  ┌────────────┐  ┌──────────────────┐
  │遍历26个槽位│  │ children[idx]    │
  │            │  │     == null?     │
  │ 非空的:    │  └───────┬──────────┘
  │ dfs(idx+1,│     Yes      No
  │ children[i])│    │        │
  │            │    ▼        ▼
  │ 有一个true │  return   dfs(idx+1,
  │ →return    │  false     children[idx])
  │   true     │
  │            │
  │ 全部false  │
  │ →return    │
  │   false    │
  └────────────┘
```

---

## 与 LeetCode 208 的对比

| 对比项 | 208 Trie | 211 WordDictionary |
|--------|----------|---------------------|
| insert/addWord | ✅ | 完全相同 |
| search（无通配符） | find + 检查 isEnd | DFS + 检查 isEnd |
| search（有通配符 `.`） | 不支持 | DFS 遍历所有子节点 |
| startsWith | ✅ | 不需要 |
| 实现方式 | 循环 | 递归（DFS） |

---

## 复杂度分析

| 操作 | 时间 | 说明 |
|------|------|------|
| addWord | O(L) | L = 单词长度 |
| search（无通配符） | O(L) | 同 208 |
| search（有通配符） | **O(26^L)** | 最坏：全是 `.`，每层 26 条路 |

> 实际中 `.` 数量有限，远小于理论上限

---

## 关键点总结

1. **和 208 的区别仅在 search**：遇到 `.` 要 DFS 遍历所有非空子节点
2. **addWord 完全相同**：和普通 Trie 插入一样
3. **DFS 提前返回**：某条路走通就 return true，不浪费
4. **终止条件是 `index == word.length()`**：不是 `node == null`，因为要检查 isEnd
5. **`.` 匹配任意一个字母**：不是匹配 0 个或多个，只匹配恰好 1 个
