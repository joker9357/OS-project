package Project1;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class BatchProcessor {
	public static void main(String[] args)throws IOException {
		try {
			String filename = null;
			if(args.length > 0) {
				filename = args[0];
			}
			else {
				filename = "work/batch4.xml";
				//System.out.println("please select a file");
			}
			System.out.println("Opening " + filename);
			File file=new File(filename);
			BatchParser bp = new BatchParser();
			Batch batch = bp.BuildBatch(file);
			ExecuteBatch(batch);		
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void ExecuteBatch(Batch batch) throws Exception{
		Map<String, Command> map = batch.getCommands();
		for(String str : map.keySet()){
			Command command = map.get(str);
			if(command instanceof CmdCommand || command instanceof PipeCommand){
				try {
					
					command.execute(batch.getWorkingDir(), map);
				} catch (ProcessException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

}
