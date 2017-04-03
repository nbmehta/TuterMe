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
import org.springframework.web.bind.annotation.RestController;

import ae.tutorme.dao.CategoryDAO;
import ae.tutorme.dto.CategoryDTO;
import ae.tutorme.dto.converter.Converter;
import ae.tutorme.model.Category;
import ae.tutorme.utils.Helpers;

@RestController
@RequestMapping("/category")
public class CategoryResource {
	private static final Logger logger = Logger.getLogger(CategoryResource.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private Converter converter;
	
	@RequestMapping(value="/add", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addCategory(@RequestBody CategoryDTO category) {
		try {
			Category cat = categoryDAO.saveCategory(converter.toCategory(category));
			return new ResponseEntity<>(new CategoryDTO(cat), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("addCategory()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/id/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCategory(@PathVariable("id") int id) {
		try {
			CategoryDTO category = categoryDAO.getCategoryDTOById(id);
			if(category != null)
				return new ResponseEntity<CategoryDTO>(category, HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			logger.error("getCategory()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCategory(@PathVariable("id") int id, @RequestBody CategoryDTO category) {
		try {
			CategoryDTO categoryFull = categoryDAO.updateCategory(id, category);
			return new ResponseEntity<>(categoryFull, categoryFull == null ? null : HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("updateCategory()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteActivation(@RequestBody Map<String, Object> map) {
		try {
			categoryDAO.deleteCategory((int) map.get("id"));
			return new ResponseEntity<>(Helpers.returnSingleMessage("success"), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("deleteActivation()", ex);
			return new ResponseEntity<>(Helpers.returnSingleMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
