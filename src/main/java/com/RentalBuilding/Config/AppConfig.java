package com.RentalBuilding.Config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class AppConfig {
	@Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);  
        return modelMapper;
    }
	
	@Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        return mapper;
    }
	
	@Bean
	public Cloudinary cloudinary() {
		
		Cloudinary cloud = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "dvbvha70n",
				"api_key", "155738385458794",
				"api_secret", "6CjDql-zHKHWYrOvYmdIKZCYZJw",
				"secure", true
				));
		return cloud;
	}

}
