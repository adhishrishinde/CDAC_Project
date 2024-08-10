package com.sunbeam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.custom_exception.ResourceNotFoundException;
import com.sunbeam.dao.CategoryDao;
import com.sunbeam.dao.ItemDao;
import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.ItemAddDto;
import com.sunbeam.dto.ItemResDTO;
import com.sunbeam.entities.Category;
import com.sunbeam.entities.Item;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemDao itemDao;
	
	@Autowired 
	private CategoryDao categoryDao;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public ApiResponse addItem(ItemAddDto dto, Long categoryId) {
		Category category = categoryDao.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Invalid Id"));
		Item item = mapper.map(dto, Item.class);
		item.setCategory(category);
		itemDao.save(item);
		return new ApiResponse("New Item added !!");
	}

	@Override
	public List<ItemResDTO> getItemByCategoryName(String categoryName) {
		List<Item> persistentItems = itemDao.findByCategoryCategoryName(categoryName);
		List<ItemResDTO> vehiclesDtoList  = new ArrayList<>();
		for (Item item : persistentItems) {
			vehiclesDtoList.add(mapper.map(item, ItemResDTO.class));
		}
		return vehiclesDtoList;
	}

	
	@Override
	public ItemResDTO updateItem(Long itemId, ItemResDTO dto) {
		Item item = itemDao.findById(itemId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Project Id !!!"));
		// => project exists
		// dto contains the updates , so apply it --> to entity
		mapper.map(dto, item);
		System.out.println("after mapping " + itemId);
		itemDao.save(item);
		dto.setItemId(itemId);// so that it doesn't send null in the json resp
		return dto;
	}

	@Override
	public String deleteItemDetails(Long itemId) {
		if (itemDao.existsById(itemId)) {
			itemDao.deleteById(itemId);
			return "deleted category !!!";
		}
		throw new ResourceNotFoundException("Invalid category id !!!");
	}


	

}
