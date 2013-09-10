package com.interceptor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 
 */
class WriteDataChannel extends Thread {
	private Socket incoming = null;
	private Socket outgoing = null;

	public WriteDataChannel(Socket in, Socket out) {
		incoming = in;
		outgoing = out;
	}

	// Overwritten run() method of thread -- does the data transfers

	@Override
	public void run() {
		OutputStream outputToClient = null;
		InputStream inputFromClient = null;

		try {
			inputFromClient = incoming.getInputStream();
			outputToClient = outgoing.getOutputStream();

			// Create a BufferedReader using System.in
			copyStream(inputFromClient, outputToClient);

		} catch (IOException e) {
			System.err.println("WriteDataChannel > Error: IOException:: " + e);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("WriteDataChannel > Error: ArrayIndexOutOfBoundsException:: " + e);
		}
	}

	/**
	 * @param input
	 * @param output
	 * @throws IOException
	 */
	public void copyStream(InputStream input, OutputStream output) throws IOException, ArrayIndexOutOfBoundsException {
		byte[] buffer = new byte[2048];
		int inBytes;
		inBytes = input.read(buffer);
		System.out.println("copyStream() | inBytes=" + inBytes + ", buffer=" + buffer);
		output.write(buffer, 0, inBytes);
		output.flush();

		/*
		 * UnComment the below lines and comment the above, incase response is larger than 2048 bytes.
		 * PS: In below case client's EoF end of request should be properly identified, else this won't work.
		 */

		/*		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
		PrintWriter printWriter = new PrintWriter(output, true);
		String temp = bufferedReader.readLine();
		System.out.println("copyStream | Input:- \n" + temp);
		printWriter.println(temp);
		while ((temp = bufferedReader.readLine().trim()) != "") {
			System.out.println("Output:" + temp);
			printWriter.println(temp);
		}
		printWriter.flush();*/
	}
}
