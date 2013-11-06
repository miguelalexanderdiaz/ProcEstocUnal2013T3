/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estocasticos;

import java.util.ArrayList;

/**
 *
 * @author migueldiaz
 */
public class Estocasticos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {



        ArrayList<Integer> n = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            n.add(i + 1);
        }
        ArrayList<ArrayList<Integer>> result = permut(n);


        //System.out.println();
        for (ArrayList<Integer> perm : result) {
            System.out.println("");
            //bubbleSortResults.add(bubbleSort(perm));
            //System.out.println(bubbleSort(perm));
            //System.out.println(insertionSort(perm));
            System.out.println(bucketSort(perm, 3));
        }





    }

    public static ArrayList<ArrayList<Integer>> permut(ArrayList<Integer> arr) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (arr.size() == 1) {
            result.add(arr);
            return result;
        } else {
            // separate the first character from the rest
            int first = arr.remove(0);
            // get all permutationsOf the rest of the characters
            ArrayList<ArrayList<Integer>> simpler = permut(arr);
            // for each permutation,
            for (ArrayList<Integer> permutation : simpler) {
                // add the first character in all possible positions, and
                ArrayList additions = insertAtAllPos(first, permutation);
                // put each result into a new ArrayList
                result.addAll(additions);
            }

        }
        return result;
    }

    public static ArrayList<ArrayList<Integer>> insertAtAllPos(Integer first, ArrayList<Integer> arr) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i <= arr.size(); i++) {

            ArrayList<Integer> aux = new ArrayList<>();
            aux.addAll(arr);
            aux.add(i, first);
            result.add(aux);

        }
        return result;
    }

    /**
     * *********************Algoritmos de Ordenamiento*******************
     */
    /**
     * Bubble sort
     *
     * @param arr array to be sorted
     * @return array sorted in ascending order
     */
    private static ArrayList<Integer> bubbleSort(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            for (int x = 1; x < arr.size() - i; x++) {
                if (arr.get(x - 1) > arr.get(x)) {
                    //swap
                    int temp = arr.get(x - 1);
                    arr.set(x - 1, arr.get(x));
                    arr.set(x, temp);
                }
            }
        }
        return arr;
    }

    /**
     * Insertion sort
     *
     * @param arr array to be sorted
     * @return array sorted in ascending order
     */
    private static ArrayList<Integer> insertionSort(ArrayList<Integer> arr) {
        //int i, j, newValue;
        for (int i = 1; i < arr.size(); i++) {
            int newValue = arr.get(i);
            int j = i;
            while (j > 0 && arr.get(j - 1) > newValue) {
                arr.set(j, arr.get(j - 1));
                j--;
            }
            arr.set(j, newValue);
        }
        return arr;
    }

    /**
     * Bucket sort
     *
     * @param arr array to be sorted
     * @param nBuckets number of buckets
     * @return array sorted in ascending order
     */
    private static ArrayList<Integer> bucketSort(ArrayList<Integer> arr, int nBuckets) {


        int min = arr.get(0);
        int max = min;
        //find minimum and maximum
        for (Integer i : arr) {
            if (i > max) {
                max = i;
            } else if (i < min) {
                min = i;
            }
        }
        //sizeof buckets
        double interval = ((double) (max - min + 1)) / nBuckets; //range of one bucket
        //create buckets
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < nBuckets; i++) {
            buckets.add(new ArrayList<Integer>());
        }

        //put the numbers on the buckets
        for (Integer i : arr) {
            int pos = (int) ((i - min) / interval);
            buckets.get(pos).add(i);
        }
        //organize all buckets with insertion sort
        ArrayList<Integer> result = new ArrayList<>();
        for (ArrayList<Integer> i : buckets) {
            result.addAll(insertionSort(i));
        }
        return result;

    }
}
