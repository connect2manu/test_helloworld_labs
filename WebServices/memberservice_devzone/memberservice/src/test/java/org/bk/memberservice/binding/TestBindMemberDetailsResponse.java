package org.bk.memberservice.binding;

import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.bk.memberservice.message.MemberDetailsRequest;
import org.bk.memberservice.message.MemberDetailsResponse;
import org.bk.memberservice.types.MemberDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.AbstractMarshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.xml.transform.StringSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(inheritLocations = true, locations = { "classpath:/applicationContext-memberservice.xml" })
public class TestBindMemberDetailsResponse {
	@Autowired
	@Qualifier("marshaller")
	AbstractMarshaller unmarshaller;

	@Test
	public void testUnMarshallingToMemberDetailsRequest() throws Exception {
		InputStream stream = this.getClass().getClassLoader()
				.getResourceAsStream("sampleMemberResponse.xml");
		Object memberDetailsRequestObj = unmarshaller
				.unmarshal(new StreamSource(stream));
		assertNotNull(memberDetailsRequestObj);
	}

	@Test
	public void testMarshallingToMemberDetailsRequest() throws Exception {
		MemberDetail memberDetail = new MemberDetail("testname", "testphone",
				"testcity", "teststate");
		MemberDetailsResponse response = new MemberDetailsResponse(memberDetail);
		StringWriter writer = new StringWriter();
		Result result = new StreamResult(writer);
		unmarshaller.marshal(response, result);
		assertNotNull(writer.toString());
		assertTrue(writer.toString().contains("memberdetail"));
	}

}
