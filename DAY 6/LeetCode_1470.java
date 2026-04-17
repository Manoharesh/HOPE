public class LeetCode_1470 {
    class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] arr=new int[nums.length];
        int i=0,j=n;
        int k=0;
        while(j<nums.length){
            arr[k]=nums[i];
            arr[k+1]=nums[j];
            i++;
            j++;
            k+=2;
        }
        return arr;
       

    }
}
}
