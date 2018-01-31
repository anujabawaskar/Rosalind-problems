import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShortestSuperstring {
	public void superstring(String s) {
		String[] seq = s.split("\n>");
		int length = seq.length;
		List<String> title = new ArrayList<String>();
		List<String> sequence = new ArrayList<String>();
		//String[] title = new String[length];
		//String[] sequence = new String[length];
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
			sequence.add(seq[i].substring(index).replaceAll("\\s+","").trim());
			title.add(seq[i].substring(0, index).replaceAll("\\s+","").trim());
			//System.out.println("title" + title.get(i));
			//System.out.println("sequence"+sequence.get(i));
		}
		title.set(0, title.get(0).substring(1));
		while(sequence.size() != 1) {
		int max = 0; 
		String prefix = new String();
		String suffix = new String();
		for(int i = 0; i < sequence.size(); i++) {
			for(int j = 0; j < sequence.size(); j++) {
				if(i != j) {
					for (int k = 1; k <= Math.min(sequence.get(i).length(),sequence.get(j).length() ); k++) {
			            if (sequence.get(j).startsWith(sequence.get(i).substring(sequence.get(i).length() - k, sequence.get(i).length()))) {
			                if(k > max) {
			                	max = k;
			                	prefix = sequence.get(j);
			                	suffix = sequence.get(i);
			                }
			            }
			        }
				}
			}
		}
		/*System.out.println("max match " + max);
		System.out.println("prefix " + prefix);
		System.out.println("suffix " + suffix);
		System.out.println("new concat " + (prefix + suffix.substring(max)));*/
		sequence.add(suffix + prefix.substring(max));
		sequence.remove(suffix);
		sequence.remove(prefix);
		}
		System.out.println(sequence.get(0));
		/*boolean flag;
		int match = 0, max = 0;
		int prefix, suffix, j;
		int lengthSeq = title.size();
		System.out.println("length of sequence" + lengthSeq);
		for(int i = 0 ; i < lengthSeq; i++) {
			System.out.println("here");
			for(j = 0; j < lengthSeq; j++) {
				if(i != j) {
					System.out.println("here1");
					char[] iArray = sequence.get(i).toCharArray();
					char[] jArray = sequence.get(j).toCharArray();
					flag = false;
					match = 0;
					for(int k = 1, l = 0 ; k < iArray.length; k++, l++) {
						System.out.println("here2");
						if(iArray[k] == jArray[l] && flag == false) {
							match = 0;
							match++;
							flag = true;
						}
						else if(iArray[k] != jArray[l] && flag == true) {
							match = 0;
							l = 0;
							flag = false;
						}
						else if(iArray[k] != jArray[l] && flag == false) {
							l = 0;
						}
						else if(iArray[k] == jArray[l] && flag == true) {
							match++;
						}
					}
					if(match > max) {
						max = match;
						prefix = j;
						suffix = i;
					}
				}
			}
			String tempSuper = sequence.get(i) + sequence.get(j).substring(match + 1, sequence.get(j).length());
			sequence.remove(sequence.get(i));
			sequence.remove(sequence.get(i));
			sequence.add(tempSuper);
		}
		System.out.println(sequence.get(0));
*/
	}
	public static void main(String args[]) throws IOException {
		ShortestSuperstring obj = new ShortestSuperstring();
		BufferedReader br = new BufferedReader(new FileReader("D:\\Fall 17\\CB\\dataset\\rosalind_long.txt"));
		try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	    obj.superstring(sb.toString());
	    } finally {
	        br.close();
	    }
	}
}