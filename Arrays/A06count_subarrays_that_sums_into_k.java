/*
Problem: Count Subarrays with Sum equal to K

Description:
Given an integer array "nums" and an integer "k", the objective is to determine the total number of contiguous subarrays that sum up exactly to k. A subarray is a contiguous segment of the array. 

Constraints:
- The array may contain negative values.
- k may be any integer (zero, positive, or negative).
- The array could be large, which requires an efficient solution.

Approach:
1. Use a cumulative sum technique along with a hash map to store the frequency of each cumulative sum encountered.
2. Iterate over the array while calculating the cumulative sum. For each new sum, check if (currentSum - k) exists in the map. If it does, add its frequency to the result count.
3. Update the hash map with the current cumulative sum.

Function Signature:
public int subarraySum(int[] nums, int k)

This approach yields an O(n) time complexity and O(n) space complexity.
*/

package Arrays;

import java.util.HashMap;

public class A06count_subarrays_that_sums_into_k {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int k = 2;
        System.out.println(subarraySum_bruteForce(nums, k)); // Output: 2
        System.out.println(subarraySum_efficient(nums, k)); // Output: 2
    }

    /**
     * Brute force approach to count subarrays with sum equal to k.
     * Time Complexity: O(n³) - Three nested loops
     * Space Complexity: O(1) - Constant extra space
     */
    static int subarraySum_bruteForce(int[] nums, int k) {
        int count = 0;
        // Iterate through all possible left boundaries
        for (int left = 0; left < nums.length; left++) {
            // Iterate through all possible right boundaries
            for (int right = left; right < nums.length; right++) {
                int totalSum = 0;
                // Calculate sum of current subarray
                for (int j = left; j <= right; j++) {
                    totalSum += nums[j];
                }
                if (totalSum == k) {
                    count++;
                }
            }
        }
        return count;
    }
    
    /**
     * Efficient approach using prefix sum and HashMap.
     * Time Complexity: O(n) - Single pass through the array
     * Space Complexity: O(n) - HashMap can store at most n different sums
     */
    static int subarraySum_efficient(int[] nums, int k) {
        HashMap<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1); // Empty subarray has sum 0
        
        int count = 0;
        int currentSum = 0;
        
        for (int num : nums) {
            currentSum += num;
            
            // If (currentSum - k) exists in map, it means we have subarrays with sum k
            if (prefixSum.containsKey(currentSum - k)) {
                count += prefixSum.get(currentSum - k);
            }
            
            // Update the frequency of current sum
            prefixSum.put(currentSum, prefixSum.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }
}