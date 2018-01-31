import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class ModelingRandomGenomes {
	public double[] probability(String s, double arr[]) {
		int countGC = 0;
		int	countAT = 0;
		double[] result = new double[arr.length];
		int i = 0;
		char[] stringArray = s.toCharArray();
		for(char c : stringArray) {
			if(c == 'A' || c == 'T')
			    countAT+=1;
			else
				countGC+=1;
		}
		for(double p:arr) {
			double pairGC = p / 2;
			double pairAT = (1 - p) / 2;
			result[i] = (countGC * Math.log10(pairGC)) + (countAT * Math.log10(pairAT));
			i++;
		}
		return result;
	}
	public static void main(String args[]) throws IOException {
		ModelingRandomGenomes obj = new ModelingRandomGenomes();
		BufferedReader br = new BufferedReader(new FileReader("D:\\Fall 17\\CB\\dataset\\rosalind_prob.txt"));
		try {
	        String line = br.readLine();
	        String seq = line;
	        line = br.readLine();
	        String[] prob = line.split(" ");
	        int i = 0;
	        double[] probValues = new double[prob.length];
	        for(String p1:prob) {
	        	probValues[i] = Double.parseDouble(p1);
	        	i++;
	        }
	        probValues = obj.probability(seq, probValues);
	        for(double answer: probValues)
	        	System.out.print(new DecimalFormat(".###").format(answer) + " " );
	    } finally {
	        br.close();
	    }
	}
}