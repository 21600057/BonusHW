package BonusHW;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import java.io.File;
import java.util.ArrayList;

public class Main
{
	static String CurDir;
	static int length;
	static ArrayList<String> arr = new ArrayList<String>();
	static ArrayList<String> revarr = new ArrayList<String>();
	boolean help;
	boolean reverse=false;
	boolean upperCase = false;
	boolean hiddenck = false;
	
	
	public static void main(String[] args)
	{
		CurDir = System.getProperty("user.dir");
	    
	    File file = new File(CurDir);
	    File[] fileList = file.listFiles();
	    //length = fileList.length; // 전역변수에 length 길이 들어감.
		        
        
	    for (File tFile : fileList)
	    {
	    	if (tFile.isHidden() == true)
	    		continue;
	    	else
	    	 arr.add(tFile.getName()); // arr에 저장다함.
	    }
	    length = arr.size();
	    
		Main Bonus = new Main();
		Bonus.run(args);
	}
	
	public void run(String[] args)
	{			
			Options options = createOptions();
			
			if(parseOptions(options, args))
			{
				if (help)
				{
					printHelp(options);
					return;
				}
				
				if (upperCase == false)
				{
					if (reverse == true)
					{
						ReverseArr(arr);
						out(revarr);
						System.exit(0);
					}
					
					if (reverse == false)
					 out(arr);
					 System.exit(0);					
				}


				if (upperCase == true)
				{
					if (reverse == false)
					{
						optionFarr(arr,false);
						System.exit(0);
					}
					
					if (reverse == true)
					{
						optionFarr(revarr,true);
						System.exit(0);
					}					
				}
				
				if (hiddenck == true)
				{
					ignoreHide();
				}
			}
	}
	
	public boolean parseOptions(Options options, String[] args) 
	{
		CommandLineParser parser = new DefaultParser();

		try 
		{
			CommandLine cmd = parser.parse(options, args);

			hiddenck = cmd.hasOption("a");
			help = cmd.hasOption("h");
			reverse = cmd.hasOption("r");
			upperCase = cmd.hasOption("F");

		} catch (Exception e) 
		
		{
			printHelp(options);
			return false;
		}

		return true;
	}
	
	private Options createOptions() 
	{
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("a").longOpt("allPrint")
				.desc("Set No Hiding to display")
				.argName("No Hiding to display")
				.build());
		
		options.addOption(Option.builder("F").longOpt("justDir")
				.desc("Show Just Directory")
				.argName("Just Directory Name to display")
				.build());
	
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Help")
		        .build());
		
		options.addOption(Option.builder("r").longOpt("reverse")
		        .desc("reverse Print")
				.argName("reverse name to display")
		        .build());

		return options;
	}
	
	private void printHelp(Options options) 
	{
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "BonusHW program!!";
		String footer ="\nBonusHW program!!";
		formatter.printHelp("BonusHW", header, options, footer, true);
	}
		
	public ArrayList<String> ReverseArr(ArrayList<String> array)
	{
		ArrayList<String> reverseArr = new ArrayList<String>();
		
		for (int i=length-1; i>=0; i--)
		{
			revarr.add(array.get(i));
			reverseArr.add(array.get(i));
		}
		
		return reverseArr;
	}
	
	public void out(ArrayList<String> outarr)
	{
		for (int i=0; i<length; i++)
		 System.out.println(outarr.get(i));
	}
	
	public ArrayList<String> optionFarr(ArrayList<String> oarr, boolean checker)
	{
		ArrayList<String> st = new ArrayList<String>();
		
	    File file = new File(CurDir);
		File[] fileList = file.listFiles();
		
		if (checker == false)
		{
			for(File tFile : fileList)
		    {
				if(tFile.isDirectory()) // 디렉토리라면 
				 st.add(tFile.getName() + "/");
				else // 파일이라면 
				{
					if (tFile.canExecute() == true) // 실행가능한 파일이면 *
					 st.add(tFile.getName() + "*");
					else
					 st.add(tFile.getName());
				}
		    }				
			for (int i=0; i<length; i++)
				 System.out.println(st.get(i));
		
		}
		
		if (checker == true)
		{
			for(File tFile : fileList)
		    {
				if(tFile.isDirectory()) // 디렉토리라면 
				 st.add(tFile.getName() + "/");
				else // 파일이라면 
				{
					if (tFile.canExecute() == true) // 실행가능한 파일이면 *
					 st.add(tFile.getName() + "*");
					else
					 st.add(tFile.getName());
				}
		    }
			
			for (int i=length-1; i>=0; i--)
			 System.out.println(st.get(i));
		}
		
	return st;
	
	}
	
	public void ignoreHide()
	{
	    File file = new File(CurDir);
		File[] fileList = file.listFiles();
		
		for(File tFile : fileList)
		{
			if ((tFile.isHidden() == true) || (tFile.isHidden() == false))
			 System.out.println(tFile.getName());			
		}
	}

}
