package net.linksguard.jobBatch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.linksguard.dao.ProviderTicketsRepository;
import net.linksguard.entities.ProviderTickets; 
@Component
public class ProviderTicketsItemsWriter implements ItemWriter<ProviderTickets> {
	@Autowired
	private ProviderTicketsRepository providerTicketsRepository;
	@Override
	public void write(List<? extends ProviderTickets> items) throws Exception {
		providerTicketsRepository.saveAll(items);
		
	}

}
