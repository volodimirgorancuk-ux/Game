package javax.microedition.sensor;
public interface DataListener {
    public void dataReceived(SensorConnection sc, Data[] data, int[] sequenceNumbers);
}
