/*
Given an input FILE consisting of numbers separated by spaces, write a program which displays
the frequency distribution for the given input. 
 */
public class frequency 
{
    public static void main(String [] args)
    {
        Map<Integer, Integer> numberMap  = new TreeMap<Integer, Integer>();
        
        Scanner scanner;
        try 
        {
            scanner = new Scanner(new File("numbers"));
            while(scanner.hasNextInt())
            {
                int n = scanner.nextInt();
                if(numberMap.containsKey(n))
                {
                    int value = (int) numberMap.get(n);
                    numberMap.put(n, ++value);
                }
                else
                {
                    numberMap.put(n, new Integer(1));
                }
            }
            
            for(Map.Entry<Integer,Integer> entry : numberMap.entrySet())
            {
                System.out.println(entry.getKey() + " appeared " + entry.getValue()+ " times.");
            }
    
        } catch (Exception e) {
            // TODO: handle exception
        }
 
    }
}
