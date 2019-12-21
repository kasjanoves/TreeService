package treeservice.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import treeservice.config.TreeServiceSpringBootWebApp;
import treeservice.domain.CalcRequestResult;
import treeservice.domain.TreeNode;
import treeservice.repo.CalcRequestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TreeServiceSpringBootWebApp.class)
@AutoConfigureMockMvc
public class CalcServiceTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CalcRequestRepository calcRequestRepository;

    @Test
    public void save() throws Exception {
	TreeNode root = new TreeNode(8L)
		.addNode(new TreeNode(10L).addNode(new TreeNode(14L).addNode(new TreeNode(15L))))
		.addNode(new TreeNode(3L).addNode(new TreeNode(1L))
			.addNode(new TreeNode(6L).addNode(new TreeNode(4L).addNode(new TreeNode(7L)))));
	CalcRequestResult result = new CalcRequestResult(root, LocalDateTime.now(), 100L);
	calcRequestRepository.save(result);

	List<CalcRequestResult> results = calcRequestRepository.getAll();
	assertNotNull(results);
	assertNotNull(results.get(0));
	CalcRequestResult resultRead = results.get(0);
	assertNotNull(resultRead.getRequestTime());
	assertTrue(LocalDateTime.now().toLocalDate().compareTo(resultRead.getRequestTime().toLocalDate()) == 0);
	assertEquals(new Long(100), resultRead.getResult());
	assertNotNull(resultRead.getRoot());
	TreeNode treeRead = resultRead.getRoot();
	assertTrue(treeRead.equals(root));

    }

    @Test
    public void calc() throws Exception {
	TreeNode root = new TreeNode(8L)
		.addNode(new TreeNode(10L).addNode(new TreeNode(14L).addNode(new TreeNode(13L))))
		.addNode(new TreeNode(3L).addNode(new TreeNode(1L))
			.addNode(new TreeNode(6L).addNode(new TreeNode(4L).addNode(new TreeNode(7L)))));

	ObjectMapper mapper = new ObjectMapper();
	mapper.registerModule(new JavaTimeModule());
	String json = mapper.writeValueAsString(root);

	RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/tree/calc").content(json)
		.contentType(MediaType.APPLICATION_JSON);

	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	assertEquals(String.valueOf(8 + 3 + 10 + 1 + 6 + 14 + 4 + 7 + 13), result.getResponse().getContentAsString());

	List<CalcRequestResult> results = calcRequestRepository.getAll();
	assertNotNull(results);
	assertNotNull(results.get(0));
	CalcRequestResult resultRead = results.get(0);
	assertNotNull(resultRead.getRequestTime());
	assertTrue(LocalDateTime.now().toLocalDate().compareTo(resultRead.getRequestTime().toLocalDate()) == 0);
	assertEquals(new Long(8 + 3 + 10 + 1 + 6 + 14 + 4 + 7 + 13), resultRead.getResult());
	assertNotNull(resultRead.getRoot());
	TreeNode treeRead = resultRead.getRoot();
	assertTrue(treeRead.equals(root));

    }
}
