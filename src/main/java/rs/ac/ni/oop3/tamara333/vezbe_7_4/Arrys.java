package rs.ac.ni.oop3.tamara333.vezbe_7_4;

import javax.swing.text.ComponentView;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Arrys {

    public static final int VALUE = 7;

    public static final ArrayProcessor processMax = new ArrayProcessor() {
        @Override
        public int apply(int[] array) {
            return  Arrays.stream(array).max().orElse(-444);
        }
    };

    public static final ArrayProcessor processSum = new ArrayProcessor() {
        @Override
        public int apply(int[] array) {
            return Arrays.stream(array).sum();
        }
    };

    public static final ArrayProcessor processAverage = new ArrayProcessor() {
        @Override
        public int apply(int[] array) {
            return (int) Arrays.stream(array).average().orElse(-444);
        }
    };

    public static final ArrayProcessor processAppearance = new ArrayProcessor() {
        @Override
        public int apply(int[] array) {
            return (int) Arrays.stream(array)
                    .filter(el -> el == VALUE)
                    .count();
        }
    };

    public static final ArrayTransformer reverseArray = new ArrayTransformer() {
        @Override
        public int[] transform(int[] array) {
            List<Integer> list = new ArrayList<>();

            for(int e : array){
                list.add(e);
            }

            Collections.reverse(list);

            int[] result = new int[list.size()];

            int index = 0;
            for(int e : list){
                result[index++] = e;
            }

            return result;
        }
    };

    /*public static final long countShows(int[] array, int val){
        return Arrays.stream(array)
                .filter(el -> el == val).count();
    }*/

    public static final void reverse(Integer[] array){
        Collections.reverse(Arrays.asList(array));
        System.out.println(Arrays.asList(array));
    }

    public static final void shuffle(Integer[] array){
        Collections.shuffle(Arrays.asList(array));
        System.out.println(Arrays.asList(array));
    }

    public static void main(String[] args) {
        int[] array = {1, 7, 8, 9, 7, 5, 6};
        System.out.println("Maximal element: " + processMax.apply(array));
        int sum = processSum.apply(array);
        System.out.println("Sum:" + sum);
        System.out.println("Rounded average: " + processAverage.apply(array));
        System.out.println("Average: " + (double)sum / array.length); //0
//        System.out.println("Number 7 is showed: " + countShows(array,7) + " times in array" );
        System.out.println("Number " + VALUE +  " is showed: " + processAppearance.apply(array) + " times in array" );
        System.out.println(Arrays.toString(reverseArray.transform(array)));

    }

}
