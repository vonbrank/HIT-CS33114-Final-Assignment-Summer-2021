package tools;


import java.io.*;


public class EasyFileIO {

    private final String fileName;

    public EasyFileIO(String fileName) {
        this.fileName = fileName;
    }
    //覆盖式写入文件
    public void coverWriter(String s){
        PrintWriter toFile=null;

        try{
            toFile=new PrintWriter(this.fileName);         //将数据流outStream连接到名为f.txt的文件
            //此方法将文件连接到数据库时，总是从一个空文件开始
            //若文件已存在，则原来的文件内容丢失；若不存在，则重新创建一个空文件
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("PrintWriter error opening the file:"+this.fileName);
            System.exit(0);
        }
        System.out.println("Please input four lines of text:");         //本例中控制输入4行
        toFile.print(s);

        System.out.println("Four lines were written to "+this.fileName);
        toFile.close();      //显示关闭数据流，避免数据丢失
    }
    //追加式写入文件
    public void appendWriter(String s){

        FileWriter fw=null;
        PrintWriter toFile=null;

        try{
            fw=new FileWriter(this.fileName,true);  //本代码中增加FileWriter      //可以抛出IOException异常
            toFile=new PrintWriter(fw);         //将数据流outStream连接到名为f.txt的文件  //可以抛出FileNotFoundException异常

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("PrintWriter error opening the file:"+this.fileName);
            System.exit(0);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("FileWriter error opening the file:"+this.fileName);
            System.exit(0);
        }
        System.out.println("Please input four additional lines of text:");         //本例中控制输入4行
        toFile.print(s);

        System.out.println("Four lines were written to "+this.fileName);
        toFile.close();      //显示关闭数据流，避免数据丢失
    }

}
