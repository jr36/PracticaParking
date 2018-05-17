package a.b.c;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class MapperParking implements RowMapper<DTOParking>{
	
	@Override 
	public DTOParking mapRow(ResultSet rs, int rowNum) throws SQLException {
		DTOParking dto = new DTOParking();
		dto.setMatricula(rs.getString("Matricula"));
		dto.setParkingId(rs.getInt("ParkingId"));
		dto.setRegistro(rs.getInt("Registro"));
		dto.setTimeStamp(rs.getTimestamp("TimeStamp"));
		return dto;
		
	}

}