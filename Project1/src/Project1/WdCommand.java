package Project1;

import java.util.Map;

import org.w3c.dom.Element;

public class WdCommand extends Command{
	

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return Cid;
	}
	
	public String getPath(){
		return CWorkPath;
	}

	@Override
	public	String describe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void parse(Element elem) throws ProcessException {
		// TODO Auto-generated method stub
		System.out.println("Parsing wd command");
		String Cid = elem.getAttribute("id");
		if (Cid == null || Cid.isEmpty()) {
			throw new ProcessException("Missing ID in WD Command");
		}
		System.out.println("ID: " + Cid);
		
		
		CWorkPath = elem.getAttribute("path");
		if (CWorkPath == null || CWorkPath.isEmpty()) {
			throw new ProcessException("Missing PATH in WD Command");
		}
		System.out.println("Path: " + CWorkPath);
		
		
	}

	@Override
	public void execute(String workingDir, Map<String, Command> map) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
