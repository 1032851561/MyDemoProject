package czm.demo.smb;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jcifs.smb.SmbAuthException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

public class ReadFile {

	public static void main(String[] args) {
		if(args.length==0){
			System.out.println("java -cp xxx.jar ReadFile.java <必：读取的文件名> <选:共享地址,默认smb://guest:151931@192.168.20.23/MyShare/>");
			return;
		}
		
		String fileName = args[0];
		String shareUrl = "smb://guest:151931@192.168.20.23/MyShare/";
		if (args.length > 1) {
			shareUrl = args[1];
		}
		try {
			SmbFile smbFile = new SmbFile(shareUrl + fileName);
			int length = smbFile.getContentLength();// 得到文件的大小
			byte buffer[] = new byte[length];
			SmbFileInputStream in = new SmbFileInputStream(smbFile); // 建立smb文件输入流
			while ((in.read(buffer)) != -1) {
				System.out.println("读取文件test.txt,内容:" + new String(buffer));
			}
			in.close();
		} catch (SmbAuthException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readFile(String shareUrl, String filePath) {

	}

	/**
	 * 方法二： 路径格式：smb://192.168.75.204/test/新建 文本文档.txt
	 * smb://username:password@192.168.0.77/test
	 * 
	 * @param remoteUrl
	 *            远程路径
	 * @param localDir
	 *            要写入的本地路径
	 */
	public static void downloadFile(String remoteUrl, String localDir) {
		InputStream in = null;
		OutputStream out = null;
		try {
			SmbFile remoteFile = new SmbFile(remoteUrl);
			if (remoteFile == null) {
				System.out.println("共享文件不存在");
				return;
			}
			String fileName = remoteFile.getName();
			File localFile = new File(localDir + File.separator + fileName);
			in = new BufferedInputStream(new SmbFileInputStream(remoteFile));
			out = new BufferedOutputStream(new FileOutputStream(localFile));
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
