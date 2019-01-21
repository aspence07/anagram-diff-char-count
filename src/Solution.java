import java.io.File;
import java.net.URL;
import java.util.*;

class Solution {

    /*
     * Complete the 'getMinimumDifference' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY a
     *  2. STRING_ARRAY b
     */

    public static void main(String[] args) throws Exception {

        URL path = Solution.class.getResource("input.txt");
        File file = new File(path.getFile());
        Scanner sc = new Scanner(file);

        int n = sc.nextInt();
        sc.nextLine();

        List<String> a = new LinkedList();
        List<String> b = new LinkedList();
        for (int i = 0; i < n; i++) {
            a.add(sc.nextLine());
            b.add(sc.nextLine());
        }

        List<Integer> result = getMinimumDifference(a, b);

        for (Integer diff : result) {
            System.out.println(diff);
        }

    }

    public static List<Integer> getMinimumDifference(List<String> a, List<String> b) {
        // Write your code here

        int n = a.size();

        List<Integer> list = new ArrayList(n);

        mainFor: for (int i = 0; i < n; i++) {
            String x = a.get(i);
            String y = b.get(i);

            Map<Character, Integer> xMap = new TreeMap();
            Map<Character, Integer> yMap = new TreeMap();

            for (int j = 0; j < x.length(); j++) {
                Character c = x.charAt(j);
                Integer count = xMap.get(c);
                if (count == null) count = 0;
                count++;
                xMap.put(c, count);
            }

            for (int j = 0; j < y.length(); j++) {
                Character c = y.charAt(j);
                Integer count = yMap.get(c);
                if (count == null) count = 0;
                count++;
                yMap.put(c, count);
            }

            int count = 0;

            for (Character c : xMap.keySet()) {
                Integer xCount = xMap.get(c);
                Integer yCount = yMap.get(c);
                if (yCount == null) {
                    count += xCount;
                    xMap.put(c, 0);
                } else {
                    if (yCount > xCount) {
                        count += (yCount - xCount);
                        yMap.put(c, yCount - (yCount - xCount));
                    } else if (xCount > yCount) {
                        count += (xCount - yCount);
                        xMap.put(c, xCount - (xCount - yCount));
                    }
                }
            }

            for (Character c : yMap.keySet()) {
                Integer xCount = xMap.get(c);
                Integer yCount = yMap.get(c);
                if (xCount == null) {
                    count += yCount;
                    yMap.put(c, 0);
                } else {
                    if (yCount > xCount) {
                        count += (yCount - xCount);
                        yMap.put(c, yCount - (yCount - xCount));
                    } else if (xCount > yCount) {
                        count += (xCount - yCount);
                        xMap.put(c, xCount - (xCount - yCount));
                    }
                }
            }

            int remainingXCount = 0;
            for (Integer val : xMap.values())
                remainingXCount += val;

            int remainingYCount = 0;
            for (Integer val : yMap.values())
                remainingYCount += val;

            if (remainingXCount == 0 || remainingYCount == 0)
                count = -1;

            list.add(count);


        } // END for

        return list;

    } // END method

}