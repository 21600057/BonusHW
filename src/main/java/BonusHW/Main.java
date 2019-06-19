package BonusHW;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import java.io.File;

public class Main
{
	static String CurDir;
	static String path;
	boolean help, reverse=false;

	public static void main(String[] args)
	{
		CurDir = System.getProperty("user.dir");
	    System.out.println("현재 디렉토리는 " + CurDir + " 입니다");
	    
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
				
			    File file = new File(CurDir);
			    File[] fileList = file.listFiles();
	            int length = fileList.length;
	            
			    if (reverse == true)
				{
			    	if(file.isDirectory()) 
				    {
			    		for (int i=length-1; i>0; i--)
		            	 System.out.println(fileList[i].getName());
				    }
			    	else
			    	 System.out.println("It is not a directory.");
				}
			    if (reverse == false)
			    {
			    	if(file.isDirectory()) 
				    {
			    		for(File tFile : fileList) 
				         System.out.println(tFile.getName());
				    }
			    	else
			    	 System.out.println("It is not a directory.");
			    }
			}
	}
	
	public boolean parseOptions(Options options, String[] args) 
	{
		CommandLineParser parser = new DefaultParser();

		try 
		{
			CommandLine cmd = parser.parse(options, args);

			path = cmd.getOptionValue("p");
			help = cmd.hasOption("h");
			reverse = cmd.hasOption("r");

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
		options.addOption(Option.builder("p").longOpt("path")
				.desc("Set a path of a directory or a file to display")
				.hasArg()
				.argName("Path name to display")
				.build());
	
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Help")
		        .build());
		
		options.addOption(Option.builder("r").longOpt("reverse")
		        .desc("reverse Print")
				.argName("reverse name print")
		        .build());

		return options;
	}
	
	private void printHelp(Options options) 
	{/*
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "BonusHW program!!";
		String footer ="\nBonusHW program!!";
		formatter.printHelp("BonusHW", header, options, footer, true);*/
	}
}