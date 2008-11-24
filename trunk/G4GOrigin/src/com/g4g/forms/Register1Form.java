/**********************************************************
 * Register1Form.java :
 *
 * Created by Ankur
 * Last modified Date: 18 Apr .08 by Punam
 * Revision: 0.1
 * Version : 0.0.8
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.forms;

import java.net.InetAddress;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.g4g.delegator.SubNationalCodeServiceDelegator;
import com.g4g.dto.BaseDTO;
import com.g4g.dto.NationalCodeDTO;
import com.g4g.dto.PlayerDTO;
import com.g4g.dto.SearchCriteria;
import com.g4g.dto.SubNationalCodeDTO;
import com.g4g.dto.UserDTO;
import com.g4g.utils.AuditUtil;
import com.g4g.utils.DataUtil;
import com.g4g.utils.DataValidator;
import com.g4g.utils.DateUtil;
import com.g4g.utils.G4GConstants;

/**
 * The Class Register1Form contains properties for register1 page.It is used in
 * Register1Action.It contains the attribute for register1page as userid, name,
 * password, nationCode...etc.
 *
 * @struts.form name="register1Form"
 * @author Ankur
 */
public class Register1Form extends BaseForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8410243432461725370L;

	/** The user id. */
	private String userId;

	/** The screen name. */
	private String screenName; // G

	/** The email one. */
	private String emailOne; // F/G

	/** The email two. */
	private String emailTwo; // PRESENTATION

	/** The password one. */
	private String passwordOne;// F

	/** The password two. */
	private String passwordTwo;// PRESENTATION

	/** The first name. */
	private String firstName;// F

	/** The last name. */
	private String lastName;// F

	/** The middle name. */
	private String middleName; // TBD

	/** The birth date. */
	private String birthDate; // F

	/** The nation codes. */
	private String nationCodes; // F

	/** The street1. */
	private String street1; // F -ADDRESS

	/** The street2. */
	private String street2; // TBD

	/** The city field. */
	private String cityField; // F

	/** The states. */
	private String states;// F

	/** The billing zip. */
	private String billingZip;// F

	/** The gender. */
	private String gender = G4GConstants.M;// F

	/** The month. */
	private String month;

	/** The day. */
	private String day;

	/** The year. */
	private String year;

	/** The country list. */
	private List<NationalCodeDTO> countryList;

	/** The state list. */
	private List<SubNationalCodeDTO> stateList;

	/** The phone. */
	private String phone;

	/**
	 * Gets the state list.
	 *
	 * @return stateList
	 */
	public List<SubNationalCodeDTO> getStateList() {
		return stateList;
	}

	/**
	 * Sets the state list.
	 *
	 * @param stateList
	 *            the state list
	 */
	public void setStateList(List<SubNationalCodeDTO> stateList) {
		this.stateList = stateList;
	}

	/**
	 * Gets the billing zip.
	 *
	 * @return billingZip
	 */
	public String getBillingZip() {
		return billingZip;
	}

	/**
	 * Gets the birth date.
	 *
	 * @return birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * Gets the city field.
	 *
	 * @return cityField
	 */
	public String getCityField() {
		return cityField;
	}

	/**
	 * Gets the country list.
	 *
	 * @return countryList
	 */
	public List<NationalCodeDTO> getCountryList() {
		return countryList;
	}

	/**
	 * Gets the day.
	 *
	 * @return day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * Gets the email one.
	 *
	 * @return emailOne
	 */
	public String getEmailOne() {
		return emailOne;
	}

	/**
	 * Gets the email two.
	 *
	 * @return emailTwo
	 */
	public String getEmailTwo() {
		return emailTwo;
	}

	/**
	 * Gets the first name.
	 *
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the gender.
	 *
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Gets the last name.
	 *
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets the middle name.
	 *
	 * @return middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Gets the month.
	 *
	 * @return month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * Gets the nation codes.
	 *
	 * @return nationCodes
	 */
	public String getNationCodes() {
		return nationCodes;
	}

	/**
	 * Gets the password one.
	 *
	 * @return passwordOne
	 */
	public String getPasswordOne() {
		return passwordOne;
	}

	/**
	 * Gets the password two.
	 *
	 * @return passwordTwo
	 */
	public String getPasswordTwo() {
		return passwordTwo;
	}

	/**
	 * Gets the screen name.
	 *
	 * @return screenName
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * Gets the states.
	 *
	 * @return states
	 */
	public String getStates() {
		return states;
	}

	/**
	 * Gets the street1.
	 *
	 * @return street1
	 */
	public String getStreet1() {
		return street1;
	}

	/**
	 * Gets the street2.
	 *
	 * @return street2
	 */
	public String getStreet2() {
		return street2;
	}

	/**
	 * Gets the user id.
	 *
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Gets the year.
	 *
	 * @return year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Sets the billing zip.
	 *
	 * @param billingZip
	 *            the billing zip
	 */
	public void setBillingZip(String billingZip) {
		this.billingZip = billingZip;
	}

	/**
	 * Sets the birth date.
	 *
	 * @param birthDate
	 *            the birth date
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Sets the city field.
	 *
	 * @param cityField
	 *            the city field
	 */
	public void setCityField(String cityField) {
		this.cityField = cityField;
	}

	/**
	 * Sets the country list.
	 *
	 * @param countryList
	 *            the country list
	 */
	public void setCountryList(List<NationalCodeDTO> countryList) {
		this.countryList = countryList;
	}

	/**
	 * Sets the day.
	 *
	 * @param day
	 *            the day
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * Sets the email one.
	 *
	 * @param emailOne
	 *            the email one
	 */
	public void setEmailOne(String emailOne) {
		this.emailOne = emailOne;
	}

	/**
	 * Sets the email two.
	 *
	 * @param emailTwo
	 *            the email two
	 */
	public void setEmailTwo(String emailTwo) {
		this.emailTwo = emailTwo;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName
	 *            the first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender
	 *            the gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName
	 *            the last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Sets the middle name.
	 *
	 * @param middleName
	 *            the middle name
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Sets the month.
	 *
	 * @param month
	 *            the month
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * Sets the nation codes.
	 *
	 * @param nationCodes
	 *            the nation codes
	 */
	public void setNationCodes(String nationCodes) {
		this.nationCodes = nationCodes;
	}

	/**
	 * Sets the password one.
	 *
	 * @param passwordOne
	 *            the password one
	 */
	public void setPasswordOne(String passwordOne) {
		this.passwordOne = passwordOne;
	}

	/**
	 * Sets the password two.
	 *
	 * @param passwordTwo
	 *            the password two
	 */
	public void setPasswordTwo(String passwordTwo) {
		this.passwordTwo = passwordTwo;
	}

	/**
	 * Sets the screen name.
	 *
	 * @param screenName
	 *            the screen name
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * Sets the states.
	 *
	 * @param states
	 *            the states
	 */
	public void setStates(String states) {
		this.states = states;
	}

	/**
	 * Sets the street1.
	 *
	 * @param street1
	 *            the street1
	 */
	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	/**
	 * Sets the street2.
	 *
	 * @param street2
	 *            the street2
	 */
	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Sets the year.
	 *
	 * @param year
	 *            the year
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * Gets the phone.
	 *
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone
	 *            the phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Populates UesrDTO as per register1form.
	 *
	 * @param baseDto
	 *            the base dto
	 *
	 * @see com.g4g.forms.BaseForm#populate(com.g4g.dto.BaseDTO)
	 */
	@Override
	public void populate(BaseDTO baseDto) {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		UserDTO dto = (UserDTO) baseDto;
		com.impessa.worldgaming.client.User userDTO = dto.getUser();
		PlayerDTO playerDTO = dto.getPlayerDTO();

		List<String> street = DataUtil.breakString(userDTO.getAddress());
		if (street.size() == 1) {
			this.setStreet1(street.get(0));
		} else {
			this.setStreet1(street.get(0));
			this.setStreet2(street.get(1));
		}
		String temp = DataUtil.getString(DataUtil.getDate(userDTO
				.getBirthdate(), G4GConstants.DATE_DD_MM_YYYY));
		List<String> db = DataUtil.breakString(temp, G4GConstants.SLASH);
		this.setDay(db.get(0));
		this.setMonth(db.get(1));
		this.setYear(db.get(2));
		this.setCityField(userDTO.getCity());
		this.setNationCodes(userDTO.getCountry());
		this.setEmailOne(userDTO.getEmail());
		this.setFirstName(userDTO.getFirstname());
		this.setGender(userDTO.getGender());
		this.setPasswordTwo(userDTO.getPassword());
		this.setEmailTwo(userDTO.getEmail());
		try {
			InetAddress ip = InetAddress.getLocalHost();
			userDTO.setIpAddress(ip.getHostAddress());
		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							G4GConstants.CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(
							G4GConstants.CURRENTMETHOD).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(
							G4GConstants.RECORD_NOT_ADDED).toString(),
					Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							G4GConstants.CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(
							G4GConstants.CURRENTMETHOD).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(e.getMessage())
							.toString(), Level.ERROR);
		}

		this.setLastName(userDTO.getLastname());
		this.setPasswordOne(userDTO.getPassword());
		this.setBillingZip(userDTO.getPostalcode());
		this.setStates(userDTO.getState());

		// settting stateList

		this.setEmailOne(playerDTO.getEmailaddress());
		this.setScreenName(playerDTO.getScreenname());
		// this.setPhone(playerDTO.getPhone());
		this.setPhone(userDTO.getPhone());
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
	}

	/**
	 * Returns Userdto populated by register1form.
	 *
	 * @return the DTO
	 *
	 * @see com.g4g.forms.BaseForm#getDTO()
	 */
	@Override
	public BaseDTO getDTO() {
		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		UserDTO dto = new UserDTO();
		com.impessa.worldgaming.client.User userDTO = new com.impessa.worldgaming.client.User();
		PlayerDTO playerDTO = new PlayerDTO();
		userDTO.setAddress(new StringBuffer(this.getStreet1()).append(
				G4GConstants.COMMA).append(this.getStreet2()).toString());
		String bd = new StringBuffer(this.getDay()).append(G4GConstants.SLASH)
				.append(this.getMonth()).append(G4GConstants.SLASH).append(
						this.getYear()).toString();
		userDTO.setBirthdate(DataUtil.getLongDate(bd));
		userDTO.setCity(this.getCityField());
		userDTO.setCountry(this.getNationCodes());
		userDTO.setEmail(this.getEmailOne());
		userDTO.setFirstname(this.getFirstName());
		userDTO.setGender(this.getGender());
		try {
			InetAddress ip = InetAddress.getLocalHost();
			userDTO.setIpAddress(ip.getHostAddress());
		} catch (Exception e) {
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							G4GConstants.CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(
							G4GConstants.CURRENTMETHOD).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(
							G4GConstants.RECORD_NOT_ADDED).toString(),
					Level.ERROR);
			AuditUtil.getInstance().writeLog(
					AuditUtil.FILE_TYPE_HIBERNATE,
					new StringBuffer(this.getClass().getName()).append(
							G4GConstants.COLON_WITH_SPACES).append(
							G4GConstants.CALLINGMETHOD).append(
							DataUtil.getCallingMethod()).append(
							G4GConstants.CURRENTMETHOD).append(
							DataUtil.getCurrentMethod()).append(
							G4GConstants.DASHES).append(e.getMessage())
							.toString(), Level.ERROR);
		}
		userDTO.setLastname(this.getLastName());
		userDTO.setPassword(this.getPasswordOne());
		userDTO.setPostalcode(this.getBillingZip());
		userDTO.setState(this.getStates());
		userDTO.setPhone(this.getPhone());
		// financialDTO.setSiteId("");
		// settting playerDTO
		playerDTO.setId(DataUtil.getInteger(this.getUserId()));
		playerDTO.setEmailaddress(this.getEmailOne());
		playerDTO.setScreenname(this.getScreenName());
		playerDTO.setLoginname(this.getEmailOne());
		playerDTO.setCreationdate(new Date());
		playerDTO.setPhone(this.getPhone());
		dto.setPlayerDTO(playerDTO);
		dto.setUser(userDTO);

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(G4GConstants.ENDED)
						.toString(), Level.INFO);
		return dto;
	}

	/**
	 * validates extra validation for register1form.
	 *
	 * @param mapping
	 *            the mapping
	 * @param request
	 *            the request
	 *
	 * @return the action errors
	 *
	 * @see org.apache.struts.validator.ValidatorForm#validate(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);

		if (request.getSession().getAttribute(G4GConstants.BRACKETLEFT) != null) {
			return new ActionErrors();
		}

		if(!DataValidator.isNull(this.nationCodes)) {
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setAttribute(G4GConstants.NATCODEID, Integer.parseInt(this.getNationCodes()));
		List<SubNationalCodeDTO> subNationList = SubNationalCodeServiceDelegator.getList(searchCriteria);
		request.setAttribute(G4GConstants.STATE_LIST, subNationList);
		}
		ActionErrors errors = super.validate(mapping, request);
		if (!errors.isEmpty()) {
			return errors;
		}
		ActionMessage msg;

		String bd = new StringBuffer(this.day).append(G4GConstants.SLASH)
				.append(this.month).append(G4GConstants.SLASH)
				.append(this.year).toString();

		if (!DataValidator.isValidDateStr(bd, G4GConstants.DATE_DD_MM_YYYY)) {
			msg = new ActionMessage(G4GConstants.ERRORS_DATE,
					G4GConstants.BIRTH_DATE);
			errors.add(G4GConstants.YEAR, msg);
		} else if(DateUtil.age(Integer.parseInt(this.year), Integer.parseInt(this.month) - 1, Integer.parseInt(this.day)) < 18) {
			msg = new ActionMessage(G4GConstants.ERROR_UNDERAGE);
			errors.add(G4GConstants.YEAR, msg);
		}

		AuditUtil.getInstance().writeLog(
				AuditUtil.FILE_TYPE_G4G,
				new StringBuffer(this.getClass().getName()).append(
						G4GConstants.COLON_WITH_SPACES).append(G4GConstants.CALLINGMETHOD)
						.append(DataUtil.getCallingMethod()).append(
								G4GConstants.CURRENTMETHOD).append(
								DataUtil.getCurrentMethod()).append(
								G4GConstants.DASHES).append(
								G4GConstants.STARTED).toString(), Level.INFO);
		return errors;
	}

}