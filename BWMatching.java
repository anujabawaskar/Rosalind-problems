import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MWMatching {
	public int position(String s, int pos) {
		char[] last = s.toCharArray();
		char[] first = s.toCharArray();
		Arrays.sort(first);
		//System.out.println(first);
		char ch = last[pos];
		int count = 0;
		//System.out.println(ch);
		for(int i = 0; i < pos; i++) {
			if(last[i] == ch)
				count++;
		}
		
		int countA = 0;
		int countG = 0;
		int countT = 0;
		int countC = 0;
		for(char c : first) {
			if(c == 'A')
				countA++;
			else if(c == 'G')
				countG++;
			else if(c == 'T')
				countT++;
			else if(c == 'C')
				countC++;
		}
		char charAtPos = last[pos];
		if(charAtPos == 'A') {
			return 1 + count;
		}
		if(charAtPos == 'C') {
			return 1 + countA + count;
		}
		if(charAtPos == 'G') {
			return 1 + countA + countC + count;
		}
		if(charAtPos == 'T') {
			return 1 + countA + countC + countG + count;
		}
		return 0;
	}
	public int mwMatching(String text, String pattern) {
		pattern = pattern.trim();
		int top = 0;
		int bottom = text.length() - 1;
		List<Character> list = new ArrayList<Character>();
		for(int i = 0 ; i < pattern.length(); i++) {
			list.add(pattern.charAt(i));
		}
		StringBuffer sb = new StringBuffer(pattern);
		while(top <= bottom) {
			if(list.isEmpty())
				return bottom - top + 1;
			if(!list.isEmpty()) {
				char symbol = list.remove(list.size() - 1);
				if(symbol == '$')
					continue;
				int topIndex = -1;
		        int botIndex = -1;
		        for(int i = top; i <= bottom; ++i) {
		        	if(text.charAt(i) == symbol) {
		        		if(topIndex == -1) {
		        			topIndex = i;
		                    botIndex = i;
		                }
		                else {
		                	botIndex = i;
		                }
		            }
		       }
		       if(topIndex == -1 || botIndex == -1) {
		    	   //System.out.println("here");
		    	   return 0;
		       }
		       top = position(text, topIndex);
		       bottom = position(text, botIndex);
			}
			else
				return bottom - top + 1;
		}
		return 0;
	}
	public static void main(String args[]) throws IOException {
		MWMatching obj = new MWMatching();
		BufferedReader br = new BufferedReader(new FileReader("D:\\Fall 17\\CB\\dataset\\rosalind_ba9l.txt"));
		try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        String BWT = line;
	        line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	    }
	    String[] pattern = sb.toString().split(" ");
	    List<Integer> answer = new ArrayList<Integer>();
	    for(String word:pattern)
	       answer.add(obj.mwMatching(BWT, word));
	    Iterator<Integer> itr = answer.iterator();
	    while(itr.hasNext())
	    	System.out.print(itr.next() + " ");
	    } finally {
	        br.close();
	    }
	}
}