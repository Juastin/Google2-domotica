package src.core;
import com.fazecast.jSerialComm.*;

import java.io.InputStream;

public class Arduino {
    final SerialPort comPort = SerialPort.getCommPort("COM3");
    private String var1;
    private String var2="";

    public String getArduinoData(){
        comPort.openPort();
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        InputStream in = comPort.getInputStream();
        try{
            for (int j = 0; j < 5; ++j){
                var1 += (char)in.read();
                char eerste = var1.charAt(0);
                char laatste = var1.charAt(var1.length()-1);
                if(eerste=='<'&&laatste=='>'){
                    var2=var1;
                    break;}
            }
            in.close();
            var1="";
        } catch (Exception e) {}

        comPort.closePort();

        var2 = var2.replace("<","");
        var2 = var2.replace(">","");
        return var2;
    }
}
