package com.liuguodong.util;

import java.util.HashSet;
import java.util.Random;

/**
 * 
 * @ClassName: RandomUilt 
 * @Description: 随机数工具类
 * @author: Lenovo
 * @date: 2020年1月2日 下午7:52:53
 */
public class RandomUtil {

	//方法1：返回min-max之间的随机整数（包含min和max值），例如返回1-3之间的随机数，那么返回1或2或3都是正确的，返回4就不对。
	public static int random(int min, int max){
		Random random = new Random();
		int num = random.nextInt(max-min+1)+min;
		return num;
	}
	
	
	//方法2：在最小值min与最大值max之间截取subs个不重复的随机数。例如在1-10之间取3个不重复的随机数，那么[2,6,9]是对的，[3,5,5]则不对，因为5重复了。应用场景：在100篇文章中随机取10篇文章，月考可能会使用到。
	public static int[] subRandom (int min, int max, int subs){
//		声明数组用来存放结果值
		int[] nums = new int[subs];
		HashSet<Integer> set = new HashSet<Integer>();
		while (set.size() != subs) {
			set.add(random(min, max));
		}
		int i=0;
//		把set集合放入数组中
		for (Integer integer : set) {
			nums[i] = integer;
			i++;
		}
		return nums;
	}
	
	
	//方法3：返回1个1-9,a-Z之间的随机字符。
	public static char randomCharacter (){
		String str = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return str.charAt(random(0, str.length()-1));
	}
	
	//方法3：返回随机性别。
	public static String randomsex (){
			String str = "男女";
			return Character.toString(str.charAt(random(0, str.length()-1)));
	}
	
	
	//方法4：返回参数length个字符串，方法内部要调用randomCharacter()方法
	public static String randomString(int length){
		String str = "";
		for (int i = 0; i < length; i++) {
			str += randomCharacter();
		}
		return str;
	}
}
