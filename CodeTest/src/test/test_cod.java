package test;

public class test_cod {
	
	public static void main(String[] args) {
		
		String promotion = "12,13";
		String[] indexs = null;
		String substitutionString = "";
		
		if (promotion.contains(",")) {
		
			indexs = promotion.split(",");
			
			System.out.println("indexs"+indexs[0]+indexs[1]);
		}
		if (indexs != null && indexs.length == 2) {
			
			System.out.println("indexs[0]"+indexs[0]);
			String yn_index = indexs[0];
			if (yn_index.equals("Y")) {
				System.out.println("indexs[0]"+indexs[0]);
			}
		}
		
	}

}
