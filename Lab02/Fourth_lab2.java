package Lab02;
import java.util.Random;

public class Fourth_lab2 {

    public static void findLocalMin(int[][] matrix, int rows, int cols) {
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int current = matrix[i][j];
                // || - если учитывать тех, у кого нет соседа
                if (((i > 0) && (current < matrix[i - 1][j])) && // Верхний
                        ((i < rows - 1) && (current < matrix[i + 1][j])) && // Нижний
                        ((j > 0) && (current < matrix[i][j - 1])) && // Левый
                        ((j < cols - 1) && (current < matrix[i][j + 1]))) // Правый
                {
                    count++;
                    System.out.printf("[%d][%d] = %d\n", i, j, current);
                }
            }
        }

        if(count == 0) {
            System.out.print("Локальный минимум отсутствует!");
            return;
        }
        System.out.printf("\nКол-во минимумов - %d", count);
    }

    public static void main(String[] args) {
        int[][] matrix = new int[8][8];
        /*int[][] matrix = { // 9
                {1, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 0, 1, 0, 1, 1, 0}
        };*/
        int rows = matrix.length;
        int cols = matrix[0].length;
        Random random = new Random();

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(21) - 10;
            }
        }

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
        findLocalMin(matrix, rows, cols);
    }
}
