package com.upn.sistemas.capsof_project.utils;

public class Constants {

	/* Values */
	public static final String TRUE_VALUE = "TRUE";
	public static final String FALSE_VALUE = "FALSE";
	public static final Long ROLE_USER_VALUE = (long) 2;
	public static final String OK = "OK";
	public static final String ACTIVE_CONSTANT = "A";
	/* End values */

	/* Strings */
	/* HTTP text */
	public static final String HTTP_TEXT_200 = "Successful request";
	public static final String HTTP_TEXT_201 = "Object saved";
	public static final String HTTP_TEXT_400 = "Not found";
	public static final String HTTP_TEXT_401 = "Not authorized!";
	public static final String HTTP_TEXT_403 = "Action denied";
	public static final String HTTP_TEXT_500 = "Oh, we creashed :(";
	/* End HTTP text */

	/* UserType Controller */
	public static final String USER_TP_API_OP_GET = "Get all UserTypes from database";
	public static final String USER_TP_API_OP_POST = "Save a UserType to database";
	public static final String USER_TP_API_OP_PUT = "Update a UserType into database";
	public static final String USER_TP_API_OP_DELETE = "Delete a UserType from database";
	public static final String USER_TP_API_OP_FIND = "Find a UserType from database";
	public static final String USER_TP_API_OP_FIND_BY_ID = "Find a UserType by ID from database";
	/* End UserType Controller */

	/* User Controller */
	public static final String USER_API_OP_GET = "Get all User from database";
	public static final String USER_API_OP_POST = "Save a User to database";
	public static final String USER_API_OP_PUT = "Update a User into database";
	public static final String USER_API_OP_DELETE = "Delete a User from database";
	public static final String USER_API_OP_FIND_BY_ID = "Find a User by ID from database";
	public static final String USER_API_OP_LOGIN = "Login in the app with credencials";
	/* End User Controller */

	/* Profile Controller */
	public static final String PROFILE_API_OP_POST = "Save a Profile to database";
	public static final String PROFILE_API_OP_PUT = "Update a Profile to database";
	public static final String PROFILE_API_OP_DELETE = "Delete a Profile to database";
	/* End Profile Controller */

	/* Company Controller */
	public static final String COMPANY_API_OP_GET = "Get all company from database";
	public static final String COMPANY_API_OP_GET_BY_ID = "Get company by id from database";
	public static final String COMPANY_API_OP_SAVE = "Save company in database";
	public static final String COMPANY_API_OP_UPDATE = "Update company in database";
	public static final String COMPANY_API_OP_DELETE = "Delete company in database";
	/* End Company Controller */

	/* CompanyOffer Controller */
	public static final String COMP_OFFER_API_OP_GET = "Get all company offer by company id from database";
	public static final String COMP_OFFER_API_OP_POST = "Save a company offer into database";
	public static final String COMP_OFFER_API_OP_DELETE = "Delete a company offer by id from database";
	public static final String COMP_OFFER_API_OP_PUT = "Update a company offer by id from database";
	public static final String COMP_OFFER_API_OP_GET_BY_USER = "Get all company offer by user id and type profiles from database";
	/* End CompanyOffer Controller */

	/* Domain Controller */
	public static final String DOMAIN_API_OP_GET = "Get all domains by cod of domain";
	/* End Domain Controller */

	/* Skill Controller */
	public static final String SKILL_API_OP_GET_ALL = "Get all skills from database";
	public static final String SKILL_API_OP_GET_BY_TYPE = "Get all skills by type from database";
	/* End Skill Controller */
	
	/* User Application Controller */
	public static final String USER_APPLICATION_OFFER_SAVE = "User Application Offer Save";
	public static final String USER_APPLICATION_OFFER_UPDATE = "User Application Offer Update";
	/* End User Application Controller */

}
