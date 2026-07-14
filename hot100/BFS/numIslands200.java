package hot100.BFS;

public class numIslands200 {
    /*
    从左到右、从上到下遍历每个格子：
    遇到海水 '0'：跳过。
    遇到一块还没访问过的陆地 '1'：岛屿数量加一；
    以它为起点进行 DFS: 把和它连通的所有陆地全部标记为已访问。


    这样，同一座岛后续再被扫描到时，已经被标记过，不会重复计数。
    这里可以直接把访问过的 '1' 改成 '0'，相当于“淹掉”整座岛。
     */
    public int numIslands(char[][] grid){
        if(grid==null||grid.length==0)return 0;

        int rows=grid.length;
        int cols=grid[0].length;
        int count=0;

        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                //发现一块未访问的陆地
                if(grid[i][j]=='1'){
                    count++;
                    //将整座岛的陆地全部标记为已访问
                    dfs(grid,i,j);
                }
            }
        }
        return count;
    }
    private void dfs(char[][] grid,int row,int col){
        //越界 或者当前位置是海水 结束
        if(row<0||row>=grid.length
                ||col<0 ||col>=grid[0].length
                ||grid[row][col]=='0'){
            return;
        }

        //将当前陆地标记为已访问
        grid[row][col]='0';

        //向四个方向继续搜索
        dfs(grid,row-1,col);
        dfs(grid,row+1,col);
        dfs(grid,row,col-1);
        dfs(grid,row,col+1);
    }
}
