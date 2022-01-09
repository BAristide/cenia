package net.linksguard.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import net.linksguard.dao.KBcaseRepository;
import net.linksguard.entities.KBcase;
 
import net.linksguard.models.UploadModel;
@RestController
public class RestUploadController { 
	@Autowired
	private KBcaseRepository kBcaseRepository;
	 private static final Logger logger  = LoggerFactory.getLogger(RestUploadController.class);
	 //Save the uploaded file to this folder
	 private static String UPLOADED_FOLDER = "";
	 
	 private String createUser = "1";
	    private final String os = System.getProperty("os.name").toLowerCase();
	    
	

	    // 3.1.1 Single file upload
	    @PostMapping("/api/upload")
	    // If not @RestController, uncomment this
	    //@ResponseBody
	    public ResponseEntity<?> uploadFile(
	    		@RequestParam("projectLabel") String projectLabel,
	            @RequestParam("processionPriority") String processionPriority,
	            @RequestParam("shortLabel") String shortLabel,
	            @RequestParam("descriptionLabel") String descriptionLabel,
	            @RequestParam("shortCauseLabel") String shortCauseLabel,
	            @RequestParam("causeLabel") String causeLabel,
	            @RequestParam("file") MultipartFile uploadfile) {
	    	
	    	
	    	

	        logger.debug("Single file upload!");
/*
	        if (uploadfile.isEmpty()) {
	            return new ResponseEntity("please select a file!", HttpStatus.OK);
	        }
	    */    
	        

	        try {
	        	kBcaseRepository.save(new KBcase(new Date(), new Date(), Long.parseLong(createUser), projectLabel, processionPriority, shortLabel, descriptionLabel, causeLabel, uploadfile.getOriginalFilename(), shortCauseLabel, new Date(), new Date(), Long.parseLong(createUser)));
	        	if (!uploadfile.isEmpty()) {
	        		  saveUploadedFiles(Arrays.asList(uploadfile));
		        }
	          

	        } catch (IOException e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        return new ResponseEntity("Successfully added  ", new HttpHeaders(), HttpStatus.OK);

	    }

	    // 3.1.2 Multiple file upload
	    @PostMapping("/api/upload/multi")
	    public ResponseEntity<?> uploadFileMulti(
	            @RequestParam("projectLabel") String projectLabel,
	            @RequestParam("processionPriority") String processionPriority,
	            @RequestParam("shortLabel") String shortLabel,
	            @RequestParam("descriptionLabel") String descriptionLabel,
	            @RequestParam("shortCauseLabel") String shortCauseLabel,
	            @RequestParam("causeLabel") String causeLabel,
	            @RequestParam("files") MultipartFile[] uploadfiles) {

	        logger.debug("Multiple file upload!");

	        // Get file name
	        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
	                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

	        if (StringUtils.isEmpty(uploadedFileName)) {
	            return new ResponseEntity("please select a file!", HttpStatus.OK);
	        }

	        try {

	            saveUploadedFiles(Arrays.asList(uploadfiles));

	        } catch (IOException e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        return new ResponseEntity("Successfully uploaded - "
	                + uploadedFileName, HttpStatus.OK);

	    }

	    // 3.1.3 maps html form to a Model
	    @PostMapping("/api/upload/multi/model")
	    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadModel model) {

	        logger.debug("Multiple file upload! With UploadModel");

	        try {

	            saveUploadedFiles(Arrays.asList(model.getFiles()));

	        } catch (IOException e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);

	    }

	    //save file
	    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {
	        if(os.contains("win")) {
		    	UPLOADED_FOLDER = "/Users/pc/eclipse-workspace/eNIA/uploadFiles/";
		      	
		  }else {
			UPLOADED_FOLDER = "/var/lib/tomcat/uploadFiles/";
		      	
		  }
	        for (MultipartFile file : files) {

	            if (file.isEmpty()) {
	                continue; //next pls
	            }

	            byte[] bytes = file.getBytes();
	            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
	            Files.write(path, bytes);

	        }

	    }
	}
