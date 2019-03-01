/*
TCP客户端:输入命令从服务器获得数据
输入：0即开关继电器，当服务器执行好任务时返回89
    w获得温度数据，服务器返回温度数据
    s获得水深数据，服务器返回水深数据
    其他输入均为无效输入，服务器返回78
PS：服务器只接受1个char,返回也是一个char,上述数据均为16进制
*/
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    public static void main(String [] args) throws IOException
    {
        Socket s = null;
        BufferedReader DataIn = null;
        PrintWriter DataOut = null;
        try {
            //s=new Socket(InetAddress.getByName("192.168.128.100"),1010);
            s=new Socket("192.168.128.100",8080);
            DataIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOut=new PrintWriter(s.getOutputStream(), true);
            System.out.println("连接成功");
            @SuppressWarnings("resource")
            Scanner keyIn= new Scanner(System.in);
            while(keyIn.hasNext()){
                String c=keyIn.nextLine();
                System.out.println("输入: "+c);
                if(c.length()==0)continue;
                DataOut.println(c);
                System.out.println("收到: "+DataIn.readLine());
            }
            DataIn.close();
            DataOut.close();
            s.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        DataIn.close();
        DataOut.close();
        s.close();
    }
}