package br.com.daione.pavan;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@SpringBootApplication
public class WhereigoapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhereigoapiApplication.class, args);
		ReactiveMongoTemplate mongoOps = new ReactiveMongoTemplate(MongoClients.create(), "database");

	}
	public @Bean
	MongoClient reactiveMongoClient()  {
		return MongoClients.create("mongodb+srv://root:root2@cluster0.yl6fx.mongodb.net/test?retryWrites=true&w=majority");
	}
}
