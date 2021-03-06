package Models.utils.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteFiles {
	private String path = System.getProperty("user.dir")+"\\src\\Models\\txt";

	public WriteFiles(){}
	
	public WriteFiles(String path, String name){
		String timeName = new SimpleDateFormat("yyyy_MM_dd_hh_mm")
										.format(new Date());
		this.path = path  + '\\' + timeName + "_" + name;
	}
	
	public void writeInLogFile(String value) {
		String logTime = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS")
										.format(new Date());
		this.writeInFile(new StringBuffer(logTime)
								.append("  ")
								.append(value).toString());
	}
	

	public void writeInFile(String value) {
		try {
			File file = new File(path);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
			writer.write(value);
			writer.newLine();
			// Criando o conte�do do arquivo
			writer.flush();
			// Fechando conex�o e escrita do arquivo.
			writer.close();
			System.out.println("Arquivo gravado em: " + path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setFileName(String name) {
		this.path = this.path + '\\' + name;
	}

	public void setCompletPath(String completePath) {
		this.path = completePath;
	}

	public String getAtualPath() {
		return this.path;
	}
}