package front;

public class Test {
	
	static String input = "C:/Users/Daniel/Hiperspectral images/Gulf_Wetlands_Sample_Rad/Suwannee_0609-1331_rad.dat"; 
	static String inputHeader = "C:/Users/Daniel/Hiperspectral images/Gulf_Wetlands_Sample_Rad/Suwannee_0609-1331_rad.hdr";
	//static String input = "C:/Users/Daniel/Hiperspectral images/DeepHorizon_OilSpill/0612-1615_rad_sub.dat";
	//static String inputHeader = "C:/Users/Daniel/Hiperspectral images/DeepHorizon_OilSpill/0612-1615_rad_sub.hdr";
	//static String input = "C:/Users/Daniel/Hiperspectral images/Beltsville_Radiance_w_IGM/0810_2022_rad.dat";
	//static String inputHeader = "C:/Users/Daniel/Hiperspectral images/Beltsville_Radiance_w_IGM/0810_2022_rad.hdr";
	//static String input = "C:/Users/Daniel/Hiperspectral images/Reno_Radiance_wIGMGLT/0913-1248_rad.dat";
	//static String inputHeader = "C:/Users/Daniel/Hiperspectral images/Reno_Radiance_wIGMGLT/0913-1248_rad.hdr";
	//static String input = "C:/Users/Daniel/Hiperspectral images/Cuprite_Reflectance/f970619t01p02_r02_sc01.a.rfl";
	//static String inputHeader = "C:/Users/Daniel/Hiperspectral images/Cuprite_Reflectance/f970619t01p02_r02_sc01.a.hdr";
	//static String input =  "C:/Users/Daniel/Hiperspectral images/cupriteBSQ/Cuprite";
	//static String inputHeader = "C:/Users/Daniel/Hiperspectral images/cupriteBSQ/Cuprite.hdr";
	//static String input = "C:/Users/Daniel/Hiperspectral images/Low_Altitude/f960705t01p02_r02c_img";
	//static String inputHeader = "C:/Users/Daniel/Hiperspectral images/Low_Altitude/f960705t01p02_r02c_img.hdr";
	static String output = "C:/Users/Daniel/Basurero/hyppo_out.dat"; 
	static String outputHeader = "C:/Users/Daniel/Basurero/hyppo_out.hdr";
	
	public static void main (String[] args) {
		String [] testArgs = {
			"-i", input,
			"--input_header", inputHeader,
			"-o", output,
			"--output_header", outputHeader,
			"--image_info",
			"--verbose"
		};
		
		Hyppo.main(testArgs);
	}

}
