package demojava.net;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className NetworkInterfaceTest
 * @description TODO
 * @date 2024/5/13
 */
@Slf4j
public class NetworkInterfaceTest {
    @Test
    void test() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            System.out.println(networkInterface.getName());
            log.info("网络接口名称:{},是否回环地址:{},是否点对点：{},是否虚拟地址:{},是否在线:{},最大传输单元:{},硬件地址:{}",
                    networkInterface.getName(),
                    networkInterface.isLoopback(), networkInterface.isPointToPoint()
                    , networkInterface.isVirtual(), networkInterface.isUp(),
                    networkInterface.getMTU(),
                    networkInterface.getHardwareAddress()
            );
            // 获取接口地址
            networkInterface.getInterfaceAddresses().forEach(
                    i -> System.out.println(i.getAddress().getHostAddress())
            );
            // 获取inet地址
            log.info("inet地址:");
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                System.out.println(inetAddress.getHostAddress());
            }
            log.info("子接口:");
            // 获取子接口
            Enumeration<NetworkInterface> subInterfaces = networkInterface.getSubInterfaces();
            while (subInterfaces.hasMoreElements()) {
                NetworkInterface subInterface = subInterfaces.nextElement();
                System.out.println(subInterface.getName());
            }
        }


    }
    public void t(){
    }
}
