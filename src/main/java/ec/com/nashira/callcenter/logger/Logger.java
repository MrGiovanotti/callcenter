package ec.com.nashira.callcenter.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ec.com.nashira.callcenter.AppProperties;
import ec.com.nashira.callcenter.utils.ConstantsUtils;

@Component
public class Logger {

	@Autowired
	AppProperties properties;

	private static final String FILE_EXTENSION = ".log";

	public void writeLog(String text) {
		File file = new File(getLogFileName());
		try (FileWriter fw = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(fw)) {
			bw.write(new Date() + ConstantsUtils.DASH_SEPARATOR + text + ConstantsUtils.LINE_BREAK);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getLogFileName() {
		Date toDay = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return properties.getLogFilePath().concat(formatter.format(toDay)).concat(FILE_EXTENSION);
	}

}
