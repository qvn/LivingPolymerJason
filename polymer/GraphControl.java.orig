package polymer;





public class GraphControl {
	
    static double Io = 1;
    static double Mo = 100;
    static double ki = 1;
    static double kp = 100;
    static double r = kp / ki;
    static double R = Math.abs(r-1);


	// Calculate Max time
    // Method provide function for Ri
	private static double Rm=0.001;
    static double Ri=Ri();
    private static double ti=ti();
    // Bisection method to find Ri - copied from http://www.physics.unlv.edu/~pang/cp2_j.html
	private static double Ri() {
		double del = 0.00001, a = 0, b = 1, x=0;
		double result;
		double dx = b-a;   

		      while (Math.abs(dx) > del) {
		      x = (a+b)/2;
		      if ((f(a)*f(x)) < 0) {
		        b  = x;
		        dx = b-a;
		      }
		      else {
		        a = x;
		        dx = b-a;
		      }
		    }
		        if(x==(a+b)/2) {
		        	x=0;
		        	//This is to prevent if no root exist at Rm=0.001, if that is the
		        	//case then all Ri has been gone a long time ago.
		        }
		    result =x;

		return result;
      }



      private static double f(double x) {
        return Mo/Io*(Rm-1)-(1-r)*(x-1)-r*Math.log(x);
        //return Math.exp(Mo/Io*(Rm-1))/Math.exp((1-r)*(x-1))-r*x;
      }
      
      //Calculate time
      private static double ti() { 
          double result = -Math.log(Rm)/(ki*Io*Ri+kp*Io*(1-Ri));
		return result;
      }
      //Calculate Degree of Polymerization Mean
      private static int xe(){   	 
    	  double L=((Mo-0)-(Io-Io*Ri)*(1-r))/(Io*r);
    	  int xe=(int) (r*L);
    	  if (xe==0 | xe==1) {xe=2;}
		return xe;
      }
      
     public static int xe=xe();

     //This gives more time to see the curve level off
     static double sc=(ti)/100;
      

     
      public static double getSc(){
    	  return sc;
      }
	
      public static double getIo() {
    	  return Io;
      }
      
      public static double getMo(){
    	  return Mo;
      }
	
      public static double getki() {
    	  return ki;
      }
      
      public static double getkp(){
    	  return kp;
      }
	
      public static double getr(){
    	  return r;
      }
	
      public static double getR() {
    	  return R;
      }
      
      
	
}
