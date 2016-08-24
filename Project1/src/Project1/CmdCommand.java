package Project1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.w3c.dom.Element;

public class CmdCommand extends Command{
	
	List<String> Cargs = new ArrayList<String>();

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<String> getArgs(){
		return Cargs;
	}
	
	public String getIn(){
		return Cin;
	}
	
	public String getOut(){
		return Cout;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void parse(Element elem) throws ProcessException {
		// TODO Auto-generated method stub
        System.out.println("Parsing CMD");
		
        Cid = elem.getAttribute("id");
		if(Cid == null || Cid.isEmpty()){
			throw new ProcessException("Missing ID in CMD command");
		}
		System.out.println("ID: " + Cid);
		
		CWorkPath = elem.getAttribute("path");
		if(CWorkPath == null || CWorkPath.isEmpty()){
			throw new ProcessException("Missing Path in CMD command");
		}
		System.out.println("Path: " + CWorkPath);
		
		Cin = elem.getAttribute("in");
		if (!(Cin == null || Cin.isEmpty())) {
			System.out.println("inID: " + Cin);
		}

		Cout = elem.getAttribute("out");
		if (!(Cout == null || Cout.isEmpty())) {
			System.out.println("outID: " + Cout);
		}
		
		String arg = elem.getAttribute("args");
		if (!(arg == null || arg.isEmpty())) {
			StringTokenizer st = new StringTokenizer(arg);
			while (st.hasMoreTokens()) {
				String tok = st.nextToken();
				this.Cargs.add(tok);
			}
		}
		for(String args: Cargs) {
			System.out.println("Arg " + args);
		}
		
	}
		
	

	@Override
	public void execute(String workingDir, Map<String, Command> map) throws Exception {
		// TODO Auto-generated method stub
		
		ProcessBuilder process = new ProcessBuilder();
		System.out.println("The working directory will be set to work");
		process.directory(new File(workingDir));
		List<String> list = new ArrayList<String>();
		list.add(CWorkPath);
		
		
		for(String args : Cargs){
			list.add(args);
			
		}
		
		
		
		if(Cin != null && !(Cin.isEmpty())){
			String cmdinId = Cin;
			FileCommand input = (FileCommand)map.get(cmdinId);
			if(input != null){
				String inputPath = input.getPath();
				System.out.println("File Command on file: " + inputPath);
				File in = new File(workingDir, inputPath);
				process.redirectInput(in);
			}else {
				throw new ProcessException("Unable to locate IN FileCommand with id: " + cmdinId);
			}			
		}
		
		process.command(list);
		
		if(this.Cout!=null){
			String cmdoutId = Cout;
			FileCommand output = (FileCommand)map.get(cmdoutId);
			if(output!= null){
				String outputPath = output.getPath();
				System.out.println("File Command on file: " + outputPath);
				File out = new File(workingDir, outputPath);
				process.redirectOutput(out);
			}else{
				throw new ProcessException("Unable to locate OUT FileCommand with id: " + cmdoutId);
			}
		}
		
		String outputerrpath = workingDir+"/"+"error.txt";
		process.redirectError(new File(outputerrpath));
		
	    Process process1 = process.start();
		process1.waitFor();
		
		System.out.println("Program terminated!");
		
	}

	

}
