package numVisiblePeopleInAQuery;

public class NumVisiblePeopleInAQuery {

    boolean debugMode;

    public NumVisiblePeopleInAQuery(boolean debugMode) {
        this.debugMode = debugMode;
    }

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

    public int[] canSeePersonsCount(int[] heights) {
        int numElements = heights.length;
        int[] seeList = new int[numElements];

        if (isSortedDownward(heights)) {
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


    public static boolean compareArrays(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }

    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        NumVisiblePeopleInAQuery visiblePeople = new NumVisiblePeopleInAQuery(false);

        int[] queue1 = {10,6,8,5,11,9};
        int[] viewList1 = {3,1,2,1,1,0};
        System.out.println(compareArrays(visiblePeople.canSeePersonsCount(queue1), viewList1));

        int[] queue3 = {100000,99998,99996,99994,99992,99991,99990,99988,99987,99986,99985,99984,99983,99981,99980,99979,99978,99977,99976,99975,99974,99973,99972,99970,99969,99968,99967,99965,99964,99963,99962,99961,99960,99956,99954,99951,99950,99949,99948,99947,99945,99944,99942,99940,99939,99938,99936,99935,99934,99933,99928,99927,99926,99924,99923,99919,99918,99917,99916,99915,99914,99913,99909,99908,99906,99905,99904,99903,99900,99899,99898,99897,99896,99895,99894,99893,99892,99891,99890,99889,99888,99887,99886,99885,99884,99883,99882,99881,99879,99878,99877,99876,99875,99874,99873,99871,99870,99869,99868,99867,99866,99865,99864,99863,99862,99861,99860,99859,99858,99857,99856,99855,99854,99853,99852,99851,99850,99849,99848,99847,99846,99845,99843,99842,99841,99839,99838,99837,99836,99834,99832,99831,99829,99825,99823,99822,99821,99820,99818,99816,99815,99812,99810,99808,99807,99806,99804,99803,99802,99800,99799,99798,99795,99793,99792,99791,99790,99787,99786,99785,99784,99783};
        int[] viewList3 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0};
        System.out.println(compareArrays(visiblePeople.canSeePersonsCount(queue3), viewList3));

    }

}
