package ticket.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class SerStat {
	public static HashMap<String, String> statInit() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("stat.dat")));
		@SuppressWarnings("unchecked")
		HashMap<String, String> stat = (HashMap<String, String>) ois.readObject();
		ois.close();
		return stat;
	}

}
