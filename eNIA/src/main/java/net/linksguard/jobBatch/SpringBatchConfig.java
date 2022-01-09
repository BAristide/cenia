package net.linksguard.jobBatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import net.linksguard.entities.ProviderTickets; 

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private ItemReader<ProviderTickets> providerTicketsItemReader;
	@Autowired
	private ItemWriter<ProviderTickets> providerTicketsItemWriter;
	@Autowired
	private ItemProcessor<ProviderTickets, ProviderTickets> providerTicketsItemProcessor;
	
	@Bean
	public Job ticketsJob() {
		Step step1 = stepBuilderFactory.get("ETL-Transaction-File-load")
				.<ProviderTickets,ProviderTickets>chunk(100)
				.reader(providerTicketsItemReader)
				.writer(providerTicketsItemWriter)
				.processor(providerTicketsItemProcessor)
				.build();
				 
		return jobBuilderFactory.get("ETL-load")
				.incrementer(new RunIdIncrementer())
				.start(step1)
				.build();
	}
	
	@Bean
	public FlatFileItemReader<ProviderTickets> flatFileItemReader(@Value("${inputFile}") Resource resource  ){
		
		FlatFileItemReader<ProviderTickets> flatFileItemReader = new FlatFileItemReader<ProviderTickets>();
		flatFileItemReader.setName("CSV-READER");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setResource(resource);
		flatFileItemReader.setEncoding("Cp1252");
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
		
	}
	
	@Bean
	public LineMapper<ProviderTickets> lineMapper(){
		DefaultLineMapper<ProviderTickets> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		
		lineTokenizer.setNames(
				"reference","titre", 
		"csvdatededebut","csvDateFin","serviceName","priority");
		lineMapper.setLineTokenizer(lineTokenizer);
		BeanWrapperFieldSetMapper<ProviderTickets> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(ProviderTickets.class);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}
	
	

}
