import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BurrowsWheelerMatrix {
	public String BWT(String s) {
		int len = s.length();
		List<String> list = new ArrayList<String>();
		int index = len - 1;
		StringBuffer temp = new StringBuffer();
		for(int i = 0; i < len; i++) {
			temp.append(s.substring(index));
			temp.append(s.substring(0, index));
			list.add(temp.toString());
			temp.setLength(0);
			index--;
		}
		String s1 = list.remove(0);
		StringBuffer answer = new StringBuffer();
		answer.append(s1.substring(len - 1));
		Collections.sort(list);
		Iterator<String> itr = list.iterator();
		while(itr.hasNext()) {
			answer.append(itr.next().substring(len - 1));
		}
		
		return answer.toString();
	}
	public static void main(String args[]) throws IOException {
		BurrowsWheelerMatrix obj = new BurrowsWheelerMatrix();
		BufferedReader br = new BufferedReader(new FileReader("D:\\Fall 17\\CB\\dataset\\rosalind_ba9i.txt"));
		try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        //System.out.println(obj.BWT(sb.toString()));
	    } finally {
	        br.close();
	    }
		System.out.println(obj.BWT("CGCTCAGTCATCAGGACCGACCCCCGCTAGAGGTAAGCGAATTTTAAGGATGGGATGCTAAAACCAAGGTGCTACTAGTAAGCGGAATGAGGGTTCCTAGCCCTCCGATTTGCTACCCGGGTATATGTTGCCCAGGAGGACTATGTCTGAACGTCGTCTGTCTCGCTCAGTTATATAACTTCCAGCTGAGGCGAGGCATGTCCCTCCGATTGGGTTTATATCCCAGTGATTGGCAGCGGCTCCGTTATTCGAGTATCAATAAACCCAATCTCCACATGCAAGGTTAGTACAAGATAACTCTTAAGTTACCTCCTACCCGTACTCCGACGCATAACACAGCCAGCGACGCCGACCTCTCGACCAGGTAGGGACACGTGTACCGTTCGCGCTGGACACTTTACGCGAGTTTGAAGACTAGTGCTATCAGGTGACCGGTGCAGATGCGAGACTGCCACCGTTCGTCGCACAAAACCAACTGACTTCGGAGATTCGCCTGTTCTCGTTGTACAGCCCTATGTTTGTTTTGTTCGAGCCCTCTCTAGAGGGGAAAGCAGTTCTCACCGATTGTCCGGCCGCCTGATCTCAGATTATGAGTGCCTCTTCAGGCAATCAGAAATGCACGAGAGTTGCATTGATAGTCTAGGAGATCTTAATATCGCCCAGTATTGGTGGGGTCAGTTTTGGGAGATACGCAGCCTGAGCCTAATGCGTGGGTCTCAGTGGATGACTACCATTATCTAACTCATGATGACAATTTCGTCAGTATGACGTATCCGCATGCGGATTGGATGGTCCTACTGCGCGACACGCGAAATGAAAGTGACCGGCTGCCTCGGAACAAAGTCGTCCCTGACTTGACGCATCAGGGACGGAAACAAAATAGCCCCAGAGGAAGTCTCATTATGACTTAACTAGGCCGCAAAC$"));
	}
}