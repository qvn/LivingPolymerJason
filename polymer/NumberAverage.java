package polymer;

import polymer.Ufunc;


public class NumberAverage {
	
	Initiator initiator = new Initiator();
	Monomer monomer = new Monomer();
	
	double Io=GraphControl.getIo();
	double Mo=GraphControl.getMo();
	double r=GraphControl.getr();
	double R=GraphControl.getR();
	
	public double[] XnArray;

    Ufunc uFunc = new Ufunc();

	//x=x*GraphController.scale();
	
    
	private double calXn (int i) {

		double result=1;
		if (r!=1 ) {
			result = getNotOne(i);
        }
    
		else if (r==1) {
			result = getIsOne(i);
		}	
     
    return result;
}
	
	private double getNotOne(int i){
		double u=uFunc.getU(i);
		double term1 = 1.0-r;
		double term2 = 1.0-Math.exp(-1.0*u/R);
		double result = ((term1*term2)+(u*r/R))/term2;
        if (result<=0.0) {
            return 1.0;
        }
        else {
            return result;
        }
	}
	private double getIsOne(int i){
    	double M=monomer.getRm(i)*Mo;
    	double I=initiator.getRi(i)*Io;
		double result = (M-Mo)/(I-Io);
		//System.out.print("Xn = ");
    	//System.out.println(result);
		if (result<=0.0) {
	    	return 0.0;
	    	
	    } else {
	    	return result;
	    }
	}

	public void setXnArray() {
		XnArray = new double[100];
		XnArray[0]=0;
//		System.out.println(1+" "+XnArray[0]);
			for (int j=1; j<100; j++) {
				XnArray[j]=calXn(j);
//				System.out.println(j+" "+RiArray[j]);
			}
		}
	
	public double getXn(int i) {
		double result;
		if (XnArray == null) {
			setXnArray();
			result=XnArray[i];
		} else {
			result=XnArray[i];
		}
		
		return result;
	}
}
