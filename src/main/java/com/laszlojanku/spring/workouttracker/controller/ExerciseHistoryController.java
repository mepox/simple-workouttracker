package com.laszlojanku.spring.workouttracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laszlojanku.spring.workouttracker.model.ExerciseHistory;
import com.laszlojanku.spring.workouttracker.service.AppUserService;
import com.laszlojanku.spring.workouttracker.service.ExerciseHistoryService;

/**
 * Handles the client's REST API requests that are related to the ExerciseHistory.
 * 
 * ExerciseHistory can be added, deleted or retrieved by a date.
 */
@RestController
public class ExerciseHistoryController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private ExerciseHistoryService exerciseHistoryService;
	
	/**
	 * Handles the client GET request to retrieve all the ExerciseHistory from a specific date 
	 * @param	strDate	the requested date as a String in yyyy-MM-dd format
	 * @param	auth	Authentication token received from the client
	 * @return			List of all ExerciseHistory on the specific date in a JSON String format and a HttpStatus
	 */	
	@GetMapping("/user/history/{date}")
	public ResponseEntity<String> getAllByDate(@PathVariable("date") String strDate, Authentication auth) {
		List<ExerciseHistory> exerciseHistoryList = new ArrayList<ExerciseHistory>();
		
		try {
			if (auth == null) {
				return new ResponseEntity<String>("Couldn't get the username.", HttpStatus.BAD_REQUEST);
			}
			int userId = appUserService.getId(auth.getName());
			exerciseHistoryList = exerciseHistoryService.getAll(userId, strDate);		
			
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>(exerciseHistoryList.toString(), HttpStatus.OK);
	}
	
	/**
	 * Handles the client POST request to add a new ExerciseHistory
	 * @param	exerciseHistory	an ExerciseHistory object sent by the client
	 * @param 	auth			Authentication token sent by the client
	 * @return					status message and HttpStatus
	 */	
	@PostMapping("/user/history/add")
	public ResponseEntity<String> add(@RequestBody ExerciseHistory exerciseHistory, Authentication auth) {
		try {
			if (auth == null) {
				return new ResponseEntity<String>("Couldn't get the username.", HttpStatus.BAD_REQUEST);
			}
			// Get and apply the userId
			exerciseHistory.setUserId(appUserService.getId(auth.getName()));
			exerciseHistoryService.add(exerciseHistory);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("New exercise added to the history.", HttpStatus.OK);	
	}
	
	/**
	 * Handles the client DELETE request to delete an ExerciseHistory
	 * @param	id	the id of the ExerciseHistory to be deleted
	 * @return		status message and HttpStatus
	 */	
	@DeleteMapping("/user/history/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id) {
		try {			
			exerciseHistoryService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("Exercise deleted.", HttpStatus.OK);		
	}

}