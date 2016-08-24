package Project1;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

public abstract class Command {
    String Cid;
    String CWorkPath;
    String Cin;
    String Cout;
    List<String> Cargs;
    
	
	public abstract String getId();
	public abstract String describe();
	public abstract void parse(Element elem) throws ProcessException;
	public abstract void execute (String workingDir, Map<String, Command> map) throws Exception;
}
