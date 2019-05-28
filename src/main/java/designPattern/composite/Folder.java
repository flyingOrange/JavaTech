package designPattern.composite;

import java.util.ArrayList;
import java.util.List;

//文件夹
public class Folder implements IFile{
    private String name;
    private List<IFile> childen;
    
    public Folder(String name) {
        super();
        this.name = name;
        childen = new ArrayList<IFile>();
    }

    @Override
    public void display() {
        System.out.println(name);        
    }

    @Override
    public boolean add(IFile file) {
        return this.childen.add(file);
    }

    @Override
    public boolean remove(IFile file) {
        return this.childen.remove(file);
    }

    @Override
    public List<IFile> getChild() {
        return this.childen;
    }

}
