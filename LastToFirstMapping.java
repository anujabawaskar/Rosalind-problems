import java.util.Arrays;

public class LastToFirstMapping {
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
	public static void main(String args[]) {
		LastToFirstMapping obj = new LastToFirstMapping();
		String s = "CCTAAACTCCGCCTACAGATCACTAAGAATGCGCATGGCACCCCCTGGCCTTAAGGTCAGTTGTGGCTTATTTATCGTTGTTCATGTCTAGAGGGAACGGTGAACGCACTTGACAGAGAGCCTTACATTACCAGCCTTTATCAGATCTCTACTATCTAAACACAGCTGGCGTTGTGAGCCCCCAGCGTACTAACATCTCACGTAGTACGGTGCGATCAAGACGTATCGCCTCAAGTCCGCGTGGTGCATATTTCGTGGAGTTTTATACGAATCCACAGAGTCCCGTCTCTGTATTCAAACCAAGCCGGTCTTATGCCTTTCATGTGCAGTCGCGCCTCCCTATTGTTATGGCTAACATGGGTTATCTGATCGCTTCAGTCGAGATTACTTACCTGCCGAGTGCCCCGCGGCTAGCGGGTAAAACCTTCACCAGCCGTCACGTGGACTGCGGTAGCGTTGATTGCTGCTTCTCATGCGGGTACGAGTATTGCATGACAAGTGAAACATTAGTTGAGTCCAGAAACACGTAAAGCCGCCGGTTCCAGGGGCACATTGGGAAGATGCCGTCAGTTAGAAGACGGTCCCTTCGAACGCGCACCGGATAAACTCCCACTACCTCGTGTGAGCCGTCCTACATGGTTGTTTACTGTTCATGAAAGGTACCTACACCATTCCAGGTCGGCCTATAGTGCCGATGCACTGGACACGCTGGGTGCGCTCCGGACTTAATTCGCCTAGGTACCGGTGCAAGAAATCGTCGGGGTGTGCGGCATACTTTAAGCCAGACGACTACGTCCATCATCAATATTAAGATGGTCTTGG$";
		int pos = 283;
		System.out.println(obj.position(s, pos));
	}
}