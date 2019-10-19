import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class VehicleSerializationTest {

    @Test
    public void testVehicleSerialization() {

        File file = FileUtils.getFile("assets/", "ferrari.obj");
        Vehicle v = new Vehicle("123", "Ferrari", 2012, 12.0, true);
        byte[] data = SerializationUtils.serialize((Serializable) v);
        try {
            FileUtils.writeByteArrayToFile(file, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(file.exists());
    }

    @Test
    public void testVehicleDeserialization() {
        File file = FileUtils.getFile("assets/", "ferrari.obj");
        byte[] data = null;
        try {
            data = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Vehicle v = SerializationUtils.deserialize(data);

    }

}
