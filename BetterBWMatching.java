import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BetterBWMatching {
	public Map<Character, Integer> firstOccurence(String s) {
		char first[] = s.toCharArray();
		Arrays.sort(first);
		String firstString = first.toString();
		Map<Character, Integer> m = new HashMap<Character, Integer>();
		m.put('$', 0);
		m.put('A', firstString.indexOf("A"));
		m.put('C', firstString.indexOf("C"));
		m.put('G', firstString.indexOf("G"));
		m.put('T', firstString.indexOf("T"));
		return m;
	}
	public int count(int top, String s, char sym) {
		int count = 0;
		char[] arr = s.toCharArray();
		for(int i = 0; i < top; i++) {
			if(arr[i] == sym)
				count++;
		}
		return count;
	}
	public int betterFunction(String s, String pattern, char c) {
		int top = 0;
		int bottom = s.length() - 1;
		pattern = pattern.trim();
		List<Character> list = new ArrayList<Character>();
		Map<Character, Integer> m = firstOccurence(s);
		for(int i = 0 ; i < pattern.length(); i++) {
			list.add(pattern.charAt(i));
		}
		while(top <= bottom) {
			int flag = 0;
			if(!list.isEmpty()) {
				char symbol = list.remove(list.size() - 1);
		        for(int i = top; i <= bottom; ++i) {
		        	flag = 1;
		        	top = m.get(symbol) + count(top, s, symbol);
		        	bottom = m.get(symbol) + count(bottom + 1, s, symbol) - 1;
		        }
		        if(flag == 0)
		        	return 0;
			}
			else
				return bottom - top + 1;
		}
		return 0;
	}
	public static void main(String args[]) throws IOException {
		MWMatching obj = new MWMatching();
		BufferedReader br = new BufferedReader(new FileReader("D:\\Fall 17\\CB\\dataset\\rosalind_ba9m.txt"));
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