package polymer;
import java.util.Arrays;
import java.io.*;
public class StandAlone {



static double I,M,dI=0,dM=0;
static double[] RiArray,RmArray,UArray,XwArray,XnArray,PDIArray;
static String PrintOut_i, PrintOut_Ri, PrintOut_Rm;



//Let's do it!
public static void main(String[] args) throws IOException {
double Io,Mo,ki,kp,sc,r,R,dt,u,L,Xw=1,Xn=1,PDI;
String FileName = "test";
//Get initial Values
//Io,Mo,sc,ki,kp
Io = StandAlone.getDoubleFromShell("Io: ");
Mo = StandAlone.getDoubleFromShell("Mo: ");
ki = StandAlone.getDoubleFromShell("ki: ");
kp = StandAlone.getDoubleFromShell("kp: ");
dt = StandAlone.getDoubleFromShell("dt (default of 0.0005): ");
int FileType = StandAlone.getIntFromShell("File Type As: (1) SpaceDlim (2) Matlab: ");
if (FileType==2){FileName = StandAlone.getStringFromShell("File Name As: ");}

//if (dt==null) {
	//dt=0.0005;
//	}
r=kp/ki;
R=Math.abs(1-r);




// Looking for the optimum Time when M depletes

//Looking for Ri
	double del = 0.00001, a = 0, b = 1, x, Rm_temp=0.001, Ri_temp=0;
	x=Ri_temp;
	double dx = b-a;   
	      while (Math.abs(dx) > del) {
	      x = (a+b)/2;
	      double fa=Mo/Io*(Rm_temp-1)-(1-r)*(a-1)-r*Math.log(a);
	      double fx=Mo/Io*(Rm_temp-1)-(1-r)*(x-1)-r*Math.log(x);
	      if ((fa*fx) < 0) {
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
	    Ri_temp =x;

	    double TimeTotal = -Math.log(Rm_temp)/(ki*Io*Ri_temp+kp*Io*(1-Ri_temp));

	    System.out.println("%"+"Io= "+Io+" Mo= "+Mo+" ki= "+ki+" kp= "+kp+" r= "+r+" dt= "+dt);
	    System.out.println("%"+"Total Time to deplete M= "+round5(TimeTotal)+" Steps should be: "+TimeTotal/dt);
	    System.out.println(" ");
	    //Note that your sc is your dt for now. This is to avoid over calculation.







	    int pt=100; //Number of points to plot, dt would determine the scale.

RiArray=new double[pt];
RmArray=new double[pt];
UArray=new double[pt];
XwArray=new double[pt];
XnArray=new double[pt];
PDIArray=new double[pt];



//set initial values i==0
I=Io;
M=Mo;


for (int i=0; i<pt; i++) {

//now get dI and dM
dI=-dt*ki*M*I;
I=I+dI;
if (I<0) {I=0;}
dM=-dt*(ki*M*I+kp*M*(Io-I));
M=M+dM;
if (M<0) {M=0;}


if (i==0) {I=Io;M=Mo;}

//U Cal methods
L=Mo/(Io*r)*(1-M/Mo)-(1-r)/r*(1-I/Io);
if (r==1) {u = L;} else {u = R*L;}
if (u<=0.0) {u=0;} 


//NumberAvg
if (r!=1 ) {
	double term1 = 1.0-r;
	double term2 = 1.0-Math.exp(-1.0*u/R);
	Xn = ((term1*term2)+(u*r/R))/term2;
    }
else {Xn = (M-Mo)/(I-Io);}
if (Xn<=0.0  | (Double.isNaN(Xn))) {Xn = 1;}

//Weight Avg
if (r<1) {
    double term1 = r*u/R;
    double term2 = 1.0-Math.exp(-1.0*u/R);
    double term3 = 1.0-r;
    Xw = ((((term1)*(1.0+term1))+(2.0*r*u)-  (((2.0*r-1))*term3*term2))/((term3*term2)+term1));
}

if (r>1) {
    double term1 = r*u/R;
    double term2 = 1.0-Math.exp(-1.0*u/R);
    double term3 = 1.0-r;
    Xw = (((term1)*(1.0+term1))-(2.0*r*u+(2.0*r-1)*term3*term2))/((term3*term2)+term1);
}

if (r==1) {Xw = 1 - (M-Mo)/Io;}
if (Xw<=0.0 | (Double.isNaN(Xw))) {Xw = 1;}

PDI = Xw/Xn;

RiArray[i]=round5(I/Io);
RmArray[i]=round5(M/Mo);
UArray[i]=round5(u);
XwArray[i]=round5(Xn);
XnArray[i]=round5(Xw);
PDIArray[i]=round5(PDI);
}


PrintOut_Ri=Arrays.toString(RiArray);
PrintOut_Rm=Arrays.toString(RmArray);
PrintOut_Rm=Arrays.toString(UArray);

	if (FileType==1) {//Txt Output




	File SpaceDelimFile = new java.io.File(FileName+".txt");
	FileOutputStream fos = new FileOutputStream(SpaceDelimFile, true);
	System.out.println("Io="+Io+" Mo="+Mo+" ki="+ki+" kp="+kp+" r="+r+" dt="+dt);
	System.out.println("t Ri Rm U Xn Xw PDI");
		for (int j=0; j<pt; j++){
			
			System.out.print(j*dt+" ");
			System.out.print(RiArray[j]+" ");
//			System.out.print(RmArray[j]+" ");
			System.out.println(UArray[j]+" ");
//			System.out.print(XnArray[j]+" ");
//			System.out.print(XwArray[j]+" ");
//			System.out.println(PDIArray[j]);
			
//			fos.write((i*dt+" ").getBytes());
//			fos.write((RiArray[i]+" ").getBytes());
//			fos.write((RmArray[i]+" ").getBytes());
//			fos.write((UArray[i]+" ").getBytes());
//			fos.write((XnArray[i]+" ").getBytes());
//			fos.write((XwArray[i]+" ").getBytes());
//			fos.write((PDIArray[i]+"").getBytes());

			

		}

		 
		
		} else if (FileType==2){
			//Matlab file
//			File outfile = new java.io.File(FileName+".m");
//			PrintStream pen = new java.io.PrintStream(outfile);
//			pen.println("x=linspace(0,1,100);");
//			pen.println("Ri="+PrintOut_Ri+";");
//			pen.println("Rm="+PrintOut_Rm+";");
//			pen.println("plot(x,Ri,x,Rm);h=legend('Ri', 'Rm');title("+"'"+FileName+": Io= "+Io+" Mo= "+Mo+" ki= "+ki+" kp= "+kp+"'"+")");
		}
}






//get String or simply enter from shell
public static String getStringFromShell(String prompt)
{
      try
      {
            System.out.print(prompt);
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
      }
      catch (IOException e)
      {
            e.printStackTrace();
      }
      return null ;
}
public static double getDoubleFromShell(String prompt)
{
      String line = "" ;
      double num = 0 ;
      while(line.equals(""))
      {
            line = getStringFromShell(prompt);
            try
            {
             num = Double.parseDouble(line);
                  //num = Integer.parseInt(line);
            }
            catch(NumberFormatException e)
            {
                  System.out.println("Error: Invalid number");
                  line = "" ;
            }
      }
      return num ;
}

public static int getIntFromShell(String prompt)
{
      String line = "" ;
      int num = 0 ;
      while(line.equals(""))
      {
            line = getStringFromShell(prompt);
            try
            {
             //num = Double.parseDouble(line);
             num = Integer.parseInt(line);
            }
            catch(NumberFormatException e)
            {
                  System.out.println("Error: Invalid number");
                  line = "" ;
            }
      }
      return num ;
}
public static double round5(double num) {
	 double result = num * 1E5;
	 result = Math.round(result);
	 result = result / 1E5;
	 return result;
	 }
}