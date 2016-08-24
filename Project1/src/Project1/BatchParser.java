package Project1;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BatchParser {
	
	Batch batch=new Batch();
	
	public Batch BuildBatch(File BatchFile){
		
		
		
		try{
			FileInputStream fis = new FileInputStream(BatchFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fis);
			Element pnode = doc.getDocumentElement();
			NodeList nodes = pnode.getChildNodes();
			for (int idx = 0; idx < nodes.getLength(); idx++) {
				Node node = nodes.item(idx);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;
					Command command=buildCommand(elem);
					if (command instanceof WdCommand) {
						WdCommand wdCommand=(WdCommand) command;
						batch.setWorkingDir(wdCommand.getPath());
					}
					//batch.addCommand(command);
					batch.addMap(command.Cid, command);
				
				}
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return batch;
	}
	
	public Command buildCommand(Element elem) throws ProcessException{
		Command command=null;
		String cmdName = elem.getNodeName();
		
		if (cmdName == null) {
			throw new ProcessException("unable to parse command from " + elem.getTextContent());
		}
		else if ("wd".equalsIgnoreCase(cmdName)) {
			command=new WdCommand();
		}
		else if ("file".equalsIgnoreCase(cmdName)) {
			command=new FileCommand();
		}
		else if ("cmd".equalsIgnoreCase(cmdName)) {
			command=new CmdCommand();
		}
		else if ("pipe".equalsIgnoreCase(cmdName)) {
			command=new PipeCommand();
		}
		else {
			throw new ProcessException("An unknown element in the batch file.");
		}
		command.parse(elem);
		return command;
	}
}

