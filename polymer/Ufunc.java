package polymer;

import polymer.GraphControl;
import polymer.Initiator;
import polymer.Monomer;


public class Ufunc {
	
	Initiator initiator = new Initiator();
	Monomer monomer = new Monomer();
	double Io=GraphControl.getIo();
	double Mo=GraphControl.getMo();
	double r=GraphControl.getr();
	
	private double getL(int i) {
		double Rm=monomer.getRm(i);
		double Ri=initiator.getRi(i);

		double L=Mo/(Io*r)*(1-Rm)-(1-r)/r*(1-Ri);
		
		return L;
	}
	
	
	public double getU(int i){

	double result;
	if (r==1) {
		result = getL(i);
		//System.out.println("L= " +L);
	} else {
		result = GraphControl.getR()*getL(i);
	}
	//System.out.println("u= " +result);
	if (result<=0.0) {
		return 0.0;
	} else {
		return result;
	}
	//Q
	
	}
}
