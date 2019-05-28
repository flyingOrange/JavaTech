package designPattern.composite;
import java.util.List;

/*
 * 组合模式:依据树形结构来组合对象，用来表示部分以及整体层次。可以通过一个对象递归访问整个对象树
 * Component--树形结构抽象节点(IFile)
 * 
 * */
public class MainClass {
    public static void main(String[] args) {
        //C盘
        Folder rootFolder = new Folder("C:");
        //一个目录
        Folder orangeFolder = new Folder("orange");
        rootFolder.add(orangeFolder);
        //一个文件
        File orangeFile = new File("orangeFile.txt");
        rootFolder.add(orangeFile);
        
        Folder newFolder = new Folder("newFolder");
        File newFile = new File("newFile.txt");
        orangeFolder.add(newFile);
        orangeFolder.add(newFolder);
        
        displayTree(rootFolder,0);
    }
    
    public static void displayTree(IFile rootFolder,int deep) {
        for(int i=0;i<deep;i++) {
            System.out.print("--");
        }
        rootFolder.display();
        //获得并遍历子树
        List<IFile> children = rootFolder.getChild();
        for(IFile iFile:children) {
            if(iFile instanceof File) {
                for(int i=0;i<=deep;i++) {
                    System.out.print("--");
                }
                //如果是文件，直接展示
                iFile.display();
            }else {
                //递归遍历子树
                displayTree(iFile,deep+1);
            }
        }
    }

}
