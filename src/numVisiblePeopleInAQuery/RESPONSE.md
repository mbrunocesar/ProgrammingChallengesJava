# Intuition
I thought that I just needed a pointer for the "viewer" and another that would go running down the list.

# Approach
Basically I followed that intuition path, but after getting stuck in a performance issue, I saw that for ordered sets the problem was linear, so I added a check for ordered sets, and that's it.

# Complexity
- Time complexity:
  O(n log n)

- Space complexity:
  O(n)

# Code
```
class Solution {
    public boolean isSortedDownward(int[] heights) {
        int previous = heights[0];
        for (int current : heights) {
            if (current > previous) {
                return false;
            }
            previous = current;
        }

        return true;
    }

    public boolean isSortedUpward(int[] heights) {
        int previous = heights[0];
        for (int current : heights) {
            if (current < previous) {
                return false;
            }
            previous = current;
        }

        return true;
    }

    public int[] canSeePersonsCount(int[] heights) {
        int numElements = heights.length;
        int[] seeList = new int[numElements];

        if (isSortedDownward(heights) || isSortedUpward(heights)) {
            for (int i = 0; i < numElements - 1; i++) {
                seeList[i] = 1;   
            }
            return seeList;
        }

        int index = 0;
        for (int currentHeight: heights) {
            int currentMinViewHeight = 0;
            int canSee = 0;

            for (int j = index + 1; j < numElements; j++) {
                int comparedHeight = heights[j];
                if (comparedHeight > currentHeight) {
                    canSee++;
                    break;
                }
                if (comparedHeight > currentMinViewHeight) {
                    currentMinViewHeight = comparedHeight;
                    canSee++;
                }
            }
            seeList[index] = canSee;
            index++;
        }

        return seeList;
    }
}
```