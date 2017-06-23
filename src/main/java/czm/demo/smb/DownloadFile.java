package czm.demo.smb;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

public class DownloadFile {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("java -cp xxx.jar DownloadFile.java <必：下载的文件名> <必:下载到什么目录> <选:共享地址,默认smb://guest:151931@192.168.20.23/MyShare/>");
			return;
		}
		String fileName = args[0];
		String localDir = args[1];
		// String shareUrl = "smb://guest:151931@192.168.20.23/MyShare/";
		String shareUrl = "smb://guest:151931@192.168.239.101/MyShare/classes/com/gtland/";
		if (args.length > 2) {
			shareUrl = args[2];
		}
		InputStream in = null;
		OutputStream out = null;
		try {
			SmbFile remoteFile = new SmbFile(shareUrl + fileName);
			if (remoteFile == null) {
				System.out.println("共享文件不存在");
				return;
			}
			File localFile = new File(localDir + File.separator + remoteFile.getName());
			in = new BufferedInputStream(new SmbFileInputStream(remoteFile));
			out = new BufferedOutputStream(new FileOutputStream(localFile));
			byte[] buffer = new byte[1024];
			while (in.read(buffer) != -1) {
				out.write(buffer);
				buffer = new byte[1024];
			}
			System.out.println("下载成功");
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

	/**
	 * 上传文件
	 * 
	 * @throws Exception
	 */
	public static void upLoadFile(String shareUrl, String filePath) throws Exception {
		InputStream in = null;
		OutputStream out = null;
		try {
			File localFile = new File(filePath);
			SmbFile remoteFile = new SmbFile(shareUrl + localFile.getName());
			if (remoteFile.exists()) {
				System.out.println("共享目录已存在文件：" + localFile.getName() + ",先删除");
				return;
			}
			in = new BufferedInputStream(new FileInputStream(localFile));
			out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));
			byte[] buffer = new byte[1024];
			while (in.read(buffer) != -1) {
				out.write(buffer);
				buffer = new byte[1024];
			}
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
