package com.futao.springbootlearnbatch;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.File;

@EnableBatchProcessing
@SpringBootApplication
public class SpringbootLearnBatchApplication {

    public static void main(String[] args) {
        System.setProperty("input", "file://" + new File("/Users/futao/Desktop/springBatch.csv").getAbsolutePath());
        SpringApplication.run(SpringbootLearnBatchApplication.class, args);
    }


    @Getter
    @Setter
    public static class Person {
        private int id;
        private int age;
        private String name;
        private String email;
    }

    @Bean
    public FlatFileItemReader<Person> flatFileItemReader(@Value("${input}") Resource in) {
        return new FlatFileItemReaderBuilder<Person>()
                .name("file-reader")
                .resource(in)
                .targetType(Person.class)
                .delimited()
                .delimiter(",")
                .names(new String[]{"name", "email", "age"})
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Person> jdbcBatchItemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Person>()
                .dataSource(dataSource)
                .sql("insert into batch(id,email,name,age) values(:id,:email,:name,:age)")
                .beanMapped()
                .build();
    }

    @Bean
    Job job(JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory,
            ItemReader<? extends Person> itemReader,
            ItemWriter<? super Person> itemWriter) {

        Step step = stepBuilderFactory.get("file-to-db")
                .<Person, Person>chunk(100)
                .reader(itemReader)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory
                .get("etl")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

}
