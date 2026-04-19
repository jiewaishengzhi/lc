package one.tree.b75;
/*
前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
这一数据结构有相当多的应用情景，例如自动补全和拼写检查。

请你实现 Trie 类：

1.Trie() 初始化前缀树对象。
2.void insert(String word) 向前缀树中插入字符串 word 。
3.boolean search(String word) 如果字符串 word 在前缀树中，
返回 true（即，在检索之前已经插入）；否则，返回 false 。
4.boolean startsWith(String prefix) 如果之前已经插入的
字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 */
public class a12Trie208 {
    private static class TrieNode{
        TrieNode[] children=new TrieNode[26];
        boolean isEnd=false;
    }

    //Trie的根节点 本身不代表任何字符 起点
    private final TrieNode root;


    public a12Trie208(){
        root=new TrieNode();
    }

    /**
     * 从根节点开始：
     * 遍历 word 的每个字符
     * 如果对应孩子不存在，就创建
     * 移动到孩子节点
     * 遍历结束后，把最后节点标记为单词结尾
     * @param word
     */
    public void insert(String word){
        TrieNode node=root;

        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            //把字符映射成0-25的下标
            int index=c-'a';

            //当前字符不存在就创建
            if(node.children[index]==null){
                node.children[index] =new TrieNode();
            }

            node=node.children[index];
        }
        //所有单词都插入完成后 标记当前节点是一个完整单词的末尾
        node.isEnd=true;
    }

    /**
     * 从根节点开始：
     * 按字符往下走
     * 中途如果某个字符节点不存在，返回 false
     * 如果完整走完，检查最后节点是不是单词结尾
     * @param word
     * @return
     */
    public boolean search(String word){
        TrieNode node = searchPrefix(word);

        // 路径存在，并且最后一个节点是单词结尾，才说明完整单词存在
        return node != null && node.isEnd;
    }

    /**
     * 只要能完整走完 prefix
     * 就返回 true
     * 不需要检查 isEnd
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix){
        TrieNode node=searchPrefix(prefix);

        //只要能走完整个prefix 说明存在这个前缀
        return node!=null;
    }

    /**
     * 辅助函数 从root出发 沿着字符串s往下走
     * @param s
     * @return 如果能走完 返回最后一个节点 中途断掉 返回null
     */
    private TrieNode searchPrefix(String s){
        TrieNode node=root;

        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            int index=c-'a';
            //当前字符路径不存在 说明这个单词前缀不存在
            if(node.children[index]==null)return null;
            node=node.children[index];
        }
        return node;
    }
}
