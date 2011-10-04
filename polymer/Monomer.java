package polymer;

import polymer.Initiator;

public class Monomer {
	Initiator I= new Initiator();
	
	
	double Io = GraphControl.getIo();
	double Mo = GraphControl.getMo();
	double ki = GraphControl.getki();
	double kp = GraphControl.getkp();
	double r = GraphControl.getr();
	double sc = GraphControl.getSc();
	public double[] RmArray;
	
	public void setRmArray() {
		RmArray = new double[100];
		RmArray[0]=1;
//		System.out.println(1+" "+RmArray[0]);
			for (int j=1; j<100; j++) {
				RmArray[j]=calRm(j);
				if (RmArray[j]>RmArray[j-1]) {
					RmArray[j]=0;
				}
//				System.out.println(j+" "+RiArray[j]);
			}
		}
	
	private double calRm(int i){
		double t=i*sc;
		double Ri = I.getRi(i);
		double Rm;
		if (Ri==0) {
			Rm=Math.exp(-t*kp);
		} else {
			Rm=(1-r)*(Ri-1)*Io/Mo+Io/Mo*r*Math.log(Ri)+1;
			//Rm=Math.exp(-t*(ki*Io*Ri+kp*Io*(1-Ri)));
		}
		//System.out.println("Ri=" +Ri +" Rm="+Rm);
		//System.out.println("Rm="+Rm);
		
		return Rm;
	}
	
	public double getRm(int i) {
		double result=0;
		if (RmArray == null) {
			setRmArray();
			result=RmArray[i];
		} else {
			result=RmArray[i];
		}
		
		return result;
	}

}
