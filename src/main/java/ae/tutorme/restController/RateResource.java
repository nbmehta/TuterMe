package ae.tutorme.restController;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ae.tutorme.dao.RateDAO;
import ae.tutorme.dto.RateDTO;
import ae.tutorme.model.Rate;
import ae.tutorme.utils.Helpers;

@Controller
@RequestMapping("/rate")
public class RateResource {
	private static final Logger logger = Logger.getLogger(RateResource.class);
	
	@Autowired
	private RateDAO rateDAO;
	
	@RequestMapping(value="/add", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addRate(@RequestBody Rate rate) {
		try {
			rateDAO.saveRate(rate);
			return new ResponseEntity<RateDTO>(new RateDTO(rate), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("addRate()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/id/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRate(@PathVariable("id") int id) {
		try {
			RateDTO rate = rateDAO.getRateDTOById(id);
			if(rate != null)
				return new ResponseEntity<RateDTO>(rate, HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			logger.error("getRate()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateRate(@PathVariable("id") int id, @RequestBody RateDTO rate) {
		try {
			RateDTO rateFull = rateDAO.updateRate(id, rate);
			return new ResponseEntity<>(rateFull, rateFull == null ? null : HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("updateRate()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteActivation(@RequestBody Map<String, Object> map) {
		try {
			rateDAO.deleteRate((int) map.get("id"));
			return new ResponseEntity<>(Helpers.returnSingleMessage("success"), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("deleteActivation()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
