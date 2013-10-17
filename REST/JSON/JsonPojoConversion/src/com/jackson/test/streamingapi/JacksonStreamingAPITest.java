package com.jackson.test.streamingapi;

public class JacksonStreamingAPITest {

	public static void main(String[] args) throws Exception {
		JacksonStreamReader.read(JacksonStreamWriter.write());
	}

}
