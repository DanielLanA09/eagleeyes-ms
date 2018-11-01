package com.eagleshing.ms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eagleshing.ms.payload.PriceCheckResponse;
import com.eagleshing.ms.scrapy.model.BuildingBase;
import com.eagleshing.ms.scrapy.model.BuildingPrice;
import com.eagleshing.ms.scrapy.repository.BuildingBaseRepository;
import com.eagleshing.ms.scrapy.repository.BuildingPriceRepository;

@RestController
@RequestMapping("/api/scrapy")
public class ScrapyController {
	
	@Autowired 
	BuildingBaseRepository buildingBaseHelper;
	
	@Autowired
	BuildingPriceRepository buildingPriceHelper;
	
	@GetMapping("/findByProject/{project}")
	@Transactional
	public ResponseEntity<?> findByProject(@PathVariable String project){
		try {
			List<BuildingBase> buildings = buildingBaseHelper.findByProjectLike(project);
			List<PriceCheckResponse> priceList = new ArrayList<>();
			for (BuildingBase building : buildings) {
				List<BuildingPrice> prices = buildingPriceHelper.findByBuildingId(building.getId(), new Sort(Direction.DESC,"priceTime"));
				priceList.add(new PriceCheckResponse(building,prices.get(0)));
			}
			return ResponseEntity.ok(priceList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
		}
	}
}
