package utd.persistentDataStore.datastoreServer.commands;

import java.io.IOException;

import org.apache.log4j.Logger;

import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class DeleteCommand extends ServerCommand {
	private static Logger logger = Logger.getLogger(DeleteCommand.class);
	@Override
	public void run() throws IOException, ServerException {
		// TODO Auto-generated method stub
		String name = StreamUtil.readLine(inputStream);
		logger.debug("name: "+name);
		if(FileUtil.deleteData(name)==true){
			this.sendOK();
		}else{
			this.sendError("Cannot found the data");
			throw new ServerException("File deletion failed");
		}
	}

}
