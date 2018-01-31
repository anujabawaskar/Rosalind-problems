import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CoutingOptimalAlignments {
	static int MODVALUE = 134217727;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("D:\\Fall 17\\CB\\dataset\\rosalind_ctea2.txt"));
		StringBuilder sb = new StringBuilder();
		try {
	        sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
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
			sequence[i] = seq[i].substring(index).replaceAll("\\s+","").trim();
			title[i] = seq[i].substring(0, index).replaceAll("\\s+","").trim();
		}
		title[0] = title[0].substring(1);
    	String s1 = sequence[0];
    	String s2 = sequence[1];
    	int n = s1.length();
    	int m = s2.length();
    	int gap = 1;
    	int match = 0;
    	int substituiton = 1;
    	int[][] opt = new int[n + 1][m + 1];
    	int[][] numAlignments = new int[n ][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				numAlignments[i][j] = -1;
			}
		}
    	opt[0][0] = 0;
    	for(int i = 1; i <= m; i++)
    		opt[0][i] = i * gap;
    	for(int i = 1; i <= n; i++)
    		opt[i][0] = (i) * gap;
    	
    	for(int i = 1; i <= n; i++) {
    		for(int j = 1; j <= m; j++) {
    			 int costDiagonal = opt[i - 1][j - 1] + (s1.charAt(i - 1) == s2.charAt(j - 1) ? match : substituiton); //diagonal 
    		        int costLeft = opt[i][j - 1] + gap; // gap
    		        int costAbove = opt[i - 1][j] + gap; // gap
    		        // we take the minimum
    		        opt[i][j] = Math.min(Math.min(costDiagonal, costLeft), costAbove);
    		}
    	}
    	int[][] opt1 = new int[n][m];
    	for(int i = 1, k = 0; i <= n; i++, k++) {
    		for(int j = 1, l = 0 ; j <= m; j++, l++) {
    			opt1[k][l] = (opt[i][j]);
    		}
    	}
    	/*for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			System.out.print(numAlignments[i][j]+ " ");
    		}
    		System.out.println();
    	}*/
    	int allAlignments = CoutingOptimalAlignments.findAllAlignments(s1, s2, n - 1, m - 1, numAlignments, opt1, opt1[n - 1][m - 1]);
    	/*System.out.println("matrix");
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			System.out.print(numAlignments[i][j]+ " ");
    		}
    		System.out.println();
    	}*/
		System.out.println(allAlignments);
    }
	public static int findAllAlignments(String s, String t, int i, int j, int[][] numAlignments, int[][] opt, int optValue) {
		if ((i < 0 && j >= 0) || (i >= 0 && j < 0) || ((i < 0 && j < 0)))
			return 1;
		else if (numAlignments[i][j] != -1) 
			return numAlignments[i][j];
		else {
			char a = s.charAt(i);
			char b = t.charAt(j);
			int allAlignments = 0;
			if (optValue - score(a, b) == findScore(opt, i - 1, j - 1))
				allAlignments = (allAlignments + findAllAlignments(s, t, i - 1, j - 1, numAlignments, opt, optValue - score(a, b))) % MODVALUE;
			if (optValue - 1 == findScore(opt, i - 1, j))
				allAlignments = (allAlignments + findAllAlignments(s, t, i - 1, j, numAlignments, opt, optValue - 1)) % MODVALUE;
			if (optValue - 1 == findScore(opt, i, j - 1))
				allAlignments = (allAlignments + findAllAlignments(s, t, i, j - 1, numAlignments, opt, optValue - 1)) % MODVALUE;
			numAlignments[i][j] = allAlignments;
			return allAlignments;
		}
	}
	public static int score(char a, char b) {
		if(a == b) //match
			return 0;
		else //mismatch
			return 1;
	}
	private static int findScore(int[][] opt, int i, int j) {
		if (i < 0 && j < 0)
			return 0;
		else if (!(j < 0) && i < 0)
			return (j + 1) * 1;
		else if (!(i < 0) && j < 0)
			return (i + 1) * 1;
		else
			return opt[i][j];
	}
}