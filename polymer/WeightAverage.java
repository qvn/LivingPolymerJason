package polymer;

import polymer.Ufunc;

public class WeightAverage {

	static IM RiRm = new IM();
	double Io=GraphControl.getIo();
	double Mo=GraphControl.getMo();
	double r=GraphControl.getr();
	double R=GraphControl.getR();
	public double XwArray[];
	
	
    Ufunc uFunc = new Ufunc();
    
	
	private double calXw (int i){

        double result = 1;
	
		if (r<1) {
			result = getLessThanOne(i); 
	    }
		
	    if (r>1) {
	    	result = getMoreThanOne(i);
	    }
	    
		if (r==1) {
			result = getEqualOne(i);
		}
		
		return result;

	}
	
	private double getLessThanOne (int i){
		double u = uFunc.getU(i);
        double term1 = r*u/R;
        double term2 = 1.0-Math.exp(-1.0*u/R);
        double term3 = 1.0-r;
        double result = (
            (
            ((term1)*(1.0+term1))
            +
            (2.0*r*u)
             -  
            (((2.0*r-1))*term3*term2)
            )
            /
            (
            (term3*term2)+term1)
            );
        if (result<=0.0) {
            return 0.0;
        } else {
            return result;
        }
	}
	private double getMoreThanOne (int i){
		double u = uFunc.getU(i);
        double term1 = r*u/R;
        double term2 = 1.0-Math.exp(-1.0*u/R);
        double term3 = 1.0-r;
        double result = (((term1)*(1.0+term1))-(2.0*r*u+(2.0*r-1)*term3*term2))/((term3*term2)+term1);
        if (result<=0.0) {
            return 0.0;
        } else {
            return result;
        }
	}
	private double getEqualOne (int i){

		double Rm=RiRm.getRm(i);
		double M=Rm*Mo;
    	double result = 1 - (M-Mo)/Io;
    	//System.out.print("Xw = ");
    	//System.out.println(result);
    	if (result <= 0.0) {
    		return 0.0;
    	} else {
    		return result;
    	}
	}
	
	public void setXwArray() {
		XwArray = new double[100];
		XwArray[0]=0; //when u=0 it is actually undefined. 
//		System.out.println(1+" "+XwArray[0]);
			for (int j=1; j<100; j++) {
				XwArray[j]=calXw(j);

//				System.out.println(j+" "+RiArray[j]);
			}
		}
	public double getXw(int i) {
		double result;
		if (XwArray == null) {
			setXwArray();
			result=XwArray[i];
		} else {
			result=XwArray[i];
		}
		
		return result;
	}
}


