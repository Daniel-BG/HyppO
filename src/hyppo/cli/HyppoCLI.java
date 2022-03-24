package hyppo.cli;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * Anything related to command line interface stuff goes here
 * @author Daniel
 */
public class HyppoCLI {
	/** Help option constant. Use for retrieving arguments and/or flags */
	public static final String OPTION_HELP = "help";
	/** Input option constant. Use for retrieving arguments and/or flags */
	public static final String OPTION_INPUT = "input";
	/** Input header option constant. Use for retrieving arguments and/or flags */
	public static final String OPTION_INPUT_HEADER = "input_header";
	/** Output option constant. Use for retrieving arguments and/or flags */
	public static final String OPTION_OUTPUT = "output";
	/** Output header option constant. Use for retrieving arguments and/or flags */
	public static final String OPTION_OUTPUT_HEADER = "output_header";
	/** Output header option constant. Use for retrieving arguments and/or flags */
	public static final String OPTION_OUTPUT_ESSENTIAL_HEADER = "essential_header";
	/** Flag to be verbose. Use for retrieving arguments and/or flags  */
	public static final String OPTION_VERBOSE = "verbose";
	/** Flag to not output the header. Use for retrieving arguments and/or flags  */
	public static final String OPTION_NO_HEADER_OUTPUT = "no_header_output";
	/** Flag to show image INFO **/
	public static final String OPTION_IMAGE_INFO = "image_info";
	
	/* Options for jypec */
	private static Options jypecOptions;
	/* Only one instance */
	static {
		/* flags */
		Option help				= new Option("h", OPTION_HELP, false, "print this message");
		Option verbose			= new Option("v", OPTION_VERBOSE, false, "be verbose when processing");
		Option essentialHeader 	= new Option(null, OPTION_OUTPUT_ESSENTIAL_HEADER, false, "output only essential information, cut all extra");
		Option imageInfo 		= new Option(null, OPTION_IMAGE_INFO, false, "output image information retrieved from header");
		
		/* input output files */
		Option input = Option
				.builder("i")
				.argName("file")
				.desc("input file")
				.hasArg()
				.longOpt(OPTION_INPUT)
				.required()
				.build();
		
		Option inputHeader = Option
				.builder()
				.argName("file")
				.desc("input file header location")
				.hasArg()
				.longOpt(OPTION_INPUT_HEADER)
				.build();
		
		Option output = 	Option
				.builder("o")
				.argName("file")
				.desc("output file")
				.hasArg()
				.longOpt(OPTION_OUTPUT)
				.build();
		
		Option outputHeader = Option
				.builder()
				.argName("file")
				.desc("output file header location")
				.hasArg()
				.longOpt(OPTION_OUTPUT_HEADER)
				.build();
		
		
		
		jypecOptions = new Options();
		
		jypecOptions.addOption(output);
		jypecOptions.addOption(input);
		jypecOptions.addOption(help);
		jypecOptions.addOption(inputHeader);
		jypecOptions.addOption(outputHeader);
		jypecOptions.addOption(essentialHeader);
		jypecOptions.addOption(verbose);
		jypecOptions.addOption(imageInfo);
		
	}
	
	
	/**
	 * testing
	 * @return the options for the jypec cli
	 */
	public static Options getOptions() {
		return jypecOptions;
	}

	/**
	 * Prints the help for the command line interface of jypec
	 */
	public static void printHelp() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( "hyppo", HyppoCLI.getOptions());
	}

}
