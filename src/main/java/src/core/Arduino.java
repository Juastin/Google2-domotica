package src.core;

import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;

public class Arduino {
    final static SerialPort comPort = SerialPort.getCommPort("COM3");
    private boolean isportopen=false;
    private String lichtwaarde= "";

    public String getlichtwaarde(){
        if(!isportopen){
            comPort.openPort();
            System.out.println("Port opened");
            isportopen=true;
        }
        byte[] b = new byte[5];
        int l = comPort.readBytes(b, 5);
        String s = new String(b);
        try{
            lichtwaarde = s;}catch (NumberFormatException e){return lichtwaarde;}
        System.out.println(lichtwaarde);
        return lichtwaarde;
    }

    public void getoutputstream(Integer waarde) throws IOException {
        String cijfer = ""+waarde;
        if(waarde>999){
            comPort.getOutputStream().write(cijfer.charAt(0));
            comPort.getOutputStream().write(cijfer.charAt(1));
            comPort.getOutputStream().write(cijfer.charAt(2));
            comPort.getOutputStream().write(cijfer.charAt(3));
        }
        else if(waarde>254){
            comPort.getOutputStream().write(cijfer.charAt(0));
            comPort.getOutputStream().write(cijfer.charAt(1));
            comPort.getOutputStream().write(cijfer.charAt(2));
        }
        else {comPort.getOutputStream().write(waarde);}
        System.out.println(waarde);
        comPort.getOutputStream().write('X');
    }
}
