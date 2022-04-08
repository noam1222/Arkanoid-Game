//209407162 Noam Maimon

/**
 * bunch of operations on arrays.
 */
public class ArraysOperations {
    /**
     * convert String array to int array.
     *
     * @param arr String array to convert.
     * @return arr as int Array.
     */
    public static int[] stringArrayToInt(String[] arr) {
        int[] newArr = new int[arr.length];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = Integer.parseInt(arr[i]);
        }
        return newArr;
    }

    /**
     * cut array of string to new array in given size.
     * @param args array to cut.
     * @param start start the cut from here (included).
     * @param stop stop the cut here (excluded).
     * @return the cut array.
     */
    public static String[] cutArrayOfString(String[] args, int start, int stop) {
        int newSize = stop - start;
        String[] arr = new String[newSize];

        int index = 0;
        for (int i = start; i < stop; i++) {
            arr[index] = args[i];
            index++;
        }
        return arr;
    }

    /**
     * sort the receiving int array.
     *
     * @param arr the receiving int array to sort.
     */
    public static void sortIntArray(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    //swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
