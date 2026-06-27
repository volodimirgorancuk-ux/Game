package javax.microedition.rms;
public class RecordStore {
    public static RecordStore openRecordStore(String name, boolean create) throws Exception { return null; }
    public int addRecord(byte[] data, int offset, int numBytes) throws Exception { return 0; }
    public byte[] getRecord(int recordId) throws Exception { return null; }
    public int getNumRecords() { return 0; }
    public void closeRecordStore() throws Exception {}
    public void deleteRecordStore(String name) {}
    public static RecordStore openRecordStore(String name) throws Exception { return null; }
    public static RecordStore openRecordStore(String name, String vendorName, String appName) throws Exception { return null; }
}
