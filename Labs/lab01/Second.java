package lab01;
public class Second {

    public static void main(String[] args) {
        int res = 1;

        System.out.print("   ");
        for(int i = 1; i <= 9; i++) { // Первая строка с отступом
            System.out.printf("%3d ", i);
        }

        System.out.println();
        for(int i = 1; i <= 9; i++) {
            System.out.printf("%3d", i); // 1й столбец

            for(int j = 1; j <= 9; j++) {
                res = i * j;
                System.out.printf("%3d ", res);
            }
            System.out.println();
        }
    }
}
