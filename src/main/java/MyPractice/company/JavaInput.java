package company;

import java.io.*;

/**
 * Created by junius on 14-9-6.
 */

public class JavaInput {
    static class Node{
        int income;
        int expense;
        int balance;
    }

    public static void main(String[] args) {
        Node [] nodes = new Node[26];
        for(int m = 0; m<26; m++) {
            nodes[m] = new Node();
            nodes[m].income = -1;
            nodes[m].expense = -1;
            nodes[m].balance = -1;
        }
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bufr = new BufferedReader(isr);

        byte[] b = new byte[100];
        int i = 0;
        nodes[0].balance = -1;
        try {
            while (true){
                String str = bufr.readLine();
                if (str == null)
                    break;
                String[]  items = str.split(";");
                System.out.println(items[0] + " junius " + items[1] + items[2] + items[3]);
                Integer id = new Integer(items[0]);
                int idx = id.intValue();
                if(items[1].equals("?"))
                    nodes[idx].income = -1;
                else
                    nodes[idx].income = (new Integer(items[1].replace(".", ""))).intValue();
                if(items[2].equals("?"))
                    nodes[idx].expense = -1;
                else
                    nodes[idx].expense = (new Integer(items[2].replace(".", ""))).intValue();
                if(items[3].equals("?"))
                    nodes[idx].balance = -1;
                else
                    nodes[idx].balance = (new Integer(items[3].replace(".", ""))).intValue();
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        i = 1;
        int j = 0;


        while(i <= 25){
            if ((nodes[i-1].balance == -1) && ((nodes[i].income == -1) || (nodes[i].expense == -1))){
                j = i;
                ++i;
                continue;
            }

            if (nodes[i].balance == -1){
                ++i;
                continue;
            }

            int k = j;
            j = i;

            for(int l = i; l > k; --l){
                if ((nodes[i-1].balance != -1) && ((nodes[i].income == -1) || (nodes[i].expense == -1))){
                    if (nodes[i].income == -1)
                        nodes[i].income = nodes[i].balance - nodes[i-1].balance + nodes[i].expense;
                    else
                        nodes[i].expense = nodes[i].balance - nodes[i-1].balance + nodes[i].income;
                }
                else {
                    nodes[i-1].balance = nodes[i].balance + nodes[i].expense - nodes[i].income;
                }
            }
            ++i;
        }

        i = 1;
        while(i <= 25){
            String str = (new Integer(nodes[i].income)).toString();
            str += " ";
            str += (new Integer(nodes[i].expense)).toString();
            str += " ";
            str += (new Integer(nodes[i].balance)).toString();
            System.out.println(str);
            ++i;
        }

    }
}
