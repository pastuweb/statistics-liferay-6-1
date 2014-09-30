package com.appuntivarinet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javax.portlet.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.util.PortalUtil;


public class MyStatistics extends GenericPortlet {
	
	public static Log log = LogFactory.getLog("MyStatistics");
	
	/* initialize the default parameter of "portlet.xml" */
	protected String editJSP;
	protected String viewJSP;
	
	public void init() throws PortletException{
		editJSP = getInitParameter("edit-jsp");
		viewJSP = getInitParameter("view-jsp");
	}
	
	
	//set the Portlet's default View
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException{
		
		PortletPreferences prefs = renderRequest.getPreferences();
		
		TableDatas tableDatas = new TableDatas();//inizializza tutto a -1
		ChartDatas chartDatas = new ChartDatas();
				
		String typeStatistics = (String) prefs.getValue("typeStatistics", "");
		String typeDataView = (String) prefs.getValue("typeDataView", "");
		
		System.out.println("\n\ndoView");
		String chkUserCreate = (String) prefs.getValue("chkUserCreate", "");
		System.out.println("chkUserCreate "+chkUserCreate);
		String chkRoles = (String) prefs.getValue("chkRoles", "");
		System.out.println("chkRoles "+chkRoles);
		String chkBlogEntry = (String) prefs.getValue("chkBlogEntry", "");
		System.out.println("chkBlogEntry "+chkBlogEntry);
		String chkWikiPage = (String) prefs.getValue("chkWikiPage", "");
		System.out.println("chkWikiPage "+chkWikiPage);
		String chkGroups = (String) prefs.getValue("chkGroups", "");
		System.out.println("chkGroups "+chkGroups);
		String chkAssetEntry = (String) prefs.getValue("chkAssetEntry", "");
		System.out.println("chkAssetEntry "+chkAssetEntry);
		String chkUsersLastLogin = (String) prefs.getValue("chkUsersLastLogin", "");
		System.out.println("chkUsersLastLogin "+chkUsersLastLogin);
		String chkRatingsEntry = (String) prefs.getValue("chkRatingsEntry", "");
		System.out.println("chkRatingsEntry "+chkRatingsEntry);
		String chkCalEvents = (String) prefs.getValue("chkCalEvents", "");
		System.out.println("chkCalEvents "+chkCalEvents);
		
		String typeCharts = (String) prefs.getValue("typeCharts", "");
		String typeResults = (String) prefs.getValue("typeResults", "");
		String typePartition = (String) prefs.getValue("typePartition", "");
		String dateRangeFrom = (String) prefs.getValue("dateRangeFrom", "");
		String dateRangeTo = (String) prefs.getValue("dateRangeTo", "");
		
		String chartStatistics = (String) prefs.getValue("chartStatistics", "");
		String widthChart = (String) prefs.getValue("widthChart", "");
		String heightChart = (String) prefs.getValue("heightChart", "");
		
		String notDefinedMessage = (String) prefs.getValue("notDefinedMessage", "");
		
		/* Default value */
		if(typeStatistics.equals("") || typeDataView.equals("")){
			notDefinedMessage = "click on Preferences";
			renderRequest.setAttribute("notDefinedMessage", notDefinedMessage);
		}else if(typeDataView.equals("TABLE")){
			
			tableDatas = new TableDatas();//inizializza tutto a -1
			System.out.println("\n\nExecute Utils");
			try {
				
				if(chkUserCreate.equals("true")){
					tableDatas.setCountUserCreate(Utils.getCountUserCreate(typeStatistics));
					System.out.println("chkUserCreate TRUE");
				}else{
					tableDatas.setCountUserCreate(-1);
				}
				if(chkRoles.equals("true")){
					tableDatas.setCountRoles(Utils.getCountRoles(typeStatistics));
					System.out.println("chkRoles TRUE");
				}else{
					tableDatas.setCountRoles(-1);
				}
				if(chkBlogEntry.equals("true")){
					tableDatas.setCountBlogsEntry(Utils.getCountBlogsEntry(typeStatistics));
					System.out.println("chkBlogEntry TRUE");
				}else{
					tableDatas.setCountBlogsEntry(-1);
				}
				if(chkWikiPage.equals("true")){
					tableDatas.setCountWikiesPage(Utils.getCountWikiesPage(typeStatistics));
					System.out.println("chkWikiPage TRUE");
				}else{
					tableDatas.setCountWikiesPage(-1);
				}
				if(chkGroups.equals("true")){
					tableDatas.setCountGroups(Utils.getCountGroups(typeStatistics));
					System.out.println("chkGroups TRUE");
				}else{
					tableDatas.setCountGroups(-1);
				}
				if(chkAssetEntry.equals("true")){
					tableDatas.setCountAssetsEntry(Utils.getCountAssetsEntry(typeStatistics));
					System.out.println("chkAssetEntry TRUE");
				}else{
					tableDatas.setCountAssetsEntry(-1);
				}
				if(chkUsersLastLogin.equals("true")){
					tableDatas.setLastLogin(Utils.getLastLogin(typeStatistics));
					System.out.println("chkUsersLastLogin TRUE");
				}else{
					tableDatas.setLastLogin("");
				}
				if(chkRatingsEntry.equals("true")){
					tableDatas.setCountRatingsEntry(Utils.getCountRatingsEntry(typeStatistics));
					System.out.println("chkRatingsEntry TRUE");
				}else{
					tableDatas.setCountRatingsEntry(-1);
				}
				if(chkCalEvents.equals("true")){
					tableDatas.setCountCalEvents(Utils.getCountCalEvents(typeStatistics));
					System.out.println("chkCalEvents TRUE");
				}else{
					tableDatas.setCountCalEvents(-1);
				}
				
				
				
				
			} catch (SystemException e) {
				e.printStackTrace();
			}
			
			renderRequest.setAttribute("typeStatistics", typeStatistics);
			renderRequest.setAttribute("typeDataView", typeDataView);
			renderRequest.setAttribute("tableDatas", tableDatas);
			
			renderRequest.setAttribute("notDefinedMessage", new String(""));
			renderRequest.setAttribute("typeCharts", null);
			renderRequest.setAttribute("typeResults", null);
			renderRequest.setAttribute("typePartition", null);
			renderRequest.setAttribute("chartDatas", null);
			renderRequest.setAttribute("dateRangeFrom", null);
			renderRequest.setAttribute("dateRangeTo", null);
			renderRequest.setAttribute("chartStatistics", null);
		
		}else if(typeDataView.equals("CHARTS")){
			
			
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH); 			 
			
			try {
				Date dateFrom = new Date();
				dateFrom = originalFormat.parse(dateRangeFrom);
				Date dateTo = new Date();
				dateTo = originalFormat.parse(dateRangeTo);
				
				System.out.println("chartStatistics: "+chartStatistics);
				if(chartStatistics.equals("Users Create")){
					chartDatas.setPointList(Utils.getChartsPoint_UsersCreate(typeStatistics, typeResults, typePartition, dateFrom, dateTo));
				}else if(chartStatistics.equals("Blogs Entry")){
					chartDatas.setPointList(Utils.getChartsPoint_BlogsEntry(typeStatistics, typeResults, typePartition, dateFrom, dateTo));
				}else if(chartStatistics.equals("Wiki Pages")){
					chartDatas.setPointList(Utils.getChartsPoint_WikiPages(typeStatistics, typeResults, typePartition, dateFrom, dateTo));
				}else if(chartStatistics.equals("Assetts Entry")){
					chartDatas.setPointList(Utils.getChartsPoint_AssettsEntry(typeStatistics, typeResults, typePartition, dateFrom, dateTo));				
				}else if(chartStatistics.equals("Ratings Entry")){
					chartDatas.setPointList(Utils.getChartsPoint_RatingsEntry(typeStatistics, typeResults, typePartition, dateFrom, dateTo));
				}else if(chartStatistics.equals("Calendar Events")){
					chartDatas.setPointList(Utils.getChartsPoint_CalendarEvents(typeStatistics, typeResults, typePartition, dateFrom, dateTo));
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (SystemException e) {
				e.printStackTrace();
			} 
			
			renderRequest.setAttribute("typeStatistics", typeStatistics);
			renderRequest.setAttribute("typeDataView", typeDataView);
			renderRequest.setAttribute("typeCharts", typeCharts);
			renderRequest.setAttribute("typeResults", typeResults);
			renderRequest.setAttribute("typePartition", typePartition);
			renderRequest.setAttribute("dateRangeFrom", dateRangeFrom);
			renderRequest.setAttribute("dateRangeTo", dateRangeTo);
			renderRequest.setAttribute("chartStatistics", chartStatistics);
			renderRequest.setAttribute("widthChart", widthChart);
			renderRequest.setAttribute("heightChart", heightChart);
			
			renderRequest.setAttribute("chartDatas", chartDatas);
			
			renderRequest.setAttribute("notDefinedMessage", new String(""));
			renderRequest.setAttribute("tableDatas", null);
		}

		include(viewJSP, renderRequest, renderResponse);
	}
	
	
	/* special method: used to dispatch to right JSP */
	protected void include(String path, RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException{
		PortletRequestDispatcher portletRequestDispatcher = getPortletContext().getRequestDispatcher(path);
		if(portletRequestDispatcher == null){
			log.info("path : "+path+" non e' valido.");
		}else{
			portletRequestDispatcher.include(renderRequest, renderResponse);
		}
	}
	
	/* set the Portlet's default Edit: it's a simple <form> */
	public void doEdit(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException{
		
		renderResponse.setContentType("text/html");
		PortletURL saveSettingsStatisticsURL = renderResponse.createActionURL();
		saveSettingsStatisticsURL.setParameter("saveSettingsStatistics", "saveSettingsStatistics");
		renderRequest.setAttribute("saveSettingsStatisticsURL", saveSettingsStatisticsURL.toString());

		include(editJSP, renderRequest, renderResponse);
		
	}
	
	
	/* ACTION call from Portlet's <form> of EDIT JSP */
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException{
		
		String saveSettingsStatistics = actionRequest.getParameter("saveSettingsStatistics");
		/*You can add other getParameter of EDIT JSP*/
		
		if(saveSettingsStatistics != null){
			PortletPreferences prefs = actionRequest.getPreferences();
			
			prefs.setValue("typeStatistics","");
			prefs.setValue("typeDataView","");
			prefs.setValue("chkUserCreate","");
			prefs.setValue("chkRoles","");
			prefs.setValue("chkBlogEntry","");
			prefs.setValue("chkWikiPage","");
			prefs.setValue("chkGroups","");
			prefs.setValue("chkAssetEntry","");
			prefs.setValue("chkUsersLastLogin","");
			prefs.setValue("chkRatingsEntry","");
			prefs.setValue("chkCalEvents","");
			prefs.setValue("typeCharts","");
			prefs.setValue("typeResults","");
			prefs.setValue("typePartition","");
			prefs.setValue("dateRangeFrom","");
			prefs.setValue("dateRangeTo","");
			prefs.setValue("chartStatistics","");
			prefs.setValue("widthChart","");
			prefs.setValue("heightChart","");
			
			System.out.println("\n\ndoEdit");
			if(actionRequest.getParameter("inTypeStatistics")!= null)
				prefs.setValue("typeStatistics", actionRequest.getParameter("inTypeStatistics"));
			if(actionRequest.getParameter("inTypeDataView")!= null)
				prefs.setValue("typeDataView", actionRequest.getParameter("inTypeDataView"));
			if(actionRequest.getParameter("inUserCreate")!= null)
				prefs.setValue("chkUserCreate", actionRequest.getParameter("inUserCreate"));
				System.out.println("chkUserCreate "+actionRequest.getParameter("inUserCreate"));
			if(actionRequest.getParameter("inRoles")!= null)
				prefs.setValue("chkRoles", actionRequest.getParameter("inRoles"));
				System.out.println("chkRoles "+actionRequest.getParameter("inRoles"));
			if(actionRequest.getParameter("inBlogEntry")!= null)
				prefs.setValue("chkBlogEntry", actionRequest.getParameter("inBlogEntry"));
				System.out.println("chkBlogEntry "+actionRequest.getParameter("inBlogEntry"));
			if(actionRequest.getParameter("inWikiPage")!= null)
				prefs.setValue("chkWikiPage", actionRequest.getParameter("inWikiPage"));
				System.out.println("chkWikiPage "+actionRequest.getParameter("inWikiPage"));
			if(actionRequest.getParameter("inGroups")!= null)
				prefs.setValue("chkGroups", actionRequest.getParameter("inGroups"));
				System.out.println("chkGroups "+actionRequest.getParameter("inGroups"));
			if(actionRequest.getParameter("inAssetEntry")!= null)
				prefs.setValue("chkAssetEntry", actionRequest.getParameter("inAssetEntry"));
				System.out.println("chkAssetEntry "+actionRequest.getParameter("inAssetEntry"));
			if(actionRequest.getParameter("inUsersLastLogin")!= null)
				prefs.setValue("chkUsersLastLogin", actionRequest.getParameter("inUsersLastLogin"));
				System.out.println("chkUsersLastLogin "+actionRequest.getParameter("inUsersLastLogin"));
			if(actionRequest.getParameter("inRatingsEntry")!= null)
				prefs.setValue("chkRatingsEntry", actionRequest.getParameter("inRatingsEntry"));
				System.out.println("chkRatingsEntry "+actionRequest.getParameter("inRatingsEntry"));
			if(actionRequest.getParameter("inCalEvents")!= null)
				prefs.setValue("chkCalEvents", actionRequest.getParameter("inCalEvents"));
				System.out.println("chkCalEvents "+actionRequest.getParameter("inCalEvents"));
			if(actionRequest.getParameter("inTypeCharts")!= null)
				prefs.setValue("typeCharts", actionRequest.getParameter("inTypeCharts"));
			if(actionRequest.getParameter("inTypeResults")!= null)
				prefs.setValue("typeResults", actionRequest.getParameter("inTypeResults"));
			if(actionRequest.getParameter("inTypePartition")!= null)
				prefs.setValue("typePartition", actionRequest.getParameter("inTypePartition"));
			if(actionRequest.getParameter("from")!= null)
				prefs.setValue("dateRangeFrom", actionRequest.getParameter("from"));
			if(actionRequest.getParameter("to")!= null)
				prefs.setValue("dateRangeTo", actionRequest.getParameter("to"));
			if(actionRequest.getParameter("inChartStatistics")!= null)
				prefs.setValue("chartStatistics", actionRequest.getParameter("inChartStatistics"));
			
			if(actionRequest.getParameter("inWidthChart")!= null)
				prefs.setValue("widthChart", actionRequest.getParameter("inWidthChart"));
			
			if(actionRequest.getParameter("inHeightChart")!= null)
				prefs.setValue("heightChart", actionRequest.getParameter("inHeightChart"));
			
			
			
			prefs.store();
			actionResponse.setPortletMode(PortletMode.VIEW);
		}
		
		
		
	}

	
}
