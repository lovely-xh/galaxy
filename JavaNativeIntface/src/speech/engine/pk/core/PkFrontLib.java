package speech.engine.pk.core;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class PkFrontLib {

	public static String LIB_HOME = "ENGINE_PK_HOME";
		                                 																									
	static {
		System.out.println(System.getProperty("java.library.path"));
		
		System.load("C:/Users/admin/Desktop/20170508/bin_x64/PkFront.dll");
	}
	
	/**
	 * 进程级全局初始化函数，初始化相关资源
	 * @return
	 */
	public native int pkFrontInitialize();
	
	/**
	 * 逆初始化，释放全局资源 
	 * @return
	 */
	public native int pkFrontUninitialize();
	
	/**
	 * create instance 创建一个语音预处理实例
	 * 
	 * @param handle
	 *            [out] one element contains the instance handle
	 */
	public native int pkFrontCreateInst(long[] handle);
	
	/**
	 * Destory instance 销毁一个处理实例，该实例由CreateInst生成
	 * 					
	 * @param handle
	 * 			  [in] one element contains the instance handle
	 * @return
	 */
	public native int pkFrontDestroyInst(long handle);
			
	/**
	 * get instance parameters 获取当前实例参数
	 * @param handle
	 *            [in] instance handle
	 * @param param
	 *            [in] parameter name
	 * @param value
	 *            [out] one element contains the parameter value
	 * @return error code
	 */
	public native int pkFrontGetParam(long handle, String param, String[] value);
	
	/**
	 * set instance parameters 设置当前实例参数
	 * @param handle
	 * 			  [in] instance handle
	 * @param param
	 *            [in] parameter name
	 * @param value
	 *            [in] one element contains the parameter value
	 * @return
	 */	 
	public native int pkFrontSetParam(long handle, String param, String value);
	
	/**
	 * write audio data 调用该接口将语音数据写入引擎，为后续的计算做准备
	 * 
	 * @param handle
	 * 
	 * @param audioType
	 *    	      [in] PCM_8K_16Bit_ = 0, ALAW_8K_8Bit_ = 1, ULAW_8K_8Bit_ = 2,
	 *                 PCM_16K_16Bit_ = 3, ALAW_16K_8Bit_ = 4, ULAW_16K_8Bit_ = 5  
 	 * @param data
 	 *            [in] 指向音频数据的缓冲区指针
	 * @param status 
	 *            [in/out] 标志数据状态      
	 *                PKFRONT_AUDIO_BEGIN    = 0, indicate data input begin 
	 *                PKFRONT_AUDIO_END      = 1, the end of input data 
	 *                PKFRONT_AUDIO_CONTINUE = 2, input data is continuing
	 *                PKFRONT_AUDIO_FULL     = 3, data of special type is full
	 * @return
	 */	
	public native int pkFrontAudioWrite(long handle, int audioType, byte[] data, int[] status);

	/**
	 * 获取语音去噪后、分离后段标记结果，以XML格式输出
	 * 	
	 * @param handle 
	 *            [in] 由PkFrontCreateInst创建的实例句柄
	 * @param ppdata 
	 *            [out] 返回结果，结果采用XML格式的字符串
	 * @return
	 */
	public native int pkFrontGetXmlResult(long handle, byte[][] ppdata);
		
	/**
	 * 获取对应目标人的结构体特征，主要包括PLP特征、FB特征以及PNCC特征
	 * 	
	 * @param handle 
	 *            [in] 由PkFrontCreateInst创建的实例句柄
	 * @param data_index 
	 *            [in] 根据XML结果的信息，输入对应的索引ID号
	 * @param struct_fea_data 
	 *            [out] 输出结构体特征
	 * @return
	 */
	public native int pkFrontGetStructFeature(long handle, int data_index, PkFrontStructFeatureInfo[]struct_fea_data);

	/**
	 * 获取对应目标人的语音，如果cluseter_idx 为-1，表示输出全部人的语音
	 * 	
	 * @param handle
	 *            [in] 由PkFrontCreateInst创建的实例句柄
	 * @param data_index
	 *            [in] 根据XML结果的信息，输入对应的索引ID号
	 * @param ppdata
	 *            [out] 输出的语音数据
	 * @return
	 */
	public native int pkFrontGetAudioData(long handle, int data_index ,byte[][]ppdata);
		
	/**
	 * GetSdcData for LID System(暂未开发完成)
	 * 
	 * @param handle
	 *            [in] 由PkFrontCreateInst创建的实例句柄
	 * @param data_index
	 *            [in] 根据XML结果的信息，输入对应的索引ID号
	 * @param ppdata
	 *            [out] 输出的Sdc特征数据
	 * @return
	 */
	public native int pkFrontGetSdcData(long handle, int data_index ,byte[][]ppdata);
	
	/**
	 * GetDifFbData for BN-LID System 	
	 * 
	 * @param handle
	 * @param data_index
	 * @param ppdata
	 * @return
	 */
	public native int pkFrontGetDifFBData(long handle, int data_index ,byte[][]ppdata);
	
	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		PkFrontLib pkfront = new PkFrontLib();
		System.out.println("NowProcess PkFront\n");
		
		int ret = pkfront.pkFrontInitialize();
		if(ret != 0)
		{
			System.out.println(ret);
			return ;
		}
		
		long[] handles = { 0 };
		ret = pkfront.pkFrontCreateInst(handles);
		if(ret != 0)
		{
			System.out.println(ret);
			return ;
		}		
		long handle = handles[0];		
		ret = pkfront.pkFrontSetParam(handle, "use_spk_sep", "true");
		if(ret != 0)
		{
			System.out.println(ret);
			return ;
		}
		
		//Input WAV Dir
		String fileDir = "/data02/llgu/code/TestData/chin_tmp";
		//OutputFeaDir
		String fileOutDir = "/data02/llgu/code/Jni_Project/Jni/source/PKFrontJni/bin/FeaOut/";		
		
		for (File fileName : new File(fileDir).listFiles()) 
		{
			byte[] audioBuffer = FileUtils.readFileToByteArray(fileName);
			
			int[] status = {0};
			
			ret = pkfront.pkFrontAudioWrite(handle, 0, audioBuffer, status);
			
			status[0] = 1; // end the audio
			
			ret = pkfront.pkFrontAudioWrite(handle, 0, new byte[0], status);
			
			byte[][] ppdata = new byte[1][];
			
			ret = pkfront.pkFrontGetXmlResult(handle, ppdata);
			if(ret != 0)
			{				
				continue;				
			}
			FileUtils.writeByteArrayToFile(new File(fileOutDir+fileName.getName() + ".xml"), ppdata[0]);
			//FileUtils.writeByteArrayToFile(new File(fileName.getPath() + ".xml"), ppdata[0]);
			
			//System.out.println(status[0]);
			String pp_string = new String(ppdata[0]);
			
			int spk_num = 0;
			String regEx = "<speaker_num>(\\d+)</speaker_num>";
			Pattern pat = Pattern.compile(regEx);
			
			Matcher mat = pat.matcher(pp_string);
			boolean rs = mat.find();
			int len1 = mat.groupCount();
			for(int i =1 ; i<=len1;i++)
			{
				System.out.print(mat.group(i));				
				spk_num = Integer.parseInt(mat.group(i));
			}
			
			PkFrontStructFeatureInfo[] struct_fea_info = new PkFrontStructFeatureInfo[1];
			
			for(int i= 0; i < spk_num ;i++)
			{
				ret= pkfront.pkFrontGetStructFeature(handle, i,struct_fea_info);
				if(ret != 0)
				{
					continue;
				}			
				
				//System.out.print(ret);
				
				String tmp_string = new String();
				tmp_string = "##" +String.valueOf(i); 	
				if(struct_fea_info[0].plp_data_size > 0)
				{
					FileUtils.writeByteArrayToFile(new File(fileOutDir + fileName.getName() + tmp_string +".plp"), struct_fea_info[0].plp_data);
				}
				if(struct_fea_info[0].fb_data_size > 0)
				{
					FileUtils.writeByteArrayToFile(new File(fileOutDir + fileName.getName() + tmp_string +".fb"), struct_fea_info[0].fb_data);
					//FileUtils.writeByteArrayToFile(new File(             fileName.getPath() + tmp_string +".fb"), struct_fea_info[0].fb_data);				
				}
				if(struct_fea_info[0].pncc_data_size > 0)
				{	
					FileUtils.writeByteArrayToFile(new File(fileOutDir + fileName.getName() + tmp_string +".pncc"), struct_fea_info[0].pncc_data);
					//FileUtils.writeByteArrayToFile(new File(fileName.getPath() + tmp_string +".pncc"), struct_fea_info[0].pncc_data);		
				}				
				byte[][] ppaudio_data = new byte[1][];
				
				ret = pkfront.pkFrontGetAudioData(handle, i, ppaudio_data);				
				if(ret != 0)
				{
					continue;
				}				
				//FileUtils.writeByteArrayToFile(new File(fileName.getPath() + tmp_string +".pcm_out"), ppaudio_data[0]);
				FileUtils.writeByteArrayToFile(new File(fileOutDir + fileName.getName() + tmp_string +".pcm_out"), ppaudio_data[0]);

				byte[][] ppsdc_data = new byte[1][];
				ret = pkfront.pkFrontGetSdcData(handle, i, ppsdc_data);				
				if(ret != 0)
				{
					continue;
				}				
				//FileUtils.writeByteArrayToFile(new File(fileName.getPath() + tmp_string +".sdc"), ppsdc_data[0]);				
				FileUtils.writeByteArrayToFile(new File(fileOutDir + fileName.getName() + tmp_string +".sdc"), ppsdc_data[0]);				
				
				byte[][] ppdif_fb_data = new byte[1][];
				
				ret = pkfront.pkFrontGetDifFBData(handle, i,ppdif_fb_data);
				if(ret != 0)
				{
					continue;
				}
				//FileUtils.writeByteArrayToFile(new File(fileName.getPath() + tmp_string +".sdc"), ppsdc_data[0]);				
				FileUtils.writeByteArrayToFile(new File(fileOutDir + fileName.getName() + tmp_string +".dif_fb"), ppdif_fb_data[0]);				
			
			}
		}		
		ret = pkfront.pkFrontDestroyInst(handle);
		if(ret != 0)
		{
			System.out.print(ret);
			return;
		}
		ret = pkfront.pkFrontInitialize();
		if(ret != 0)
		{
			System.out.print(ret);
			return;
		}		
		System.out.print("\n over \n");
				
	}
}
