package com.sunbeam.service;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.readFileToByteArray;
import static org.apache.commons.io.FileUtils.writeByteArrayToFile;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sunbeam.custom_exception.ApiException;
import com.sunbeam.custom_exception.ResourceNotFoundException;
import com.sunbeam.dao.CategoryDao;
import com.sunbeam.dao.ItemDao;
import com.sunbeam.dto.ApiResponse;
import com.sunbeam.entities.Category;
import com.sunbeam.entities.Item;

@Service
@Transactional
public class ImageHandlingServiceImpl implements ImageHandlingService{

	@Value("${file.upload.location}")
	private String uploadFolder;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@PostConstruct
	public void init() throws IOException {
		// chk if folder exists --yes --continue
		File folder = new File(uploadFolder);
		if (folder.exists()) {
			System.out.println("folder exists alrdy !");
		} else {
			// no --create a folder
			folder.mkdir();
			System.out.println("created a folder !");
		}
	}
	
	@Override
	public ApiResponse uploadImage(Long categoryId, MultipartFile image) throws IOException {
		Category category = categoryDao.
				findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Invalid category ID!!!!"));
		// cat found --> PERSISTENT
		// store the image on server side folder
		String path = uploadFolder.concat(image.getOriginalFilename());
		System.out.println(path);
		// Use FileUtils method : writeByte[] --> File
		writeByteArrayToFile(new File(path), image.getBytes());
		// set image path in DB (emps table)
		category.setImagePath(path);
		// OR to store the img directly in DB as a BLOB
		// emp.setImage(image.getBytes());
		return new ApiResponse("Image file uploaded successfully for cat id " + categoryId);
	}


	@Override
	public void uploadImage(Category category, MultipartFile image) throws IOException {
		// store the image on server side folder
		String path = uploadFolder.concat(image.getOriginalFilename());
		System.out.println(path);
		// Use FileUtils method : writeByte[] --> File
		writeByteArrayToFile(new File(path), image.getBytes());
		// set image path
		category.setImagePath(path);
		// OR to store the img directly in DB as a BLOB
		// emp.setImage(image.getBytes());
		System.out.println("Image file uploaded successfully for category " + category.getCategoryName());
	}
	
	@Override
	public byte[] serveImage(Long categoryId) throws IOException {
		// get emp by id
		Category category = categoryDao.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Invalid emp ID!!!!"));
		// emp found --> PERSISTENT
		String path = category.getImagePath();
		if (path != null) {
			// path ---> File --> byte[]
			return readFileToByteArray(new File(path));
			// OR from DB : return emp.getImage();
		} else
			throw new ApiException("Image not yet assigned !!!!");

	}
	
	
//	@Override
//	public ApiResponse uploadItemImage(Long itemId, MultipartFile image) throws IOException {
//		Category category = categoryDao.
//				findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Invalid item ID!!!!"));
//		// cat found --> PERSISTENT
//		// store the image on server side folder
//		String path = uploadFolder.concat(image.getOriginalFilename());
//		System.out.println(path);
//		// Use FileUtils method : writeByte[] --> File
//		writeByteArrayToFile(new File(path), image.getBytes());
//		// set image path in DB (emps table)
//		category.setImagePath(path);
//		// OR to store the img directly in DB as a BLOB
//		// emp.setImage(image.getBytes());
//		return new ApiResponse("Image file uploaded successfully for item id " + itemId);
//	}
	
	@Override
	public ApiResponse uploadItemImage(Long itemId, MultipartFile image) throws IOException {
		Item item = itemDao.
				findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Invalid item ID!!!!"));
		
		String path = uploadFolder.concat(image.getOriginalFilename());
		System.out.println(path);
		writeByteArrayToFile(new File(path), image.getBytes());
		item.setItemImagePath(path);
		return new ApiResponse("Image file uploaded successfully for item id " + itemId);
	}


//	@Override
//	public void uploadItemImage(Item item, MultipartFile image) throws IOException {
//		// store the image on server side folder
//		String path = uploadFolder.concat(image.getOriginalFilename());
//		System.out.println(path);
//		// Use FileUtils method : writeByte[] --> File
//		writeByteArrayToFile(new File(path), image.getBytes());
//		// set image path
//		item.setItemImagePath(path);
//		// OR to store the img directly in DB as a BLOB
//		// emp.setImage(image.getBytes());
//		System.out.println("Image file uploaded successfully for item " + item.getItemName());
//	}
	
	@Override
	public void uploadItemImage(Item item, MultipartFile image) throws IOException {
		String path = uploadFolder.concat(image.getOriginalFilename());
		System.out.println(path);
		writeByteArrayToFile(new File(path), image.getBytes());
		item.setItemImagePath(path);
		System.out.println("Image file uploaded successfully for item " + item.getItemName());
	}
	
//	@Override
//	public byte[] serveItemImage(Long itemId) throws IOException {
//		// get emp by id
//		Category category = categoryDao.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Invalid item ID!!!!"));
//		// emp found --> PERSISTENT
//		String path = category.getImagePath();
//		if (path != null) {
//			// path ---> File --> byte[]
//			return readFileToByteArray(new File(path));
//			// OR from DB : return emp.getImage();
//		} else
//			throw new ApiException("Image not yet assigned !!!!");
//
//	}
	
	@Override
	public byte[] serveItemImage(Long itemId) throws IOException {
		Item item = itemDao.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Invalid item ID!!!!"));
		String path = item.getItemImagePath();
		if (path != null) {
			return readFileToByteArray(new File(path));
		} else
			throw new ApiException("Image not yet assigned !!!!");

	}



}
