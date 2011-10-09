package polymer;

import polymer.*;


//import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class Nx {
	
	double Io = GraphControl.getIo();
	double Mo = GraphControl.getMo();
	double ki = GraphControl.getki();
	double kp = GraphControl.getkp();
	double r = GraphControl.getr();
	double R = GraphControl.getR();
	Ufunc uFunc = new Ufunc();
	
	
    int mean=GraphControl.xe;
    int MaxX=(int) (4*Math.sqrt(mean)+mean);


	
	
	
	private double IncompleteGamma(double s, double z){
    	double max,tiny,eps,a,b,f,c,d,del;	
		tiny=10E-30;
		eps=10E-15;
		max=100;	
		b=s;
		f=b;
		if (b==0) {f=tiny; }
		c=f;
		d=0;
		for (int i=1; i<max; i++) {
		    if (i % 2 != 0) {//i=odd
		        a=-(s+(i-1)/2)*z;
		    } else {
		    	a=(i/2)*z;
		    }
		    b=s+i;
		    d=b+a*d;
		    if (Math.abs(d)<tiny) {
		        d=tiny;
		    }
		    c=b+a/c;
		    if (Math.abs(c)<tiny) {
		        c=tiny;
		    }
		    d=1/d;
		    del=c*d;
		    f=f*del;
		    if (Math.abs(del)<eps) {break; }
		}
    	
    	return f;
    }
	private double PoissonPDF(double u, double x) { //use Math.exp(x*ln(u)-u-ln(gamma(x+1)))
    	double term1=x*Math.log(u);
    	double term2=u;
    	//ln(gamma) term => numerical recipe
    	double term3=LnGamma(x+1);
    	double result=Math.exp(term1-term2-term3);
    	return result;
    }
    private double LnGamma(double xx) {
    	double x,y,tmp,ser;
    	final double cof[] = {76.18009172847146,-86.50532032941677,
    		24.01409824083091,-1.231739572450155,
    		0.1208650973866179E-2,-0.5395239384953E-5};
    	int j;
    	
    	y=x=xx;
    	tmp=x+5.5;
    	tmp -= (x+0.5)*Math.log(tmp);
    	ser=1.000000000190015;
    	for (j=0;j<=5;j++) {
    		ser +=cof[j]/++y;
    	}
    	return -tmp+Math.log(2.5066282746310005*ser/x);
    	
    }


    
    double calNx (int i, int x){
    	double result = 0;
    	if (r<=1.01 && r>=.99) {
    		result = getAroundOne (i,x);
    	}
    	
    	if (r>1.01) {
    		result = getMoreThanOne (i,x);
    	}
    	
    	if (r<.99) {
    		result = getLessThanOne(i,x);
    	}
    	return result;
    }
    private double getAroundOne (int i, int x) {

        double u= uFunc.getU(i);
        
        double p=PoissonPDF(u, x);
        
        double result=Io*p;

        return result;

    }
	private double getMoreThanOne (int i, int x) {

    	double q = r/R;
    	double u= uFunc.getU(i);

		double result, term1,term2,term3;
		
		double f = IncompleteGamma(x,u);

		term1=Io/r*x;
		term2=PoissonPDF(q*u, x);
		term3=1/f;
		result=term1*term2*term3;
		return result;
    	
	}
	private double getLessThanOne(int i, int x){

    	double q = r/R;
    	double u= uFunc.getU(i);
    	
		double result, term1,term2,term3;
		
		double f = IncompleteGamma(x,-u);
		
		term1=Io/r*x;
		term2=PoissonPDF(q*u, x);
		term3=1/f;
		result=term1*term2*term3;
		//System.out.println("x= "+x+"Nx<1= "+result);
		return result;
    }



}

