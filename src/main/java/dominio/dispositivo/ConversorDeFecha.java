package dominio.dispositivo;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ConversorDeFecha implements AttributeConverter<LocalDate, Date>{

	@Override
	public Date convertToDatabaseColumn(LocalDate locDate) {
		// TODO Auto-generated method stub
		return (locDate == null ? null : Date.valueOf(locDate));
	}

	@Override
	public LocalDate convertToEntityAttribute(Date sqlDate) {
		// TODO Auto-generated method stub
		return (sqlDate == null ? null : sqlDate.toLocalDate());
	}

}
