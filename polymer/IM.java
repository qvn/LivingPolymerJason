import java.util.Arrays;

public class Initiator_Monomer {
	
	
	static double I,M,dI=0,dM=0,Rm,Ri;
	static double[] RiArray,RmArray;

	
	static String PrintOut_i, PrintOut_Ri="Ri=[", PrintOut_Rm;
	
	//Get initial Values
	//Io,Mo,sc,ki,kp
	static double Io=1;
	static double Mo=100;
	static double ki=1;
	static double kp=100; 
	static double sc=0.0005;
	static double r=kp/ki;
	static double R=Math.abs(1-r);
	//Note that your sc is your dt for now. This is to avoid over calculation. 
	static double dt=sc;
	//Let's do it!
	public static void main(String[] args) {

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
		
		dM=-dt*(ki*M*I+kp*M*(Io-I));
		M=M+dM;
		
		RiArray[i]=I/Io;
		RmArray[i]=M/Mo;
	}
	

	PrintOut_Ri=Arrays.toString(RiArray);
	PrintOut_Rm=Arrays.toString(RmArray);
	
	System.out.println("x=linspace(0,1,100);");
	System.out.println("Ri="+PrintOut_Ri+";");
	System.out.println("Rm="+PrintOut_Rm+";");
	System.out.println("plot(x,Ri,x,Rm)");
	
	}
}

