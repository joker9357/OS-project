package utd.persistentDataStore.datastoreServer.commands;

import java.io.IOException;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class ReadCommand extends ServerCommand {
	private static Logger logger=Logger.getLogger(ReadCommand.class);
	@Override
	public void run() throws IOException, ServerException {
		// TODO Auto-generated method stub
		try {
			String name = StreamUtil.readLine(inputStream);
			byte [] data=FileUtil.readData(name);
			String size=String.valueOf(data);
			logger.debug("data: "+data);
			this.sendOK();
			StreamUtil.writeLine(size, outputStream);
			StreamUtil.writeData(data, outputStream);
			logger.debug("Reading success");
		} catch (Exception e) {
			// TODO: handle exception
			this.sendError("Error with the data");
		}
		

	}

}
