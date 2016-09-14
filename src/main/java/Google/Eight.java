package Google;

public class Eight {
    int[] pos = new int[8];
    int sum = 0;
    public boolean ifValid(int layer) {
        for(int i=0;i<layer;i++) {
            if (pos[i] == pos[layer] || Math.abs(pos[i] - pos[layer]) == layer - i)
                return false;

        }
        return true;
    }

    public void find(int layer) {
        if (layer >= 8) sum++;
        else {
            for(int i=0;i<8;i++) {
                pos[layer] = i;
                if (ifValid(layer)) find(layer+1);
            }
        }
    }

    public static void main(String[] args) {
        Eight eigth = new Eight();
        eigth.find(0);
        System.out.println(eigth.sum);
    }
}
