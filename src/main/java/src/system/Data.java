package src.system;

import java.util.ArrayList;

public class Data {
    private static ArrayList<ArrayList<String>> SensorData = Queries.getSensorData();

    public static int getSensorLight(){
        return Integer.parseInt(SensorData.get(0).get(4));
    }

    public static int getSensorTemp(){
        return Integer.parseInt(SensorData.get(0).get(1));
    }

    public static int getSensorHumidity(){
        return Integer.parseInt(SensorData.get(0).get(3));
    }
    
    public static int getSensorAirPressure(){
        return Integer.parseInt(SensorData.get(0).get(2));
    }
    
}
