import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
public class UsernameChecker {
    public static void main(String[] args) {
        //yString input = "your_input_string"; // 替换为实际要检查的用户名
        //boolean isValid = checkUsername(input);
        //System.out.println("Username is valid: " + isValid);

        String reversed = reverseCheckUsername();
        System.out.println("Reversed str: " + reversed);

        System.out.println("password:");
        reverseCheckPass();
    }
    //非逆向部分
    public static boolean checkUsername(String str) {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    if (str != null) {
                        MessageDigest instance = MessageDigest.getInstance("MD5");
                        instance.reset();
                        instance.update("zhishixuebao".getBytes());
                        String hexString = toHexString(instance.digest(), "");
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < hexString.length(); i += 2) {
                            sb.append(hexString.charAt(i));
                        }
                        String sb2 = sb.toString();
                        return sb2.equals(str);
                    }
                }
                return false;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String reverseCheckUsername() {
        String targetString = "";

        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update("zhishixuebao".getBytes());
            
            String hexString = toHexString(instance.digest(), "");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hexString.length(); i += 2) {
                sb.append(hexString.charAt(i));
            }
            targetString = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return targetString;
    }

    public static String toHexString(byte[] bArr, String str) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hex = Integer.toHexString(b & 255);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex).append(str);
        }
        return sb.toString();
    }
    public static void reverseCheckPass() {
        char[] charArray = new char[15]; // 初始化为全0的字符数组
    
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = '0'; // 假设初始值为字符 '0'
        }
    
        for (int i = charArray.length - 1; i >= 0; i--) {
            charArray[i] = (char) ((((255 - i) + 2) - 98) - charArray[i]);
        }
        System.out.println((String)(Arrays.toString(charArray)));
        return;
    }
}