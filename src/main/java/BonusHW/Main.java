package BonusHW;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Main 
{
	String path;
	boolean help;

	public static void main(String[] args)
	{
		System.out.println("¡¯¿‘∫Œ");
		Main Bonus = new Main();
		Bonus.run(args);
	}
	
	public void run(String[] args)
	{
		
		System.out.println("§æ§æ");
		
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

			path = cmd.getOptionValue("p");
			help = cmd.hasOption("h");

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
				.required()
				.build());
	
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Help")
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
}