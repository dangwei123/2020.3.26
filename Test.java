给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。

定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。

找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res=new ArrayList<>();
        PriorityQueue<int[]> p=new PriorityQueue<>(
            new Comparator<int[]>(){
                public int compare(int[] o1,int[] o2){
                    return (o1[0]+o1[1])-(o2[0]+o2[1]);
                }
            }
            );
        for(int i=0;i<Math.min(nums1.length,k);i++){
            for(int j=0;j<Math.min(nums2.length,k);j++){
                int[] tmp=new int[]{nums1[i],nums2[j]};
                p.offer(tmp);
            }
        }
        while(k--!=0&&!p.isEmpty()){
            List<Integer> row=new ArrayList<>();
            int[] arr=p.poll();
            for(int i=0;i<2;i++){
                row.add(arr[i]);
            }
            res.add(row);
        }
        return res;
    }
}

在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。它们分别以字符 “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。

车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。

返回车能够在一次移动中捕获到的卒的数量。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/available-captures-for-rook
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int numRookCaptures(char[][] board) {
        int row=board.length;
        if(row==0) return 0;
        int col=board[0].length;
        int r=-1;
        int c=-1;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(board[i][j]=='R'){
                    r=i;
                    c=j;
                    break;
                }
            }
        }
        if(r==-1) return 0;
        int sum=0;
        for(int i=r-1;i>=0;i--){
            if(board[i][c]=='B'){
                break;
            }
            if(board[i][c]=='p'){
                sum++;
                break;
            }
        }
        for(int i=r+1;i<row;i++){
            if(board[i][c]=='B'){
                break;
            }
            if(board[i][c]=='p'){
                sum++;
                break;
            }
        }
        for(int i=c-1;i>=0;i--){
            if(board[r][i]=='B'){
                break;
            }
            if(board[r][i]=='p'){
                sum++;
                break;
            }
        }
        for(int i=c+1;i<col;i++){
            if(board[r][i]=='B'){
                break;
            }
            if(board[r][i]=='p'){
                sum++;
                break;
            }
        }
        return sum;
    }
}

有一堆石头，每块石头的重量都是正整数。

每一回合，从中选出两块最重的石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：

如果 x == y，那么两块石头都会被完全粉碎；
如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。

 

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/last-stone-weight
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int compare(int a,int b){
        return b-a;
    }
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue=new PriorityQueue<>(
            (a,b)->compare(a,b)
        );
        
        for(int i=0;i<stones.length;i++){
            queue.offer(stones[i]);
        }
        while(queue.size()>1){
            int a=queue.poll();
            int b=queue.poll();
            queue.offer(a-b);
        }
        return queue.size()==1?queue.poll():0;
        
    }
}