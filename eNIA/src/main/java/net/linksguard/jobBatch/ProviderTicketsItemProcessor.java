package net.linksguard.jobBatch;

import java.text.SimpleDateFormat;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.linksguard.dao.ProviderTicketsRepository;
import net.linksguard.entities.ProviderTickets;
 
@Component
public class ProviderTicketsItemProcessor implements ItemProcessor<ProviderTickets, ProviderTickets> {
	@Autowired
	private ProviderTicketsRepository providerTicketsRepository;
	DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
	/*
	 * On prend in objet A = ProviderTickets , on va faire des traitement 
	 * pour ensuite retourner un objet B. Dans notre B = ProviderTickets. On aurai un autre onjet à 
	 * retourner
	 */
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public ProviderTickets process(ProviderTickets item) throws Exception {
		System.out.println("--------------------" + item.getCsvdateDebut());
		
		try {
			item.setDateDebut(dateFormat.parse(item.getCsvdateDebut()));
			item.setDateFin(dateFormat.parse(item.getCsvDateFin())); 
			String classService = providerTicketsRepository.findServiceClassByName(item.getServiceName());
			if(classService.equals("GOLD")) {
				DateTime startDateTime = DateTime.parse(item.getCsvdateDebut(), formatter);
				DateTime finDateTime = DateTime.parse(item.getCsvDateFin(), formatter);
				
				Interval duration = new Interval(startDateTime, finDateTime);
				item.setResolveTime(duration.toDuration().toString());
				
			}
			
			
			
		} catch (Exception e) {
			item.setDateDebut(dateFormat.parse("2020-02-17 21:39:27"));
			item.setDateFin(dateFormat.parse("2020-02-17 21:39:27")); 
			e.printStackTrace();
		}
		
		// On retourn lobjet B
		
		return item;
	}
}
