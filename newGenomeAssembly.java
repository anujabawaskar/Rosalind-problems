import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class newGenomeAssembly {
  public static void main( String[] args ) throws IOException {
    ArrayList<String> kmers = new ArrayList<String>();
    BufferedReader br = new BufferedReader(new FileReader("D:\\Fall 17\\CB\\dataset\\rosalind_pcov (2).txt"));
	try {
        String line = br.readLine();
        while (line != null) {
        	kmers.add(line);
            line = br.readLine();
        }
    } finally {
        br.close();
    }
    System.out.println(assembleGenome(kmers));
  }
  public static String assembleGenome(ArrayList<String> kmers ) {
	    int numKmer = kmers.size();
	    int k = kmers.get(0).length();
	    String answer = kmers.remove(numKmer - 1);
	    while(answer.length() < numKmer) {
	      String suffix = answer.substring(answer.length()-k,answer.length());
	      for(int i = 0; i < kmers.size(); i++) {
	        String temp = kmers.get(i);
	        if(checkNext(suffix,temp)) {
	          answer += temp.charAt(k-1);
	          kmers.remove(i);
	          break;
	        }
	      }
	    }
	    return answer;
	  }
  public static boolean checkNext(String s, String t) {
	    for(int i = 1; i < s.length() - 1; i++) {
	      if(s.charAt(i) != t.charAt(i - 1)) 
	        return false;
	    }
	    return true;
  }
  
}