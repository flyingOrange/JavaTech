package serializable;

import java.io.IOException;

public class GenerateClass {
	public static void main(String[] args) {
        String protoFile = "person-entity.proto";//  
        System.out.println(protoFile);
        String strCmd = "D:/program/protoc-3.6.1-win32/bin/protoc.exe -I=D:/work/MyEclipseWorkspace/JavaTech/src --java_out=D:/work/MyEclipseWorkspace/JavaTech/src/main/java/serializable/protobuf/proto D:/work/MyEclipseWorkspace/JavaTech/src/main/java/serializable/protobuf/proto/"+ protoFile;  
        try {
            Runtime.getRuntime().exec(strCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }//通过执行cmd命令调用protoc.exe程序  
	}
}
