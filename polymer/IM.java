package polymer;

import java.util.Arrays;
import polymer.GraphControl;
public class IM {
	static double I,M,dI,dM;
	static double[] RiArray,RmArray;

	
	//Get initial Values
	//Io,Mo,sc,ki,kp
	static double Io = GraphControl.getIo();
	static double Mo = GraphControl.getMo();
	static double ki = GraphControl.getki();
	static double kp = GraphControl.getkp();
	static double r = GraphControl.getr();
	static double sc = GraphControl.getSc();
	static double R=Math.abs(1-r);
	//Note that your sc is your dt for now. This is to avoid over calculation. 
	static double dt=sc;
	//Let's do it!
	public void setRiRmArray() {
		RiArray=new double[100];
		RmArray=new double[100];
	for (int i=0; i<100; i++) {
		//set initial values
		if (i==0) {
			I=Io;
			M=Mo;
			RiArray[i]=1;
			RmArray[i]=1;
		}
		//now get dI and dM
		dI=-dt*ki*M*I;
		I=I+dI;
		if (I<0) {I=0;}
		dM=-dt*(ki*M*I+kp*M*(Io-I));
		M=M+dM;
		if (M<0) {M=0;}
		
		RiArray[i]=I/Io;
		RmArray[i]=M/Mo;
		}
	}
	
	public double getRi(int i) {
		double result;
		if (RiArray == null) {
			setRiRmArray();
			result=RiArray[i];
		} else {
			result=RiArray[i];
		}
		
		return result;
	}
	
	public double getRm(int i) {
		double result;
		if (RmArray == null) {
			setRiRmArray();
			result=RmArray[i];
		} else {
			result=RmArray[i];
		}
		
		return result;
	}

	//PrintOut_Ri=Arrays.toString(RiArray);
	//PrintOut_Rm=Arrays.toString(RmArray);
	
	//System.out.println("x=linspace(0,1,100);");
	//System.out.println("Ri="+PrintOut_Ri+";");
	//System.out.println("Rm="+PrintOut_Rm+";");
	//System.out.println("plot(x,Ri,x,Rm)");
	
	}


