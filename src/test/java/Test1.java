import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @ClassName Test1
 * @Description TODO
 * @Author 马善军
 * @Date 2024/7/1 13:53
 * @Version 1.0
 */

public class Test1 {
    public static void main(String[] args) {
        /*LocalDate endOfMonth = LocalDate.now();
        System.out.println(endOfMonth);*/

        /*Date date = new Date();

        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(date);*/
        //System.out.println(currentDate);
        /*if (true) System.out.println(121212);
        String str1 = "123";
        Byte.parseByte("12");

        Test1 test1 = new Test1();
        int num = 1;
        String str = "aa";
        System.out.println(test1.getreturn(num));
        System.out.println(num);
        System.out.println(test1.getr(str));
        System.out.println(str);
        */
        /*List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.remove("2");
        System.out.println(list.size());
        String str = "123";
        str.length();*/
        ;
        /*int empno = ThreadLocalRandom.current().nextInt(1000, 10000);
        System.out.println(empno);*/
        String str = "";
        System.out.println(str == "");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        int[] nums = new int[2];
        nums[0] = list.get(0);
        System.out.println(nums[0]);
        Map<String,Object> map = new HashMap<>();
        map.put("count",0);
        System.out.println(map.get("count").equals(0));
    }
    public int getreturn(int num){
        return num + 1;
    }
    public String getr(String str){
        str = str + "1";
        return str + "1";
    }

}
