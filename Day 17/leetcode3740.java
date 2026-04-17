class leetcode3740 {
    public int minimumDistance(int[] nums) {
        int dis = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] == nums[j] && nums[j] == nums[k]) {
                        int curr = Math.abs(i - j) + Math.abs(j - k) + Math.abs(i - k);
                        dis = Math.min(dis, curr);
                    }
                }
            }
        }
        return dis == Integer.MAX_VALUE ? -1 : dis;
    }
}