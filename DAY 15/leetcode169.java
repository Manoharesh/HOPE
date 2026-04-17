import java.util.*;
class leetcode169 {
    public int majorityElement(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int maxm = 0,val=0;
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0) +1);
            if(map.get(nums[i])>maxm){
                val = nums[i];
                maxm = map.get(nums[i]);
            }
        }
        return val;
    }
}