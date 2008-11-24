/**********************************************************
 * Copyright (c) 2008 - 2008, Askdigi, All rights reserved.
 *
 **********************************************************/
package com.g4g.dto;

import java.io.IOException;

import javax.mail.MessagingException;

import com.g4g.utils.MailHelper;

/**
 * The Class Test.
 */
public class Test {

	public static void main(String[] args) throws MessagingException, IOException {
		MailHelper mail = new MailHelper();
		mail.sendMail("ankur.jain@cyberthininfotech.com", "jigar.mistry@cyberthinkinfotech.com", "test", "hello");
	}
}
