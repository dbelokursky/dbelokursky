import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class Pinger {
    public static void main(String[] args) throws IOException {
        int firstOctet = 172;
        int secondOctet = 16;
        int thirdOctet = 16;
        int fourthOctet = 0;
        Map<String, InetAddress> availableAddresses = new HashMap<>();
        for (int i = 0; i < 255; i++) {
            fourthOctet = i;
            byte[] addressTemplate = new byte[]{(byte) firstOctet, (byte) secondOctet, (byte) thirdOctet, (byte) fourthOctet};
            InetAddress ipAddr = InetAddress.getByAddress(addressTemplate);
            if (ipAddr.isReachable(1000)) {
                availableAddresses.put(ipAddr.toString(), ipAddr);
            }
//            System.out.println(i + " "+ ipAddr.isReachable(1000));

        }
        System.out.println(availableAddresses.size());
        for (InetAddress ia : availableAddresses.values()) {
            System.out.println(ia.getCanonicalHostName());
        }
    }
}
