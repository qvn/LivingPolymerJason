//package polymer;
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.text.DecimalFormat;
//
//import polymer.*;
//
////import org.json.simple.JSONAware;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONArray;
//
//public class Start_Test {
//
//	/**
//	 * @param args
//	 * @throws IOException 
//	 */
//	
//
//	
//	public static Double round(double x) {
////		float result = (float)((int)(x*1000))/1000;
////		System.out.println(result);
//		
//    	DecimalFormat twoDForm = new DecimalFormat("#.###");
//		double result= Double.valueOf(twoDForm.format(x));
////		System.out.println(result);
//		
//		return result;
//	}
//	
//	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
//		Initiator initiator = new Initiator();
//		Monomer monomer = new Monomer();
//		NumberAverage Xn = new NumberAverage();
//		WeightAverage Xw = new WeightAverage();
//		PDI PDI = new PDI();
//		Nx Nx= new Nx();
//		
////		double[] RiArrays;
////		RiArrays=new double[100];
////		RiArrays[0]=initiator.getRi(0);
////		System.out.println(RiArrays);
//		
//		initiator.setRiArray();
//		monomer.setRmArray();
//		Xn.setXnArray();
//		Xw.setXwArray();
//		PDI.setPDI_Array();
//		
////		System.out.println("Ri= "+initiator.RiArray[2]);
////		System.out.println("Rm= "+monomer.RmArray[2]);
//		
//		
//		
//		class Output implements JSONAware{
//	        private int id;
//	        private double Ri;
//	        private double Rm;
//	        private double Xn;
//	        private double Xw;
//	        private double PDI;
//	        private JSONArray Nx;
//	        
//	        
//	        
//	        public Output(int id, double Ri, double Rm, double Xn, double Xw, double PDI, JSONArray Nx ){
//	                this.id = id;
//	                this.Ri = Ri;
//	                this.Rm = Rm;
//	                this.Xn = Xn;
//	                this.Xw = Xw;
//	                this.PDI = PDI;
//	                this.Nx = Nx;
//	        }
//	        
////	        public String toJSONString(){
////	                StringBuffer sb = new StringBuffer();
////	                
////	                sb.append("{");
////	                
////	                sb.append(JSONObject.escape("outputName"));
////	                sb.append(":");
////	                sb.append("\"" + JSONObject.escape(name) + "\"");
////	                
////	                sb.append(",");
////	                
////	                sb.append(JSONObject.escape("ID"));
////	                sb.append(":");
////	                sb.append(id);
////	                
////	                sb.append("}");
////	                
////	                return sb.toString();
////	        }
//	        
//	        public String toJSONString(){
//	            JSONObject obj = new JSONObject();
//	            obj.put("ID", new Integer(id));
//	            obj.put("Ri", Ri);
//	            obj.put("Rm", Rm);
//	            obj.put("Xn", Xn);
//	            obj.put("Xw", Xw);
//	            obj.put("PDI", PDI);
//	            obj.put("Nx",Nx);
//	            return obj.toString();
//	          }
//	}
//
//	  JSONArray outputs = new JSONArray();
//	  
//	  
//	  for (int i=1; i<=100 ; i++){ //last id is 100 but call last index in array as 99
//		  outputs.add(new Output(i, round(initiator.RiArray[i-1]), round(monomer.RmArray[i-1])
//				  , round(Xn.XnArray[i-1]), round(Xw.XwArray[i-1]), round(PDI.PDI_Array[i-1]), Nx.getNx_JSONArray(i-1)));
//	  
//	  
//	  }
//	  
//	  
////	  System.out.println(Nx.getNx_JSONArray(5).toString());
//	  
//	  String String_outputs = outputs.toString();
//
//	  
//	  
//	//Creating the json file http://www.daniweb.com/code/snippet217078.html
//		//Will replace current file.
//		FileWriter ryt=new FileWriter("Test_Gson_json.jsp");
//		BufferedWriter out=new BufferedWriter(ryt);
//		out.write(String_outputs);
//		out.close();
//	//end Creating File
////	  System.out.println(outputs);
//		}
//		
//		
//	}
//

