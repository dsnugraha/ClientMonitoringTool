package dsn.cmon.secure;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

import dsn.cmon.dao.UserBrokerDAO;
import dsn.cmon.dao.UserBrokerDAOImpl;
import dsn.cmon.model.UserBroker;

public class Authentication {

	public Authentication() {
		// TODO Auto-generated constructor stub
	}

	public boolean isValid() {
		boolean result = false;
		UserBrokerDAO dao = new UserBrokerDAOImpl();
		UserBroker userBroker = dao.getUserBroker();
		int ubrec = dao.getCountRec();

		if (ubrec > 0) {

			String ubusr = userBroker.getUsername().trim();
			String ubpwd = userBroker.getPassword().trim();
			String ubsn = userBroker.getSerialnum().trim();

			String content = ubusr.concat(ubpwd).concat(ubsn);
			String fc = DigestUtils.md5Hex(content);

			String dir = System.getProperty("user.home");
			try {
				@SuppressWarnings("resource")
				DataInputStream input = new DataInputStream(
						new FileInputStream(dir + File.separator + ".cmon" + File.separator + "CMON.DAT"));

				String rd = input.readUTF();
				if (fc.equals(rd)) {
					result = true;
				}
			} catch (Exception e) {
			}
		}

		return result;

	}

	public static void main(String[] args) {
		Authentication auth = new Authentication();
		System.out.println(auth.isValid());

		String dir = System.getProperty("user.home");
		String fc = DigestUtils.md5Hex("content");

		File file = new File(dir + File.separator + ".cmon");
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}

		try {
			DataOutputStream output = new DataOutputStream(
					new FileOutputStream(dir + File.separator + ".cmon" + File.separator + "CMON.DAT"));
			output.writeUTF(fc);
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
