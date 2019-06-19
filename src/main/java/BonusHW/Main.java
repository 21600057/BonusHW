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
	boolean help, reverse=false, upperCase = false, hiddenck = false;
	static ArrayList<String> arr = new ArrayList<String>();

	public static void main(String[] args)
	{
		CurDir = System.getProperty("user.dir");
	    System.out.println("현재 디렉토리는 " + CurDir + " 입니다");
	    
	    File file = new File(CurDir);
	    File[] fileList = file.listFiles();
        length = fileList.length;
        
	    for (File tFile : fileList)
	     arr.add(tFile.getName());
	    
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
	
	public ArrayList<String> getArr(ArrayList<String> value)
	{
		
	}
}