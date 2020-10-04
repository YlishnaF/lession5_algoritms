import java.util.Arrays;
import java.util.Random;
// 5.1 Примеры рекурсии: различные традиции, матрешка, дерево, курица-яйцо, "У попа была собака..."

public class Main {
    // 5.2 Бесконечная рекурсия
    public static int sum(int n){
        System.out.println(n);
        return sum(n+2);
    }
    // 5.2 Правильная рекурсия
    public static int sumCorrect(int n){
        System.out.println(n);
        if(n>1000){
            return 1;
        }
        return sumCorrect(n+2);
    }

    // 5.3 Стек вызова method1 и method2
    public static int method1(int n){

        return n*(method2(n));
    }

    public static int method2(int n){
        return n*3;
    }
    // 5.3 Стек вызовов с рекурсией

    public static int method3(int n){
        System.out.println(n*2);
        if(n>100){
            return 1;
        }
        return method3(n*2);
    }
    public static void fillArray(int[] arr) {
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(arr.length * 2);
        }
    }

    public static int recbinarySerch(int key, int low, int high, int[] arr){
        if(low>high){
            return arr.length;
        }

        int mid = (low + high)/2;
        if (arr[mid] == key){
            return mid;
        }
        else if(arr[mid] < key){
            return recbinarySerch(key,low + 1, high,arr);
        } else {
            return recbinarySerch(key, low, high - 1, arr );
        }
    }

    private static int[] sortMerge(int[] arr){
        int len = arr.length;
        if(len<2){
            return arr;
        }
        int middle = len/2;
        return merge(sortMerge(Arrays.copyOfRange(arr, 0, middle)),
                sortMerge(Arrays.copyOfRange(arr, middle, len)));
    }

    public static int[] merge(int[] a, int[] b){
        int[] result = new int[a.length + b.length];
        int aIndex = 0;
        int bIndex = 0;

        for (int i = 0; i < result.length; i++) {
            result[i] = a[aIndex] < b[bIndex] ? a[aIndex++] : b[bIndex++];
            if (aIndex == a.length) {
                System.arraycopy(b, bIndex, result, ++i, b.length -bIndex);
                break;
            }
            if(bIndex == b.length){
                System.arraycopy(a,aIndex, result, ++i, a.length -aIndex);
                break;
            }

        }
        return result;
    }




    public static void main(String[] args) {
        int n=2;
        int m=0;
  //      sum(n);
        sumCorrect(n);
        System.out.println(method1(n));
        method3(n);

        //5.4 Алгоритм цикла против алгоритма с рекурсией
        // Вывод: простой цикл в среднем работает в два раза быстрее
        long start1 = System.nanoTime();
        while (m<=1000){
            m+=2;
            System.out.println(m);
        }
        System.out.println("Время выполнения цикла " + (System.nanoTime() - start1));


        long start2 = System.nanoTime();
        sumCorrect(n);
        System.out.println("Время выполнения цикла с рекурсией " + (System.nanoTime() - start2));

        // Двоичный поиск из задания 2
        int[] simpleTypeArray = new int[6];
        fillArray(simpleTypeArray);
        int[] copyAr = new int[6];
        copyAr = Arrays.copyOf(simpleTypeArray, simpleTypeArray.length);
        Arrays.sort(simpleTypeArray);
        System.out.println(Arrays.toString(simpleTypeArray));

        long timeStart = System.nanoTime();
        System.out.println("Элемент " + simpleTypeArray[5] + " найден как элемент с индексом " + Arrays.binarySearch(simpleTypeArray, simpleTypeArray[5]));
        System.out.println("Затраты времени на двоичный поиск: " + (System.nanoTime() - timeStart) + " наносекунд.");

        //5.5 Алгоритм двоичного рекурсивного поиска
        long timeStart2 = System.nanoTime();
        System.out.println(recbinarySerch(12, 0, simpleTypeArray.length-1, simpleTypeArray));
        System.out.println("Затраты времени на рекурсивный двоичный поиск: " + (System.nanoTime() - timeStart2) + " наносекунд.");

        //5.6 Алгоритм сортировки слиянием
        long start = System.nanoTime();
        System.out.println(Arrays.toString(copyAr));
        System.out.println(Arrays.toString(sortMerge(copyAr)));
        System.out.println("Время сортировки слиянием " + (System.nanoTime() - start));

    }
}
