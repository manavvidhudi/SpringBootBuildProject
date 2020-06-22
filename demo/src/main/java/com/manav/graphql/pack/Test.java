package com.manav.graphql.pack;

import java.util.Optional;

public class Test {

	public static void main(String[] args) {
		
		String s = null;
		String v = "";
		System.out.println(!IsNotEmptyOrNull(s) && !IsNotEmptyOrNull(v));
	}
	
	public static Boolean IsNotEmptyOrNull(String csdlProjectName) {

		Boolean isEmptyOrNull = Optional.ofNullable(csdlProjectName).map(item -> !csdlProjectName.isEmpty())
				.orElse(false);
		return isEmptyOrNull;
	}

}
