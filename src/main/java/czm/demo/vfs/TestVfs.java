package czm.demo.vfs;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.VFS;

public class TestVfs {

	public static void testSmb() throws FileSystemException {
		System.out.println("-------------smb------------");
		FileSystemOptions opts = new FileSystemOptions();
		FileSystemManager fsManager = VFS.getManager();
		FileObject localFileObject = fsManager.resolveFile("smb://guest:151931@192.168.20.140/MyShare/");
		FileObject[] children = localFileObject.getChildren();
		for (int i = 0; i < children.length; i++) {
			FileObject fileObject = children[i];
			if (fileObject.getType() == FileType.FILE)
				System.out.println(fileObject.getName().getBaseName() + ":" + fileObject.getContent().getSize());
		}
	}

	public static void testFtp() throws FileSystemException {
		System.out.println("-------------ftp------------");
		FileSystemOptions opts = new FileSystemOptions();
		FileSystemManager fsManager = VFS.getManager();
		FileObject localFileObject = fsManager.resolveFile("ftp://test:test@192.168.20.19/test");
		FileObject[] children = localFileObject.getChildren();
		for (int i = 0; i < children.length; i++) {
			FileObject fileObject = children[i];
			if (fileObject.getType() == FileType.FILE)
				System.out.println(fileObject.getName().getBaseName() + ":" + fileObject.getContent().getSize());
		}
	}

	public static void testLocal() throws FileSystemException {
		System.out.println("-------------ftp------------");
		FileSystemOptions opts = new FileSystemOptions();
		FileSystemManager fsManager = VFS.getManager();
		FileObject localFileObject = fsManager.resolveFile("file://y:/");
		FileObject[] children = localFileObject.getChildren();
		for (int i = 0; i < children.length; i++) {
			FileObject fileObject = children[i];
			if (fileObject.getType() == FileType.FILE)
				System.out.println(fileObject.getName().getBaseName() + ":" + fileObject.getContent().getSize());
		}
	}

	public static void testAllFileDs(String url) throws FileSystemException {
		System.out.println("-------------allfile------------");
		FileSystemManager fsManager = VFS.getManager();
		FileObject localFileObject = fsManager.resolveFile(url);
		FileObject[] children = localFileObject.getChildren();
		for (int i = 0; i < children.length; i++) {
			FileObject fileObject = children[i];
			System.out.println(fileObject.getName().getPath());
			if (fileObject.getType() == FileType.FILE){
				System.out.println(fileObject.getName().getBaseName() + ":" + fileObject.getContent().getSize());
			}
		}
	}

	public static void main(String[] args) throws FileSystemException {
		//org.apache.commons.vfs2.FileNotFolderException
		// org.apache.commons.vfs2.FileSystemException
		testAllFileDs("file://g:/classs");
		testAllFileDs("ftp:://test:test@192.168.20.19/test");
	}
}
