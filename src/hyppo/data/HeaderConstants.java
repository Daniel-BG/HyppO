package hyppo.data;

import hyppo.io.headerio.enums.BandOrdering;
import hyppo.io.headerio.enums.ByteOrdering;
import hyppo.io.headerio.primitives.ArrayValueReaderWriter;
import hyppo.io.headerio.primitives.ByteValueReaderWriter;
import hyppo.io.headerio.primitives.EnumValueReaderWriter;
import hyppo.io.headerio.primitives.FloatValueReaderWriter;
import hyppo.io.headerio.primitives.IntegerValueReaderWriter;
import hyppo.io.headerio.primitives.StringValueReaderWriter;
import hyppo.io.headerio.primitives.ValueReaderWriter;

/**
 * Class storing various header constants<br>
 * Check http://harrisgeospatial.com/docs/ENVIHeaderFiles.html for more info
 * <br>
 * <br>
 * Do NOT change the order to ensure backwards compatibility. IF something is no
 * longer use, at most substitute for dummy enum
 * 
 * @author Daniel
 */
public class HeaderConstants {

	/** Time of picture in ISO-8601 format */
	public static final String HEADER_ACQ_TIME = "acquisition time";
	/** Array of names of image bands. Format: {B1name = ; B2name = ; ...} */
	public static final String HEADER_BAND_NAMES = "band names";
	/** Number of bands in the image */
	public static final String HEADER_BANDS = "bands";
	/**
	 * Bad band multiplier for each band. 0 for bad 1 for good typically {BB1 = ;
	 * BB2 = ; ...}
	 */
	public static final String HEADER_BBL = "bbl";
	/** Byte order of the data. 0 for lil endian 1 for big endian */
	public static final String HEADER_BYTE_ORDER = "byte order";
	/**
	 * Array of RGB integers referring to {@link #HEADER_CLASS_NAMES}. Size is 3 *
	 * {@link #HEADER_CLASSES}
	 */
	public static final String HEADER_CLASS_LOOKUP = "class lookup";
	/** Name of classes {@link #HEADER_CLASSES} */
	public static final String HEADER_CLASS_NAMES = "class names";
	/** Number of classes = ; including unclassified */
	public static final String HEADER_CLASSES = "classes";
	/** Percentage of cloud cover */
	public static final String HEADER_CLOUD_COVER = "cloud cover";
	/**
	 * Complex function to use for complex data. Values include Real = ; Imaginary =
	 * ; Power = ; Magnitude = ; and Phase
	 */
	public static final String HEADER_COMPLEX_FUNC = "complex function";
	/** Specifies coordinates. Information between braces */
	public static final String HEADER_COORD_SYS_STR = "coordinate system string";
	/** Gain values for each band in W/(m2 * µm * sr). */
	public static final String HEADER_DATA_GAIN_VALUES = "data gain values";
	/** Specifies pixels to be ignored for e.g. feature extraction */
	public static final String HEADER_DATA_IGNORE_VAL = "data ignore value";
	/** Offset values for each band */
	public static final String HEADER_DATA_OFFSET_VALUES = "data offset values";
	/** Array of reflectance gain values */
	public static final String HEADER_DATA_REF_GAIN_VALUES = "data reflectance gain values";
	/** Array of reflectance offset values */
	public static final String HEADER_DATA_REF_OFF_VALUES = "data reflectance offset values";
	/**
	 * The data type in the image. Options are <br>
	 * The type of data representation:<br>
	 * 1 = Byte: 8-bit unsigned integer<br>
	 * 2 = Integer: 16-bit signed integer<br>
	 * 3 = Long: 32-bit signed integer<br>
	 * 4 = Floating-point: 32-bit single-precision<br>
	 * 5 = float-precision: 64-bit float-precision floating-point<br>
	 * 6 = Complex: Real-imaginary pair of single-precision floating-point<br>
	 * 9 = float-precision complex: Real-imaginary pair of float precision
	 * floating-point<br>
	 * 12 = Unsigned integer: 16-bit<br>
	 * 13 = Unsigned long integer: 32-bit<br>
	 * 14 = 64-bit long integer (signed)<br>
	 * 15 = 64-bit unsigned long integer (unsigned)<br>
	 */
	public static final String HEADER_DATA_TYPE = "data type";
	/** Array of bands to load by default */
	public static final String HEADER_DEFAULT_BANDS = "default bands";
	/** Stretch to use when displaying the image. Various formats allowed */
	public static final String HEADER_DEFAULT_STRETCH = "default stretch";
	/**
	 * Index of the dem band. Only indicate if there is more than one band and it is
	 * not the first one = ; otherwise not needed as it defaults to Zero
	 */
	public static final String HEADER_DEM_BAND = "dem band";
	/** Path of the DEM band file associated */
	public static final String HEADER_DEM_FILE = "dem file";
	/** String describing the image */
	public static final String HEADER_DESCRIPTION = "description";
	/** String specifying file type */
	public static final String HEADER_FILE_TYPE = "file type";
	/**
	 * Set full-width-half-maximum values for each band if the image = ; units are
	 * the same as {@link #HEADER_WAVELENGTH_UNITS}
	 */
	public static final String HEADER_FWHM = "fwhm";
	/** List of up to 16 values indicating corner coordinates */
	public static final String HEADER_GEO_POINTS = "geo points";
	/** Number of bytes to skip to get from the header to the data */
	public static final String HEADER_OFFSET = "header offset";
	/** BSQ = ; BIP = ; BIL */
	public static final String HEADER_INTERLEAVE = "interleave";
	/** Number of lines per band */
	public static final String HEADER_LINES = "lines";
	/** Geographic information in an array */
	public static final String HEADER_MAP_INFO = "map info";
	/** Pixel size in meters {x = ; y} */
	public static final String HEADER_PIXEL_SIZE = "pixel size";
	/** Info about a projection */
	public static final String HEADER_PROJECTION_INFO = "projection info";
	/** Define a custom file reader */
	public static final String HEADER_READ_PROCEDURES = "read procedures";
	/** Reflectance scaling */
	public static final String HEADER_REFL_SCALE_FACTOR = "reflectance scale factor";
	/** Rational polinomial coefficient (geospatial information) */
	public static final String HEADER_RPC_INFO = "rpc info";
	/** Number of samples in the image per line per band */
	public static final String HEADER_SAMPLES = "samples";
	/** String with security info */
	public static final String HEADER_SECURITY_TAG = "security tag";
	/** Sensor type */
	public static final String HEADER_SENSOR_TYPE = "sensor type";
	/** Solar irradiance per band */
	public static final String HEADER_SOLAR_IRRADIANCE = "solar irradiance";
	/** List of spectra names */
	public static final String HEADER_SPECTRA_NAMES = "spectra names";
	/** Degrees of azimuth */
	public static final String HEADER_SUN_AZIMUTH = "sun azimuth";
	/** Degrees of elevation */
	public static final String HEADER_SUN_ELEVATION = "sun elevation";
	/** Wavelengths of each band */
	public static final String HEADER_WAVELENGTH = "wavelength";
	/**
	 * Wavelength units: Micrometers = ; um = ; Nanometers = ; nm = ; Millimeters =
	 * ; mm = ; Centimeters = ; cm = ; Meters = ; m = ; Wavenumber = ; Angstroms = ;
	 * GHz = ; MHz = ; Index = ; Unknown
	 */
	public static final String HEADER_WAVELENGTH_UNITS = "wavelength units";
	/** Coordinate for upper left subpixel */
	public static final String HEADER_X_START = "x start";
	/** Coordinate for upper left supixel */
	public static final String HEADER_Y_START = "y start";
	/** Number of pixels in x and y direction to average in z plot */
	public static final String HEADER_Z_PLOT_AVG = "z plot average";
	/** Min and max for z_plot */
	public static final String HEADER_Z_PLOT_RANGE = "z plot range";
	/** X and Y axis titles for z_plots */
	public static final String HEADER_Z_PLOT_TITLES = "z plot titles";

	/**
	 * @return the compressor decompressor for this header constant. a new one is
	 *         returned each time to ensure no overwriting
	 */
	public static ValueReaderWriter getValueComDec(String headerConstant) {
		switch (headerConstant) {
		/* Enums */
		case HEADER_INTERLEAVE:
			return new EnumValueReaderWriter<BandOrdering>(BandOrdering.class);
		case HEADER_BYTE_ORDER:
			return new EnumValueReaderWriter<ByteOrdering>(ByteOrdering.class);
		/* Integer values */
		case HEADER_BANDS:
		case HEADER_CLASSES:
		case HEADER_DEFAULT_BANDS:
		case HEADER_DEM_BAND:
		case HEADER_OFFSET:
		case HEADER_LINES:
		case HEADER_SAMPLES:
			return new IntegerValueReaderWriter();
		/* Float values */
		case HEADER_CLOUD_COVER:
		case HEADER_REFL_SCALE_FACTOR:
		case HEADER_SUN_AZIMUTH:
		case HEADER_SUN_ELEVATION:
		case HEADER_X_START:
		case HEADER_Y_START:
			return new FloatValueReaderWriter();
		/* Byte values */
		case HEADER_DATA_TYPE:
			return new ByteValueReaderWriter();
		/* Array of integers */
		case HEADER_DATA_IGNORE_VAL:
		case HEADER_Z_PLOT_AVG:
			return new ArrayValueReaderWriter(new IntegerValueReaderWriter());
		/* Array of floats */
		case HEADER_BBL:
		case HEADER_DATA_GAIN_VALUES:
		case HEADER_DATA_OFFSET_VALUES:
		case HEADER_DATA_REF_GAIN_VALUES:
		case HEADER_DATA_REF_OFF_VALUES:
		case HEADER_FWHM:
		case HEADER_GEO_POINTS:
		case HEADER_PIXEL_SIZE:
		case HEADER_WAVELENGTH:
		case HEADER_Z_PLOT_RANGE:
			return new ArrayValueReaderWriter(new FloatValueReaderWriter());
		/* Array of bytes */
		case HEADER_CLASS_LOOKUP:
			return new ArrayValueReaderWriter(new ByteValueReaderWriter());
		/* Array of strings */
		case HEADER_BAND_NAMES:
		case HEADER_CLASS_NAMES:
		case HEADER_SPECTRA_NAMES:
		case HEADER_Z_PLOT_TITLES:
			return new ArrayValueReaderWriter(new StringValueReaderWriter());
		/* Strings or other non easily parseable data */
		case HEADER_ACQ_TIME: // is a date, would need to be parsed
		case HEADER_COMPLEX_FUNC: // can be enum'd
		case HEADER_COORD_SYS_STR: // weird coordinate format
		case HEADER_DEFAULT_STRETCH:// can be enum'd
		case HEADER_DEM_FILE:
		case HEADER_DESCRIPTION:
		case HEADER_FILE_TYPE:
		case HEADER_MAP_INFO: // list of different types
		case HEADER_PROJECTION_INFO:
		case HEADER_READ_PROCEDURES:
		case HEADER_RPC_INFO:
		case HEADER_SECURITY_TAG:
		case HEADER_SENSOR_TYPE:
		case HEADER_SOLAR_IRRADIANCE:
		case HEADER_WAVELENGTH_UNITS:// can be enum'd
		default:
			return new StringValueReaderWriter();
		}

	}

	/**
	 * @return true if this data is essential to be able to read the image. <br>
	 *         e.g: Bands, lines and samples are essential, sensor type is not
	 */
	public static boolean isEssential(String headerConstant) {
		switch (headerConstant) {
		case HEADER_ACQ_TIME:
		case HEADER_BAND_NAMES:
		case HEADER_BBL:
		case HEADER_CLASSES:
		case HEADER_CLASS_LOOKUP:
		case HEADER_CLASS_NAMES:
		case HEADER_CLOUD_COVER:
		case HEADER_COMPLEX_FUNC:
		case HEADER_COORD_SYS_STR:
		case HEADER_DATA_GAIN_VALUES:
		case HEADER_DATA_IGNORE_VAL:
		case HEADER_DATA_OFFSET_VALUES:
		case HEADER_DATA_REF_GAIN_VALUES:
		case HEADER_DATA_REF_OFF_VALUES:
		case HEADER_DEFAULT_BANDS:
		case HEADER_DEFAULT_STRETCH:
		case HEADER_DEM_BAND:
		case HEADER_DEM_FILE:
		case HEADER_DESCRIPTION:
		case HEADER_FILE_TYPE:
		case HEADER_FWHM:
		case HEADER_GEO_POINTS:
		case HEADER_MAP_INFO:
		case HEADER_PIXEL_SIZE:
		case HEADER_PROJECTION_INFO:
		case HEADER_READ_PROCEDURES:
		case HEADER_REFL_SCALE_FACTOR:
		case HEADER_RPC_INFO:
		case HEADER_SECURITY_TAG:
		case HEADER_SENSOR_TYPE:
		case HEADER_SOLAR_IRRADIANCE:
		case HEADER_SPECTRA_NAMES:
		case HEADER_SUN_AZIMUTH:
		case HEADER_SUN_ELEVATION:
		case HEADER_WAVELENGTH:
		case HEADER_WAVELENGTH_UNITS:
		case HEADER_X_START:
		case HEADER_Y_START:
		case HEADER_Z_PLOT_AVG:
		case HEADER_Z_PLOT_RANGE:
		case HEADER_Z_PLOT_TITLES:
			return false;
		/**
		 * Even though some are only needed for the uncompressed version, we need them
		 * in the compressed one to be able to recover the original
		 */
		case HEADER_BANDS:
		case HEADER_BYTE_ORDER: // needed for uncompressed
		case HEADER_DATA_TYPE: // needed for uncompressed
		case HEADER_INTERLEAVE: // needed for uncompressed
		case HEADER_LINES:
		case HEADER_OFFSET: // needed for embedded headers
		case HEADER_SAMPLES:
			return true;
		default:
			return false;
		}
	}

}
