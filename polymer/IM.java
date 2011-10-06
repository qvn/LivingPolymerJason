import java.util.Arrays;
import java.io.*;
public class IM {
	
	
	static double I,M,dI=0,dM=0,Rm,Ri;
	static double[] RiArray,RmArray;
	static String PrintOut_i, PrintOut_Ri, PrintOut_Rm;
	

	
	//Let's do it!
	public static void main(String[] args) throws FileNotFoundException {
		double Io,Mo,ki,kp,sc,r,R,dt;
		//Get initial Values
		//Io,Mo,sc,ki,kp
	    Io = IM.getDoubleFromShell("Io: ");
	    Mo = IM.getDoubleFromShell("Mo: ");
	    ki = IM.getDoubleFromShell("ki: ");
	    kp = IM.getDoubleFromShell("kp: ");	   
	    String FileName = IM.getStringFromShell("File Name As: ");
		sc=0.0005;
		r=kp/ki;
		R=Math.abs(1-r);
		
		System.out.println("%"+"Io= "+Io+" Mo= "+Mo+" ki= "+ki+" kp= "+kp+"r= "+r);
		//Note that your sc is your dt for now. This is to avoid over calculation. 
		dt=sc;
		
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
	

	PrintOut_Ri=Arrays.toString(RiArray);
	PrintOut_Rm=Arrays.toString(RmArray);
	
	//System.out.println("x=linspace(0,1,100);");
	//System.out.println("Ri="+PrintOut_Ri+";");
	//System.out.println("Rm="+PrintOut_Rm+";");
	//System.out.println("plot(x,Ri,x,Rm)");
	//System.out.println("h=legend('Ri', 'Rm');");
	//System.out.println("title("+"'"+"Io= "+Io+" Mo= "+Mo+" ki= "+ki+" kp= "+kp+"'"+")");
	File outfile = new java.io.File(FileName+".m"); 
	PrintStream pen = new java.io.PrintStream(outfile); 
	pen.println("x=linspace(0,1,100);");
	pen.println("Ri="+PrintOut_Ri+";");
	pen.println("Rm="+PrintOut_Rm+";");
	pen.println("plot(x,Ri,x,Rm);h=legend('Ri', 'Rm');title("+"'"+"Io= "+Io+" Mo= "+Mo+" ki= "+ki+" kp= "+kp+"'"+")");
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
}

// similiar methods can be made for getting char , double etc








