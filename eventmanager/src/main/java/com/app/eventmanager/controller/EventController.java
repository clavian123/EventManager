package com.app.eventmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.eventmanager.model.Event;
import com.app.eventmanager.repository.EventRepository;
import com.app.eventmanager.service.EventService;


@RestController
@RequestMapping(value = "/event")
public class EventController {
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	EventService eventService;
	
	@GetMapping(value = "all")
	public ResponseEntity<List<Event>> getAllEvent(@RequestParam(required = false) String title) {
		try {
			List<Event> event = eventRepository.findAll();

			if (event.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			
			return new ResponseEntity<>(event, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				}
		}
	
	  @GetMapping("/{id}")
	  public ResponseEntity<Event> getTutorialById(@PathVariable("id") Long id) {
	    Optional<Event> tutorialData = eventRepository.findById(id);

	    if (tutorialData.isPresent()) {
	      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
//    @GetMapping(value = "id")/*Komunikasi API dengan jenis GET*/
//    ResponseEntity<Response> getById (@PathVariable ("id")Long id)/*Mengambil Request data dari Berdasarkan id*/
//    {
//        /*Informasi Tentang Nama Method*/
//        String nameofCurrMethod = new Throwable()
//                .getStackTrace()[0]
//                .getMethodName();
//        /*Memanggil Class Response yang telah dibuat*/
//        Response response = new Response();
//        response.setService(this.getClass().getName() + nameofCurrMethod);
//        response.setMessage("Berhasil Mengambil Data Berdasarkan Id");
//
//        /*Set Data Dari Database*/
//        response.setData(eventService.findById(id));
//
//        return  ResponseEntity
//                .status(HttpStatus.OK)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(response);
//    }
//
//    @GetMapping/*Komunikasi API dengan jenis GET*/
//    ResponseEntity<Response> findAll ()
//    {
//        /*Informasi Tentang Nama Method*/
//        String nameofCurrMethod = new Throwable()
//                .getStackTrace()[0]
//                .getMethodName();
//        /*Memanggil Class Response yang telah dibuat*/
//        Response response = new Response();
//        response.setService(this.getClass().getName() + nameofCurrMethod);
//        response.setMessage("Berhasil Menampilkan Seluruh Data");
//
//        /*Set Data Dari Database*/
//        response.setData(eventService.findAll());
//
//        return  ResponseEntity
//                .status(HttpStatus.OK)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(response);
//    }
//	
//    @PostMapping /*Komunikasi API dengan jenis POST*/
//    ResponseEntity<Response> create (@RequestBody @Validated Event event) /*Mengambil Request data dari Body dan melakukan Proses Validasi*/
//    {
//        /*Informasi Tentang Nama Method*/
//        String nameofCurrMethod = new Throwable()
//                .getStackTrace()[0]
//                .getMethodName();
//        /*Memanggil Class Response yang telah dibuat*/
//        Response response = new Response();
//        response.setService(this.getClass().getName() + nameofCurrMethod);
//        response.setMessage("Berhasil Membuat Data");
//
//        /*Set Data Dari Database*/
//        response.setData(eventService.create(event));
//
//        return  ResponseEntity
//                .status(HttpStatus.OK)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(response);
//    }
//    
//    @PutMapping/*Komunikasi API dengan jenis PUT*/
//    ResponseEntity<Response> update (@PathVariable ("id")Long id, @RequestBody @Validated Event event) /*Mengambil Request data dari Body dan melakukan Proses Validasi, diseleksi berdasarkan id*/
//    {
//        /*Informasi Tentang Nama Method*/
//        String nameofCurrMethod = new Throwable()
//                .getStackTrace()[0]
//                .getMethodName();
//
//        Response response = new Response();
//        response.setService(this.getClass().getName() + nameofCurrMethod);
//        response.setMessage("Berhasil Update Data");
//
//        /*Set Data Dari Database*/
//        response.setData(eventService.update(id, event));
//
//        return  ResponseEntity
//                .status(HttpStatus.OK)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(response);
//    }
//    
//
//    
//    @DeleteMapping(value = "id")/*Komunikasi API dengan jenis GET*/
//    ResponseEntity<Response> deleteById (@PathVariable ("id")Long id)/*Mengambil Request data dari Berdasarkan id*/
//    {
//        /*Informasi Tentang Nama Method*/
//        String nameofCurrMethod = new Throwable()
//                .getStackTrace()[0]
//                .getMethodName();
//
//        /*Memanggil Class Response yang telah dibuat*/
//        Response response = new Response();
//        response.setService(this.getClass().getName() + nameofCurrMethod);
//        response.setMessage("Data Berhasil dihapus");
//        response.setData(eventService.findById(id));
//
//        eventService.delete(id);
//
//        return  ResponseEntity
//                .status(HttpStatus.OK)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(response);
//    }

}
