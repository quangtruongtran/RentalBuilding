package com.RentalBuilding.Converter;

import org.modelmapper.ModelMapper;

public class Converter {
	
	protected static ModelMapper modelMapper = new ModelMapper();

	public static ModelMapper getModelMapper() {
		return modelMapper;
	}

	public static void setModelMapper(ModelMapper modelMapper) {
		Converter.modelMapper = modelMapper;
	}
	
	
}
