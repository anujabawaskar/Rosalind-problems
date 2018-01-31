public class LevenshteinAlignment {
	public static void backTrace(String s1, String s2, int opt[][]) {
		StringBuffer alignment1 = new StringBuffer(s1);
		StringBuffer alignment2 = new StringBuffer(s2);
		int i = s1.length();
		int j = s2.length();
		while (i != 0 && j != 0) {
			//preference given to gap symbols
			if (opt[i][j] == opt[i][j - 1] + 1) { //from left
				alignment1.insert(i, '-');
				j--;
			}
			else if (opt[i][j] == opt[i - 1][j] + 1) { //from above
				alignment2.insert(j, '-');
				i--;
			}
			else { //diagonal
				i--;
				j--;
			}
			if (i == 0 && j > 0) {	//insert extra gaps if the lengths mismatch
				alignment1.insert(0, '-');
				i++;
			}	
			else if (i > 0 && j == 0) { //insert extra gaps if the lengths mismatch
				alignment2.insert(0, '-');
				j++;
			}
		}
		System.out.println(alignment1.toString());
		System.out.println(alignment2.toString());
	}
    public static void main(String[] args) {
    	String s1 = "WDESWFVWSQDRAPSEWWYNYYQGWKICPCIGEASVRKEKNMPKQSIHDKPMDRYENQHSFEKIQWDTYSQQVSATVLCGCNHKRVKLDLSEMQIKTCILMMNFCRAQGSEANIPRVSFQNQGSERLPYDLCTSGWIIFNTYVGCYPKAYAEDVKQHNMWPIIPPQWWPWSGYSIGSRQIDWCNQFTMYQVFCPNTWDYVQQMTPLDDFWNFPTFKHALPYDDSTEWILCFDHWNSWDTKGIIRKMPTSGETKDKNQHQIEEWFLDIEVPRRRKDMHMYKFAHGQYLFGWQSLQPSWKHDPQEHTWCQNAAYQWEVFAHWVPALFFCKMYTRDEKPYWGDRLLCYMNWLALFSHCWTQNHQAHVEYWFHYLFHRQELAQLIGFPAPNSENWFSHVEGPPASYNTHFTYLFKDNEEGFDWNYGYGYWWMTFVERDHKMQVFHKYRSLPWFYSFRHMALHSFNMYVITFKWCAFWWYNVQCPNCQPKDWASGGTKQNKTKIKSKNGPYTVAQGHQVCPCPCHPCTHDGWEAGGADKYYDFHWSQKHGKYHLYAMFIQWRSHLCSHKGEHHGSYIKTGYTKGEPGTRKFFDLVGLSAAWATDAIHQNSVQCNGRVDDHKYKANCFAYLAHMTASNLWQYHNWTLCTNWVAENMIAERACYHFPNNLHFNEVGPGLRGYNFPLFCRCAANLDLLHLGIRVSQETDYINRRQHAADRGCFELKVHFAQPGLGDFYEKMRPLRMVMFPPMTAVKTVRSAYAHWQESAWYINETSHRAAKGWSNLHPQNYFML";
    	String s2 = "DLSWFSEWWYNYYQGWKICPCIASDRKEKNMPKYSIHLKPPDRYEWQHSFECIQQVSATVCCGCMHKRVCLDLSLMQCILMMNFCRARGSEANIPHNVSFQNQGSERQPYDDCTSGRIIFNTYVGCALSNIWPKAYVKPWTMHNMWPSIPPQWWPWSGYSIGSEQTITPRMNDWDWVCTRCVWNVRAPCRMEPCQHLKMDDHWNFPTFKHALPYDDEYPRINFDHWQHCVRDISWDTKGIIRKMQHYSYCNEDKNQVMIEEWATNVAKIFIMCWRRMWREVPRRSKDMHKPSCKYHFSHGQYLFGWQSLQPSWQDHTWCQNAWQILANYFCKMYTRDEKPYHCHKITAEWMPFYACYDRLLCYMNWLAKFAHVEYHRFSIEIFPAPNSENWFAYEWSYNGQFTYLFCDNEEGFDWNEGYGYWWMTFVEFIVKTQLMDKMQHCYGSRVPWFYSIRHMALNSFNMPNSGGTIKKKGGPYTVAAKLNHLEHWYQQDHDHPLFEFHWSQKAMFCCHDTPMQDDNFGNSYFSFKGEHHGSYTKGEEFEEWYLGPSAAQATDAIAQNSVCKVAGSPQCNGRVHDPNCFAYLAHMTASNLWQLHGYFAEKQEWTLCTNVDKRNTKVEENMIPNNLNEVNPELRRGYNFPLFCRCPQVDTLPGHNLDLLHLYIRVGNAQRQHARDRGCEFGELKLHFAKPGLGDFYEGLKMDVLAVKTQWRAAYAHWQESAWYINETSHRAAKGQANVGMTDMWHPQNYFML";
    	int n = s1.length();
    	int m = s2.length();
  
    	int gap = 1;
    	int match = 0;
    	int substituiton = 1;
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
    		        // we take the minimum
    		        opt[i][j] = Math.min(Math.min(costDiagonal, costLeft), costAbove);
    		}
    	}
    	System.out.println(opt[n][m]);
    	backTrace(s1, s2, opt);
    }
}