package Project1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PipeCommand extends Command{
	public String inputPath;
	public String outputPath;
	public CmdCommand cmd1;
	public CmdCommand cmd2;
	public ArrayList<Element> elemlist= new ArrayList<>();

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void parse(Element elem) throws ProcessException {
		// TODO Auto-generated method stub
		System.out.println("Parsing Pipe");
		String id = elem.getAttribute("id");
		if (id == null || id.isEmpty()) {
			throw new ProcessException("Missing ID in Pipe Command");
		}
		System.out.println("ID: " + id);
		
		NodeList nodes = elem.getChildNodes();
		
		for (int idx = 0; idx < nodes.getLength(); idx++) {
			Node node = nodes.item(idx);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element ele = (Element) node;
				elemlist.add(ele);
				
			}
		}
		BatchParser batchParser=new BatchParser();
		cmd1=(CmdCommand)batchParser.buildCommand(elemlist.get(0));
		cmd2=(CmdCommand)batchParser.buildCommand(elemlist.get(1));
	}

	@Override
	public void execute(String workingDir, Map<String, Command> map) throws Exception {
		// TODO Auto-generated method stub
		if(cmd1.getIn() != null && !(cmd1.getIn().isEmpty())){
			String cmdinId = cmd1.getIn();
			FileCommand input = (FileCommand)map.get(cmdinId);
			if(input != null){
				inputPath = input.getPath();
				System.out.println("File Command on file: " + inputPath);
				
			}else {
				throw new ProcessException("Unable to locate IN FileCommand with id: " + cmdinId);
			}			
		}
		
		List<String> list1 = new ArrayList<String>();
		list1.add(cmd1.CWorkPath);
		System.out.println(list1);
		List<String> cmd1Cargs=cmd1.getArgs();
		for(String args : cmd1Cargs){
			list1.add(args);
			
		}
		
		
		
		if(cmd2.getOut()!= null && !(cmd2.getOut().isEmpty())){
			String cmdoutId = cmd2.getOut();
			FileCommand output = (FileCommand)map.get(cmdoutId);
			if(output != null){
				outputPath = output.getPath();
				System.out.println("File Command on file: " + outputPath);
				
			}else {
				throw new ProcessException("Unable to locate IN FileCommand with id: " + cmdoutId);
			}			
		}
		
		List<String> list2 = new ArrayList<String>();
		list2.add(cmd2.CWorkPath);
		
		List<String> cmd2Cargs=cmd2.getArgs();
		
		for(String args : cmd2Cargs){
			list2.add(args);
			
		}
		
		
		
		try {
			System.out.println("Execute the Pipe Command");
			ProcessBuilder inputprocess = new ProcessBuilder(list1);
			inputprocess.directory(new File(workingDir));
			System.out.println("Execute cmd1");
			
			final Process inprocess = inputprocess.start();
			OutputStream os = inprocess.getOutputStream();
			FileInputStream fis = new FileInputStream(new File(workingDir,inputPath));
			int achar;
			while ((achar = fis.read()) != -1) {
				os.write(achar);
			}
			os.close();
			fis.close();
			System.out.println("Exit cmd1");
			
			ProcessBuilder outputprocess = new ProcessBuilder(list2);
			outputprocess.directory(new File(workingDir));
																																																																																																																																												System.out.println("Execute cmd2");
			
			final Process outprocess = outputprocess.start();
			InputStream inOS=inprocess.getInputStream();
			OutputStream outOs = outprocess.getOutputStream();
			while ((achar = inOS.read()) != -1) {
				outOs.write(achar);
			}
			outOs.close();
			
			File outfile = new File(workingDir,outputPath);
			FileOutputStream fos = new FileOutputStream(outfile);
			InputStream is = outprocess.getInputStream();
			while ((achar = is.read()) != -1) {
				fos.write(achar);
				System.out.print((char) achar);
			}
			fos.close();
			System.out.println("Exit cmd2");
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
