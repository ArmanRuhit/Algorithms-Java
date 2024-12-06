
public class A01Kadanes {

    public static void main(String[] args) {
        int[] nums = new int[]{4, -1, 2, -7, 3, 4};
        System.out.println(kadanes(nums));
    }

    public static int kadanes(int[] nums){
        nums.l
        int maxSum = nums[0];
        int currentSum = 0;

        for(int i : nums) {
            currentSum = Math.max(currentSum, 0);
            currentSum += i;
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}