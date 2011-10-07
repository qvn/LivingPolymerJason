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

System.out.println("%"+"Io= "+Io+" Mo= "+Mo+" ki= "+ki+" kp= "+kp+" r= "+r+" dt= "+dt);
//Note that your sc is your dt for now. This is to avoid over calculation.


RiArray=new double[100];
RmArray=new double[100];
UArray=new double[100];
XwArray=new double[100];
XnArray=new double[100];
PDIArray=new double[100];

int pt=100; //Number of points to plot, dt would determine the scale.

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

RiArray[i]=I/Io;
RmArray[i]=M/Mo;
UArray[i]=u;
XwArray[i]=Xn;
XnArray[i]=Xw;
PDIArray[i]=PDI;
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

}