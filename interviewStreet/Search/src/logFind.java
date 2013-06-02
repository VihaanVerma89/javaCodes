import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public class logFind
{
	private String filename;
	public double fileSize;
	private int num404 = 0;
	private int numReq = 0;
	
	
	
	public void setFileName(String filename)
	{
		this.filename = filename;
	}
	
	private Date convertToDate(String unixTimeStamp)
	{
		Date date = new Date(Long.valueOf(unixTimeStamp) * 1000);
		String gmtDate  = displayGMT(date);
		return date;
	}
	
	private String convertToGMTDate(String unixTimeStamp)
	{
		Date date = new Date(Long.valueOf(unixTimeStamp) * 1000);
		String gmtDate  = displayGMT(date);
		return gmtDate;
	}
	
	private String displayGMT(Date date)
	{
		DateFormat gmtFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		gmtFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		return gmtFormat.format(date);
	}
	
	private Date lineToDate(String line, boolean timeZone)
	{
		Date date = new Date();
		DateFormat sdf;
		try
		{
			sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			date = sdf.parse(line);
			String result = displayGMT(date);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return date;
	}
	
	private long getMid(long low, long high)
	{
		return (low+high)/2;
	}
	
	private String [] binarySearch(long low, long high, RandomAccessFile raf, Date startDate, Date endDate)
	{
		long mid, currentPointer;
		Date currentDate;
		String currentLine, result;
		
		List<Integer> responseTimes = new ArrayList<Integer>();
		
		 mid = (low+high)/2;		 
		 try
		 {
			 raf.seek(mid);
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }

		while(low < high)
		{
		 
			 try
		 {
			 byte b = raf.readByte();
			 long currentLocation = raf.getFilePointer();
			 
			 //if byte b is 10 then we are at the starting of a new line.
			 while(b != 10)
			 {
				 currentLocation -= 2;
				 raf.seek(currentLocation);
				 b = raf.readByte();
//				 System.out.println((char)b);
			 }
			 
			 //store a reference to the starting point of the line read as it moves to the next
			 //line.
			 currentPointer = raf.getFilePointer();
			 currentLine = raf.readLine();
			 currentDate = lineToDate(currentLine, false);
			 
			 // helpful while debugging as the toString() method on date shows time in IST format.
			 // but the one's in file are in GMT format.
			 
			 String start = displayGMT(startDate);
			 String end = displayGMT(endDate);
			 String current = displayGMT(currentDate);
			 
			 if((currentDate.after(startDate)|| currentDate.equals(startDate))&& 
					 (currentDate.before(endDate) || currentDate.equals(endDate)))
			 {
				 
				 //process all lines between low and mid.
				 responseTimes = processTillStart(currentPointer, startDate, currentDate);
				 
				 //mid line has been taken care by processTillStart so move the pointer to mid+1.
				 raf.readLine();
				 
				 //process all lines between mid+1 and high.
				 responseTimes.addAll(processTillEnd(raf.getFilePointer(),currentDate, endDate));
				 
				 //we are done reading.
				 high = -1;
			 }
			 else if( currentDate.after(endDate))
			 {
				high = raf.getFilePointer(); 
				raf.seek(getMid(low, high));
			 }
			 else if( currentDate.before(startDate))
			 {
				 low = raf.getFilePointer();
				 raf.seek(getMid(low, high));
			 }
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		}
		
		return calculateValues(responseTimes);
	}
	
	private String [] calculateValues(List<Integer> list)
	{
		long sum = 0;
		String [] result = new String [3];
		
		for(int i = 0 ; i < list.size() ; i ++)
		{
			sum+=list.get(i);
		}
		
		result[0] = String.valueOf(numReq);
		result[1] = String.valueOf(num404);
		result[2] = String.valueOf((float)sum/list.size());
		
		return result;
	}
	
	private List<Integer> processTillStart(long lineStart, Date startDate , Date currentDate)
	{
		String line, parts [];
		List<Integer> responseTime = new ArrayList<Integer>();
		RandomAccessFile raf;
		
		while(!currentDate.before(startDate))
		{
			try
			{
				String current = displayGMT(currentDate);
				String start = displayGMT(startDate);
				
				raf = new RandomAccessFile(filename, "rw");
				raf.seek(lineStart);
				
				line = raf.readLine();
				numReq++;
				parts = line.split(" ");
				
				if(Integer.parseInt(parts[5]) == 404)
				{
					num404++;
					responseTime.add(Integer.parseInt(parts[6]));
				}
				
				
				//Get the starting point of the previous line.
				lineStart = getPreviousLineStart(lineStart , raf);
						
				
				//dont point to the new line character instead point to the first Character.
				lineStart++; 
		
				raf.seek(lineStart);
				
				//update the currenDate for the comparison.
				currentDate = lineToDate(raf.readLine(), false);
				
				//not required but convenient while debugging.
				String temp  = displayGMT(currentDate);
				
				raf.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
			return responseTime;
	}
	
	private long getPreviousLineStart(long lineStart, RandomAccessFile raf) throws IOException
	{
		// Goto the end of previous line
		lineStart -=2 ;
		raf.seek(lineStart);
		byte b = raf.readByte();
		
		while( b != 10 )
		{
			lineStart --;
			raf.seek(lineStart);
			b = raf.readByte();
//			System.out.println((char)b);
		}
		
		return lineStart;
	}
	
	private List<Integer> processTillEnd(long currentPosition, Date currentDate, Date endDate)
	{
		String line;
		String [] parts;
		List<Integer> responseTime = new ArrayList<Integer>();
		RandomAccessFile  raf;
		while(!currentDate.after(endDate))
		{
			try 
			{
				raf = new RandomAccessFile(filename, "rw");
				raf.seek(currentPosition);
				line =  raf.readLine();
				currentPosition = raf.getFilePointer();
				currentDate = lineToDate(raf.readLine(), false);
				
				parts = line.split(" ");
				
				if(Integer.parseInt(parts[5]) == 404)
				{
					num404++;
				}
				
				responseTime.add(Integer.parseInt(parts[6]));
				raf.close();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		return responseTime;
	}
		
	public String [] compute(String filename, String start_time, String end_time)
	{
		Date startDate, endDate;
		String  [] results = new String [3];
		startDate = convertToDate(start_time);
		endDate = convertToDate(end_time);
		
		File f = new File(filename);
		
		if(f.exists())
		{
			try
			{
				RandomAccessFile raf = new RandomAccessFile(filename, "rw");
				fileSize = raf.length();
				raf.seek(raf.length());
				long low = 0;
				long high = raf.getFilePointer();
				
				results = binarySearch(low, high,raf, startDate, endDate);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("The file doesn't exist. The program will now exit.");
			System.exit(0);
		}
		
		return results;
	}
	
	
	public static double getFileSizeGB(double size)
	{
		 double BASE = 1024, KB = BASE, MB = KB*BASE, GB = MB*BASE;
		 
		 return ((float)size/GB);
	}
	
	public static void main(String [] args)
	{
		String [] results ;
		double fileSize;
		
		long startTime = System.currentTimeMillis();
		logFind lf = new logFind();
		
		if (args.length !=3 )
		{
			System.out.println("Usage: logFind <filename> <start_time> <end_time>");
		}
		else
		{
			lf.setFileName(args[0]);
			results = lf.compute(args[0], args[1], args[2]);
			
			
			System.out.println("================================Results================================");
			System.out.println("\nInformation of requests between " + lf.convertToGMTDate(args[1]) + " and " +lf.convertToGMTDate(args[2]));
			System.out.println("\n1. Total number of requests : " + results[0]);
			System.out.println("2. Number of 404 requests : " + results[1]);
			System.out.println("3. Average response for 404 requests : " + results[2]);
			
			
			fileSize = getFileSizeGB(lf.fileSize);
			long endTime = System.currentTimeMillis();
			System.out.println("\nResults produced in " + String.valueOf((float)(endTime - startTime)/1000) + " secs on " + args[0] 
					+ " having file size : "+ fileSize + " GB.");
			
		}
	}
}
