package javaCampHomework5.core;

public class BusinessRules {
	public static boolean Run(boolean... logics) {
    	for (var logic : logics) 
    	{ 
    		if (!logic)
            {
                return logic;
            }
    	} 
        return true;
    
    }
}
