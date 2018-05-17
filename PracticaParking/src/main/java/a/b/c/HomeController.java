package a.b.c;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	DAOInterfazParking dao;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		
		return "testparking";
	}
	
	@RequestMapping(value = "/registroMatricula", method = {RequestMethod.GET,RequestMethod.POST})
	public ResponseEntity<DTOParking> insertarcoche(@RequestBody DTOParking parking) {
		
		
		
		System.out.println("Dentro de servicio REST: " + parking.getMatricula());
		dao.AñadirCoche(parking);//añadimos a la bbdd
		HttpHeaders  cabeceras = new HttpHeaders();
		try {
			cabeceras.setLocation(new URI("http://localhost:8080/coche/registroMatricula/"+parking.getParkingId()+parking.getMatricula()));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<DTOParking>(parking,cabeceras,HttpStatus.CREATED);

}
	
	@RequestMapping(value = "/coste", method = RequestMethod.GET)
	public String coste(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "testcoste";
	
	}
	
	 @RequestMapping(value = "/coste/{matricula}", method = {RequestMethod.POST,RequestMethod.GET})
		public @ResponseBody double coste (@PathVariable(value="matricula") String matricula) {
			System.out.println("Entrada al coste");
			double tarifa=0.005;
			Timestamp tiemposalida = dao.BuscarCoche(matricula,1).getTimeStamp();
			Timestamp tiempoentrada = dao.BuscarCoche(matricula, 0).getTimeStamp();
			System.out.println( "Tiempo: "+((tiemposalida.getTime()-tiempoentrada.getTime())/1000) ); 
			double segundos=((tiemposalida.getTime()-tiempoentrada.getTime())/1000);
			
			double coste=segundos*tarifa;
		
			return coste;
		}
}