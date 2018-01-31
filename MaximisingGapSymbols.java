import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MaximisingGapSymbols {
	public void backTrace(String s1, String s2, int opt[][]) {
		int i = s1.length();
		int j = s2.length();
		int count = 0;
		while(i > 0 && j > 0) {
			if (opt[i][j] == opt[i][j - 1] - 1000) { //from left
				//alignment1.insert(i, '-');
				count++;
				j--;
			}
			else if (opt[i][j] == opt[i - 1][j] - 1000) { //from above
				//alignment2.insert(j, '-');
				count++;
				i--;
			}
			else { //diagonal
				i--;
				j--;
			}
		}
		if(j > 0) {
			while(j > 0) {
				count++;
				j--;
			}
		}
		if(i > 0) {
			while(i > 0) {
				count++;
				i--;
			}
		}
		/*while (i != 0 && j != 0) {
			//preference given to gap symbols
			if (opt[i][j] == opt[i][j - 1] - 1000) { //from left
				//alignment1.insert(i, '-');
				count++;
				j--;
			}
			else if (opt[i][j] == opt[i - 1][j] - 1000) { //from above
				//alignment2.insert(j, '-');
				count++;
				i--;
			}
			else { //diagonal
				i--;
				j--;
			}
			if (i == 0 && j > 0) {	//insert extra gaps if the lengths mismatch
				//alignment1.insert(0, '-');
				count++;
				i++;
			}	
			else if (i > 0 && j == 0) { //insert extra gaps if the lengths mismatch
				//alignment2.insert(0, '-');
				count++;
				j++;
			}
		}*/
		System.out.println(count);
		//System.out.println(alignment1.toString());
		//System.out.println(alignment2.toString());
	}
	public static void main(String[] args) throws IOException {
		MaximisingGapSymbols obj = new MaximisingGapSymbols();
		BufferedReader br = new BufferedReader(new FileReader("D:\\Fall 17\\CB\\dataset\\rosalind_mgap1.txt"));
		StringBuilder sb = new StringBuilder();
		try {
	        sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	    //obj.separateSequences(sb.toString());
	    } finally {
	        br.close();
	    }
		String[] seq = sb.toString().split("\n>");
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
			//System.out.println("title" + title[i]);
			//System.out.println("sequence"+sequence[i]);
		}
		title[0] = title[0].substring(1);
    	String s1 = sequence[0];
    	String s2 = sequence[1];
    	int n = s1.length();
    	int m = s2.length();
  
    	int gap = -1000;
    	int match = 1000;
    	int substituiton = -5000;
    	int[][] opt = new int[n + 1][m + 1];
    	
    	opt[0][0] = 0;
    	//making s1 and s2 from null string
    	for(int i = 1; i <= m; i++)
    		opt[0][i] = i * gap;
    	for(int i = 1; i <= n; i++)
    		opt[i][0] = i * gap;
    	
    	for(int i = 1; i <= n; i++) {
    		for(int j = 1; j <= m; j++) {
    			 int costDiagonal = opt[i - 1][j - 1] + (s1.charAt(i - 1) == s2.charAt(j - 1) ? match : substituiton); //diagonal 
    		        int costLeft = opt[i][j - 1] + gap; // gap
    		        int costAbove = opt[i - 1][j] + gap; // gap
    		        // we take the maximum
    		        opt[i][j] = Math.max(Math.max(costDiagonal, costLeft), costAbove);
    		}
    	}
    	obj.backTrace(s1, s2, opt);
    }
}