package com.appuntivarinet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryServiceUtil;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.calendar.service.CalEventLocalServiceUtil;
import com.liferay.portlet.ratings.model.RatingsEntry;
import com.liferay.portlet.ratings.service.RatingsEntryLocalServiceUtil;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;

public class Utils {

	public static Log log = LogFactory.getLog("Utils");
	
	public static long getCountUserCreate(String typeStatistics) throws SystemException{
		if(typeStatistics.equals("PORTAL")){
			//portal
			log.info("PORTAL");
			return UserLocalServiceUtil.getUsersCount();
		}else{
			//logged user
			log.info("logged userId = "+PrincipalThreadLocal.getUserId());
			return -1;
		}
	}
	
	
	
	public static List<ChartPoints> getChartsPoint_UsersCreate(String typeStatistics, String typeResult, String typePartition, Date from, Date to) throws SystemException{
		
		List<ChartPoints> lista = new ArrayList<ChartPoints>();
		
		ChartPoints temp = new ChartPoints();

		boolean firstTime = true;
		int cont = 0;
		
		String currentDayMonthYear = null; //from 1 to 31 
		String currentDayWeek = null; //from 1 to 7
		String currentMonthYear = null; //from 1 to 12
		String currentYear = null;

		SimpleDateFormat targetFormatDayMonthYear = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		SimpleDateFormat targetFormatDayWeek= new SimpleDateFormat("EE", Locale.ENGLISH);
		SimpleDateFormat targetFormatMonthYear = new SimpleDateFormat("MM-yyyy", Locale.ENGLISH);
		SimpleDateFormat targetFormatYear = new SimpleDateFormat("yyyy", Locale.ENGLISH);
	
		if(typeStatistics.equals("PORTAL")){
			log.info("size UserLocalServiceUtil.getUsers = "+UserLocalServiceUtil.getUsersCount() );
			
			for(User item: UserLocalServiceUtil.getUsers(0, UserLocalServiceUtil.getUsersCount())){
				if(item.getCreateDate().after(from) && item.getCreateDate().before(to)){
					
					if(typePartition.equals("DAY")){
						
						if(firstTime == true){
							currentDayMonthYear = targetFormatDayMonthYear.format(item.getCreateDate());
							log.info("dateX = "+currentDayMonthYear);
							temp.setX(currentDayMonthYear);
							firstTime = false;
						}
						
						if(currentDayMonthYear.equals(targetFormatDayMonthYear.format(item.getCreateDate()))){
							cont++;
							log.info("cont = "+cont);
						}else{
							//date change
							//store Y
							if(typeResult.equals("TOT")){
								temp.setY(cont);
							}
							lista.add(temp);
							log.info("lista.add");
							
							//change
							currentDayMonthYear = targetFormatDayMonthYear.format(item.getCreateDate());
							log.info("dateX change = "+currentDayMonthYear );
							temp = new ChartPoints();
							temp.setX(currentDayMonthYear);
							cont=0;
							cont++;
							log.info("cont change = "+cont);
						}

					}else if(typePartition.equals("MONTH")){
						
						if(firstTime == true){
							currentMonthYear = targetFormatMonthYear.format(item.getCreateDate());
							log.info("dateX = "+currentMonthYear);
							temp.setX(currentMonthYear);
							firstTime = false;
						}
						
						if(currentMonthYear.equals(targetFormatMonthYear.format(item.getCreateDate()))){
							cont++;
							log.info("cont = "+cont);
						}else{
							//date change
							//store Y
							if(typeResult.equals("TOT")){
								temp.setY(cont);
							}
							lista.add(temp);
							log.info("lista.add");
							
							//change
							currentMonthYear = targetFormatMonthYear.format(item.getCreateDate());
							log.info("dateX change = "+currentMonthYear );
							temp = new ChartPoints();
							temp.setX(currentMonthYear);
							cont=0;
							cont++;
							log.info("cont change = "+cont);
						}
						
					}else if(typePartition.equals("YEAR")){
						
						if(firstTime == true){
							currentYear = targetFormatYear.format(item.getCreateDate());
							log.info("dateX = "+currentYear);
							temp.setX(currentYear);
							firstTime = false;
						}
						
						if(currentYear.equals(targetFormatYear.format(item.getCreateDate()))){
							cont++;
							log.info("cont = "+cont);
						}else{
							//date change
							//store Y
							if(typeResult.equals("TOT")){
								temp.setY(cont);
							}
							lista.add(temp);
							log.info("lista.add");
							
							//change
							currentYear = targetFormatYear.format(item.getCreateDate());
							log.info("dateX change = "+currentYear );
							temp = new ChartPoints();
							temp.setX(currentYear);
							cont=0;
							cont++;
							log.info("cont change = "+cont);
						}
						
					}

				}
			}

			//in last iter, could be only cont++ and NOT lista.add. So lista.add also out of cycle.
			if(UserLocalServiceUtil.getUsersCount() > 0){
				if(typeResult.equals("TOT")){
					temp.setY(cont);
				}
				lista.add(temp);
				log.info("lista.add");
			}
			
		}
		
		
		return lista;
	}
	
public static List<ChartPoints> getChartsPoint_BlogsEntry(String typeStatistics, String typeResult, String typePartition, Date from, Date to) throws SystemException{
		
		List<ChartPoints> lista = new ArrayList<ChartPoints>();
		
		ChartPoints temp = new ChartPoints();
		boolean firstTime = true;
		int cont = 0;
		
		String currentDayMonthYear = null; //from 1 to 31 
		String currentDayWeek = null; //from 1 to 7
		String currentMonthYear = null; //from 1 to 12
		String currentYear = null;

		SimpleDateFormat targetFormatDayMonthYear = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		SimpleDateFormat targetFormatDayWeek= new SimpleDateFormat("EE", Locale.ENGLISH);
		SimpleDateFormat targetFormatMonthYear = new SimpleDateFormat("MM-yyyy", Locale.ENGLISH);
		SimpleDateFormat targetFormatYear = new SimpleDateFormat("yyyy", Locale.ENGLISH);
	
		log.info("size BlogsEntryLocalServiceUtil.getBlogsEntries = "+BlogsEntryLocalServiceUtil.getBlogsEntriesCount() );
			
			for(BlogsEntry item: BlogsEntryLocalServiceUtil.getBlogsEntries(0, BlogsEntryLocalServiceUtil.getBlogsEntriesCount())){
				if(item.getCreateDate().after(from) && item.getCreateDate().before(to)){
					
					//test PORTAL OR LOGGED USER
					if(typeStatistics.equals("PORTAL") || 
							( typeStatistics.equals("LOGGED USER") && item.getUserId() == PrincipalThreadLocal.getUserId()) ){
						if(typePartition.equals("DAY")){
							
							if(firstTime == true){
								currentDayMonthYear = targetFormatDayMonthYear.format(item.getCreateDate());
								log.info("dateX = "+currentDayMonthYear);
								temp.setX(currentDayMonthYear);
								firstTime = false;
							}
							
							if(currentDayMonthYear.equals(targetFormatDayMonthYear.format(item.getCreateDate()))){
								cont++;
								log.info("cont = "+cont);
							}else{
								//date change
								//store Y
								if(typeResult.equals("TOT")){
									temp.setY(cont);
								}
								lista.add(temp);
								log.info("lista.add");
								
								//change
								currentDayMonthYear = targetFormatDayMonthYear.format(item.getCreateDate());
								log.info("dateX change = "+currentDayMonthYear );
								temp = new ChartPoints();
								temp.setX(currentDayMonthYear);
								cont=0;
								cont++;
								log.info("cont change = "+cont);
							}
	
						}else if(typePartition.equals("MONTH")){
							
							if(firstTime == true){
								currentMonthYear = targetFormatMonthYear.format(item.getCreateDate());
								log.info("dateX = "+currentMonthYear);
								temp.setX(currentMonthYear);
								firstTime = false;
							}
							
							if(currentMonthYear.equals(targetFormatMonthYear.format(item.getCreateDate()))){
								cont++;
								log.info("cont = "+cont);
							}else{
								//date change
								//store Y
								if(typeResult.equals("TOT")){
									temp.setY(cont);
								}
								lista.add(temp);
								log.info("lista.add");
								
								//change
								currentMonthYear = targetFormatMonthYear.format(item.getCreateDate());
								log.info("dateX change = "+currentMonthYear );
								temp = new ChartPoints();
								temp.setX(currentMonthYear);
								cont=0;
								cont++;
								log.info("cont change = "+cont);
							}
							
						}else if(typePartition.equals("YEAR")){
							
							if(firstTime == true){
								currentYear = targetFormatYear.format(item.getCreateDate());
								log.info("dateX = "+currentYear);
								temp.setX(currentYear);
								firstTime = false;
							}
							
							if(currentYear.equals(targetFormatYear.format(item.getCreateDate()))){
								cont++;
								log.info("cont = "+cont);
							}else{
								//date change
								//store Y
								if(typeResult.equals("TOT")){
									temp.setY(cont);
								}
								lista.add(temp);
								log.info("lista.add");
								
								//change
								currentYear = targetFormatYear.format(item.getCreateDate());
								log.info("dateX change = "+currentYear );
								temp = new ChartPoints();
								temp.setX(currentYear);
								cont=0;
								cont++;
								log.info("cont change = "+cont);
							}
							
						}
					
					}
					
					

				}
			}

			//in last iter, could be only cont++ and NOT lista.add. So lista.add also out of cycle.
			if(BlogsEntryLocalServiceUtil.getBlogsEntriesCount() > 0){
				if(typeResult.equals("TOT")){
					temp.setY(cont);
				}
				lista.add(temp);
				log.info("lista.add");
			}
	
		
		return lista;
	}

public static List<ChartPoints> getChartsPoint_WikiPages(String typeStatistics, String typeResult, String typePartition, Date from, Date to) throws SystemException{
	
	List<ChartPoints> lista = new ArrayList<ChartPoints>();
	
	ChartPoints temp = new ChartPoints();
	boolean firstTime = true;
	int cont = 0;
	
	String currentDayMonthYear = null; //from 1 to 31 
	String currentDayWeek = null; //from 1 to 7
	String currentMonthYear = null; //from 1 to 12
	String currentYear = null;

	SimpleDateFormat targetFormatDayMonthYear = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
	SimpleDateFormat targetFormatDayWeek= new SimpleDateFormat("EE", Locale.ENGLISH);
	SimpleDateFormat targetFormatMonthYear = new SimpleDateFormat("MM-yyyy", Locale.ENGLISH);
	SimpleDateFormat targetFormatYear = new SimpleDateFormat("yyyy", Locale.ENGLISH);

	log.info("size WikiPageLocalServiceUtil.getWikiPagesCount = "+WikiPageLocalServiceUtil.getWikiPagesCount() );
		
		for(WikiPage item: WikiPageLocalServiceUtil.getWikiPages(0, WikiPageLocalServiceUtil.getWikiPagesCount())){
			if(item.getCreateDate().after(from) && item.getCreateDate().before(to)){
				
				//test PORTAL OR LOGGED USER
				if(typeStatistics.equals("PORTAL") || 
						( typeStatistics.equals("LOGGED USER") && item.getUserId() == PrincipalThreadLocal.getUserId()) ){
					if(typePartition.equals("DAY")){
						
						if(firstTime == true){
							currentDayMonthYear = targetFormatDayMonthYear.format(item.getCreateDate());
							log.info("dateX = "+currentDayMonthYear);
							temp.setX(currentDayMonthYear);
							firstTime = false;
						}
						
						if(currentDayMonthYear.equals(targetFormatDayMonthYear.format(item.getCreateDate()))){
							cont++;
							log.info("cont = "+cont);
						}else{
							//date change
							//store Y
							if(typeResult.equals("TOT")){
								temp.setY(cont);
							}
							lista.add(temp);
							log.info("lista.add");
							
							//change
							currentDayMonthYear = targetFormatDayMonthYear.format(item.getCreateDate());
							log.info("dateX change = "+currentDayMonthYear );
							temp = new ChartPoints();
							temp.setX(currentDayMonthYear);
							cont=0;
							cont++;
							log.info("cont change = "+cont);
						}

					}else if(typePartition.equals("MONTH")){
						
						if(firstTime == true){
							currentMonthYear = targetFormatMonthYear.format(item.getCreateDate());
							log.info("dateX = "+currentMonthYear);
							temp.setX(currentMonthYear);
							firstTime = false;
						}
						
						if(currentMonthYear.equals(targetFormatMonthYear.format(item.getCreateDate()))){
							cont++;
							log.info("cont = "+cont);
						}else{
							//date change
							//store Y
							if(typeResult.equals("TOT")){
								temp.setY(cont);
							}
							lista.add(temp);
							log.info("lista.add");
							
							//change
							currentMonthYear = targetFormatMonthYear.format(item.getCreateDate());
							log.info("dateX change = "+currentMonthYear );
							temp = new ChartPoints();
							temp.setX(currentMonthYear);
							cont=0;
							cont++;
							log.info("cont change = "+cont);
						}
						
					}else if(typePartition.equals("YEAR")){
						
						if(firstTime == true){
							currentYear = targetFormatYear.format(item.getCreateDate());
							log.info("dateX = "+currentYear);
							temp.setX(currentYear);
							firstTime = false;
						}
						
						if(currentYear.equals(targetFormatYear.format(item.getCreateDate()))){
							cont++;
							log.info("cont = "+cont);
						}else{
							//date change
							//store Y
							if(typeResult.equals("TOT")){
								temp.setY(cont);
							}
							lista.add(temp);
							log.info("lista.add");
							
							//change
							currentYear = targetFormatYear.format(item.getCreateDate());
							log.info("dateX change = "+currentYear );
							temp = new ChartPoints();
							temp.setX(currentYear);
							cont=0;
							cont++;
							log.info("cont change = "+cont);
						}
						
					}
				
				}
				
				

			}
		}

		//in last iter, could be only cont++ and NOT lista.add. So lista.add also out of cycle.
		if(WikiPageLocalServiceUtil.getWikiPagesCount() > 0){
			if(typeResult.equals("TOT")){
				temp.setY(cont);
			}
			lista.add(temp);
			log.info("lista.add");
		}

	
	return lista;
}
	
public static List<ChartPoints> getChartsPoint_AssettsEntry(String typeStatistics, String typeResult, String typePartition, Date from, Date to) throws SystemException{
	
	List<ChartPoints> lista = new ArrayList<ChartPoints>();
	
	ChartPoints temp = new ChartPoints();
	boolean firstTime = true;
	int cont = 0;
	
	String currentDayMonthYear = null; //from 1 to 31 
	String currentDayWeek = null; //from 1 to 7
	String currentMonthYear = null; //from 1 to 12
	String currentYear = null;

	SimpleDateFormat targetFormatDayMonthYear = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
	SimpleDateFormat targetFormatDayWeek= new SimpleDateFormat("EE", Locale.ENGLISH);
	SimpleDateFormat targetFormatMonthYear = new SimpleDateFormat("MM-yyyy", Locale.ENGLISH);
	SimpleDateFormat targetFormatYear = new SimpleDateFormat("yyyy", Locale.ENGLISH);

	log.info("size AssetEntryLocalServiceUtil.getAssetEntriesCount = "+AssetEntryLocalServiceUtil.getAssetEntriesCount() );
		
		for(AssetEntry item: AssetEntryLocalServiceUtil.getAssetEntries(0, AssetEntryLocalServiceUtil.getAssetEntriesCount())){
			if(item.getCreateDate().after(from) && item.getCreateDate().before(to)){
				
				//test PORTAL OR LOGGED USER
				if(typeStatistics.equals("PORTAL") || 
						( typeStatistics.equals("LOGGED USER") && item.getUserId() == PrincipalThreadLocal.getUserId()) ){
					if(typePartition.equals("DAY")){
						
						if(firstTime == true){
							currentDayMonthYear = targetFormatDayMonthYear.format(item.getCreateDate());
							log.info("dateX = "+currentDayMonthYear);
							temp.setX(currentDayMonthYear);
							firstTime = false;
						}
						
						if(currentDayMonthYear.equals(targetFormatDayMonthYear.format(item.getCreateDate()))){
							cont++;
							log.info("cont = "+cont);
						}else{
							//date change
							//store Y
							if(typeResult.equals("TOT")){
								temp.setY(cont);
							}
							lista.add(temp);
							log.info("lista.add");
							
							//change
							currentDayMonthYear = targetFormatDayMonthYear.format(item.getCreateDate());
							log.info("dateX change = "+currentDayMonthYear );
							temp = new ChartPoints();
							temp.setX(currentDayMonthYear);
							cont=0;
							cont++;
							log.info("cont change = "+cont);
						}

					}else if(typePartition.equals("MONTH")){
						
						if(firstTime == true){
							currentMonthYear = targetFormatMonthYear.format(item.getCreateDate());
							log.info("dateX = "+currentMonthYear);
							temp.setX(currentMonthYear);
							firstTime = false;
						}
						
						if(currentMonthYear.equals(targetFormatMonthYear.format(item.getCreateDate()))){
							cont++;
							log.info("cont = "+cont);
						}else{
							//date change
							//store Y
							if(typeResult.equals("TOT")){
								temp.setY(cont);
							}
							lista.add(temp);
							log.info("lista.add");
							
							//change
							currentMonthYear = targetFormatMonthYear.format(item.getCreateDate());
							log.info("dateX change = "+currentMonthYear );
							temp = new ChartPoints();
							temp.setX(currentMonthYear);
							cont=0;
							cont++;
							log.info("cont change = "+cont);
						}
						
					}else if(typePartition.equals("YEAR")){
						
						if(firstTime == true){
							currentYear = targetFormatYear.format(item.getCreateDate());
							log.info("dateX = "+currentYear);
							temp.setX(currentYear);
							firstTime = false;
						}
						
						if(currentYear.equals(targetFormatYear.format(item.getCreateDate()))){
							cont++;
							log.info("cont = "+cont);
						}else{
							//date change
							//store Y
							if(typeResult.equals("TOT")){
								temp.setY(cont);
							}
							lista.add(temp);
							log.info("lista.add");
							
							//change
							currentYear = targetFormatYear.format(item.getCreateDate());
							log.info("dateX change = "+currentYear );
							temp = new ChartPoints();
							temp.setX(currentYear);
							cont=0;
							cont++;
							log.info("cont change = "+cont);
						}
						
					}
				
				}
				
				

			}
		}

		//in last iter, could be only cont++ and NOT lista.add. So lista.add also out of cycle.
		if(AssetEntryLocalServiceUtil.getAssetEntriesCount() > 0){
			if(typeResult.equals("TOT")){
				temp.setY(cont);
			}
			lista.add(temp);
			log.info("lista.add");
		}

	
	return lista;
}

public static List<ChartPoints> getChartsPoint_RatingsEntry(String typeStatistics, String typeResult, String typePartition, Date from, Date to) throws SystemException{
	
	List<ChartPoints> lista = new ArrayList<ChartPoints>();
	
	ChartPoints temp = new ChartPoints();
	boolean firstTime = true;
	int cont = 0;
	
	String currentDayMonthYear = null; //from 1 to 31 
	String currentDayWeek = null; //from 1 to 7
	String currentMonthYear = null; //from 1 to 12
	String currentYear = null;

	SimpleDateFormat targetFormatDayMonthYear = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
	SimpleDateFormat targetFormatDayWeek= new SimpleDateFormat("EE", Locale.ENGLISH);
	SimpleDateFormat targetFormatMonthYear = new SimpleDateFormat("MM-yyyy", Locale.ENGLISH);
	SimpleDateFormat targetFormatYear = new SimpleDateFormat("yyyy", Locale.ENGLISH);

	log.info("size RatingsEntryLocalServiceUtil.getRatingsEntriesCount = "+RatingsEntryLocalServiceUtil.getRatingsEntriesCount() );
		
		for(RatingsEntry item: RatingsEntryLocalServiceUtil.getRatingsEntries(0, RatingsEntryLocalServiceUtil.getRatingsEntriesCount())){
			if(item.getCreateDate().after(from) && item.getCreateDate().before(to)){
				
				//test PORTAL OR LOGGED USER
				if(typeStatistics.equals("PORTAL") || 
						( typeStatistics.equals("LOGGED USER") && item.getUserId() == PrincipalThreadLocal.getUserId()) ){
					if(typePartition.equals("DAY")){
						
						if(firstTime == true){
							currentDayMonthYear = targetFormatDayMonthYear.format(item.getCreateDate());
							log.info("dateX = "+currentDayMonthYear);
							temp.setX(currentDayMonthYear);
							firstTime = false;
						}
						
						if(currentDayMonthYear.equals(targetFormatDayMonthYear.format(item.getCreateDate()))){
							cont++;
							log.info("cont = "+cont);
						}else{
							//date change
							//store Y
							if(typeResult.equals("TOT")){
								temp.setY(cont);
							}
							lista.add(temp);
							log.info("lista.add");
							
							//change
							currentDayMonthYear = targetFormatDayMonthYear.format(item.getCreateDate());
							log.info("dateX change = "+currentDayMonthYear );
							temp = new ChartPoints();
							temp.setX(currentDayMonthYear);
							cont=0;
							cont++;
							log.info("cont change = "+cont);
						}

					}else if(typePartition.equals("MONTH")){
						
						if(firstTime == true){
							currentMonthYear = targetFormatMonthYear.format(item.getCreateDate());
							log.info("dateX = "+currentMonthYear);
							temp.setX(currentMonthYear);
							firstTime = false;
						}
						
						if(currentMonthYear.equals(targetFormatMonthYear.format(item.getCreateDate()))){
							cont++;
							log.info("cont = "+cont);
						}else{
							//date change
							//store Y
							if(typeResult.equals("TOT")){
								temp.setY(cont);
							}
							lista.add(temp);
							log.info("lista.add");
							
							//change
							currentMonthYear = targetFormatMonthYear.format(item.getCreateDate());
							log.info("dateX change = "+currentMonthYear );
							temp = new ChartPoints();
							temp.setX(currentMonthYear);
							cont=0;
							cont++;
							log.info("cont change = "+cont);
						}
						
					}else if(typePartition.equals("YEAR")){
						
						if(firstTime == true){
							currentYear = targetFormatYear.format(item.getCreateDate());
							log.info("dateX = "+currentYear);
							temp.setX(currentYear);
							firstTime = false;
						}
						
						if(currentYear.equals(targetFormatYear.format(item.getCreateDate()))){
							cont++;
							log.info("cont = "+cont);
						}else{
							//date change
							//store Y
							if(typeResult.equals("TOT")){
								temp.setY(cont);
							}
							lista.add(temp);
							log.info("lista.add");
							
							//change
							currentYear = targetFormatYear.format(item.getCreateDate());
							log.info("dateX change = "+currentYear );
							temp = new ChartPoints();
							temp.setX(currentYear);
							cont=0;
							cont++;
							log.info("cont change = "+cont);
						}
						
					}
				
				}
				
				

			}
		}

		//in last iter, could be only cont++ and NOT lista.add. So lista.add also out of cycle.
		if(RatingsEntryLocalServiceUtil.getRatingsEntriesCount() > 0){
			if(typeResult.equals("TOT")){
				temp.setY(cont);
			}
			lista.add(temp);
			log.info("lista.add");
		}

	
	return lista;
}

public static List<ChartPoints> getChartsPoint_CalendarEvents(String typeStatistics, String typeResult, String typePartition, Date from, Date to) throws SystemException{
	
	List<ChartPoints> lista = new ArrayList<ChartPoints>();
	
	ChartPoints temp = new ChartPoints();
	boolean firstTime = true;
	int cont = 0;
	
	String currentDayMonthYear = null; //from 1 to 31 
	String currentDayWeek = null; //from 1 to 7
	String currentMonthYear = null; //from 1 to 12
	String currentYear = null;

	SimpleDateFormat targetFormatDayMonthYear = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
	SimpleDateFormat targetFormatDayWeek= new SimpleDateFormat("EE", Locale.ENGLISH);
	SimpleDateFormat targetFormatMonthYear = new SimpleDateFormat("MM-yyyy", Locale.ENGLISH);
	SimpleDateFormat targetFormatYear = new SimpleDateFormat("yyyy", Locale.ENGLISH);

	log.info("size CalEventLocalServiceUtil.getCalEventsCount = "+CalEventLocalServiceUtil.getCalEventsCount() );
		
		for(CalEvent item: CalEventLocalServiceUtil.getCalEvents(0, CalEventLocalServiceUtil.getCalEventsCount())){
			if(item.getCreateDate().after(from) && item.getCreateDate().before(to)){
				
				//test PORTAL OR LOGGED USER
				if(typeStatistics.equals("PORTAL") || 
						( typeStatistics.equals("LOGGED USER") && item.getUserId() == PrincipalThreadLocal.getUserId()) ){
					if(typePartition.equals("DAY")){
						
						if(firstTime == true){
							currentDayMonthYear = targetFormatDayMonthYear.format(item.getCreateDate());
							log.info("dateX = "+currentDayMonthYear);
							temp.setX(currentDayMonthYear);
							firstTime = false;
						}
						
						if(currentDayMonthYear.equals(targetFormatDayMonthYear.format(item.getCreateDate()))){
							cont++;
							log.info("cont = "+cont);
						}else{
							//date change
							//store Y
							if(typeResult.equals("TOT")){
								temp.setY(cont);
							}
							lista.add(temp);
							log.info("lista.add");
							
							//change
							currentDayMonthYear = targetFormatDayMonthYear.format(item.getCreateDate());
							log.info("dateX change = "+currentDayMonthYear );
							temp = new ChartPoints();
							temp.setX(currentDayMonthYear);
							cont=0;
							cont++;
							log.info("cont change = "+cont);
						}

					}else if(typePartition.equals("MONTH")){
						
						if(firstTime == true){
							currentMonthYear = targetFormatMonthYear.format(item.getCreateDate());
							log.info("dateX = "+currentMonthYear);
							temp.setX(currentMonthYear);
							firstTime = false;
						}
						
						if(currentMonthYear.equals(targetFormatMonthYear.format(item.getCreateDate()))){
							cont++;
							log.info("cont = "+cont);
						}else{
							//date change
							//store Y
							if(typeResult.equals("TOT")){
								temp.setY(cont);
							}
							lista.add(temp);
							log.info("lista.add");
							
							//change
							currentMonthYear = targetFormatMonthYear.format(item.getCreateDate());
							log.info("dateX change = "+currentMonthYear );
							temp = new ChartPoints();
							temp.setX(currentMonthYear);
							cont=0;
							cont++;
							log.info("cont change = "+cont);
						}
						
					}else if(typePartition.equals("YEAR")){
						
						if(firstTime == true){
							currentYear = targetFormatYear.format(item.getCreateDate());
							log.info("dateX = "+currentYear);
							temp.setX(currentYear);
							firstTime = false;
						}
						
						if(currentYear.equals(targetFormatYear.format(item.getCreateDate()))){
							cont++;
							log.info("cont = "+cont);
						}else{
							//date change
							//store Y
							if(typeResult.equals("TOT")){
								temp.setY(cont);
							}
							lista.add(temp);
							log.info("lista.add");
							
							//change
							currentYear = targetFormatYear.format(item.getCreateDate());
							log.info("dateX change = "+currentYear );
							temp = new ChartPoints();
							temp.setX(currentYear);
							cont=0;
							cont++;
							log.info("cont change = "+cont);
						}
						
					}
				
				}
				
				

			}
		}

		//in last iter, could be only cont++ and NOT lista.add. So lista.add also out of cycle.
		if(CalEventLocalServiceUtil.getCalEventsCount() > 0){
			if(typeResult.equals("TOT")){
				temp.setY(cont);
			}
			lista.add(temp);
			log.info("lista.add");
		}

	
	return lista;
}
	
	
	public static long getCountRoles(String typeStatistics) throws SystemException{
		if(typeStatistics.equals("PORTAL")){
			//portal
			log.info("getCountRoles PORTAL");
			return RoleLocalServiceUtil.getRolesCount();
		}else{
			//logged user
			log.info("getCountRoles logged userId = "+PrincipalThreadLocal.getUserId());
			return RoleLocalServiceUtil.getUserRolesCount(PrincipalThreadLocal.getUserId());
		}
	}
	
	public static long getCountBlogsEntry(String typeStatistics) throws SystemException{
		if(typeStatistics.equals("PORTAL")){
			//portal
			log.info("getCountBlogsEntry PORTAL");
			return BlogsEntryLocalServiceUtil.getBlogsEntriesCount();
		}else{
			//logged user
			log.info("getCountBlogsEntry logged userId = "+PrincipalThreadLocal.getUserId());
			List<BlogsEntry> lista = new ArrayList<BlogsEntry>();
			
			for(BlogsEntry item: BlogsEntryLocalServiceUtil.getBlogsEntries(0, BlogsEntryLocalServiceUtil.getBlogsEntriesCount())){
				if(item.getUserId() == PrincipalThreadLocal.getUserId())
					lista.add(item);
			}
			return lista.size();
		}
	}
	
	public static long getCountWikiesPage(String typeStatistics) throws SystemException{
		if(typeStatistics.equals("PORTAL")){
			//portal
			log.info("getCountWikiesPage PORTAL");
			return WikiPageLocalServiceUtil.getWikiPagesCount();
		}else{
			//logged user
			log.info("getCountWikiesPage logged userId = "+PrincipalThreadLocal.getUserId());
			List<WikiPage> lista = new ArrayList<WikiPage>();
			
			for(WikiPage item: WikiPageLocalServiceUtil.getWikiPages(0, WikiPageLocalServiceUtil.getWikiPagesCount())){
				if(item.getUserId() == PrincipalThreadLocal.getUserId())
					lista.add(item);
			}
			return lista.size();
		}
	}
	
	public static long getCountGroups(String typeStatistics) throws SystemException{
		if(typeStatistics.equals("PORTAL")){
			//portal
			log.info("getCountGroups PORTAL");
			return GroupLocalServiceUtil.getGroupsCount();
		}else{
			//logged user
			log.info("getCountGroups logged userId = "+PrincipalThreadLocal.getUserId());
			try {
				return UserLocalServiceUtil.getUser(PrincipalThreadLocal.getUserId()).getRoles().size();
			} catch (PortalException e) {
				e.printStackTrace();
				return -1;
			}
		}
	}
	
	public static long getCountAssetsEntry(String typeStatistics) throws SystemException{
		if(typeStatistics.equals("PORTAL")){
			//portal
			log.info("getCountAssetsEntry PORTAL");
			return AssetEntryLocalServiceUtil.getAssetEntriesCount();
		}else{
			//logged user
			log.info("getCountAssetsEntry logged userId = "+PrincipalThreadLocal.getUserId());
			List<AssetEntry> lista = new ArrayList<AssetEntry>();
			
			for(AssetEntry item: AssetEntryLocalServiceUtil.getAssetEntries(0, AssetEntryLocalServiceUtil.getAssetEntriesCount())){
				if(item.getUserId() == PrincipalThreadLocal.getUserId())
					lista.add(item);
			}
			return lista.size();
		}
	}
	
	public static String getLastLogin(String typeStatistics){
		if(typeStatistics.equals("PORTAL")){
			log.info("getLastLogin PORTAL");
			return "";
		}else{
			try {
				log.info("getLastLogin logged userId = "+PrincipalThreadLocal.getUserId());
				return  UserLocalServiceUtil.getUser(PrincipalThreadLocal.getUserId()).getLastLoginDate().toString();
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
		}
	}
	
	public static long getCountRatingsEntry(String typeStatistics) throws SystemException{
		if(typeStatistics.equals("PORTAL")){
			//portal
			log.info("getCountRatingsEntry PORTAL");
			return RatingsEntryLocalServiceUtil.getRatingsEntriesCount();
		}else{
			//logged user
			log.info("getCountRatingsEntry logged userId = "+PrincipalThreadLocal.getUserId());
			List<RatingsEntry> lista = new ArrayList<RatingsEntry>();
			
			for(RatingsEntry item: RatingsEntryLocalServiceUtil.getRatingsEntries(0, RatingsEntryLocalServiceUtil.getRatingsEntriesCount())){
				if(item.getUserId() == PrincipalThreadLocal.getUserId())
					lista.add(item);
			}
			return lista.size();
		}
	}
	
	public static long getCountCalEvents(String typeStatistics) throws SystemException{
		if(typeStatistics.equals("PORTAL")){
			//portal
			log.info("getCountCalEvents PORTAL");
			return CalEventLocalServiceUtil.getCalEventsCount();
		}else{
			//logged user
			log.info("getCountCalEvents logged userId = "+PrincipalThreadLocal.getUserId());
			List<CalEvent> lista = new ArrayList<CalEvent>();
			
			for(CalEvent item: CalEventLocalServiceUtil.getCalEvents(0, CalEventLocalServiceUtil.getCalEventsCount())){
				if(item.getUserId() == PrincipalThreadLocal.getUserId())
					lista.add(item);
			}
			return lista.size();
		}
	}
	
	
}
