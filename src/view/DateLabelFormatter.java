package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

@SuppressWarnings("serial")
public class DateLabelFormatter extends AbstractFormatter{
    private String datePattern = "dd-MM-yyyy";
    private SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
	
	@Override
	public Object stringToValue(String text) throws ParseException {
		return sdf.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
            Calendar cal = (Calendar) value;
            return sdf.format(cal.getTime());
        }
		return "";
	}
}
