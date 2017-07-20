package speech.engine.pk.core;

public class PkFrontStructFeatureInfo {
	
	// PLP特征地址
	public byte[] plp_data;
	
	// PLP特征大小，以字节为单位
	public int plp_data_size;
	
	// FB特征地址地址
	public byte[] fb_data;
	
	// FB特征大小，以字节为单位
	public int fb_data_size;
	
	// PNCC特征地址地址
	public byte[] pncc_data;
	
	// PNCC特征大小，以字节为单位
	public int 	pncc_data_size;
	
	public PkFrontStructFeatureInfo(){
	}

}
