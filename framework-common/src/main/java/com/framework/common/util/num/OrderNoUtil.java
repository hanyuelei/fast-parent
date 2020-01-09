package com.framework.common.util.num;

import java.text.NumberFormat;

public class OrderNoUtil {

	public static void main(String[] args) throws InterruptedException {
		StringBuilder no=new StringBuilder();
//		String dateTime=Kalendar.getDateTime();
//		String t=(String) dateTime.replace("-", "").subSequence(4, 8);
//		no.append(t);//日期四位
		String unix=String.valueOf(System.currentTimeMillis()).substring(5, 13);
	    no.append(unix);

        int   n   =   1;   
        NumberFormat   formatter   =   NumberFormat.getNumberInstance(); 
        formatter.setMinimumIntegerDigits(4);   
        formatter.setGroupingUsed(false);   
        String   s   =   formatter.format(n);
        if(s.length()>4) {
        	s=s.substring(s.length()-4);
        }
    	no.append(s);
	    int ran=(int)((Math.random()*9+1)*1000);
	    no.append(String.valueOf(ran));
        System.out.println(no.toString());
	}
	public static String createOrderNo(String userId) {
		StringBuilder no=new StringBuilder();
		String unix=String.valueOf(System.currentTimeMillis()).substring(5, 13);
	    no.append(unix);
        int   n   =   Integer.valueOf(userId);   
        NumberFormat   formatter   =   NumberFormat.getNumberInstance(); 
        formatter.setMinimumIntegerDigits(4);   
        formatter.setGroupingUsed(false);   
        String   s   =   formatter.format(n);
        if(s.length()>4) {
        	s=s.substring(s.length()-4);
        }
    	no.append(s);
    	int ran=(int)((Math.random()*9+1)*1000);
   	    no.append(String.valueOf(ran));
		return no.toString();
	}
	
}
