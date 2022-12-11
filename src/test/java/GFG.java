// Java Program to implement
// the above approach
import org.howmuch.Main;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static java.lang.Math.abs;

class GFG {

    // Function to find the minimum
    // distance between the minimum
    // and the maximum element
    public static int minDistance(int a[], int n)
    {

        // Stores the minimum and maximum
        // array element
        int max = -1, min = Integer.MAX_VALUE;

        // Stores the most recently traversed
        // indices of the minimum and the
        // maximum element
        int min_index = -1, max_index = -1;

        // Stores the minimum distance
        // between the minimum and the
        // maximum
        int min_dist = n + 1;

        // Find the maximum and
        // the minimum element
        // from the given array
        for (int i = 0; i < n; i++) {
            if (a[i] > max){
                max = a[i];
                max_index = i;
            }
            if (a[i] < min){
                min = a[i];
                min_index = i;
            }
        }
        return abs(max_index - min_index);
    }

    // Driver Code
    public static void main(String[] args)
    {
        URI s;
        try {
            s = Objects.requireNonNull(Objects.requireNonNull(Main.class.getResource("/icons/circle_delete.png")).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        System.out.println(s);

    }
}