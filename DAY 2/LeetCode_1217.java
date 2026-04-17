public class LeetCode_1217 {
    class Solution {
    public int minCostToMoveChips(int[] position) {
        int odd=0,even=0;
        for(int i:position){
            if(i%2!=0){
                odd+=1;
            }else{
                even+=1;
            }
        }
        if(odd>even){
            return even;
        }
        else{
            return odd;
        }
    
    }
}
}
