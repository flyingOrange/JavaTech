package common;

public class TryCatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try{
			System.out.println("1");
			
			throw new Exception("Exception hahaha");
			
		}catch(Exception e){
			e.printStackTrace();
			
			System.out.println("2");
			return ;
			
		}finally{

			System.out.println("3");
		}
		
		
		
		
	}

}
