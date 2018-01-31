import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OverlapGraph {
	public void separateSequences(String s) {
		String[] seq = s.split("\n>");
		int length = seq.length;
		String[] title = new String[length];
		String[] sequence = new String[length];
		for(int i = 0; i < length; i++) {
			int k = 0;
			int index = 0;
			for(char c : seq[i].toCharArray()) {
				if(c == 'A' || c == 'G' || c == 'T' || c == 'C' ) {
					index = k;
					break;
				}
				k++;
			}
			//System.out.println("index " + index);
			sequence[i] = seq[i].substring(index).replaceAll("\\s+","").trim();
			title[i] = seq[i].substring(0, index).replaceAll("\\s+","").trim();
			/*System.out.println("title" + title[i]);
			System.out.println("sequence"+sequence[i]);*/
		}
		title[0] = title[0].substring(1);
		List<String> adjacencyList = new ArrayList<String>();
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < length; j++) {
				if(i != j) {
					int lengthI = sequence[i].length();
					if((sequence[i].substring(lengthI - 3, lengthI)).equals(sequence[j].substring(0, 3))) {
						adjacencyList.add(title[i] + " " + title[j]);
					}
						
				}
			}
		}
		Iterator<String> itr = adjacencyList.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		
	}
	public static void main(String args[]) throws IOException {
		OverlapGraph obj = new OverlapGraph();
		BufferedReader br = new BufferedReader(new FileReader("D:\\Fall 17\\CB\\dataset\\rosalind_grph2.txt"));
		try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	    obj.separateSequences(sb.toString());
	    } finally {
	        br.close();
	    }
	}
}