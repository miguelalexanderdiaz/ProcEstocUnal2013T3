/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estocasticos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.sound.midi.Soundbank;

/**
 *
 * @author migueldiaz
 */
public class Estocasticos {

    private static int NUMBER_BUCKETS=-1;
    private static int SIZE_ARRAY=-1;
    private static int countBubble = 0;
    private static int countInsertion = 0;
    private static int countBucket = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String in;
        do {
            System.out.println("Inserte el tamaño del array 1<n<10:");
            in = jin();
            try {
                SIZE_ARRAY = Integer.parseInt(in);
            } catch (Exception e) {
            }
            if (SIZE_ARRAY > 1 && SIZE_ARRAY < 10) {
                break;
            }
        } while (true);
        
        do {
            System.out.println("Inserte la cantidad de buckets n>0:");
            in = jin();
            try {
                NUMBER_BUCKETS = Integer.parseInt(in);
            } catch (Exception e) {
            }
            if (NUMBER_BUCKETS > 0 ) {
                break;
            }
        } while (true);
        
        
        StringBuilder message=new StringBuilder();
        message.append("***********************************************************\n");
        message.append("***Esto puede tardar un tiempo se ordenarán 3*"+SIZE_ARRAY+"! vectores**\n" );
        message.append("*****Al final de la ejecución se almacenará un archivo*****\n");
        message.append("**llamado 'log.txt'donde encontrarás toda la información***");
        System.out.println(message);
        
        
        
        
        


        ArrayList<Integer> n = new ArrayList<>();
        for (int i = 0; i < SIZE_ARRAY; i++) {
            n.add(i + 1);
        }
        ArrayList<ArrayList<Integer>> permutationsOfN = permut(n);
        
        ArrayList<Integer> resultsOfBubbleSort = new ArrayList<>();
        ArrayList<Integer> resultsOfInsertionSort = new ArrayList<>();
        ArrayList<Integer> resultsOfBucketSort = new ArrayList<>();
            
        ArrayList<Integer> aux=new ArrayList<>();
        for (ArrayList<Integer> perm : permutationsOfN) {
            
            
            aux.addAll(perm);
            bubbleSort(aux);
            resultsOfBubbleSort.add(countBubble);
            countBubble=0;
            
            aux.addAll(perm);
            insertionSort(aux);
            resultsOfInsertionSort.add(countInsertion);
            countInsertion=0;
            
            aux.addAll(perm);
            bucketSort(aux, NUMBER_BUCKETS);
            resultsOfBucketSort.add(countBucket);
            countBucket=0;
        }

        impresion(permutationsOfN,resultsOfBubbleSort,resultsOfInsertionSort,resultsOfBucketSort);




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
     * Bubble sort
     *
     * @param arr array to be sorted
     * @return array sorted in ascending order
     */
    private static ArrayList<Integer> bubbleSort(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {          countBubble++;
            for (int x = 1; x < arr.size() - i; x++) {  countBubble++;
                if (arr.get(x - 1) > arr.get(x)) {      countBubble++;
                    //swap
                    int temp;
                    temp = arr.get(x - 1);              countBubble++;
                    arr.set(x - 1, arr.get(x));         countBubble++;
                    arr.set(x, temp);                   countBubble++;
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
        for (int i = 1; i < arr.size(); i++) {              countInsertion++;
            int newValue;
            newValue = arr.get(i);                          countInsertion++;
            int j;
            j= i;                                           countInsertion++;
            while (j > 0 && arr.get(j - 1) > newValue) {    countInsertion++;
                arr.set(j, arr.get(j - 1));                 countInsertion++;
                j--;                                        countInsertion++;
            }
            arr.set(j, newValue);                           countInsertion++;
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
        int min,max;

        min = arr.get(0);                                           countBucket++;
        max = min;                                                  countBucket++;
        //find minimum and maximum
        for (Integer i : arr) {                                     countBucket++;
            if (i > max) {                                          countBucket++;
                max = i;                                            countBucket++;
            } else if (i < min) {                                   countBucket++;
                min = i;                                            countBucket++;
            }
        }
        //range of one bucket
        double interval;
        interval = ((double) (max - min + 1)) / nBuckets;           countBucket++;
        //create buckets
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < nBuckets; i++) {                        countBucket++;
            buckets.add(new ArrayList<Integer>());
        }

        //put the numbers on the buckets
        for (Integer i : arr) {                                     countBucket++;
            int pos;
            pos = (int) ((i - min) / interval);                     countBucket++;
            buckets.get(pos).add(i);                                countBucket++;
        }
        //organize all buckets with insertion sort
        ArrayList<Integer> result = new ArrayList<>();
        for (ArrayList<Integer> i : buckets) {                      countBucket++;
            result.addAll(insertionForBucket(i));
        }
        return result;

    }
    
    private static ArrayList<Integer> insertionForBucket(ArrayList<Integer> arr) {
        for (int i = 1; i < arr.size(); i++) {              countBucket++;
            int newValue;
            newValue = arr.get(i);                          countBucket++;
            int j;
            j= i;                                           countBucket++;
            while (j > 0 && arr.get(j - 1) > newValue) {    countBucket++;
                arr.set(j, arr.get(j - 1));                 countBucket++;
                j--;                                        countBucket++;
            }
            arr.set(j, newValue);                           countBucket++;
        }
        return arr;
    }
    
    private static String jin() {
        try {
            BufferedReader brInput = new BufferedReader(new InputStreamReader(
                    System.in));
            return brInput.readLine();
        } catch (IOException e) {
            return null;
        }
    }
    
    
    private static String findBestAndWorstCase(ArrayList<ArrayList<Integer>> permutationsOfN,ArrayList<Integer> resultsOfSort, String sortType){
        int minIndex=0;
        int maxIndex=0;
        for(int i=0;i<permutationsOfN.size();i++){
            if(resultsOfSort.get(i)<resultsOfSort.get(minIndex))
                minIndex=i;
            else if(resultsOfSort.get(i)>resultsOfSort.get(maxIndex))
                maxIndex=i; 
        }
        StringBuilder str=new StringBuilder();
        str.append( "El peor caso para "+sortType+" es el array:"+permutationsOfN.get(maxIndex).toString()+" con "+resultsOfSort.get(maxIndex)+" pasos\n");
        str.append("El mejor caso para "+sortType+" es el array:"+permutationsOfN.get(minIndex).toString()+" con "+resultsOfSort.get(minIndex)+" pasos\n");
        return str.toString();
    
    }
    

    private static void impresion(
            ArrayList<ArrayList<Integer>> permutationsOfN,
            ArrayList<Integer> resultsOfBubbleSort,
            ArrayList<Integer> resultsOfInsertionSort,
            ArrayList<Integer> resultsOfBucketSort) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("log .txt"));
            String detail="Detalle: \n"
                        + " Columna (1): Secuencia numérica correspondiente a las n-permutaciones diferentes \n"
                        + " Columna (2): Conteo de pasos realizados al ordenar el arreglo de la columna (1) con BubbleSort \n"
                        + " Columna (3): Conteo de pasos realizados al ordenar el arreglo de la columna (1) con InsertionSort \n"
                        + " Columna (4): Conteo de pasos realizados al ordenar el arreglo de la columna (1) con BucketSort \n"
                        + " n-permutaciones: "+permutationsOfN.size()+"\n"
                        + " numero de buckets: "+NUMBER_BUCKETS+"\n";


            for (int i = 0; i < permutationsOfN.size(); i++) {
                String str=new String();
                str=str.format(String.format("| %-7s | %-7s | %-7s | %-7s | \n",
                        permutationsOfN.get(i).toString(),
                        resultsOfBubbleSort.get(i).toString(),
                        resultsOfInsertionSort.get(i).toString(),
                        resultsOfBucketSort.get(i).toString()));
                System.out.println(str);
                
                       
                out.write(str);
                out.newLine();
            }
            System.out.println(detail);
            out.write(detail);
            out.newLine();
            StringBuilder str=new StringBuilder();
            str.append(findBestAndWorstCase(permutationsOfN, resultsOfBubbleSort, "Bubble Sort"));
            str.append(findBestAndWorstCase(permutationsOfN, resultsOfInsertionSort, "Insertion Sort"));
            str.append(findBestAndWorstCase(permutationsOfN, resultsOfBucketSort, "Bucket Sort"));
            System.out.println(str.toString());
            out.write(str.toString());
            out.newLine();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
