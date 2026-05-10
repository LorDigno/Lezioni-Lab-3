import java.net.*;

public class UdpProof {
    public static void main (String args[])throws Exception{
        try(DatagramSocket dgs = new DatagramSocket( );){
            //mostra quanto sono grandi i buffer sei socket udp
            int r = dgs.getReceiveBufferSize();
            int s = dgs.getSendBufferSize();
            System.out.println("receive buffer"+r);
            System.out.println("send buffer"+s);
        }catch(Exception e){}
    }
}