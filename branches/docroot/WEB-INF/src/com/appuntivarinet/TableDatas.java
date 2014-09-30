package com.appuntivarinet;

public class TableDatas {
	private long countUserCreate;
	private long countRoles;
	private long countBlogsEntry;
	private long countWikiesPage;
	private long countGroups;
	private long countAssetsEntry;
	private String lastLogin;
	private long countRatingsEntry;
	private long countCalEvents;
	
	public TableDatas(){
		this.countUserCreate = -1;
		this.countRoles = -1;
		this.countBlogsEntry = -1;
		this.countWikiesPage = -1;
		this.countGroups = -1;
		this.countAssetsEntry = -1;
		this.lastLogin = "";
		this.countRatingsEntry = -1;
		this.countCalEvents = -1;
	}
	
	public TableDatas(long countUserCreate, long countRoles,
			long countBlogsEntry, long countWikiesPage, long countGroups,
			long countAssetsEntry, String lastLogin, long countRatingsEntry,
			long countCalEvents) {
		super();
		this.countUserCreate = countUserCreate;
		this.countRoles = countRoles;
		this.countBlogsEntry = countBlogsEntry;
		this.countWikiesPage = countWikiesPage;
		this.countGroups = countGroups;
		this.countAssetsEntry = countAssetsEntry;
		this.lastLogin = lastLogin;
		this.countRatingsEntry = countRatingsEntry;
		this.countCalEvents = countCalEvents;
	}
	
	
	
	public long getCountUserCreate() {
		return countUserCreate;
	}
	public void setCountUserCreate(long countUserCreate) {
		this.countUserCreate = countUserCreate;
	}
	public long getCountRoles() {
		return countRoles;
	}
	public void setCountRoles(long countRoles) {
		this.countRoles = countRoles;
	}
	public long getCountBlogsEntry() {
		return countBlogsEntry;
	}
	public void setCountBlogsEntry(long countBlogsEntry) {
		this.countBlogsEntry = countBlogsEntry;
	}
	public long getCountWikiesPage() {
		return countWikiesPage;
	}
	public void setCountWikiesPage(long countWikiesPage) {
		this.countWikiesPage = countWikiesPage;
	}
	public long getCountGroups() {
		return countGroups;
	}
	public void setCountGroups(long countGroups) {
		this.countGroups = countGroups;
	}
	public long getCountAssetsEntry() {
		return countAssetsEntry;
	}
	public void setCountAssetsEntry(long countAssetsEntry) {
		this.countAssetsEntry = countAssetsEntry;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public long getCountRatingsEntry() {
		return countRatingsEntry;
	}
	public void setCountRatingsEntry(long countRatingsEntry) {
		this.countRatingsEntry = countRatingsEntry;
	}
	public long getCountCalEvents() {
		return countCalEvents;
	}
	public void setCountCalEvents(long countCalEvents) {
		this.countCalEvents = countCalEvents;
	}
	
	
}
