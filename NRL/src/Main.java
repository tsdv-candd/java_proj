import java.io.IOException;
import java.util.Scanner;

/**
 * @author Mason
 *
 */
public class Main {
	/**
	 * @param argas
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void main(String[] argas) throws InterruptedException
	{
		int choices ;
		
		do {
			  clearScreen();
			  showMain();
			  System.out.println("Please enter your choice: ");
				Scanner in = new Scanner(System.in);
				choices = in.nextInt();
				switch( choices) {
					case 1: 
								System.out.println("Do the method 1\n");
							    Thread.sleep(5000);
								break;
					case 2: 
								System.out.println("Do the method 2\n");
								Thread.sleep(5000);
						 		break;
					case 3: 
							    System.out.println("Do the method 3\n");
							    Thread.sleep(5000);
								break;
					case 4: 
								System.out.println("Program is exiting...");
								Thread.sleep(5000);
	                            break;
	                default:
	                	        System.out.println("Please enter the choice between 1 and 4");
	                	        Thread.sleep(10000);
	                	        break;	                	        
				}
		} 
		while (choices != 4);
		clearScreen();
		System.exit(0);
				

	}
	
	public static void showMain(){
		System.out.println("------------------------Main menu------------------------\n");
		System.out.println("[1]. Display Match Schedule                          \n");
		System.out.println("[2]. Enter Round Results                              \n");
		System.out.println("[3]. Team Results                                      \n");
		System.out.println("[4]. Exit Program                                        \n");
		System.out.println("---------------------------------------------------------------\n");
	}
	
	public static void clearScreen()  {  
		for(int i = 0; i < 1000; i++) {			
			    System.out.println();			
			  }

	   }  
}
