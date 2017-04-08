
package statisticswriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatisticsWriter {

    public static void main(String[] args) throws FileNotFoundException {

        
        try {
            Scanner sc = new Scanner(new File("orwell.txt"));
            String[] slowa = new String[300000];
            int i=0;
            while(sc.hasNext()){
                String[] temp = sc.next().split("[),.!?(]");
                for (int j = 0 ; j < temp.length ; j++ ){                  
                    slowa[i] = temp[j];
                    i+=1;
                }
            }
            
            Map<String,Integer> hM = new HashMap();
            for( String slowo : slowa){
                if(hM.containsKey(slowo)){
                    int value = (int) hM.get(slowo)+1;
                    System.out.println(value);
                    hM.put(slowo,value);
                }
                else{
                    hM.put(slowo,1);
                }
            }
            PrintWriter pw = new PrintWriter(new File("plik.txt"));
            ArrayList<Slowo> words = new ArrayList<Slowo>();
            pw.println("Statistics");
            for(String value : hM.keySet()){
                words.add(new Slowo(value,hM.get(value)));
            }

                words.sort(cmprtr);
                for(Slowo value : words){
                    pw.println(value.slowo + " " + value.count);
                }
                pw.close();
            }
catch (FileNotFoundException ex) {
            PrintWriter pw = new PrintWriter(new File("plik.txt"));
            pw.println("Incorrect file with data");
            pw.close();
        }
        
    }
    static Comparator<Slowo> cmprtr = new Comparator<Slowo>() {
                @Override
                public int compare(Slowo m1, Slowo m2) {
                    return m1.count.compareTo(m2.count);
                }
            };
    static class Slowo implements Comparable<Slowo>{
        String slowo;
        Integer count;
        
        Slowo(String slowo,int count){
            this.count = count;
            this.slowo = slowo;
        }
        @Override
        public int compareTo(Slowo s) {
            return count.compareTo(s.count);
        }
        
    }
    
}
