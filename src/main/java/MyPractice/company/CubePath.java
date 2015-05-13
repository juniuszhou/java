package company;

/**
 * Created by junius on 14-8-4.
 */
public class CubePath {

    static boolean isOnSurface(int x, int y, int z) {
        if (x > 3 || x < 0 || y > 3 || y < 0 || z > 3 || z < 0)
            return false;

        return (x == 0 || y == 0 || z == 0 || x == 3 || y == 3 || z == 3);
    }

    public static void main(String[] args) {
        int [][][] a = new int[4][4][4];

        for(int i = 0; i < 4; ++i)
            for(int j = 0; j < 4; ++j)
               for(int k = 0; k < 4; ++k){
                   a[i][j][k] = 0;
               }


        a[0][0][0] = 1;

        for(int i = 0; i < 4; ++i)
            for(int j = 0; j < 4; ++j)
                for(int k = 0; k < 4; ++k){
                    if (a[i][j][k] > 0)
                        continue;
                    if (isOnSurface(i-1, j, k) && a[i-1][j][k] > 0)
                        a[i][j][k] += a[i-1][j][k];
                    if (isOnSurface(i, j-1, k) && a[i][j-1][k] > 0)
                        a[i][j][k] += a[i][j-1][k];
                    if (isOnSurface(i, j, k-1) && a[i][j][k-1] > 0)
                        a[i][j][k] += a[i][j][k-1];
                }

        for(int i = 0; i < 4; ++i)
            for(int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    System.out.print(" " + a[i][j][k]);
                }
                System.out.println();
            }
    }
}
