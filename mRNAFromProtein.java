import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class mRNAFromProtein {
	public long possibleRNA(String s) {
		Map<Character, Integer> map = new HashMap<Character, Integer>(); 
		    map.put('A', 4);
		    map.put('C', 2);
		    map.put('D', 2);
		    map.put('E', 2);
		    map.put('F', 2);
		    map.put('G', 4);
		    map.put('H', 2);
		    map.put('I', 3);
		    map.put('K', 2);
		    map.put('L', 6);
		    map.put('M', 1);
		    map.put('N', 2);
		    map.put('P', 4);
		    map.put('Q', 2);
		    map.put('R', 6);
		    map.put('S', 6);
		    map.put('T', 4);
		    map.put('V', 4);
		    map.put('W', 1);
		    map.put('Y', 2);
		long num = 1;
		char[] arr = s.toCharArray();
		for(char c:arr) {
			num = (num * map.get(c)) % 1000000;
			//System.out.println(num);
		}
		return (num * 3) % 1000000;
	}
	public static void main(String args[]) throws IOException {
		mRNAFromProtein obj = new mRNAFromProtein();
		BufferedReader br = new BufferedReader(new FileReader("D:\\Fall 17\\CB\\dataset\\rosalind_mrna1.txt"));
		try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            line = br.readLine();
	        }
	        //System.out.println(sb.toString());
	        System.out.println(obj.possibleRNA(sb.toString()));
	    } finally {
	        br.close();
	    }
	}
}