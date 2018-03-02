import java.lang.Math;
import java.util.*;
public class Main {
    public static void main(String[] args){
        final int A_size = 200, B_size = 400, C_size = 800; // declares the sizes of the arrays
        final double fraction1 = 0.25, fraction2 = 0.5, fraction3 = 0.75; // declares the fractions of the check points
        double A[] = new double[A_size];
        double B[] = new double[B_size];
        double C[] = new double[C_size];

        fill_array(A); //fills array A in random numbers from 0 to 1023
        System.out.println("first array: A, size: " + A_size);
        // inserts the array into the tree and finds the medians after every check point
        find_array_medians(A, (int) (A_size * fraction1), (int) (A_size * fraction2), (int) (A_size * fraction3));
        /*System.out.println(find_median_in_the_hard_way(A,(int) (A_size * fraction1)));
        System.out.println(find_median_in_the_hard_way(A,(int) (A_size * fraction2)));
        System.out.println(find_median_in_the_hard_way(A,(int) (A_size * fraction3)));*/

        fill_array(B);//fills array B in random numbers from 0 to 1023
        System.out.println("second array: B, size: " + B_size);
        // inserts the array into the tree and finds the medians after every check point
        find_array_medians(B, (int) (B_size * fraction1), (int) (B_size * fraction2), (int) (B_size * fraction3));
        /*System.out.println(find_median_in_the_hard_way(B,(int) (B_size * fraction1)));
        System.out.println(find_median_in_the_hard_way(B,(int) (B_size * fraction2)));
        System.out.println(find_median_in_the_hard_way(B,(int) (B_size * fraction3)));*/

        fill_array(C);//fills array C in random numbers from 0 to 1023
        System.out.println("third array: C, size: " + C_size);
        // inserts the array into the tree and finds the medians after every check point
        find_array_medians(C, (int) (C_size * fraction1), (int) (C_size * fraction2), (int) (C_size * fraction3));
        /*System.out.println(find_median_in_the_hard_way(C,(int) (C_size * fraction1)));
        System.out.println(find_median_in_the_hard_way(C,(int) (C_size * fraction2)));
        System.out.println(find_median_in_the_hard_way(C,(int) (C_size * fraction3)));*/
    }
    //method which gets an array and 3 check points.The method prints the median after every check point by using RB-tree. Complexity: theta(n*log n)
    public static void find_array_medians(double[] array, int check1, int check2, int check3){
        Red_Black_Tree tree = new Red_Black_Tree();
        for (int i = 0; i < check1; i++)
            tree.insert(array[i]);
        System.out.println("median of the n/4 first numbers " + tree.printMedian());
        for (int i = check1; i < check2; i++)
            tree.insert(array[i]);
        System.out.println("median of the n/2 first numbers " + tree.printMedian());
        for (int i = check2; i < check3; i++)
            tree.insert(array[i]);
        System.out.println("median of the 3n/4 first numbers " + tree.printMedian());
        for (int i = check3; i < array.length; i++)
            tree.insert(array[i]);
    }
    //method which gets an array and fill it with random double numbers from 0 to 1023. Complexity: theta(n)
    public static void fill_array(double[] array){
        double rand;
        for (int i = 0; i < array.length; i++){
            rand = Math.random() * 1023; //rand between 0 and 1023
            array[i] = rand;
        }
    }

    //method which gets an array and end index.The method returns the median of the array until end in the stupid and long way.
    // This method was written just to check that the medians that find_array_medians prints are right. Complexity: O(n log(n))
    public static double find_median_in_the_hard_way(double[] array, int end){
        end=(end>array.length)?array.length:end; // sets end to be length if end>length
        double [] temp = new double [end];
        for (int i = 0; i < end; i++)
            temp[i]=array[i]; //copies the array until end to temp
        Arrays.sort(temp); // sorts array
        int medianPlace=(end+1)/2 -1;
        return temp[medianPlace]; // returns median
    }

}
