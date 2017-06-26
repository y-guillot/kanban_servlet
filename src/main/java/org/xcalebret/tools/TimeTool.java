package org.xcalebret.tools;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TimeTool {

	public static Date getLocalCurrentTime() {
		
		Date in = new Date();
		LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
	}
}
