package com.example.template.config;

import com.example.template.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;


@Configuration
public class Config {

	@Bean
	RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(new ObjectMapper());
		restTemplate.getMessageConverters().add(converter);
		return restTemplate;
	}

	@Bean
	public ResourceProcessor<Resource<Order>> orderProcessor() {

		return new ResourceProcessor<Resource<Order>>() {

			@Override
			public Resource<Order> process(Resource<Order> resource) {

//				Link selfLink = resource.getLink("self");
//				String selfLinkUrl = selfLink.getHref();
//				try {
//					URL url = new URL(selfLinkUrl);
//
//					resource.add(new Link(url.getProtocol() + "://" + url.getHost() + ":" + url.getPort() + "/deliveries/search/findByOrderIdOrderByDeliveryIdDesc?orderId=" + resource.getContent().getId(), "delivery"));
//
//				} catch (MalformedURLException e) {
//					e.printStackTrace();
//				}

				resource.add(new Link("/deliveries/search/findByOrderIdOrderByDeliveryIdDesc?orderId=" + resource.getContent().getId(), "delivery"));
				return resource;
			}
		};
	}
}
