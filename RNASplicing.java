import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RNASplicing {
	public String utility(String s) {
		String[] list = s.split("\\n>| >");
		int j = 0;
		/*for(String z:list)
			System.out.println(j++ + z);*/
		String seq = list[0].substring(list[0].indexOf("A")).replaceAll("\\s+","").trim();
		List<String> introns = new ArrayList<String>();
		for(int i = 1; i < list.length; i++) {
			int k = 0;
			int index = 0;
			for(char c:list[i].toCharArray()) {
				if(c == 'A' || c == 'G' || c == 'T' || c == 'C' ) {
					index = k;
					break;
				}
				k++;
			}
			//System.out.println("index " + index);
			introns.add(list[i].substring(index).replaceAll("\\s+","").trim());
		}
		Iterator<String> intronItr = introns.iterator();
		while(intronItr.hasNext()) {
			String intron1 = intronItr.next();
			/*System.out.println("intron " + intron1);
			System.out.println("sequence before replacing" + seq);
			System.out.println("index" + seq.indexOf(intron1));*/
			seq = seq.replaceAll(intron1, "z");
		}
		//System.out.println("intron removed" + seq);
		seq = seq.replaceAll("T", "U");
		return translation(seq);
	}
	
	public String translation(String seq) {
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
		StringBuffer protein = new StringBuffer();
		char[] arrayOfExons = seq.toCharArray();
		StringBuffer codon = new StringBuffer();
		for(char c: arrayOfExons) {
			if(codon.length() == 3) {
				//System.out.println(codon);
				String code = map.get(codon.toString());
				if(code.equalsIgnoreCase("Stop"))
					return protein.toString();
				protein.append(code);
				codon.setLength(0);
				if(c == 'z' || c == ' ')
					continue;
				else
					codon.append(c);
			}
			else {
				if(c == 'z' || c == ' ')
					continue;
				else
					codon.append(c);
			}
		}
		return protein.toString();
	}

	public static void main(String args[]) throws IOException {
		RNASplicing rna = new RNASplicing();
		BufferedReader br = new BufferedReader(new FileReader("D:\\Fall 17\\CB\\dataset\\rosalind_splc2.txt"));
		try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        System.out.println(rna.utility(sb.toString()));
	    } finally {
	        br.close();
	    }
	}
}