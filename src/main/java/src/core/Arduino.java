package src.core;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortIOException;

import java.io.IOException;

public class Arduino {
    final static SerialPort comPort = SerialPort.getCommPort("COM3");
    private static boolean isportopen=false;
    private int lastvalue=0;

    public int getlightvalue() throws IOException {
        try {
            openport();
            
            if(isportopen){
            byte[] b = new byte[3];
            int l = comPort.readBytes(b, 3);
            String s = new String(b);
            lastvalue = Integer.parseInt(s.trim());
            return Integer.parseInt(s.trim());}
        }catch (NumberFormatException e){return lastvalue;}
        return lastvalue;
    }

    public static void openport(){
        if(!isportopen){
            if(comPort.openPort()){
            System.out.println("Port opened");
            isportopen=true;}
            else{
                System.out.println(isportopen);
            }
        }
    }

    public static void getoutputstream(char value)throws IOException{
        openport();
        if(isportopen){
        comPort.getOutputStream().write(value);}
    }

    public static void getoutputstream(Integer value) throws IOException {
        openport();
        if(isportopen){
        String digit = ""+value;
        if(value>999){
            comPort.getOutputStream().write(digit.charAt(0));
            comPort.getOutputStream().write(digit.charAt(1));
            comPort.getOutputStream().write(digit.charAt(2));
            comPort.getOutputStream().write(digit.charAt(3));
        }
        else if(value>254){
            comPort.getOutputStream().write(digit.charAt(0));
            comPort.getOutputStream().write(digit.charAt(1));
            comPort.getOutputStream().write(digit.charAt(2));
        }
        else {comPort.getOutputStream().write(value);}
        System.out.println(value);
        comPort.getOutputStream().write('X');
    }}
}
