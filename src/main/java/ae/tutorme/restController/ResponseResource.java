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

import ae.tutorme.dao.ResponseDAO;
import ae.tutorme.dto.ResponseDTO;
import ae.tutorme.model.Response;
import ae.tutorme.utils.Helpers;

@Controller
@RequestMapping("/response")
public class ResponseResource {
	private static final Logger logger = Logger.getLogger(ResponseResource.class);
	
	@Autowired
	private ResponseDAO responseDAO;
	
	@RequestMapping(value="/add", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addResponse(@RequestBody Response response) {
		try {
			responseDAO.saveResponse(response);
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(response), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("addResponse()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/id/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getResponse(@PathVariable("id") int id) {
		try {
			ResponseDTO response = responseDAO.getResponseDTOById(id);
			if(response != null)
				return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			logger.error("getResponse()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateResponse(@PathVariable("id") int id, @RequestBody ResponseDTO response) {
		try {
			ResponseDTO responseFull = responseDAO.updateResponse(id, response);
			return new ResponseEntity<>(responseFull, responseFull == null ? null : HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("updateResponse()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteActivation(@RequestBody Map<String, Object> map) {
		try {
			responseDAO.deleteResponse((int) map.get("id"));
			return new ResponseEntity<>(Helpers.returnSingleMessage("success"), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("deleteActivation()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
