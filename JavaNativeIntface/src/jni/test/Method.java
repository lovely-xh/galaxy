package jni.test;

import java.io.File;

import org.apache.commons.lang.StringUtils;

public class Method {
	
	static {
		System.out.println(System.getProperty("java.library.path"));
		
		System.load("C:/Users/admin/Desktop/20170508_版本_nodog/20170508_版本_nodog/bin_x64/vspp.dll");
		
//		String path = System.getenv("DLL_HOME");
//
//		File libfile = new File(path);//, "\\JavaNativeInterface");
//		System.out.println(String.format("fullPath:\t%s", libfile.toString()));
//
//		System.loadLibrary("E:/workplaceMe/repository/JavaNativeIntface");
	}
	
	public native int add(int a, int b);
	
	public native int minus(int a, int b);
	
	public native int otherMethod(int a, int b, String method);
	
	public static void main(String[] args) {
		Method method = new Method();
		
		method.add(10, 20);
	}

}
