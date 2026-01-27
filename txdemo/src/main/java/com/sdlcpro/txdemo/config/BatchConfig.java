package com.sdlcpro.txdemo.config;

import com.sdlcpro.txdemo.core.service.PersonItemProcessor;
import com.sdlcpro.txdemo.data.entity.Address;
import com.sdlcpro.txdemo.data.entity.NetworkInfo;
import com.sdlcpro.txdemo.data.entity.Person;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    // ---------- READER ----------
    @Bean
    public FlatFileItemReader<Person> reader() {

        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("src/main/resources/data.csv"));
        reader.setLinesToSkip(1);

        reader.setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(
                        "firstName", "lastName",
                        "city", "state", "zip",
                        "phone", "email", "ip"
                );
            }});

            setFieldSetMapper(fieldSet -> {

                Address address = new Address();
                address.setCity(fieldSet.readString("city"));
                address.setState(fieldSet.readString("state"));
                address.setZipCode(fieldSet.readString("zip"));

                NetworkInfo network = new NetworkInfo();
                network.setIpAddress(fieldSet.readString("ip"));

                Person p = new Person();
                p.setFirstName(fieldSet.readString("firstName"));
                p.setLastName(fieldSet.readString("lastName"));
                p.setPhoneNumber(fieldSet.readString("phone"));
                p.setEmail(fieldSet.readString("email"));
                p.setAddress(address);
                p.setNetworkInfo(network);

                return p;
            });
        }});

        return reader;
    }

    // ---------- WRITER ----------
    @Bean
    public JpaItemWriter<Person> writer(EntityManagerFactory emf) {
        JpaItemWriter<Person> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(emf);
        return writer;
    }

    // ---------- STEP ----------
    @Bean
    public Step step(JobRepository jobRepository,
                     PlatformTransactionManager transactionManager,
                     PersonItemProcessor processor,
                     JpaItemWriter<Person> writer) {

        return new StepBuilder("person-step", jobRepository)
                .<Person, Person>chunk(1000, transactionManager) // 🔥 chunk size
                .reader(reader())
                .processor(processor)
                .writer(writer)
                .build();
    }

    // ---------- JOB ----------
    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        return new JobBuilder("person-job", jobRepository)
                .start(step)
                .build();
    }
}
