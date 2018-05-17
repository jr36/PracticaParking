package a.b.c;

import java.util.List;

import javax.sql.DataSource;
import a.b.c.DTOParking;
import a.b.c.MapperParking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class DAOParking implements DAOInterfazParking{

	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public DTOParking BuscarCoche(String matricula, int parkingId) {
		
		String sql = "select * from parking where Matricula=? and ParkingId=?";
		Object [] param = {matricula,parkingId} ;
		MapperParking mapper = new MapperParking();
		List<DTOParking> lista = this.jdbcTemplate.query(sql, param, mapper);
		if (lista.isEmpty()) return null;
		else {
			return lista.get(0);
		}
		
	}
	@Override
	public void AñadirCoche(DTOParking parking) {			
		String sql = "insert into parking (ParkingId, Matricula) values(?,?)";
		Object[ ] parametros = {parking.getParkingId(),parking.getMatricula()}; 
		this.jdbcTemplate.update(sql,parametros);
	}
	
	
}
