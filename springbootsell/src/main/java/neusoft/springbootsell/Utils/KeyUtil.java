package neusoft.springbootsell.Utils;

import java.awt.event.KeyListener;
import java.util.Random;

public class KeyUtil {
    public  static  synchronized  String getUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+ 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

    public static void main(String[] args) {
        String s = KeyUtil.getUniqueKey();
        System.out.println(s);
    }
}
