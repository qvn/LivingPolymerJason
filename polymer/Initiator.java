package polymer;

import polymer.GraphControl;






public class Initiator {
	double Io = GraphControl.getIo();
	double Mo = GraphControl.getMo();
	double ki = GraphControl.getki();
	double kp = GraphControl.getkp();
	double r = GraphControl.getr();
	double sc = GraphControl.getSc(); 
	public double[] RiArray;
	
	
	private double Ri(double t) {
		double del = 0.00001, a = -50, b = 1, x=0;
		double result;
		double dx = b-a;   

		      while (Math.abs(dx) > del) {
		      x = (a+b)/2;
		      if ((f(a, t)*f(x, t)) < 0) {
		        b  = x;
		        dx = b-a;
		      }
		      else {
		        a = x;
		        dx = b-a;
		      }
		    }
		    result =Math.exp(x);
		
		return result;
      }
	
	private double f(double z,double t) {
		
		double result=z*Io*r-Math.exp(-t*ki*Io*Math.exp(z)-t*kp+t*kp*Math.exp(z))*Mo+Io*Math.exp(z)-Io-Io*r*Math.exp(z)
		+Io*r+Mo;
		
		//double Rm = Io/Mo*(1-r)*(1-x)+r*Io/Mo*Math.log(x);
		//double result=Rm+Math.exp(t*(ki*Io*x+kp*(1-x)));
		return result;
	}
	
	public void setRiArray() {
	RiArray = new double[100];
	RiArray[0]=1;
//	System.out.println(1+" "+RiArray[0]);
		for (int j=1; j<100; j++) {
			RiArray[j]=calRi(j);
			if (RiArray[j]>RiArray[j-1]) {
				RiArray[j]=0;
			}
			if (RiArray[j]<0) {
				RiArray[j]=RiArray[j-1];
			}
//			System.out.println(j+" "+RiArray[j]);
		}
	}
	
	
	private double calRi(int i){
		
		double result=1;
		double t=sc*i;
    	//if (ki*Mo*Io*GraphControl.getSc()*.1>Io){	
    	//	result=0;
        //} else {
        	result=Ri(t);
        	
        //	}
        
    	
    	return result;
	}
	
	public double getRi(int i) {
		double result;
		if (RiArray == null) {
			setRiArray();
			result=RiArray[i];
		} else {
			result=RiArray[i];
		}
		
		return result;
	}
	
	
	
}


