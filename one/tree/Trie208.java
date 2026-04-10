package one.tree;
/*
 题目描述
  实现一个 Trie（前缀树），包含 insert、search、startsWith 三个操作。
  示例：
  insert("apple")
  search("apple")   → true
  search("app")     → false（"app"没有被完整插入过）
  startsWith("app") → true（存在以"app"开头的单词）
  insert("app")
  search("app")     → true
 */
public class Trie208 {
    //内部节点类
    private class TrieNode{
        //每个槽位对应一个字母 非空表示该字母存在
        TrieNode[] children=new TrieNode[26];
        //标记是否是单词结尾
        boolean isEnd=false;
    }

    private TrieNode root;

    public Trie208(){
        root=new TrieNode();
    }
    //插入单词
    public void insert(String word){
        TrieNode node=root;
        for(char c:word.toCharArray()){
            int idx=c-'a';
            if(node.children[idx]==null){
                node.children[idx]=new TrieNode();//不存在则创建
            }
            node=node.children[idx];//存在就复用
        }
        node.isEnd=true;//标记为完整单词
    }

    //搜索单词
    public boolean search(String word){
        TrieNode node=find(word);
        return node!=null&&node.isEnd;
    }

    //查找前缀
    public boolean startWith(String perfix){
        return find(perfix)!=null; //路径存在就行
    }

    /**
     * 沿路径查找 返回最后到达的节点
     * @param word
     * @return
     */
    public TrieNode find(String word){
        TrieNode node=root;
        for(char c:word.toCharArray()){
            int idx=c-'a';
            if(node.children[idx]==null){
                return null; //路径断了
            }
            node=node.children[idx];
        }
        return node;
    }
}
