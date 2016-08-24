package utd.persistentDataStore.datastoreServer.commands;

import java.io.IOException;

import org.apache.log4j.Logger;

import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class WriteCommand extends ServerCommand {
	private static Logger logger=Logger.getLogger(WriteCommand.class);

	@Override
	public void run() throws IOException, ServerException {
		// TODO Auto-generated method stub
		String name=StreamUtil.readLine(inputStream);
		logger.debug("name: "+name);
		String size=StreamUtil.readLine(inputStream);
		logger.debug("size: "+size);
		byte[] data=StreamUtil.readData(Integer.valueOf(size), inputStream);
		logger.debug("data: "+data);
		FileUtil.writeData(name, data);
		this.sendOK();
		logger.debug("Writing success");

	}

}
