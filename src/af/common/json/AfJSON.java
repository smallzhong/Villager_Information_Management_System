package af.common.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.json.JSONArray;
import org.json.JSONObject;

public class AfJSON
{
	// json: 可以是JSONObject 或 JSONArray
	// encoding: "UTF-8", "GBK"
	public static void toFile(Object json, File file, String encoding) throws Exception
	{
		String jsonstr = null;
		if(json instanceof JSONObject)
			jsonstr = ((JSONObject)json).toString(2);
		else if(json instanceof JSONArray)
			jsonstr = ((JSONArray)json).toString(2);
		else 
			throw new Exception("Must be org.json.JSONObject or org.json.JSONArray");
		
		// 存入文件
		OutputStream outputStream = new FileOutputStream(file);
		try {
			// UTF-8写入BOM头部
			encoding=encoding.toUpperCase();
			if(encoding.equals("UTF-8"))
			{
				byte[] bom = { (byte)0xEF, (byte)0xBB,(byte) 0xBF };
				outputStream.write(bom);
			}
			
			byte[] data = jsonstr.getBytes(encoding);
			outputStream.write(data);
		}finally {
			// 确保文件被关闭
			try { outputStream.close();} catch(Exception e) {}
		}
	}
	
	public static Object fromFile(File file, String encoding) throws Exception
	{		
		
		InputStream inputStream = new FileInputStream(file);
		try {
			int fileSize = (int)file.length();
			byte[] data = new byte[fileSize];
			int n = inputStream.read(data);
			
			int offset = 0;
			encoding=encoding.toUpperCase();
			if(n > 3 && encoding.equals("UTF-8"))
			{
				if(data[0] == (byte)0xEF && data[1]==(byte)0xBB && data[2] == (byte)0xBF)
					offset = 3; // 前3个字节是BOM
			}
			
			String jsonstr = new String(data, offset, n-offset, encoding);
			
			// 找第一个非空白字符, 从而判断它是JSONObject 还是JSONArray
			char firstChar = ' ';
			for(int i=0; i<jsonstr.length(); i++)
			{
				firstChar = jsonstr.charAt(i);
				if(firstChar != ' ' && firstChar != '\t' && firstChar != '\n' && firstChar != '\r')
					break;
			}
			
			// 如果以{开头，则转成JSONObject; 如果以[开头，则转成 JSONArray
			if(firstChar == '{' )
			{
				return new JSONObject( jsonstr );
			}
			else if(firstChar == '[' )
			{
				return new JSONArray ( jsonstr);
			}
			else
			{
				throw new Exception("JSON must begin with { or [ !");
			}
			
		}finally {
			try {inputStream.close();} catch(Exception e) {}
		}
	}
	

}
