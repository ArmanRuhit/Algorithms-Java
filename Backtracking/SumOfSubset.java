/**
 * This method solves the Subset Sum Problem using recursion.
 * It attempts to find a subset of the given array that sums to the target value.
 * If such a subset exists, the method returns a list of the indices of the elements
 * that make up the subset. If no such subset is found, it returns null.
 *
 * Algorithm:
 * - Base Case 1: If the target sum (key) is 0, it means a valid subset is found, 
 *   so an empty list is returned.
 * - Base Case 2: If no more elements are left (num < 0) or the sum becomes negative (key < 0),
 *   return null, as it is not possible to form the required subset.
 * - Recursive Case:
 *   - First, attempt to include the current element (arr[num]) in the subset and check if 
 *     the remaining sum (key - arr[num]) can be formed using the remaining elements. If a 
 *     valid subset is found, add the current index to the result.
 *   - If including the current element does not work, attempt to exclude it and check 
 *     the remaining elements for the same target sum.
 * - If neither inclusion nor exclusion leads to a solution, return null.
 *
 * Time Complexity: O(2^n) - As each element can either be included or excluded, 
 * the number of recursive calls grows exponentially.
 * Space Complexity: O(n) - For storing the subset of indices in the recursion stack.
 */

import java.util.ArrayList;

public class SumOfSubset {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 10, 12, 13, 15, 18};

        int key = 30;

        ArrayList<Integer> ans = subSetSum(arr, arr.length - 1, key);

        for(int i : ans){
            System.out.print(arr[i]+" ");
        }
    } 
    

    public static ArrayList<Integer> subSetSum(int[] arr, int num, int key){

        if(key == 0){
            return new ArrayList<>();
        }

        if(num < 0 || key < 0){
            return null;
        }

        ArrayList<Integer> include = subSetSum(arr, num-1, key-arr[num]);

        if(include != null){
            include.add(num);
            return include;
        }

        ArrayList<Integer> exclude = subSetSum(arr, num-1, key);


        if(exclude != null){
            return exclude;
        }

        return null;
    }
    
    
}