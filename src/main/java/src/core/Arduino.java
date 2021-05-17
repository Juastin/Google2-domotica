package src.core;

import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;

public class Arduino {
    final static SerialPort comPort = SerialPort.getCommPort("COM3");
    private boolean isportopen=false;

    public int getlightvalue() throws IOException {
        openport();
        byte[] b = new byte[3];
        int l = comPort.readBytes(b, 3);
        String s = new String(b);
        System.out.println(s);
        return Integer.parseInt(s);
    }

    public void openport(){
        if(!isportopen){
            comPort.openPort();
            System.out.println("Port opened");
            isportopen=true;
        }
    }

    public void getoutputstream(char value)throws IOException{
        openport();
        comPort.getOutputStream().write(value);
    }

    public void getoutputstream(Integer value) throws IOException {
        openport();
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
    }
}
