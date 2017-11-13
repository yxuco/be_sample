package bestats.test;

import static com.tibco.psg.bestats.InfluxDBHelper.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tibco.psg.bestats.StatsHelper;

public class InfluxDBHelperTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// TODO: configure these param in a resource file
		initInfluxDB("localhost","8086", "bemon", "admin", "admin", "stat");
		StatsHelper.initResetInterval(1000); // specify interval for automatic reset
	}
	
	@Test
	public void testWriteStat() {
		for (long i = 0; i < 100; i++) {
			long now = System.nanoTime();
			try {
				Thread.sleep(16);
			} catch (Exception e) {}
			
			// check result after record is written to InfluxDB
			String msg = writeStat("TEST", (System.nanoTime() - now)/1000000, null);
			if (msg != null) {
				System.out.println(msg);
				if (msg.startsWith("ERROR")) {
					assertNull("InfluxDB is not running, and connection is reset: " + msg, getInfluxClient());
				} else {
					assertTrue("Stat is written to InfluxDB: " + msg, msg.startsWith("DEBUG"));
				}
				return;
			}
		}
		fail("Did not write stats");
	}

}
