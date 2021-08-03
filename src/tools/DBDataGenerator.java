package tools;


import java.security.SecureRandom;
import cn.edu.hit.sms.utils.DBUtils;

public class DBDataGenerator {
    public static void main(String args[]) {
        SecureRandom rand = new SecureRandom();
        EasyFileIO efio = new EasyFileIO("D:/456.txt");

        efio.coverWriter("");
        for(int i=1; i<=100; i++){
            efio.appendWriter(String.format("%d\n", Math.abs(rand.nextInt() % 10)));
        }


    }


}
