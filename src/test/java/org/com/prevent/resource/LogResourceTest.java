package org.com.prevent.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.com.prevent.AbstractTest;
import org.com.prevent.domain.Log;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureTestDatabase(replace = Replace.NONE)
public class LogResourceTest extends AbstractTest {

	private String url;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		this.url = "http://localhost:8085/";
	}

	@Test
	public void getLogList() throws Exception {
		String uri = url + "logs";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Log[] loglist = super.mapFromJson(content, Log[].class);
		assertTrue(loglist.length > 0);
	}

	@Test
	public void createLog() throws Exception {
		String uri = url + "logs";

		Log log = new Log();

		String inputJson = super.mapToJson(log);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Log is created successfully");
	}

	@Test
	public void updateLog() throws Exception {
		String uri = "/log/2";
		Log log = new Log();
		log.setIp("123.654.789.654");
		String inputJson = super.mapToJson(log);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Log is updated successsfully");
	}

	   @Test
	   public void deleteLog() throws Exception {
	      String uri = "/log/2";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "Log is deleted successsfully");
	   }
	}