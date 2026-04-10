package one.tree;
/*
 设计一个数据结构，支持：
  - addWord(word) — 添加单词
  - search(word) — 搜索单词，支持 . 通配符（匹配任意一个字母）
 */
public class WordDictionary211 {
    private class TrieNode{
        TrieNode[] children=new TrieNode[26];
        boolean isEnd=false;
    }
    private TrieNode root;
    public WordDictionary211(){
        root=new TrieNode();
    }

    //添加单词
    public void addWord(String word){
        TrieNode node=root;
        for(char c:word.toCharArray()){
            int idx =c-'a';
            if(node.children[idx]==null){
                node.children[idx]=new TrieNode();
            }
            node=node.children[idx];
        }
        node.isEnd=true;
    }

    //搜索单词 支持 .通配符
    public boolean search(String word){
        return dfs(word,0,root);
    }

    /**
     * 从node出发 匹配word从index开始的后缀
     * @param word
     * @param index
     * @param node
     * @return
     */
    private boolean dfs(String word,int index,TrieNode node){
        //匹配完了所有字符 检查是否是完整单词
        if(index==word.length()){
            return node.isEnd;
        }
        char c=word.charAt(index);
        if(c=='.'){
            //'.'通配符 尝试所有非空子节点
            for(int i=0;i<26;i++){
                if(node.children[i]!=null){
                    //递归尝试每条路 有一条成功就返回true
                    if(dfs(word,index+1,node.children[i])){
                        return true;
                    }
                }
            }
            //所有路都走不通
            return false;
        }else{
            //普通字符 走对应的路
            int idx=c-'a';
            if(node.children[idx]==null){
                return false; //路径不存在
            }
            return dfs(word,index+1,node.children[idx]);
        }
    }

}
