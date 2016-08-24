package Project1;



import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Batch {
	private String WorkingDir;
	private Map<String, Command> CmdLookup=new LinkedHashMap<String, Command>();
	private List<Command> CommandList;
	
	public void addCommand(Command command){
		this.CommandList.add(command);
	}
	
	public void setWorkingDir(String workingDir){
		this.WorkingDir = workingDir;
	}
	
	public String getWorkingDir(){
		return WorkingDir;
	}
	
	public void addMap(String id, Command command){
        CmdLookup.put(id,command);
    }
	
	public Map<String, Command> getCommands(){
		return CmdLookup;
	}
	

}
