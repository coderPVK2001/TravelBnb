package com.Travelbnb;

import com.Travelbnb.entity.AppUser;
import com.Travelbnb.entity.Favourites;
import com.Travelbnb.entity.Property;
import com.Travelbnb.repository.AppUserRepository;
import com.Travelbnb.repository.FavouritesRepository;
import com.Travelbnb.repository.PropertyRepository;
import com.Travelbnb.service.BucketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TravelbnbApplicationTests {

	@Autowired
	private FavouritesRepository favouritesRepository;
	@Autowired
	private PropertyRepository propertyRepository;
	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private BucketService bucketService;

    @Test
	void contextLoads() {
		AppUser user = appUserRepository.findByUsername("tom90").get();
		System.out.println(user);
		Property property = propertyRepository.findById(2).get();
		System.out.println(property);
		Favourites favv = favouritesRepository.findByUserAndProperty(user, property);
		System.out.println(favv);
	}

	@Test
	void contextLoads1() {

		List<String> bucketList = bucketService.getBucketList();
		System.out.println(bucketList);
	}

	@Test
	void contextLoads2() {

		List<String> bucketList = bucketService.getBucketList();
		System.out.println(bucketList);
	}

}
