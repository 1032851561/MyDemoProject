package czm.demo.smb;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;
import jcifs.smb.SmbSession;

public class UploadFile {

	public static void main(String[] args) {
		String localFile = "G:/class/com/gtland/TestSmb.class";
		String shareUrl = "smb://192.168.239.101/MyShare/classes/com/////\\gtland/";
		InputStream in = null;
		OutputStream out = null;
		try {
			SmbFile path = new SmbFile(shareUrl);
			System.out.println(path.exists());
			if(true) return;
			if (!path.exists()) {
				path.mkdirs();
			}
			SmbFile remoteFile = new SmbFile(shareUrl + "UserService.class");
			in = new BufferedInputStream(new FileInputStream(localFile));
			out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));
			byte[] buffer = new byte[1024];
			while (in.read(buffer) != -1) {
				out.write(buffer);
				buffer = new byte[1024];
			}
			System.out.println("上传成功");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
