# Recursion Basics

## Table of Contents
- [What is Recursion?](#what-is-recursion)
- [Print numbers in decreasing order](#print-numbers-in-decreasing-order)
- [Stack overflow](#stack-overflow) 
- [Print numbers in increasing order](#print-numbers-in-increasing-order)
- [Find Factorial of N](#find-factorial-of-n)
- [Print sum of N natural numbers](#print-sum-of-n-natural-numbers)
- [Print Nth Fibonacci number](#print-nth-fibonacci-number)
- [Check if array is sorted or not](#check-if-array-is-sorted-or-not)
- [First Occurrence](#first-occurrence)
- [Last Occurrence](#last-occurrence)
- [Print x to the power n](#print-x-to-the-power-n)

- [Tiling Problem](#tiling-problem)
- [Remove Duplicates in a String](#remove-duplicates-in-a-string)
- [Friends Pairing Problem](#friends-pairing-problem)
- [Binary Strings Problem](#binary-strings-problem)

## What is Recursion?
*Solution coming soon*

## Print numbers in decreasing order
*Solution coming soon*

## Stack overflow
*Solution coming soon*

## Print numbers in increasing order
*Solution coming soon*

## Find Factorial of N
*Solution coming soon*

## Print sum of N natural numbers
*Solution coming soon*

## Print Nth Fibonacci number
*Solution coming soon*

## Check if array is sorted or not
[Problem Link](https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/description/)

### 📜 Restate the Problem
Given an array `nums`, determine if the array is monotonically increasing (sorted) when considering the array as a circular array. A circular array means we consider the first element after the last element.

**Input Format**: An integer array `nums`  
**Output Format**: A boolean value - `true` if the array is sorted and rotated, `false` otherwise  
**Constraints**:
- 1 <= nums.length <= 100
- 1 <= nums[i] <= 100

**Sample I/O**:
```
Input: nums = [3,4,5,1,2]
Output: true
Explanation: The array is sorted [1,2,3,4,5] and rotated to [3,4,5,1,2]

Input: nums = [2,1,3,4]
Output: false
Explanation: Not sorted in any way

Input: nums = [1,2,3]
Output: true
Explanation: The array is sorted and can be considered rotated 0 times
```

### 🧠 Plan Your Attack
**Key Observations**:
- For a sorted and rotated array, there should be at most one position where `nums[i] > nums[i+1]`.
- For a pure sorted array (no rotation), there will be no such position.
- The circular nature means we need to also check between the last and first element.

**Edge Cases**:
- Arrays of length 1 are always considered sorted.
- A fully sorted array (no rotations) should also return true.

**Strategies**:
- Brute Force: Check all consecutive pairs and count where the order is broken.
- Recursive Solution: Check if subarrays are sorted.
- Optimal: Single pass counting the pairs where order is broken.

### 🔍 Technique #1 — Brute Force
**Idea**: Iterate through the array and count how many times we find adjacent elements where the left is greater than the right (indicating a break in ascending order). For a valid sorted and rotated array, this should happen exactly once (at the rotation point) or zero times (if the array is already sorted).

**Pseudocode**:
```
function checkIfSortedAndRotated(nums):
    count = 0
    n = length of nums
    
    for i = 0 to n-1:
        if nums[i] > nums[(i+1) % n]:
            count += 1
    
    return count <= 1
```

**Time Complexity**: O(n) - single pass through the array
**Space Complexity**: O(1) - constant extra space

**Mini-Example**:
Input: `[3,4,5,1,2]`
- i=0: 3 > 4? No, count = 0
- i=1: 4 > 5? No, count = 0
- i=2: 5 > 1? Yes, count = 1
- i=3: 1 > 2? No, count = 1
- i=4: 2 > 3? (circular check) No, count = 1
Count = 1 which is ≤ 1, so return true.

### ⚡ Technique #2 — Recursive Approach
**Idea**: Use recursion to check if subparts of the array are sorted. This involves checking if the current element is less than or equal to the next, then making a recursive call for the rest of the array.

**Pseudocode**:
```
function isSortedRecursive(nums, i, n):
    // Base case: if we've checked all elements
    if i == n-1:
        return true
    
    // Check current pair and recurse for the rest
    return nums[i] <= nums[i+1] && isSortedRecursive(nums, i+1, n)

function checkIfSortedAndRotated(nums):
    n = length of nums
    rotationPoint = 0
    
    // Find rotation point (where nums[i] > nums[i+1])
    for i = 0 to n-2:
        if nums[i] > nums[i+1]:
            rotationPoint = i+1
            break
    
    // Rotate array so it starts at rotationPoint
    rotated = nums[rotationPoint:] + nums[:rotationPoint]
    
    // Check if rotated array is sorted
    return isSortedRecursive(rotated, 0, n)
```

**Time Complexity**: O(n) - we scan the array twice
**Space Complexity**: O(n) - for the call stack and rotated array

**Mini-Example**:
Input: `[3,4,5,1,2]`
1. Find rotation point: rotationPoint = 3 (index of 1)
2. Rotate array: `[1,2,3,4,5]`
3. Check if sorted recursively:
   - i=0: 1 <= 2? Yes, recurse
   - i=1: 2 <= 3? Yes, recurse
   - i=2: 3 <= 4? Yes, recurse
   - i=3: 4 <= 5? Yes, recurse
   - i=4: Base case reached, return true
   Return true

### 🚀 Technique #3 — Optimal (Iterative One-Pass)
**Idea**: The key insight is that in a sorted and rotated array, there is at most one position where `nums[i] > nums[(i+1) % n]`. We can count these positions in a single pass.

**Pseudocode**:
```
function checkIfSortedAndRotated(nums):
    count = 0
    n = length of nums
    
    for i = 0 to n-1:
        if nums[i] > nums[(i+1) % n]:
            count += 1
    
    return count <= 1
```

**Time Complexity**: O(n) - single pass through the array
**Space Complexity**: O(1) - constant extra space

**Mini-Example**:
Input: `[3,4,5,1,2]`
- i=0: 3 > 4? No, count = 0
- i=1: 4 > 5? No, count = 0
- i=2: 5 > 1? Yes, count = 1
- i=3: 1 > 2? No, count = 1
- i=4: 2 > 3? (circular check) No, count = 1
Count = 1 which is ≤ 1, so return true.

### 🧑‍💻 Working Code

```java
public boolean checkIfSortedAndRotated(int[] nums) {
    int count = 0;
    int n = nums.length;
    
    for (int i = 0; i < n; i++) {
        // Check if current element is greater than next element (with circular wraparound)
        if (nums[i] > nums[(i + 1) % n]) {
            count++;
        }
    }
    
    // For a sorted and rotated array, there should be at most one "break" in ordering
    return count <= 1;
}
```

**Recursive Version**:

```java
public boolean checkIfSortedAndRotated(int[] nums) {
    int n = nums.length;
    
    // Find the rotation point
    int rotationPoint = 0;
    for (int i = 0; i < n - 1; i++) {
        if (nums[i] > nums[i + 1]) {
            rotationPoint = i + 1;
            break;
        }
    }
    
    // Create rotated array
    int[] rotated = new int[n];
    int index = 0;
    
    // Copy elements from rotation point to end
    for (int i = rotationPoint; i < n; i++) {
        rotated[index++] = nums[i];
    }
    
    // Copy elements from start to rotation point
    for (int i = 0; i < rotationPoint; i++) {
        rotated[index++] = nums[i];
    }
    
    // Check if rotated array is sorted
    return isSortedRecursive(rotated, 0, n);
}

private boolean isSortedRecursive(int[] nums, int i, int n) {
    // Base case: if we've checked all elements
    if (i == n - 1) {
        return true;
    }
    
    // Check current pair and recurse for the rest
    return nums[i] <= nums[i + 1] && isSortedRecursive(nums, i + 1, n);
}
```

### 📊 Complexity Summary Table

| Technique          | Time      | Space     | Notes                                   |
|--------------------|-----------|-----------|----------------------------------------|
| Brute Force        | O(n)      | O(1)      | Count inversions in one pass           |
| Recursive          | O(n)      | O(n)      | Recursively check sorted after rotation|
| Optimal (Iterative)| O(n)      | O(1)      | Same as brute force, most efficient    |

### 🧩 Follow‑Up Thoughts
**Variations**:
- What if we need to check if an array can be sorted with at most k rotations?
- What if we need to find the minimum number of swaps to make the array sorted?
- How would we handle duplicate elements in the array?

**Extension Challenge**:
Given an array, find the minimum number of rotations needed to make it sorted. If it can't be sorted by rotations, return -1.

## First Occurrence
[Problem Link](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/)

### 📜 Restate the Problem
We need to find the index of the first occurrence of a substring (needle) within a main string (haystack). If the needle is not found, return -1.

**Input Format**: Two strings - `haystack` and `needle`  
**Output Format**: An integer representing the index of the first occurrence of `needle` in `haystack`, or -1 if not found  
**Constraints**:
- 1 <= haystack.length, needle.length <= 10^4
- Both strings consist of only lowercase English characters

**Sample I/O**:
```
Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6. The first occurrence is at index 0.

Input: haystack = "leetcode", needle = "leeto"
Output: -1
Explanation: "leeto" doesn't occur in "leetcode", so return -1.
```

### 🧠 Plan Your Attack
**Key Observations**:
- We need to check each possible starting position in the haystack.
- If we find a match for the first character, we need to check the subsequent characters.
- The search space is from index 0 to (haystack.length - needle.length).

**Edge Cases**:
- If needle is an empty string, traditionally we'd return 0 (but the problem states needle length is at least 1).
- If haystack is shorter than needle, return -1 immediately.

**Strategies**:
- Brute Force: Check each possible starting position in haystack.
- Better: Use the built-in string search methods (not showing this for learning purposes).
- Optimal: Implement more efficient string matching algorithms like KMP or Boyer-Moore.

### 🔍 Technique #1 — Brute Force with Recursion
**Idea**: Use recursion to check each possible starting position in the haystack. For each position, check if the substring starting from that position matches the needle.

**Pseudocode**:
```
function findFirstOccurrence(haystack, needle, startIdx):
    // Base case: If we've gone beyond the search space, return -1
    if startIdx > haystack.length - needle.length:
        return -1
    
    // Check if substring starting at startIdx matches needle
    if matches(haystack, needle, startIdx, 0):
        return startIdx
    
    // Recursively check the next starting position
    return findFirstOccurrence(haystack, needle, startIdx + 1)

function matches(haystack, needle, haystackIdx, needleIdx):
    // Base case: If we've checked all characters in needle, it's a match
    if needleIdx == needle.length:
        return true
    
    // If characters don't match, return false
    if haystack[haystackIdx + needleIdx] != needle[needleIdx]:
        return false
    
    // Recursively check the next character
    return matches(haystack, needle, haystackIdx, needleIdx + 1)
```

**Time Complexity**: O(n * m) where n is the length of haystack and m is the length of needle  
**Space Complexity**: O(n + m) due to recursion stack

**Mini-Example**:
Input: haystack = "hello", needle = "ll"

1. Call `findFirstOccurrence("hello", "ll", 0)`
2. Check if substring at index 0 matches needle:
   - Call `matches("hello", "ll", 0, 0)`
   - 'h' != 'l', so return false
3. Call `findFirstOccurrence("hello", "ll", 1)`
4. Check if substring at index 1 matches needle:
   - Call `matches("hello", "ll", 1, 0)`
   - 'e' != 'l', so return false
5. Call `findFirstOccurrence("hello", "ll", 2)`
6. Check if substring at index 2 matches needle:
   - Call `matches("hello", "ll", 2, 0)`
   - 'l' == 'l', so continue checking
   - Call `matches("hello", "ll", 2, 1)`
   - 'l' == 'l', so continue checking
   - Call `matches("hello", "ll", 2, 2)`
   - We've checked all characters in needle, return true
7. Return startIdx = 2

### ⚡ Technique #2 — Improved Recursive Approach
**Idea**: Instead of checking each position one by one, we can use a smarter search. When we find a mismatch after matching some characters, we can skip ahead based on the partial match information.

**Pseudocode**:
```
function findFirstOccurrence(haystack, needle, haystackIdx, needleIdx):
    // Base case: If we've checked all characters in needle, found a match
    if needleIdx == needle.length:
        return haystackIdx - needle.length
    
    // Base case: If we've gone beyond haystack, needle not found
    if haystackIdx == haystack.length:
        return -1
    
    // If current characters match, check next characters
    if haystack[haystackIdx] == needle[needleIdx]:
        return findFirstOccurrence(haystack, needle, haystackIdx + 1, needleIdx + 1)
    
    // If current characters don't match, reset needle and retry at next position
    return findFirstOccurrence(haystack, needle, haystackIdx - needleIdx + 1, 0)
```

**Time Complexity**: O(n * m) worst case, but typically better  
**Space Complexity**: O(n + m) due to recursion stack

**Mini-Example**:
Input: haystack = "hello", needle = "ll"

1. Call `findFirstOccurrence("hello", "ll", 0, 0)`
2. 'h' != 'l', so call `findFirstOccurrence("hello", "ll", 1, 0)`
3. 'e' != 'l', so call `findFirstOccurrence("hello", "ll", 2, 0)`
4. 'l' == 'l', so call `findFirstOccurrence("hello", "ll", 3, 1)`
5. 'l' == 'l', so call `findFirstOccurrence("hello", "ll", 4, 2)`
6. needleIdx == needle.length, return haystackIdx - needle.length = 4 - 2 = 2

### 🚀 Technique #3 — KMP Algorithm
**Idea**: The Knuth-Morris-Pratt (KMP) algorithm avoids rechecking characters we already know about by constructing a partial match table that tells us where to restart the search after a mismatch.

**Pseudocode**:
```
function findFirstOccurrence(haystack, needle):
    if needle is empty:
        return 0
        
    // Compute the partial match table
    lps = computeLPSArray(needle)
    
    // Use recursion with the partial match table
    return kmpSearch(haystack, needle, 0, 0, lps)

function computeLPSArray(pattern):
    // Implementation omitted for brevity
    // This creates a table indicating the length of the proper prefix that is also a suffix

function kmpSearch(haystack, needle, haystackIdx, needleIdx, lps):
    // Base case: If we've gone beyond haystack, needle not found
    if haystackIdx == haystack.length:
        return -1
    
    // If current characters match, move both pointers forward
    if haystack[haystackIdx] == needle[needleIdx]:
        haystackIdx++
        needleIdx++
    
    // If we've matched the entire needle, return the starting index
    if needleIdx == needle.length:
        return haystackIdx - needleIdx
    
    // If we're still within haystack but have a mismatch
    if haystackIdx < haystack.length and haystack[haystackIdx] != needle[needleIdx]:
        // If we've partially matched, use the partial match table
        if needleIdx > 0:
            needleIdx = lps[needleIdx - 1]
        else:
            haystackIdx++
    
    // Recursive call with updated indices
    return kmpSearch(haystack, needle, haystackIdx, needleIdx, lps)
```

**Time Complexity**: O(n + m) where n is the length of haystack and m is the length of needle  
**Space Complexity**: O(m) for the partial match table + recursion stack

**Mini-Example**: 
For brevity, we'll skip the detailed KMP trace, but the key idea is that it avoids unnecessary comparisons by using information from previous matches.

### 🧑‍💻 Working Code

```java
public class FindFirstOccurrence {
    // Brute Force Recursive Solution
    public static int strStr(String haystack, String needle) {
        if (needle.length() > haystack.length()) {
            return -1;
        }
        return findFirstOccurrence(haystack, needle, 0);
    }
    
    private static int findFirstOccurrence(String haystack, String needle, int startIdx) {
        // Base case: If we've gone beyond the search space, return -1
        if (startIdx > haystack.length() - needle.length()) {
            return -1;
        }
        
        // Check if substring starting at startIdx matches needle
        if (matches(haystack, needle, startIdx, 0)) {
            return startIdx;
        }
        
        // Recursively check the next starting position
        return findFirstOccurrence(haystack, needle, startIdx + 1);
    }
    
    private static boolean matches(String haystack, String needle, int haystackIdx, int needleIdx) {
        // Base case: If we've checked all characters in needle, it's a match
        if (needleIdx == needle.length()) {
            return true;
        }
        
        // If characters don't match, return false
        if (haystack.charAt(haystackIdx + needleIdx) != needle.charAt(needleIdx)) {
            return false;
        }
        
        // Recursively check the next character
        return matches(haystack, needle, haystackIdx, needleIdx + 1);
    }
    
    // Improved Recursive Approach
    public static int strStrImproved(String haystack, String needle) {
        return findFirstOccurrenceImproved(haystack, needle, 0, 0);
    }
    
    private static int findFirstOccurrenceImproved(String haystack, String needle, int haystackIdx, int needleIdx) {
        // Base case: If we've checked all characters in needle, found a match
        if (needleIdx == needle.length()) {
            return haystackIdx - needle.length();
        }
        
        // Base case: If we've gone beyond haystack, needle not found
        if (haystackIdx == haystack.length()) {
            return -1;
        }
        
        // If current characters match, check next characters
        if (haystack.charAt(haystackIdx) == needle.charAt(needleIdx)) {
            return findFirstOccurrenceImproved(haystack, needle, haystackIdx + 1, needleIdx + 1);
        }
        
        // If current characters don't match, reset needle and retry at next position
        if (needleIdx == 0) {
            return findFirstOccurrenceImproved(haystack, needle, haystackIdx + 1, 0);
        } else {
            return findFirstOccurrenceImproved(haystack, needle, haystackIdx - needleIdx + 1, 0);
        }
    }
    
    // KMP Algorithm (non-recursive implementation for simplicity)
    public static int strStrKMP(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        
        if (n == 0) return 0;
        if (m < n) return -1;
        
        // Compute the LPS (Longest Prefix Suffix) array
        int[] lps = computeLPSArray(needle);
        
        int i = 0; // Index for haystack
        int j = 0; // Index for needle
        
        while (i < m) {
            // Characters match, move both pointers
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }
            
            // Found the pattern
            if (j == n) {
                return i - j;
            }
            // Mismatch after some matches
            else if (i < m && haystack.charAt(i) != needle.charAt(j)) {
                // Use LPS array to determine where to start next match
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        
        return -1; // Pattern not found
    }
    
    private static int[] computeLPSArray(String pattern) {
        int n = pattern.length();
        int[] lps = new int[n];
        
        int len = 0; // Length of previous longest prefix suffix
        int i = 1;
        
        // lps[0] is always 0
        lps[0] = 0;
        
        while (i < n) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                // This is the tricky part
                if (len != 0) {
                    // Use the previously computed LPS value
                    len = lps[len - 1];
                } else {
                    // No match found
                    lps[i] = 0;
                    i++;
                }
            }
        }
        
        return lps;
    }
    
    public static void main(String[] args) {
        String haystack1 = "sadbutsad";
        String needle1 = "sad";
        System.out.println("Brute Force: " + strStr(haystack1, needle1));
        System.out.println("Improved: " + strStrImproved(haystack1, needle1));
        System.out.println("KMP: " + strStrKMP(haystack1, needle1));
        
        String haystack2 = "leetcode";
        String needle2 = "leeto";
        System.out.println("Brute Force: " + strStr(haystack2, needle2));
        System.out.println("Improved: " + strStrImproved(haystack2, needle2));
        System.out.println("KMP: " + strStrKMP(haystack2, needle2));
    }
}
```

### 📊 Complexity Summary Table

| Technique             | Time       | Space      | Notes                                         |
|-----------------------|------------|--------------|-----------------------------------------------|
| Brute Force Recursion | O(n * m)   | O(n + m)     | Simple but inefficient for large inputs      |
| Improved Recursion    | O(n * m)   | O(n + m)     | Better on average but still O(n*m) worst case|
| KMP Algorithm         | O(n + m)   | O(m)         | Optimal, uses pattern properties effectively  |

### 🧩 Follow‑Up Thoughts
**Variations**:
- What if we need to find all occurrences instead of just the first one?
- How would we handle very large texts efficiently (streaming inputs)?
- Can we further optimize for specific types of patterns (e.g., repeated substrings)?

**Extension Challenge**:
Implement a function that finds all occurrences of a pattern in a text using the KMP algorithm and returns a list of starting indices.

## Last Occurrence
[Problem Link](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/)

### 📜 Restate the Problem
We need to find the first and last position (indices) of a target value in a sorted array. If the target is not found, return [-1, -1].

**Input Format**: A sorted array of integers `nums` and a target value `target`  
**Output Format**: An array of two integers representing the starting and ending positions of the target  
**Constraints**:
- 0 <= nums.length <= 10^5
- -10^9 <= nums[i] <= 10^9
- nums is sorted in ascending order
- -10^9 <= target <= 10^9

**Sample I/O**:
```
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Explanation: The target value 8 appears at indices 3 and 4.

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Explanation: The target value 6 does not exist in the array.

Input: nums = [], target = 0
Output: [-1,-1]
Explanation: The array is empty.
```

### 🧠 Plan Your Attack
**Key Observations**:
- The array is sorted, which enables us to use binary search for efficiency.
- We need to find both the first and last occurrence of the target value.
- The target may appear multiple times, or not at all.

**Edge Cases**:
- Empty array
- Target appears only once
- Target appears at the beginning or end of the array
- Target doesn't exist in the array

**Strategies**:
- Brute Force: Linear scan through the array to find first and last positions.
- Better: Binary search to find any occurrence, then linear scan left and right.
- Optimal: Two modified binary searches - one to find the first occurrence and another to find the last occurrence.

### 🔍 Technique #1 — Brute Force with Recursion
**Idea**: Use recursion to scan the array from both ends to find the first and last occurrences of the target value.

**Pseudocode**:
```
function searchRange(nums, target):
    return [findFirstPosition(nums, target, 0), findLastPosition(nums, target, nums.length - 1)]

function findFirstPosition(nums, target, start):
    // Base case: If we've gone beyond the array boundaries, target not found
    if start >= nums.length:
        return -1
    
    // If target found, return current position
    if nums[start] == target:
        return start
    
    // Recursively check the next position
    return findFirstPosition(nums, target, start + 1)

function findLastPosition(nums, target, end):
    // Base case: If we've gone beyond the array boundaries, target not found
    if end < 0:
        return -1
    
    // If target found, return current position
    if nums[end] == target:
        return end
    
    // Recursively check the previous position
    return findLastPosition(nums, target, end - 1)
```

**Time Complexity**: O(n) - We may need to scan the entire array  
**Space Complexity**: O(n) - Due to the recursion stack

**Mini-Example**:
Input: nums = [5,7,7,8,8,10], target = 8

1. Call `findFirstPosition([5,7,7,8,8,10], 8, 0)`
   - nums[0] = 5, not equal to target, so call `findFirstPosition([5,7,7,8,8,10], 8, 1)`
   - nums[1] = 7, not equal to target, so call `findFirstPosition([5,7,7,8,8,10], 8, 2)`
   - nums[2] = 7, not equal to target, so call `findFirstPosition([5,7,7,8,8,10], 8, 3)`
   - nums[3] = 8, equal to target, so return 3

2. Call `findLastPosition([5,7,7,8,8,10], 8, 5)`
   - nums[5] = 10, not equal to target, so call `findLastPosition([5,7,7,8,8,10], 8, 4)`
   - nums[4] = 8, equal to target, so return 4

3. Return [3, 4]

### ⚡ Technique #2 — Binary Search with Linear Scan
**Idea**: Use binary search to find any occurrence of the target, then scan linearly left and right to find the first and last positions.

**Pseudocode**:
```
function searchRange(nums, target):
    // Find any occurrence of target using binary search
    index = binarySearch(nums, target, 0, nums.length - 1)
    
    // If target not found, return [-1, -1]
    if index == -1:
        return [-1, -1]
    
    // Find the leftmost and rightmost positions
    left = scanLeft(nums, target, index)
    right = scanRight(nums, target, index)
    
    return [left, right]

function binarySearch(nums, target, low, high):
    // Base case: If search space is exhausted, target not found
    if low > high:
        return -1
    
    mid = (low + high) / 2
    
    if nums[mid] == target:
        return mid
    elif nums[mid] > target:
        return binarySearch(nums, target, low, mid - 1)
    else:
        return binarySearch(nums, target, mid + 1, high)

function scanLeft(nums, target, index):
    // Base case: If we've reached the start of the array or the previous element is not target
    if index == 0 or nums[index - 1] != target:
        return index
    
    return scanLeft(nums, target, index - 1)

function scanRight(nums, target, index):
    // Base case: If we've reached the end of the array or the next element is not target
    if index == nums.length - 1 or nums[index + 1] != target:
        return index
    
    return scanRight(nums, target, index + 1)
```

**Time Complexity**: O(log n) for binary search + O(n) for linear scan in worst case = O(n)  
**Space Complexity**: O(log n) due to the recursion stack for binary search

**Mini-Example**:
Input: nums = [5,7,7,8,8,10], target = 8

1. Call `binarySearch([5,7,7,8,8,10], 8, 0, 5)`
   - mid = 2, nums[2] = 7 < 8, so call `binarySearch([5,7,7,8,8,10], 8, 3, 5)`
   - mid = 4, nums[4] = 8 == 8, so return 4

2. Call `scanLeft([5,7,7,8,8,10], 8, 4)`
   - nums[3] = 8, which is equal to target, so call `scanLeft([5,7,7,8,8,10], 8, 3)`
   - nums[2] = 7, which is not equal to target, so return 3

3. Call `scanRight([5,7,7,8,8,10], 8, 4)`
   - nums[5] = 10, which is not equal to target, so return 4

4. Return [3, 4]

### 🚀 Technique #3 — Modified Binary Search (Optimal)
**Idea**: Use two modified binary searches - one to find the first occurrence and another to find the last occurrence of the target.

**Pseudocode**:
```
function searchRange(nums, target):
    return [findFirstOccurrence(nums, target), findLastOccurrence(nums, target)]

function findFirstOccurrence(nums, target):
    return modifiedBinarySearch(nums, target, 0, nums.length - 1, true)

function findLastOccurrence(nums, target):
    return modifiedBinarySearch(nums, target, 0, nums.length - 1, false)

function modifiedBinarySearch(nums, target, low, high, findFirst):
    // Base case: If search space is exhausted, target not found
    if low > high:
        return -1
    
    mid = (low + high) / 2
    
    if nums[mid] > target:
        return modifiedBinarySearch(nums, target, low, mid - 1, findFirst)
    elif nums[mid] < target:
        return modifiedBinarySearch(nums, target, mid + 1, high, findFirst)
    else: // nums[mid] == target
        if findFirst:
            // If looking for first occurrence, check if this is the first or recursively check left
            if mid == 0 or nums[mid - 1] != target:
                return mid
            return modifiedBinarySearch(nums, target, low, mid - 1, findFirst)
        else:
            // If looking for last occurrence, check if this is the last or recursively check right
            if mid == nums.length - 1 or nums[mid + 1] != target:
                return mid
            return modifiedBinarySearch(nums, target, mid + 1, high, findFirst)
```

**Time Complexity**: O(log n) - We perform two binary searches  
**Space Complexity**: O(log n) - Due to the recursion stack

**Mini-Example**:
Input: nums = [5,7,7,8,8,10], target = 8

1. Call `findFirstOccurrence([5,7,7,8,8,10], 8)`
   - Call `modifiedBinarySearch([5,7,7,8,8,10], 8, 0, 5, true)`
   - mid = 2, nums[2] = 7 < 8, so call `modifiedBinarySearch([5,7,7,8,8,10], 8, 3, 5, true)`
   - mid = 4, nums[4] = 8 == 8, but nums[3] is also 8, so call `modifiedBinarySearch([5,7,7,8,8,10], 8, 3, 3, true)`
   - mid = 3, nums[3] = 8 == 8, and nums[2] != 8, so return 3

2. Call `findLastOccurrence([5,7,7,8,8,10], 8)`
   - Call `modifiedBinarySearch([5,7,7,8,8,10], 8, 0, 5, false)`
   - mid = 2, nums[2] = 7 < 8, so call `modifiedBinarySearch([5,7,7,8,8,10], 8, 3, 5, false)`
   - mid = 4, nums[4] = 8 == 8, and nums[5] != 8, so return 4

3. Return [3, 4]

### 🧑‍💻 Working Code

```java
public class FindFirstAndLastPosition {
    
    // Solution using two modified binary searches
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        
        result[0] = findFirstOccurrence(nums, target);
        result[1] = findLastOccurrence(nums, target);
        
        return result;
    }
    
    // Find the first occurrence of target
    private int findFirstOccurrence(int[] nums, int target) {
        return modifiedBinarySearch(nums, target, 0, nums.length - 1, true);
    }
    
    // Find the last occurrence of target
    private int findLastOccurrence(int[] nums, int target) {
        return modifiedBinarySearch(nums, target, 0, nums.length - 1, false);
    }
    
    // Modified binary search that can find either first or last occurrence
    private int modifiedBinarySearch(int[] nums, int target, int low, int high, boolean findFirst) {
        // Base case: If search space is exhausted, target not found
        if (low > high) {
            return -1;
        }
        
        int mid = low + (high - low) / 2; // Avoid potential overflow
        
        if (nums[mid] > target) {
            return modifiedBinarySearch(nums, target, low, mid - 1, findFirst);
        } else if (nums[mid] < target) {
            return modifiedBinarySearch(nums, target, mid + 1, high, findFirst);
        } else { // nums[mid] == target
            if (findFirst) {
                // If looking for first occurrence
                if (mid == 0 || nums[mid - 1] != target) {
                    return mid;
                }
                return modifiedBinarySearch(nums, target, low, mid - 1, findFirst);
            } else {
                // If looking for last occurrence
                if (mid == nums.length - 1 || nums[mid + 1] != target) {
                    return mid;
                }
                return modifiedBinarySearch(nums, target, mid + 1, high, findFirst);
            }
        }
    }
    
    // Brute force recursive solution for educational purposes
    public int[] searchRangeBruteForce(int[] nums, int target) {
        return new int[] {
            findFirstPosition(nums, target, 0),
            findLastPosition(nums, target, nums.length - 1)
        };
    }
    
    private int findFirstPosition(int[] nums, int target, int start) {
        // Base case: If we've gone beyond the array boundaries, target not found
        if (start >= nums.length) {
            return -1;
        }
        
        // If target found, return current position
        if (nums[start] == target) {
            return start;
        }
        
        // Recursively check the next position
        return findFirstPosition(nums, target, start + 1);
    }
    
    private int findLastPosition(int[] nums, int target, int end) {
        // Base case: If we've gone beyond the array boundaries, target not found
        if (end < 0) {
            return -1;
        }
        
        // If target found, return current position
        if (nums[end] == target) {
            return end;
        }
        
        // Recursively check the previous position
        return findLastPosition(nums, target, end - 1);
    }
    
    // Binary search + linear scan solution
    public int[] searchRangeHybrid(int[] nums, int target) {
        // Find any occurrence of target using binary search
        int index = binarySearch(nums, target, 0, nums.length - 1);
        
        // If target not found, return [-1, -1]
        if (index == -1) {
            return new int[] {-1, -1};
        }
        
        // Find the leftmost and rightmost positions
        int left = scanLeft(nums, target, index);
        int right = scanRight(nums, target, index);
        
        return new int[] {left, right};
    }
    
    private int binarySearch(int[] nums, int target, int low, int high) {
        // Base case: If search space is exhausted, target not found
        if (low > high) {
            return -1;
        }
        
        int mid = low + (high - low) / 2; // Avoid potential overflow
        
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return binarySearch(nums, target, low, mid - 1);
        } else {
            return binarySearch(nums, target, mid + 1, high);
        }
    }
    
    private int scanLeft(int[] nums, int target, int index) {
        // Base case: If we've reached the start of the array or the previous element is not target
        if (index == 0 || nums[index - 1] != target) {
            return index;
        }
        
        return scanLeft(nums, target, index - 1);
    }
    
    private int scanRight(int[] nums, int target, int index) {
        // Base case: If we've reached the end of the array or the next element is not target
        if (index == nums.length - 1 || nums[index + 1] != target) {
            return index;
        }
        
        return scanRight(nums, target, index + 1);
    }
    
    public static void main(String[] args) {
        FindFirstAndLastPosition solution = new FindFirstAndLastPosition();
        
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int target1 = 8;
        int[] result1 = solution.searchRange(nums1, target1);
        System.out.println("Optimal: [" + result1[0] + ", " + result1[1] + "]");
        
        int[] result1b = solution.searchRangeBruteForce(nums1, target1);
        System.out.println("Brute Force: [" + result1b[0] + ", " + result1b[1] + "]");
        
        int[] result1c = solution.searchRangeHybrid(nums1, target1);
        System.out.println("Hybrid: [" + result1c[0] + ", " + result1c[1] + "]");
        
        int[] nums2 = {5, 7, 7, 8, 8, 10};
        int target2 = 6;
        int[] result2 = solution.searchRange(nums2, target2);
        System.out.println("\nOptimal: [" + result2[0] + ", " + result2[1] + "]");
        
        int[] nums3 = {};
        int target3 = 0;
        int[] result3 = solution.searchRange(nums3, target3);
        System.out.println("\nOptimal: [" + result3[0] + ", " + result3[1] + "]");
    }
}
```

### 📊 Complexity Summary Table

| Technique                 | Time       | Space      | Notes                                            |
|---------------------------|------------|------------|-------------------------------------------------|
| Brute Force Recursion     | O(n)       | O(n)       | Simple but inefficient for large inputs         |
| Binary Search + Linear    | O(n)       | O(log n)   | Better average case but still O(n) worst case   |
| Modified Binary Search    | O(log n)   | O(log n)   | Optimal solution, handles all cases efficiently  |

### 🧩 Follow‑Up Thoughts
**Variations**:
- What if the array is not sorted? Could we sort it first (adding O(n log n) time complexity) or use other data structures?
- What if we need to find all numbers in a given range [left, right] instead of a single target?
- How would the solution change if we had duplicate elements but wanted to count the occurrences instead of just the range?

**Extension Challenge**:
Modify the solution to handle a rotated sorted array (where the array is rotated at some pivot point). Can you still achieve O(log n) time complexity?

## Print x to the power n
[Problem Link](https://leetcode.com/problems/powx-n/description/)

### 📜 Restate the Problem
Implement a function that calculates x raised to the power n (x^n). We need to compute this efficiently, handling both positive and negative exponents.

**Input Format**: A double value `x` and an integer `n`  
**Output Format**: A double value representing x^n  
**Constraints**:
- -100.0 < x < 100.0
- -2^31 <= n <= 2^31-1
- -10^4 <= x^n <= 10^4

**Sample I/O**:
```
Input: x = 2.00000, n = 10
Output: 1024.00000

Input: x = 2.10000, n = 3
Output: 9.26100

Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2^-2 = 1/2^2 = 1/4 = 0.25
```

### 🧠 Plan Your Attack
**Key Observations**:
- We need to handle both positive and negative exponents.
- For negative exponents: x^-n = 1/(x^n).
- Large values of n could cause overflow if we use a naive approach.

**Edge Cases**:
- n = 0 (any number to the power of 0 is 1)
- x = 0 (0 to any positive power is 0, to any negative power is undefined)
- x = 1 (1 to any power is 1)
- Very large or very small values of n could cause timing issues with naive solutions

**Strategies**:
- Brute Force: Multiply x by itself n times - O(n) time.
- Better: Use recursive approach with divide and conquer - O(log n) time.
- Optimal: Iterative binary exponentiation - O(log n) time with less stack overhead.

### 🔍 Technique #1 — Brute Force Recursive Approach
**Idea**: Simply compute x * x * ... * x (n times) using recursion.

**Pseudocode**:
```
function power(x, n):
    // Handle negative exponent
    if n < 0:
        return 1 / power(x, -n)
    
    // Base cases
    if n == 0:
        return 1
    if n == 1:
        return x
    
    // Recursive case: multiply x with x^(n-1)
    return x * power(x, n - 1)
```

**Time Complexity**: O(n) - We perform n multiplications  
**Space Complexity**: O(n) - Recursion stack depth is n

**Mini-Example**:
Input: x = 2.0, n = 4

1. Call `power(2.0, 4)`
   - n is not 0 or 1, so return 2.0 * power(2.0, 3)
2. Call `power(2.0, 3)`
   - Return 2.0 * power(2.0, 2)
3. Call `power(2.0, 2)`
   - Return 2.0 * power(2.0, 1)
4. Call `power(2.0, 1)`
   - Base case: return 2.0
5. Unwinding the recursion: 2.0 * 2.0 = 4.0
6. Continuing: 2.0 * 4.0 = 8.0
7. Continuing: 2.0 * 8.0 = 16.0
8. Final result: 16.0

### ⚡ Technique #2 — Optimized Recursive Approach (Divide and Conquer)
**Idea**: Instead of doing n multiplications, we can use the fact that x^n = x^(n/2) * x^(n/2) for even n, and x^n = x * x^(n-1) for odd n. This reduces the number of multiplications dramatically.

**Pseudocode**:
```
function powerOptimized(x, n):
    // Handle negative exponent
    if n < 0:
        return 1 / powerOptimized(x, -n)
    
    // Base cases
    if n == 0:
        return 1
    if n == 1:
        return x
    
    // For even powers
    if n % 2 == 0:
        half = powerOptimized(x, n / 2)
        return half * half
    
    // For odd powers
    else:
        half = powerOptimized(x, n / 2)
        return x * half * half
```

**Time Complexity**: O(log n) - We divide the problem size by 2 each time  
**Space Complexity**: O(log n) - Recursion stack depth is log n

**Mini-Example**:
Input: x = 2.0, n = 10

1. Call `powerOptimized(2.0, 10)`
   - n is even, so compute half = powerOptimized(2.0, 5)
2. Call `powerOptimized(2.0, 5)`
   - n is odd, so compute half = powerOptimized(2.0, 2)
3. Call `powerOptimized(2.0, 2)`
   - n is even, so compute half = powerOptimized(2.0, 1)
4. Call `powerOptimized(2.0, 1)`
   - Base case: return 2.0
5. Back to step 3: half = 2.0, return 2.0 * 2.0 = 4.0
6. Back to step 2: half = 4.0, return 2.0 * 4.0 * 4.0 = 32.0
7. Back to step 1: half = 32.0, return 32.0 * 32.0 = 1024.0

### 🚀 Technique #3 — Iterative Binary Exponentiation
**Idea**: Use binary representation of the exponent to compute the power iteratively, avoiding recursion overhead.

**Pseudocode**:
```
function powerIterative(x, n):
    // Handle negative exponent
    if n < 0:
        x = 1 / x
        n = -n
    
    result = 1
    current_product = x
    
    while n > 0:
        // If current bit in n's binary representation is 1
        if n % 2 == 1:
            result *= current_product
        
        // Square the current product for the next bit
        current_product *= current_product
        
        // Shift to next bit
        n = n / 2  // Integer division
    
    return result
```

**Time Complexity**: O(log n) - We process each bit of n  
**Space Complexity**: O(1) - Only uses a constant amount of extra space

**Mini-Example**:
Input: x = 2.0, n = 10 (binary: 1010)

1. Initialize result = 1, current_product = 2.0
2. Loop while n > 0:
   - n = 10: Is the last bit 1? No. current_product = 2.0 * 2.0 = 4.0, n = 5
   - n = 5: Is the last bit 1? Yes. result = 1 * 4.0 = 4.0, current_product = 4.0 * 4.0 = 16.0, n = 2
   - n = 2: Is the last bit 1? No. current_product = 16.0 * 16.0 = 256.0, n = 1
   - n = 1: Is the last bit 1? Yes. result = 4.0 * 256.0 = 1024.0, current_product = 256.0 * 256.0 = 65536.0, n = 0
3. Return result = 1024.0

### 🧑‍💻 Working Code

```java
public class Power {
    
    // Brute Force Recursive Approach - O(n)
    public static double myPow(double x, int n) {
        // Special case to handle Integer.MIN_VALUE
        if (n == Integer.MIN_VALUE) {
            return myPow(1/x, Integer.MAX_VALUE) * (1/x);
        }
        
        // Handle negative exponent
        if (n < 0) {
            return 1 / myPow(x, -n);
        }
        
        // Base cases
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        
        // Recursive case: multiply x with x^(n-1)
        return x * myPow(x, n - 1);
    }
    
    // Optimized Recursive Approach - O(log n)
    public static double myPowOptimized(double x, int n) {
        // Special case to handle Integer.MIN_VALUE
        if (n == Integer.MIN_VALUE) {
            return myPowOptimized(1/x, Integer.MAX_VALUE) * (1/x);
        }
        
        // Handle negative exponent
        if (n < 0) {
            return 1 / myPowOptimized(x, -n);
        }
        
        // Base cases
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        
        // Divide and conquer
        double half = myPowOptimized(x, n / 2);
        
        // For even powers
        if (n % 2 == 0) {
            return half * half;
        }
        // For odd powers
        else {
            return x * half * half;
        }
    }
    
    // Iterative Binary Exponentiation - O(log n)
    public static double myPowIterative(double x, int n) {
        // Handle special case of Integer.MIN_VALUE
        if (n == Integer.MIN_VALUE) {
            x = 1 / x;
            // Use long to avoid overflow when negating Integer.MIN_VALUE
            return myPowIterative(x, Integer.MAX_VALUE) * x;
        }
        
        // Handle negative exponent
        boolean isNegative = n < 0;
        if (isNegative) {
            n = -n;
        }
        
        double result = 1.0;
        double currentProduct = x;
        
        while (n > 0) {
            // If current bit is 1, multiply the result
            if (n % 2 == 1) {
                result *= currentProduct;
            }
            
            // Square the current product for the next bit
            currentProduct *= currentProduct;
            
            // Shift to next bit
            n /= 2;
        }
        
        return isNegative ? 1 / result : result;
    }
    
    public static void main(String[] args) {
        // Note: We don't use the brute force for large powers due to inefficiency
        System.out.println("Optimized Recursive:");
        System.out.println("2^10 = " + myPowOptimized(2.0, 10)); // Expected: 1024.0
        System.out.println("2.1^3 = " + myPowOptimized(2.1, 3)); // Expected: 9.261
        System.out.println("2^(-2) = " + myPowOptimized(2.0, -2)); // Expected: 0.25
        
        System.out.println("\nIterative:");
        System.out.println("2^10 = " + myPowIterative(2.0, 10)); // Expected: 1024.0
        System.out.println("2.1^3 = " + myPowIterative(2.1, 3)); // Expected: 9.261
        System.out.println("2^(-2) = " + myPowIterative(2.0, -2)); // Expected: 0.25
    }
}
```

### 📊 Complexity Summary Table

| Technique               | Time       | Space      | Notes                                           |
|------------------------|------------|------------|------------------------------------------------|
| Brute Force Recursion   | O(n)       | O(n)       | Simple but inefficient for large exponents     |
| Optimized Recursion     | O(log n)   | O(log n)   | Efficient divide-and-conquer approach          |
| Iterative Binary        | O(log n)   | O(1)       | Most efficient, avoids recursion stack overhead|

### 🧩 Follow‑Up Thoughts
**Variations**:
- What if we need to calculate very large powers that could cause overflow, even with the binary exponentiation technique?
- How would we handle matrix exponentiation (raising a matrix to a power)?
- Can we apply similar techniques for calculating exponents in modular arithmetic (x^n mod m)?

**Extension Challenge**:
Implement a function to calculate the power of a complex number. For example, given a complex number a + bi and an integer n, compute (a + bi)^n.

## Tiling Problem
*Solution coming soon*

## Remove Duplicates in a String
[Problem Link](https://leetcode.com/problems/remove-duplicate-letters/description/)

### 📜 Restate the Problem
Given a string s, we need to remove duplicate letters so that every letter appears once and only once. We must make sure our result is the lexicographically smallest string possible among all possible results (i.e., the result should have the smallest possible alphabetical order).

**Input Format**: A string `s`  
**Output Format**: A string with no duplicate characters that is lexicographically smallest  
**Constraints**:
- 1 <= s.length <= 10^4
- s consists of lowercase English letters

**Sample I/O**:
```
Input: s = "bcabc"
Output: "abc"
Explanation: We can remove the duplicates by keeping the first occurrence of each letter, which gives us the lexicographically smallest result.

Input: s = "cbacdcbc"
Output: "acdb"
Explanation: We need to choose which occurrences to keep to get the lexicographically smallest result.
```

### 🧠 Plan Your Attack
**Key Observations**:
- We need to keep only one occurrence of each letter.
- The result should be lexicographically smallest (alphabetically ordered as much as possible).
- If a character appears multiple times, we have options on which occurrence to keep.
- Later occurrences might allow us to achieve a lexicographically smaller result by removing earlier occurrences if they're larger.

**Edge Cases**:
- String with all unique characters (nothing to remove)
- String with all same characters (keep just one)
- String where the lexicographically smallest result is not achieved by keeping the first occurrence

**Strategies**:
- Brute Force: Generate all possible strings with unique characters and find the lexicographically smallest.
- Better: Use a greedy approach with a stack to keep track of characters.
- Optimal: Single-pass algorithm using a stack and tracking of remaining characters.

### 🔍 Technique #1 — Recursive Backtracking (Brute Force)
**Idea**: Generate all possible strings with unique characters by selecting one occurrence for each character, then find the lexicographically smallest one.

**Pseudocode**:
```
function removeDuplicateLetters(s):
    // Count the frequency of each character
    counts = count occurrences of each character in s
    
    // Track which characters we've selected
    selected = set()
    
    function backtrack(index, current):
        // Base case: if we've processed the entire string
        if index == s.length:
            // If we've selected all unique characters, return the current string
            if selected.size == counts.size:
                return current
            return null
        
        char = s[index]
        result = null
        
        // Option 1: Skip this occurrence if we've already selected this character
        if char in selected:
            result = backtrack(index + 1, current)
        else:
            // Option 2: Select this occurrence
            selected.add(char)
            result1 = backtrack(index + 1, current + char)
            selected.remove(char)
            
            // Option 3: Skip this occurrence even if we haven't selected this character yet
            if counts[char] > 1:  // If there are more occurrences of this character
                counts[char]--
                result2 = backtrack(index + 1, current)
                counts[char]++
                
                // Choose the lexicographically smaller result
                if result1 and result2:
                    result = min(result1, result2)  // lexicographically
                else:
                    result = result1 or result2  // whichever is not null
            else:
                result = result1
        
        return result
    
    return backtrack(0, "")
```

**Time Complexity**: O(2^n) in the worst case, as we have two choices for each character  
**Space Complexity**: O(n) for the recursion stack and to store the current result

**Mini-Example**:
Input: s = "bcabc"

This approach would explore too many states to show in detail, but it would eventually find "abc" as the lexicographically smallest result.

### ⚡ Technique #2 — Greedy Stack Approach
**Idea**: Use a stack to keep track of characters. For each character, consider removing characters from the stack if they are larger and will appear later.

**Pseudocode**:
```
function removeDuplicateLetters(s):
    // Count the frequency of each character
    counts = count occurrences of each character in s
    
    // Track which characters are in our result
    inResult = set()
    
    // Stack to build our result
    stack = []
    
    for i = 0 to s.length - 1:
        char = s[i]
        
        // Decrease the count for this character
        counts[char]--
        
        // If we've already included this character, skip it
        if char in inResult:
            continue
        
        // While the stack is not empty, the current character is lexicographically smaller than the last character in the stack,
        // and that character will appear again later, remove it
        while stack is not empty and char < stack[-1] and counts[stack[-1]] > 0:
            removed = stack.pop()
            inResult.remove(removed)
        
        // Add the current character to our result
        stack.push(char)
        inResult.add(char)
    
    // Convert the stack to a string and return
    return ''.join(stack)
```

**Time Complexity**: O(n) where n is the length of the string  
**Space Complexity**: O(k) where k is the number of unique characters (at most 26 for lowercase English letters)

**Mini-Example**:
Input: s = "bcabc"

1. Initialize empty stack, counts = {'b': 2, 'c': 2, 'a': 1}, inResult = {}
2. Process 'b': stack = ['b'], inResult = {'b'}
3. Process 'c': stack = ['b', 'c'], inResult = {'b', 'c'}
4. Process 'a':
   - 'a' < 'c' and 'c' appears again later, so pop 'c': stack = ['b']
   - 'a' < 'b' and 'b' appears again later, so pop 'b': stack = []
   - Push 'a': stack = ['a'], inResult = {'a'}
5. Process 'b': stack = ['a', 'b'], inResult = {'a', 'b'}
6. Process 'c': stack = ['a', 'b', 'c'], inResult = {'a', 'b', 'c'}
7. Return "abc"

### 🚀 Technique #3 — Optimized Recursive Approach
**Idea**: Use recursion to build the result character by character, always choosing the lexicographically smallest valid character at each step.

**Pseudocode**:
```
function removeDuplicateLetters(s):
    // If the string is empty, return empty string
    if s is empty:
        return ""
    
    // Count the frequency of each character
    counts = count occurrences of each character in s
    
    // Find the position of the smallest character that allows us to build a valid result
    pos = 0
    for i = 0 to s.length - 1:
        if s[i] < s[pos]:
            pos = i
        
        // Decrease the count
        counts[s[i]]--
        
        // If this is the last occurrence of this character, break
        if counts[s[i]] == 0:
            break
    
    // Get the character at the found position
    char = s[pos]
    
    // Remove all occurrences of this character from the remaining string
    // and recursively solve for the remaining characters
    remaining = s.substring(pos + 1).replaceAll(char, "")
    
    return char + removeDuplicateLetters(remaining)
```

**Time Complexity**: O(n^2) due to the string operations and recursion  
**Space Complexity**: O(n) for the recursion stack

**Mini-Example**:
Input: s = "bcabc"

1. counts = {'b': 2, 'c': 2, 'a': 1}
2. Smallest character that ensures we can build a valid result is 'a' at position 2
3. remaining = "bc" (remove all 'a's from the substring after position 2)
4. Recursively solve for "bc":
   - counts = {'b': 1, 'c': 1}
   - Smallest character is 'b' at position 0
   - remaining = "c" (remove all 'b's from the substring after position 0)
   - Recursively solve for "c":
     - counts = {'c': 1}
     - Smallest character is 'c' at position 0
     - remaining = "" (empty string)
     - Return "c"
   - Return "b" + "c" = "bc"
5. Return "a" + "bc" = "abc"

### 🧑‍💻 Working Code

```java
public class RemoveDuplicateLetters {
    
    // Greedy Stack Approach
    public String removeDuplicateLetters(String s) {
        int[] counts = new int[26]; // Count of each character
        boolean[] inResult = new boolean[26]; // Whether a character is in the result
        char[] stack = new char[26]; // Stack to build our result
        int top = -1; // Stack pointer
        
        // Count occurrences of each character
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        
        for (char c : s.toCharArray()) {
            // Decrease the count for this character
            counts[c - 'a']--;
            
            // If we've already included this character, skip it
            if (inResult[c - 'a']) {
                continue;
            }
            
            // While the stack is not empty, the current character is lexicographically smaller than the last character in the stack,
            // and that character will appear again later, remove it
            while (top >= 0 && c < stack[top] && counts[stack[top] - 'a'] > 0) {
                inResult[stack[top] - 'a'] = false;
                top--;
            }
            
            // Add the current character to our result
            stack[++top] = c;
            inResult[c - 'a'] = true;
        }
        
        // Convert the stack to a string and return
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= top; i++) {
            result.append(stack[i]);
        }
        
        return result.toString();
    }
    
    // Recursive Approach
    public String removeDuplicateLettersRecursive(String s) {
        // If the string is empty, return empty string
        if (s.isEmpty()) {
            return "";
        }
        
        // Count the frequency of each character
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        
        // Find the position of the smallest character that allows us to build a valid result
        int pos = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) {
                pos = i;
            }
            
            // Decrease the count
            counts[s.charAt(i) - 'a']--;
            
            // If this is the last occurrence of this character, break
            if (counts[s.charAt(i) - 'a'] == 0) {
                break;
            }
        }
        
        // Get the character at the found position
        char ch = s.charAt(pos);
        
        // Remove all occurrences of this character from the remaining string
        // and recursively solve for the remaining characters
        String remaining = s.substring(pos + 1).replaceAll(String.valueOf(ch), "");
        
        return ch + removeDuplicateLettersRecursive(remaining);
    }
    
    // Backtracking Approach (Not recommended for large inputs due to exponential complexity)
    public String removeDuplicateLettersBacktracking(String s) {
        // Count the frequency of each character
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        
        boolean[] selected = new boolean[26];
        return backtrack(s, 0, "", counts, selected);
    }
    
    private String backtrack(String s, int index, String current, int[] counts, boolean[] selected) {
        // Base case: if we've processed the entire string
        if (index == s.length()) {
            // Check if we've selected all unique characters
            for (int i = 0; i < 26; i++) {
                if (counts[i] > 0 && !selected[i]) {
                    return null; // Missing some characters
                }
            }
            return current;
        }
        
        char c = s.charAt(index);
        int charIndex = c - 'a';
        String result = null;
        
        // Option 1: Skip this occurrence if we've already selected this character
        if (selected[charIndex]) {
            result = backtrack(s, index + 1, current, counts, selected);
        } else {
            // Option 2: Select this occurrence
            selected[charIndex] = true;
            String result1 = backtrack(s, index + 1, current + c, counts, selected);
            selected[charIndex] = false;
            
            // Option 3: Skip this occurrence even if we haven't selected this character yet
            if (counts[charIndex] > 1) {
                counts[charIndex]--;
                String result2 = backtrack(s, index + 1, current, counts, selected);
                counts[charIndex]++;
                
                // Choose the lexicographically smaller result
                if (result1 != null && result2 != null) {
                    result = (result1.compareTo(result2) < 0) ? result1 : result2;
                } else {
                    result = (result1 != null) ? result1 : result2;
                }
            } else {
                result = result1;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        RemoveDuplicateLetters solution = new RemoveDuplicateLetters();
        
        String s1 = "bcabc";
        System.out.println("Input: " + s1);
        System.out.println("Greedy Stack: " + solution.removeDuplicateLetters(s1));
        System.out.println("Recursive: " + solution.removeDuplicateLettersRecursive(s1));
        // Note: Backtracking is very inefficient for larger inputs
        System.out.println("Backtracking: " + solution.removeDuplicateLettersBacktracking(s1));
        
        String s2 = "cbacdcbc";
        System.out.println("\nInput: " + s2);
        System.out.println("Greedy Stack: " + solution.removeDuplicateLetters(s2));
        System.out.println("Recursive: " + solution.removeDuplicateLettersRecursive(s2));
        // Note: Backtracking is very inefficient for larger inputs
        System.out.println("Backtracking: " + solution.removeDuplicateLettersBacktracking(s2));
    }
}
```

### 📊 Complexity Summary Table

| Technique              | Time       | Space      | Notes                                         |
|------------------------|------------|------------|-----------------------------------------------|
| Backtracking (Brute)   | O(2^n)     | O(n)       | Exponential and impractical for large inputs |
| Greedy Stack           | O(n)       | O(1)       | Optimal solution with constant space          |
| Recursive              | O(n^2)     | O(n)       | Good middle ground, but not as efficient     |

### 🧩 Follow‑Up Thoughts
**Variations**:
- What if we want to remove duplicates but keep the original order of characters (without the lexicographically smallest constraint)?
- What if we're allowed to keep up to k occurrences of each character?
- How would the solution change if we need to minimize the ASCII sum of the resulting string?

**Extension Challenge**:
Modify the solution to remove duplicates and produce a lexicographically largest result instead of the smallest.

## Friends Pairing Problem
*Solution coming soon*

## Binary Strings Problem
[Problem Link](https://leetcode.com/problems/find-unique-binary-string/description/)

### 📜 Restate the Problem
Given an array of strings `nums` containing `n` unique binary strings, each of length `n`, we need to find a binary string of length `n` that is not in the array. In other words, we need to find a binary string that is different from all the strings in the array.

**Input Format**: An array of `n` binary strings `nums`, each of length `n`  
**Output Format**: A binary string of length `n` that is not present in the array  
**Constraints**:
- 1 <= n <= 16
- nums.length == n
- nums[i].length == n
- nums[i] is either '0' or '1'
- All strings in nums are unique

**Sample I/O**:
```
Input: nums = ["01","10"]
Output: "00"
Explanation: "00" is not in the array, so we return it. "11" would also be a valid answer.

Input: nums = ["00","01"]
Output: "10"
Explanation: "10" is not in the array, so we return it. "11" would also be a valid answer.

Input: nums = ["111","011","001"]
Output: "000"
Explanation: "000" is the only binary string of length 3 that is not in the array.
```

### 🧠 Plan Your Attack
**Key Observations**:
- There are 2^n possible binary strings of length n.
- The array contains n strings, so there are 2^n - n binary strings that are not in the array.
- For n <= 16, we can explore all possible binary strings efficiently.

**Edge Cases**:
- n = 1 (only two possible strings: "0" and "1")
- All binary strings except one are already in the array

**Strategies**:
- Brute Force: Generate all possible binary strings and check which one is not in the array.
- Better: Use Cantor's diagonal argument - construct a string that differs from each string in the array at a specific position.
- Recursive: Build binary strings digit by digit, backtracking when we find a match.

### 🔍 Technique #1 — Recursive Backtracking
**Idea**: Use recursion to generate binary strings digit by digit. For each position, try both '0' and '1', and backtrack when we find a string that is not in the array.

**Pseudocode**:
```
function findDifferentBinaryString(nums):
    n = length of nums[0]
    
    // Convert nums array to a Set for O(1) lookups
    numSet = convert nums to a set
    
    function backtrack(index, current):
        // Base case: if we've built a string of length n
        if index == n:
            // If the current string is not in the array, return it
            if current not in numSet:
                return current
            return null
        
        // Try adding '0'
        result = backtrack(index + 1, current + '0')
        if result is not null:
            return result
        
        // Try adding '1'
        return backtrack(index + 1, current + '1')
    
    return backtrack(0, '')
```

**Time Complexity**: O(2^n) in the worst case, but typically much better due to early termination  
**Space Complexity**: O(n) for the recursion stack and to store the current string

**Mini-Example**:
Input: nums = ["01","10"]

1. Call `backtrack(0, '')`
2. Call `backtrack(1, '0')`
3. Call `backtrack(2, '00')`
4. Check if "00" is in the array - it's not, so return "00"

The function returns "00" which is not in the array.

### ⚡ Technique #2 — Cantor's Diagonal Argument
**Idea**: Create a binary string that differs from each string in the array at the corresponding position. For example, if the i-th character of the i-th string is '0', make the i-th character of our result '1', and vice versa.

**Pseudocode**:
```
function findDifferentBinaryString(nums):
    n = length of nums
    result = ''
    
    for i = 0 to n-1:
        // Take the opposite of the i-th character in the i-th string
        if nums[i][i] == '0':
            result += '1'
        else:
            result += '0'
    
    return result
```

**Time Complexity**: O(n) - We only need to look at the diagonal elements  
**Space Complexity**: O(n) to store the result string

**Mini-Example**:
Input: nums = ["01","10"]

1. For i=0: nums[0][0] = '0', so result += '1' => result = "1"
2. For i=1: nums[1][1] = '0', so result += '1' => result = "11"

The function returns "11" which is not in the array.

### 🚀 Technique #3 — Iterative Generation and Checking
**Idea**: Systematically generate binary strings and check if they're in the array. We can use the binary representation of numbers from 0 to 2^n-1 to generate all possible strings.

**Pseudocode**:
```
function findDifferentBinaryString(nums):
    n = length of nums
    numSet = convert nums to a set
    
    for i = 0 to 2^n-1:
        // Convert i to a binary string of length n
        binaryString = convert i to binary and pad to length n
        
        if binaryString not in numSet:
            return binaryString
    
    // This should never happen for valid inputs
    return ''
```

**Time Complexity**: O(n * 2^n) in the worst case, but typically much better  
**Space Complexity**: O(n) to store the binary string

**Mini-Example**:
Input: nums = ["01","10"]

1. numSet = {"01", "10"}
2. For i=0: binaryString = "00" (pad to length 2) - Not in numSet, so return "00"

The function returns "00" which is not in the array.

### 🧑‍💻 Working Code

```java
public class FindUniqueBinaryString {
    
    // Recursive Backtracking
    public String findDifferentBinaryStringRecursive(String[] nums) {
        int n = nums.length;
        Set<String> numSet = new HashSet<>();
        for (String num : nums) {
            numSet.add(num);
        }
        
        return backtrack(numSet, n, 0, "");
    }
    
    private String backtrack(Set<String> numSet, int n, int index, String current) {
        // Base case: if we've built a string of length n
        if (index == n) {
            // If the current string is not in the array, return it
            if (!numSet.contains(current)) {
                return current;
            }
            return null;
        }
        
        // Try adding '0'
        String result = backtrack(numSet, n, index + 1, current + "0");
        if (result != null) {
            return result;
        }
        
        // Try adding '1'
        return backtrack(numSet, n, index + 1, current + "1");
    }
    
    // Cantor's Diagonal Argument
    public String findDifferentBinaryStringDiagonal(String[] nums) {
        int n = nums.length;
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < n; i++) {
            // Take the opposite of the i-th character in the i-th string
            result.append(nums[i].charAt(i) == '0' ? '1' : '0');
        }
        
        return result.toString();
    }
    
    // Iterative Generation and Checking
    public String findDifferentBinaryStringIterative(String[] nums) {
        int n = nums.length;
        Set<String> numSet = new HashSet<>();
        for (String num : nums) {
            numSet.add(num);
        }
        
        for (int i = 0; i < (1 << n); i++) {
            // Convert i to a binary string of length n
            String binaryString = Integer.toBinaryString(i);
            // Pad with leading zeros to ensure length n
            while (binaryString.length() < n) {
                binaryString = "0" + binaryString;
            }
            
            if (!numSet.contains(binaryString)) {
                return binaryString;
            }
        }
        
        // This should never happen for valid inputs
        return "";
    }
    
    public static void main(String[] args) {
        FindUniqueBinaryString solution = new FindUniqueBinaryString();
        
        String[] nums1 = {"01", "10"};
        System.out.println("Input: [\"01\", \"10\"]");
        System.out.println("Recursive: " + solution.findDifferentBinaryStringRecursive(nums1));
        System.out.println("Diagonal: " + solution.findDifferentBinaryStringDiagonal(nums1));
        System.out.println("Iterative: " + solution.findDifferentBinaryStringIterative(nums1));
        
        String[] nums2 = {"00", "01"};
        System.out.println("\nInput: [\"00\", \"01\"]");
        System.out.println("Recursive: " + solution.findDifferentBinaryStringRecursive(nums2));
        System.out.println("Diagonal: " + solution.findDifferentBinaryStringDiagonal(nums2));
        System.out.println("Iterative: " + solution.findDifferentBinaryStringIterative(nums2));
        
        String[] nums3 = {"111", "011", "001"};
        System.out.println("\nInput: [\"111\", \"011\", \"001\"]");
        System.out.println("Recursive: " + solution.findDifferentBinaryStringRecursive(nums3));
        System.out.println("Diagonal: " + solution.findDifferentBinaryStringDiagonal(nums3));
        System.out.println("Iterative: " + solution.findDifferentBinaryStringIterative(nums3));
    }
}
```

### 📊 Complexity Summary Table

| Technique              | Time      | Space     | Notes                                         |
|------------------------|-----------|-----------|-----------------------------------------------|
| Recursive Backtracking | O(2^n)    | O(n)      | Can terminate early when a valid string is found |
| Cantor's Diagonal      | O(n)      | O(n)      | Most efficient, guaranteed to produce a unique string |
| Iterative Generation   | O(n*2^n)  | O(n)      | Systematically checks all possibilities        |

### 🧩 Follow‑Up Thoughts
**Variations**:
- What if we need to find all binary strings not in the array?
- What if we need to find the lexicographically smallest binary string not in the array?
- How would the solution change if we're working with ternary strings (0, 1, 2) instead of binary?

**Extension Challenge**:
Modify the solution to find the binary string that differs from the input strings in the maximum number of positions.
