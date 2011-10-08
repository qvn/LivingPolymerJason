package polymer;


public class Ufunc {
	
	static IM RiRm = new IM();
	double Io=GraphControl.getIo();
	double Mo=GraphControl.getMo();
	double r=GraphControl.getr();
	
	private double getL(int i) {
		double Rm=RiRm.getRm(i);
		double Ri=RiRm.getRi(i);

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
