package com.vds.util;


public class Demo {
	
	public static void main(String[] args) {
		
		String str = "j.grda_hkdz_jwcmc like '%署前%' or j.grda_xzz_jwcmc like '%署前%' or  j.grda_hkdz_jwcmc like '%启明%' or j.grda_xzz_jwcmc like '%启明%' or j.grda_xzz_jwcmc like '%启明%'";
		
		String arry[] = str.split("j.grda_hkdz_jwcmc");
		for(int i = 0 ;i<arry.length;i++){
			String arry2[] = arry[i].split("'%");
			
			for(int j = 0 ;j<arry2.length;j++){
				if(arry2.length>1){
					if(arry2[j].split("%'").length>1){
						System.out.println(arry2[j].split("%'")[0]);
						break;
					}
					
				}
			}
			
		}
		
	}

}
