package one.tree.b75;
/*
请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。

实现词典类 WordDictionary ：

WordDictionary() 初始化词典对象
void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；
否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 */
public class a13WordDictionary211 {
    private static class TrieNode {
        // 26 个孩子节点，分别对应 'a' 到 'z'
        TrieNode[] children = new TrieNode[26];

        // 标记当前节点是否是某个完整单词的结尾
        boolean isEnd = false;
    }
    // 根节点，不表示任何字符
    private final TrieNode root;


    public a13WordDictionary211() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node=root;
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            int index=c-'a';
            if(node.children[index]==null){
                node.children[index]=new TrieNode();
            }
            node=node.children[index];
        }
        node.isEnd=true;
    }


    /**
     * 搜索单词 .表示通配符  .可以匹配任意字符 所以要尝试当前节点的所有非空孩子
     * @param word
     * @return
     */
    public boolean search(String word) {
        return dfs(word,0,root);
    }

    private boolean dfs(String word, int index, TrieNode node) {
        //当前节点为空 说明路径不存在
        if(node==null)return false;

        //如果匹配完整个word 只有当前节点是某个单词结尾 才算成功
        if(index==word.length()){
            return node.isEnd;
        }

        char c=word.charAt(index);
        //当前字符是普通字母
        if(c!='.'){
            int childIndex=c-'a';
            //沿着对应字符的路径继续走
            return dfs(word,index+1,node.children[childIndex]);
        }

        //当前字符是.
        for(int i=0;i<26;i++){
            if(node.children[i]!=null){
                //只要有一条路径匹配成功就返回true
                if(dfs(word,index+1,node.children[i])){
                    return true;
                }
            }
        }

        //所有孩子都试过了还没匹配成功
        return false;

    }

}
