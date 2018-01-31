import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RNAintoProtein {
	public String Protein(String s) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("UUU", "F");
		map.put("UUC", "F");
		map.put("UUA", "L");
		map.put("UUG", "L");
		map.put("UCU", "S");
		map.put("UCC", "S");
		map.put("UCA", "S");
		map.put("UCG", "S");
		map.put("UAU", "Y");
		map.put("UAC", "Y");
		map.put("UAA", "Stop");
		map.put("UAG", "Stop");
		map.put("UGU", "C");
		map.put("UGC", "C");
		map.put("UGA", "Stop");
		map.put("UGG", "W");
		map.put("CUU", "L");
		map.put("CUC", "L");
		map.put("CUA", "L");
		map.put("CUG", "L");
		map.put("CCU", "P");
		map.put("CCC", "P");
		map.put("CCA", "P");
		map.put("CCG", "P");
		map.put("CAU", "H");
		map.put("CAC", "H");
		map.put("CAA",  "Q");
		map.put("CAG", "Q");
		map.put("CGU", "R");
		map.put("CGC",  "R");
		map.put("CGA", "R");
		map.put("CGG", "R");
		map.put("AUU", "I");
		map.put("AUC", "I");
		map.put("AUA", "I");
		map.put("AUG", "M");
		map.put("ACU", "T");
		map.put("ACC", "T");
		map.put("ACA", "T");
		map.put("ACG", "T");
		map.put("AAU", "N");
		map.put("AAC", "N");
		map.put("AAA", "K");
		map.put("AAG", "K");
		map.put("AGU", "S");
		map.put("AGC", "S");
		map.put("AGA", "R");
		map.put("AGG", "R");
		map.put("GUU", "V");
		map.put("GUC", "V");
		map.put("GUA", "V");
		map.put("GUG", "V");
		map.put("GCU", "A");
		map.put("GCC", "A");
		map.put("GCA", "A");
		map.put("GCG", "A");
		map.put("GAU", "D");
		map.put("GAC", "D");
		map.put("GAA", "E");
		map.put("GAG", "E");
		map.put("GGU", "G");
		map.put("GGC", "G");
		map.put("GGA", "G");
		map.put("GGG", "G");
		StringBuffer proteinString = new StringBuffer();
		for(int i = 0, j = 3; i < s.length(); j += 3, i+= 3) {
			String code = map.get(s.substring(i, j));
			if(code.equals("Stop"))
				break;
			else
				proteinString.append(code);
		}
		return proteinString.toString();
	}
	public static void main(String args[]) throws IOException {
		RNAintoProtein obj = new RNAintoProtein();
		BufferedReader br = new BufferedReader(new FileReader("D:\\Fall 17\\CB\\dataset\\rosalind_prot.txt"));
		try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        System.out.println(obj.Protein(sb.toString()));
	    } finally {
	        br.close();
	    }
	}
}