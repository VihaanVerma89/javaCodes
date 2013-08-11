import java.util.Arrays;


public class Main
{

	public static void main(String [] args)
	{
		int a [] = { -7, 1, 5, 2, -4, 3, 0};
		int result = solution(a, a.length);
	}
	
	static int solution(int A[], int N)
	{
		int [] leftArray;
		int [] rightArray;
		
		int temp [][] = new int [N][2];
		for(int i =0 ; i < N; i++)
		{
			temp = getArray(i , A);
			
			if(getSum(temp[0]) == getSum(temp[1]))
			{
				return i;
			}
		}
		return -1;
	}
	
	static int getSum(int a[])
	{
		int sum=0;
		for ( int i : a)
		{
			sum+=i;
		}
		return sum;
	}
	
	static int [][] getArray(int i, int [] A)
	{
		int temp [][] = new int [A.length][2];
		
		temp[0] = Arrays.copyOfRange(A, 0, i);
		temp[0] = Arrays.copyOfRange(A, i+1, A.length);
		
		return temp;
	}
}
