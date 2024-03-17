class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<Integer> li = new ArrayList<>();
        int i = 0;
        boolean newinsert = false;
        for (int arr[] : intervals) {
            if (arr[1] >= newInterval[0] && arr[0] <= newInterval[1]) {
                arr[1] = Math.max(arr[1], newInterval[1]);
                arr[0] = Math.min(arr[0], newInterval[0]);
                newinsert = true;
                li.add(arr[0]);
                li.add(arr[1]);
                i++;
                break;
            }
            i++;
            li.add(arr[0]);
            li.add(arr[1]);
        }
        if (i == intervals.length && newinsert == false) {
            li.add(newInterval[0]);
            li.add(newInterval[1]);
        }
        int prev = li.get(li.size() - 1);
        for (; i < intervals.length; i++) {
            if (intervals[i][0] <= prev) {
                prev = Math.max(prev, intervals[i][1]);
                li.remove(li.size() - 1);
                li.add(prev);
                continue;
            }
            li.add(intervals[i][0]);
            li.add(intervals[i][1]);
        }
        int n = li.size() / 2;
        int res[][] = new int[n][2];
        for (int j = 0; j < n; j++) {
            res[j][0] = li.remove(0);
            res[j][1] = li.remove(0);
        }
        Arrays.sort(res, Comparator.comparingInt(a -> a[0]));
        return res;
    }
}