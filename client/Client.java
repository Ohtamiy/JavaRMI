import java.rmi.*;
import java.util.Random;
import java.util.concurrent.*;

public class Client {
    public static void main(String args[]) {
        Random random = new Random();
        int sizeOfBoard = 5000;

        boolean[][] playboard = generate(random, sizeOfBoard);
        boolean[][] newPlayboard = new boolean[sizeOfBoard][sizeOfBoard];

        try {
            System.out.println("Starting of 1 server work:");

            long m1 = System.currentTimeMillis();

            String name = "rmi://192.168.31.133:65501/Compute";
            Compute comp = (Compute) Naming.lookup(name);
            ConwaysGameOfLife task = new ConwaysGameOfLife(sizeOfBoard, playboard, newPlayboard, 0, sizeOfBoard);

            int result = (int) comp.executeTask(task);
            System.out.println("1 Server was working: " + (double) (System.currentTimeMillis() - m1));

        } catch(Exception e) {
            System.err.println("ComputePi exception: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            System.out.println("Starting of 2 servers work:");

            String name1 = "rmi://192.168.31.133:65501/Compute";
            Compute comp1 = (Compute) Naming.lookup(name1);
            ConwaysGameOfLife task1 = new ConwaysGameOfLife(sizeOfBoard, playboard, newPlayboard, 0, sizeOfBoard / 2);

            String name2 = "rmi://192.168.31.133:65502/Compute";
            Compute comp2 = (Compute) Naming.lookup(name2);
            ConwaysGameOfLife task2 = new ConwaysGameOfLife(sizeOfBoard, playboard, newPlayboard, sizeOfBoard / 2, sizeOfBoard);

            ExecutorService pool = Executors.newFixedThreadPool(4);
            pool.submit(() -> {
                try {
                    long m1 = System.currentTimeMillis();
                    int result1 = (int) comp1.executeTask(task1);
                    System.out.println("1st thread was working: " + (double) (System.currentTimeMillis() - m1));
                } catch(Exception e) {
                    e.printStackTrace();
                }
            });
            pool.submit(() -> {
                try {
                    long m2 = System.currentTimeMillis();
                    int result2 = (int) comp2.executeTask(task2);
                    System.out.println("2nd thread was working: " + (double) (System.currentTimeMillis() - m2));
                } catch(Exception e) {
                    e.printStackTrace();
                }
            });

        } catch(Exception e) {
            System.err.println("ComputePi exception: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            System.out.println("Starting of 4 servers work:");

            String name1 = "rmi://192.168.31.133:65501/Compute";
            Compute comp1 = (Compute) Naming.lookup(name1);
            ConwaysGameOfLife task1 = new ConwaysGameOfLife(sizeOfBoard, playboard, newPlayboard, 0, sizeOfBoard / 4);

            String name2 = "rmi://192.168.31.133:65502/Compute";
            Compute comp2 = (Compute) Naming.lookup(name2);
            ConwaysGameOfLife task2 = new ConwaysGameOfLife(sizeOfBoard, playboard, newPlayboard, sizeOfBoard / 4, sizeOfBoard / 2);

            String name3 = "rmi://192.168.31.133:65503/Compute";
            Compute comp3 = (Compute) Naming.lookup(name3);
            ConwaysGameOfLife task3 = new ConwaysGameOfLife(sizeOfBoard, playboard, newPlayboard, sizeOfBoard / 2, (sizeOfBoard / 2) + (sizeOfBoard / 4));

            String name4 = "rmi://192.168.31.133:65504/Compute";
            Compute comp4 = (Compute) Naming.lookup(name4);
            ConwaysGameOfLife task4 = new ConwaysGameOfLife(sizeOfBoard, playboard, newPlayboard, (sizeOfBoard / 2) + (sizeOfBoard / 4), sizeOfBoard);

            ExecutorService pool = Executors.newFixedThreadPool(4);
            pool.submit(() -> {
                try {
                    long m1 = System.currentTimeMillis();
                    int result1 = (int) comp1.executeTask(task1);
                    System.out.println("1st thread was working: " + (double) (System.currentTimeMillis() - m1));
                } catch(Exception e) {
                    e.printStackTrace();
                }
            });
            pool.submit(() -> {
                try {
                    long m2 = System.currentTimeMillis();
                    int result2 = (int) comp2.executeTask(task2);
                    System.out.println("2nd thread was working: " + (double) (System.currentTimeMillis() - m2));
                } catch(Exception e) {
                    e.printStackTrace();
                }
            });
            pool.submit(() -> {
                try {
                    long m3 = System.currentTimeMillis();
                    int result3 = (int) comp3.executeTask(task3);
                    System.out.println("3rd thread was working: " + (double) (System.currentTimeMillis() - m3));
                } catch(Exception e) {
                    e.printStackTrace();
                }
            });
            pool.submit(() -> {
                try {
                    long m4 = System.currentTimeMillis();
                    int result4 = (int) comp4.executeTask(task4);
                    System.out.println("4th thread was working: " + (double) (System.currentTimeMillis() - m4));
                } catch(Exception e) {
                    e.printStackTrace();
                }
            });

        } catch(Exception e) {
            System.err.println("ComputePi exception: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static boolean[][] generate(Random random, int sizeOfBoard){
        boolean[][] playboard = new boolean[sizeOfBoard][sizeOfBoard];

        for(int i = 0; i < sizeOfBoard; i++)
            for (int j = 0; j < sizeOfBoard; j++)
                playboard[i][j] = (random.nextInt(100) > 80);

        return playboard;
    }

}