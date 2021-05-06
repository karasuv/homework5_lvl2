import java.util.Arrays;

public class Homework5 {
    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];

    static float[] a1= new float[h];
    static float[] a2= new float[h];



    public static void main(String[] args) throws InterruptedException {
         float[] arr1;
         float[] arr2;

        arr1=Method1();
        arr2=Method2();

        System.out.println("результирующие массивы равны? - "+ Arrays.equals(arr1, arr2));

    }




      static float[]  Method1(){


       Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
       for (int i = 0; i <arr.length ; i++) {
           arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

       }
         System.out.println("Время на метод1:");
       System.out.println(System.currentTimeMillis() - a);

       return arr;
   }

     static float[] Method2() throws InterruptedException {


       Arrays.fill(arr, 1);

       float[] a1= new float[h];
       float[] a2= new float[h];



        long a = System.currentTimeMillis();

       System.arraycopy(arr, 0, a1, 0, h);
       System.arraycopy(arr, h, a2, 0, h);






        oneRunnable or = new oneRunnable();
        Thread first = new Thread(or);
        first.start();

        SecondRunnable  sr = new SecondRunnable();
        Thread second = new Thread(sr);
        second.start();


       System.arraycopy(a1, 0, arr, 0, h);
       System.arraycopy(a2, 0, arr, h, h);

            first.join();
            second.join();

        System.out.println("Время на метод2:");
        System.out.println(System.currentTimeMillis() - a);

return arr;
   }



  static class oneRunnable implements Runnable {

       @Override
       public void run() {
           long a = System.currentTimeMillis();
           for (int i = 0; i < arr.length-a1.length; i++) {
               a1[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));


           }
           System.out.println("first half:");
           System.out.println(System.currentTimeMillis() - a);
       }
   }

  static   class SecondRunnable implements Runnable {

        @Override
        public void run() {
            long a = System.currentTimeMillis();
            for (int i = a1.length; i < arr.length; i++) {
                a2[i-a1.length] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.out.println("last half:");
            System.out.println(System.currentTimeMillis() - a);
        }
    }

}
