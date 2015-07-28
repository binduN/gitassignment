package minion;




 
import java.util.ArrayList;
import java.util.List;
 
class NextBiggerPalindromeNumber {
 
	
	public static void main(String[] args) {
//		for(int i=1;i<10;i++){
//			System.out.println("i= "+i+" "+getMaxPalindromeOfLenthN(i));
//		}
		int num=0;
 
		num=120;
		System.out.println("num= "+num +" palindrome ="+ getNextHigherPalnidromeNumber(num));
 
		//num=1234;
		//System.out.println("num= "+num +" palindrome ="+ getNextHigherPalnidromeNumber(num));
 
		//num=191;
		//System.out.println("num= "+num +" palindrome ="+ getNextHigherPalnidromeNumber(num));
 
	}
	public static int getNextHigherPalnidromeNumber(int input){
		// Handle single digit numbers
		if(input==9) return 11;     // For 9 next palindrome is 11
 
		if(input<9) return input+1; // for 0 to 8 next palindrome is next number
 
		// So now number is two digits or more
		// Separate out digits 
		int temp = input;
		ArrayList<Integer> digitList = new ArrayList<Integer>();
		while(temp>0){
			digitList.add(temp%10);
			temp = temp /10;
		}
		// digitList(0) is digit at unit place
		// digitList(n) will be digit at highest place.
 
		// Now check if input is equal to max palindrome of that length
		// In that case next palindrome is min palindrome of lenght+1
		if(input==getMaxPalindromeOfLenthN(digitList.size())) 
			return getMinPalindromeOfLenthN(digitList.size()+1);
 
		// It is not max palindrome of that length. next palindrome is of same length as input.
		/* if input is 1356 then we will start with first & digit same as input 1 _ _ 1
		 *  Now we will call same function to get palindrome of internal number by striping first and last digit viz. 35. So function will return 44
		 *  So answer is 1 4 4 1
		 *  
		 *  Now if number is 1996 the we will start with 1 _ _ 1
		 *  Now we will call same function to get palindrome of internal number by striping first and last digit viz. 99. So function will return 101
		 *  So it returned palindrome of length more than 2; it means we should increase outer digit
		 *  2 _ _ 2
		 *  And we should fill it up with zeros so answer for 1996 is 2002
		 *  
		 */
// 
		// Strip first and last digit
		//  for number 7986  List is -> 6,8,9,7
		// So starting with digit at index n-2 till index 1; prepare number
		int outerdigit   =digitList.get(digitList.size()-1);
		System.out.println("outerdigit is "+outerdigit);
		// So 7 _ _ 7   is time being 7007.
		int returnVal = outerdigit*(int)Math.pow(10,digitList.size()-1) + outerdigit;
		System.out.println("returnVal is "+returnVal);
		temp = 0;
		for(int i=digitList.size()-2;i>=1;i--){
			temp = temp*10 + digitList.get(i);
		}
		
		boolean a=false;
		
		if(temp==0)
		{
			a=true;
		}
		
		
		
		
		
		
		System.out.println("temp is "+temp);
		int palindromeForInnerNumber= getNextHigherPalnidromeNumber(temp);
		System.out.println("innerpal is "+palindromeForInnerNumber);
 
		// for inner number 99 palindrome will be 101. In this case we should increase higher number and use all zeros
		// Inner number is of length digitList.size()-2. So palindrome of biggger length id digitList.size()-2+1
		// For input number 79998 inner number is 999. And its palindrome is 1001. 
		// Now outer number was decided as 7 and we had prepared temporary palindrome as 7_ _7. So we should make it 8_ _ 8 means 8008
		if(palindromeForInnerNumber==getMinPalindromeOfLenthN(digitList.size()-2+1)){  
			outerdigit++;
			returnVal = outerdigit*(int)Math.pow(10,digitList.size()-1)+ outerdigit;
		}else{
			
			if(a)
			//  For input 7865 palindrome is decided as 7_ _7 i.e. 7007. Inner number is 86. Its palindrome is 99.
			// Now 99 is to be fit into middle slot. So we will multiply it by 10 and add into number
			// 7007 + 99*10 = 7007 + 990= 7997
			returnVal= returnVal+ palindromeForInnerNumber*10 ;
		}
		return returnVal;
	}
 
	public static int getMinPalindromeOfLenthN(int n){
		/* For length min palindromes are as follows
		 * 	 1 1	
		 *   2 11
		 *   3 101
		 *   4 1001
		 *   5 10001
		 */
		// So 1 is present at  10^(n-1) and unit digit
		// so number is 10^(n-1) + 1
		if(n==1) 
			return 1;
 
		return (int)Math.pow(10, n-1) + 1;
	}
 
	public static int getMaxPalindromeOfLenthN(int n){
		/* For legth max palindromes are as follows
		 * 	 1 9	
		 *   2 99
		 *   3 999
		 *   4 9999
		 */
		int num =0;
		for(int i=1;i<=n;i++){
			num = num*10+9;
		}
		return num;
	}
 
}
 
