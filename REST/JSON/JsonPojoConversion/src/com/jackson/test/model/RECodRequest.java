package com.jackson.test.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
 "accountName", 
 "optIn",
 "deviceId", 
 "userId", 
 "maxResults", 
 "insertItems", 
 "searchString",
 "preferences",
 "contentItems"})
@JsonIgnoreProperties(value = "RECodRequest")
// @JsonIgnoreProperties(ignoreUnknown = true)
public class RECodRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String accountName;
	private Boolean optIn;
	private String deviceId;
	private String userId;
	private int maxResults;
	private Boolean insertItems;
	private String searchString;
	private Map<String, String> preferences = new HashMap<String, String>();
	private List<Integer> contentItems = new ArrayList<Integer>();

	/**
	 * @return the optIn
	 */
	public Boolean getOptIn() {
		return optIn;
	}

	/**
	 * @param optIn
	 *            the optIn to set
	 */
	public void setOptIn(Boolean optIn) {
		this.optIn = optIn;
	}

	/**
	 * @return the insertItems
	 */
	public Boolean getInsertItems() {
		return insertItems;
	}

	/**
	 * @param insertItems
	 *            the insertItems to set
	 */
	public void setInsertItems(Boolean insertItems) {
		this.insertItems = insertItems;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName
	 *            the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId
	 *            the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the maxResults
	 */
	public int getMaxResults() {
		return maxResults;
	}

	/**
	 * @param maxResults
	 *            the maxResults to set
	 */
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	/**
	 * @return the searchString
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * @param searchString
	 *            the searchString to set
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	/**
	 * @return the preferences
	 */
	public Map<String, String> getPreferences() {
		return preferences;
	}

	public void setPreferences(Map<String, String> preferences) {
		this.preferences = preferences;
	}

	public void addPreference(String key, String value) {
		preferences.put(key, value);
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setContentItems(List<Integer> contentItems) {
		this.contentItems = contentItems;
	}

	public void addContentItem(Integer contentItemId) {
		contentItems.add(contentItemId);
	}

	/**
	 * @return the items
	 */
	public List<Integer> getContentItems() {
		return contentItems;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RECodRequest [accountName=");
		builder.append(accountName);
		builder.append(", optIn=");
		builder.append(optIn);
		builder.append(", deviceId=");
		builder.append(deviceId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", maxResults=");
		builder.append(maxResults);
		builder.append(", insertItems=");
		builder.append(insertItems);
		builder.append(", searchString=");
		builder.append(searchString);
		builder.append(", preferences=");
		builder.append(preferences);
		builder.append(", contentItems=");
		builder.append(contentItems);
		builder.append("]");
		return builder.toString();
	}

}
