package polymer;

import polymer.*;

public class PDI {
	NumberAverage number = new NumberAverage();
	WeightAverage weight = new WeightAverage();
	double[] PDI_Array;
	
	public void setPDI_Array() {
		PDI_Array = new double[100];
		PDI_Array[0]=1; //just so the graph won't go below 1, in fact, it's undefined. 
//		System.out.println(1+" "+RiArray[0]);
			for (int j=1; j<100; j++) {
				PDI_Array[j]=calPDI(j);
			}
	}
	
	
	double calPDI(int i) {
		double result;
		
		double Xn=number.getXn(i);
		double Xw=weight.getXw(i);
		
		result = Xw/Xn;
		return result;
	}

}
