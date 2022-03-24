package hyppo.front;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

import hyppo.cli.HyppoCLI;
import hyppo.data.HyperspectralImage;
import javelin.debug.Logger;
import hyppo.io.HyperspectralImageReader;
import hyppo.io.HyperspectralImageWriter;

public class Hyppo {

	public static void main(String[] args) {
		// logger init
		Logger.getLogger().setBaseLine();
		// Create cmd parser
		CommandLineParser parser = new DefaultParser();
		try {
			// parse the command line arguments
			CommandLine line = parser.parse(HyppoCLI.getOptions(), args);
			Logger.getLogger().setLogging(line.hasOption(HyppoCLI.OPTION_VERBOSE));
			Logger.getLogger().log("Executing args: " + String.join(" ", args));
			// go through options
			if (line.hasOption(HyppoCLI.OPTION_HELP)) {
				printHelp();
			} else if (line.hasOption(HyppoCLI.OPTION_INPUT)) {
				//input image
				HyperspectralImage hi;
				if (line.hasOption(HyppoCLI.OPTION_INPUT_HEADER)) {
					hi = readImage(line.getOptionValue(HyppoCLI.OPTION_INPUT), line.getOptionValue(HyppoCLI.OPTION_INPUT_HEADER));
				} else {
					hi = readImage(line.getOptionValue(HyppoCLI.OPTION_INPUT));
				}
				//show info if requested
				if (line.hasOption(HyppoCLI.OPTION_IMAGE_INFO)) {
					System.out.println(hi.getInfo());
				}
				//output if requested
				if (line.hasOption(HyppoCLI.OPTION_OUTPUT)) {
					//output image
					if (line.hasOption(HyppoCLI.OPTION_NO_HEADER_OUTPUT)) {
						writeImage(hi, line.getOptionValue(HyppoCLI.OPTION_OUTPUT), null, line.hasOption(HyppoCLI.OPTION_OUTPUT_ESSENTIAL_HEADER));
					} else {
						if (line.hasOption(HyppoCLI.OPTION_OUTPUT_HEADER))
							writeImage(hi, line.getOptionValue(HyppoCLI.OPTION_OUTPUT), line.getOptionValue(HyppoCLI.OPTION_OUTPUT_HEADER), line.hasOption(HyppoCLI.OPTION_OUTPUT_ESSENTIAL_HEADER));
						else 
							writeImage(hi, line.getOptionValue(HyppoCLI.OPTION_OUTPUT), line.hasOption(HyppoCLI.OPTION_OUTPUT_ESSENTIAL_HEADER));
					}
				}
			} else {
				throw new ParseException("Missing options -c -d, i don't know what to do");
			}
		} catch (ParseException exp) {
			System.err.println("Parsing failed.  Reason: " + exp.getMessage());
			printHelp();
		} catch (IOException exp) {
			System.err.println("IO failed.  Reason: " + exp.getMessage());
		}

	}
	
	/** Just wrappers */
	public static HyperspectralImage readImage(String path) throws IOException {
		return HyperspectralImageReader.read(path, true);
	}
	public static HyperspectralImage readImage(String path, String headerPath) throws IOException {
		return HyperspectralImageReader.read(path, headerPath, true);
	}
	public static void writeImage(HyperspectralImage hi, String path, boolean essentialHeader) throws IOException {
		HyperspectralImageWriter.write(hi, path, essentialHeader);
	}
	public static void writeImage(HyperspectralImage hi, String path, String headerPath, boolean essentialHeader) throws IOException {
		HyperspectralImageWriter.write(hi, path, headerPath, essentialHeader);
	}
	
	private static void printHelp() { 
		HyppoCLI.printHelp(); 
	}

}

/*
 * 
 * 
 * public static void main(String[] args) { //static initializations
 * Logger.getLogger().setBaseLine(); //create the parser CommandLineParser
 * parser = new DefaultParser(); try { //parse the command line arguments
 * CommandLine line = parser.parse( JypecCLI.getOptions(), args );
 * InputArguments iArgs = InputArguments.parseFrom(line);
 * Logger.getLogger().setLogging(iArgs.verbose);
 * Logger.getLogger().log("Executing args: " + String.join(" ", args)); //go
 * through options if (iArgs.help) { printHelp(); } else if (iArgs.compress) {
 * Jypec.compress(iArgs); } else if (iArgs.decompress) {
 * Jypec.decompress(iArgs); } else if (iArgs.compare) { Jypec.compare(iArgs); }
 * else if (iArgs.analyze) { Jypec.analyze(iArgs); } else { throw new
 * ParseException("Missing options -c -d, i don't know what to do"); } } catch(
 * ParseException exp ) { System.err.println( "Parsing failed.  Reason: " +
 * exp.getMessage() ); printHelp(); } catch( IOException ioe) {
 * System.err.println( "Failed when reading/writing.  Reason: " +
 * ioe.getMessage() ); } catch (JypecException je) { System.err.println(
 * "Jypec raised an exception.  Reason: " + je.getMessage() ); } }
 * 
 * 
 * private static void printHelp() { JypecCLI.printHelp(); }
 */