class leetcode121 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int profit = 0,max=0;
        int buy = prices[0],sell = 0;
        for(int i=1;i<n;i++){
            buy = Math.min(buy,prices[i]);
            max = Math.max(max,prices[i]-buy);
        }
        return max;
    }
}