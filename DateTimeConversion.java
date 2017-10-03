package halkom.model.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import java.text.ParseException;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

public class DateTimeConversion extends FormattingConversionServiceFactoryBean {

	@SuppressWarnings("deprecation")
	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		registry.addConverter(getStringToDateConverter());
	}

	public Converter<String, Date> getStringToDateConverter() {
		return new Converter<String, Date>() {

			@Override
			public Date convert(String source) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-Mon-yyyy");
				try {
					return sdf.parse(source);
				} catch (ParseException e) {
					return null;
				}
			}
		};
	}
}