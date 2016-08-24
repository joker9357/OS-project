package utd.persistentDataStore.datastoreServer.commands;

import java.io.IOException;
import java.util.List;


import org.apache.log4j.Logger;

import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class DirectoryCommand extends ServerCommand {
	private static Logger logger=Logger.getLogger(DirectoryCommand.class);
	@Override
	public void run() throws IOException, ServerException {
		// TODO Auto-generated method stub
		List<String> list =FileUtil.directory();
		String size =String.valueOf(list.size());
		this.sendOK();
		StreamUtil.writeLine(size, outputStream);
		for(String string : list){
			StreamUtil.writeLine(string, outputStream);
		}
		logger.debug("Directory success");
	}

}
