package Google;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class BfsDfs {
    int[] visited = new int[100];

    public void bfs(int[][] matrix, int root) {
        Deque<Integer> deque = new LinkedList<Integer>();
        deque.addFirst(root);
        while(!deque.isEmpty()) {
            int i = deque.removeFirst();
            visited[i] = 1;
            for(int j = 0; j<matrix.length;j++){
                if (matrix[i][j] > 0 && visited[j] == 0) {
                    deque.addLast(j);
                }
            }
        }
    }

    Deque<Integer> deque = new LinkedList<Integer>();
    public void bfs2(int[][] matrix) {
        if (!deque.isEmpty()) {
            int i = deque.removeFirst();
            visited[i] = 1;
            for(int j = 0; j<matrix.length;j++){
                if (matrix[i][j] > 0 && visited[j] == 0) {
                    deque.addLast(j);
                }
            }
            bfs2(matrix);
        }
    }

    public void dfs(int[][] matrix, int root) {
        System.out.println(root);
        visited[root] = 1;
        for(int i=0;i<matrix.length;i++){
            if(matrix[i][root] > 0 && visited[i] == 0) {
                dfs(matrix, i);
            }
        }
    }


    public void dfs2(int[][] matrix, int root) {
        Stack<Integer> stack = new Stack<Integer>();
        System.out.println(root);
        stack.push(root);
        while(!stack.empty()) {
            int j = stack.pop();
            visited[j] = 1;
            for(int i=0;i<matrix.length;i++){
                if(matrix[i][root] > 0 && visited[i] == 0) {
                    stack.push(i);
                }
            }
        }
    }

    public int findK(int[] nums, int k, int s, int e) {
        if(s>=e) return nums[s];
        int m = partition(nums, s, e);
        if(m == k) return nums[m];
        else if(m<k) {
            return findK(nums, k, m+1, e);
        }else {
            return findK(nums, k, s, m-1);
        }
    }

    // always put the mid element or first element at the first
    // put it to the position after partition done.
    int partition(int[] sum, int begin, int end) {
        if (begin >= end) return begin;
        else {
            int mid = sum[begin];
            int i=begin+1;
            int m = begin;
            while (i<=end){
                if (sum[i] < mid) {
                    m++;
                    swap(sum, m, i);
                }
                i++;
            }
            swap(sum, m, begin);
            System.out.println(m);
            return m;
        }
    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    int A[][];
    double D[];
    public void dijkstra(int srcId)
    {
        D = new double[A.length];
        for(int i=0;i<A.length;i++)
        {
            D[i]=999999999;
        }
        D[srcId]=0;
        int[] q = new int[A.length];
        int ql=0,qf=0; //队列

        for(int i=0;i<A.length;i++) q[ql++]=i;
        while(qf!=ql)
        {
            int min=qf;
            for(int i=qf;i<ql;i++)
            {
                if(D[q[i]]<D[q[min]])
                {
                    min=i;
                }
            }
            int id = q[qf];
            q[qf] = q[min];
            q[min] = id; //q[qf] is the min
            int u=q[qf++];
            for(int i=0;i<A.length;i++)
            {
                if(A[u][i]>0)
                {
                    //relax(u,i);
                }
            }
        }
    }
}
