package info.thomazo.findgas.config;

import lombok.Getter;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ElasticConfig {

	@Value("${app.index.name:stations}")
	@Getter
	private String indexName;

	@Value("${app.index.type.station:station}")
	@Getter
	private String stationType;

	@Value("${app.index.type.comment:comment}")
	@Getter
	private String commentType;

	@Value("${app.index.type.patch:patch}")
	@Getter
	private String patchType;


	@Bean(destroyMethod = "close")
	public Client esClient() throws UnknownHostException {
		return TransportClient.builder().build()
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
	}

}
