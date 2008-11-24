/**********************************************************
 * HibernateSessionFactory.java
 *
 * Created : 16 apr. 08 by author
 * Last modified $ Date: $ by $ Author:  $
 * Revision: $ Revision:  $
 * Version : $ ID : 1$
 * Copyright (c) 2008 - 2008 Askdigi, All rights reserved.
 **********************************************************/
package com.g4g.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.g4g.utils.properties.G4GProperties;
import com.g4g.utils.properties.PropertiesConstants;

/**
 * The Class MailHelper sends the mail to the specified email address.
 *
 * @author Ankur
 */
public class MailHelper {

	private static final String SMTP = "smtp"; //$NON-NLS-1$

	/**
	 * The Class HTMLDataSource The DataSource interface provides the JavaBeans
	 * Activation Framework with an abstraction of an arbitrary collection of
	 * data. It provides a type for that data as well as access to it in the
	 * form of InputStreams and OutputStreams where appropriate..
	 */
	private static class HTMLDataSource implements DataSource {

		/** The html. */
		private final String html;

		/**
		 * Constructor initializes html variable with htmlString value.
		 *
		 * @param htmlString
		 *            the html string
		 */
		public HTMLDataSource(String htmlString) {
			html = htmlString;
		}

		/**
		 * This method returns the MIME type of the data in the form of a
		 * string. It should always return a valid type. It is suggested that
		 * getContentType return "application/octet-stream" if the DataSource
		 * implementation can not determine the data type.
		 *
		 * @return the MIME Type
		 */
		public String getContentType() {
			return G4GConstants.TEXT_HTML;
		}

		/**
		 * This method returns an InputStream representing the data and throws
		 * the appropriate exception if it can not do so. Note that a new
		 * InputStream object must be returned each time this method is called,
		 * and the stream must be positioned at the beginning of the data.
		 *
		 * @return an InputStream
		 * @throws IOException
		 */
		public InputStream getInputStream() throws IOException {
			if (html == null) {
				throw new IOException(G4GConstants.NULL_HTML);
			}
			return new ByteArrayInputStream(html.getBytes());
		}

		/**
		 * Return the name of this object where the name of the object is
		 * dependant on the nature of the underlying objects. DataSources
		 * encapsulating files may choose to return the filename of the object.
		 * (Typically this would be the last component of the filename, not an
		 * entire pathname.)
		 *
		 * @return the name of the object.
		 */
		public String getName() {
			return G4GConstants.JAF_TEXT_HTML_SEND_EMAIL_ONLY;
		}

		/**
		 * This method returns an OutputStream where the data can be written and
		 * throws the appropriate exception if it can not do so. Note that a new
		 * OutputStream object must be returned each time this method is called,
		 * and the stream must be positioned at the location the data is to be
		 * written.
		 *
		 * @return an OutputStream
		 * @throws IOException
		 */
		public OutputStream getOutputStream() throws IOException {
			throw new IOException(G4GConstants.THIS_DATA_HANDLER_CANNOT_WRITE);
		}
	}

	/**
	 * SimpleAuthenticator is used to do simple authentication when the SMTP
	 * server requires it. It extends Authenticator which represents an object
	 * that knows how to obtain authentication for a network connection.
	 * Usually, it will do this by prompting the user for information.
	 *
	 * Applications use this class by creating a subclass, and registering an
	 * instance of that subclass with the session when it is created. When
	 * authentication is required, the system will invoke a method on the
	 * subclass (like getPasswordAuthentication). The subclass's method can
	 * query about the authentication being requested with a number of inherited
	 * methods (getRequestingXXX()), and form an appropriate message for the
	 * user.
	 */
	private class SMTPAuthenticator extends javax.mail.Authenticator {

		/**
		 * Called when password authentication is needed. Subclasses should
		 * override the default implementation, which returns null.
		 *
		 * @return PasswordAuthentication collected from the user, or null if
		 *         none is provided.
		 */
		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			String username = properties.getProperty(MAIL_USERNAME);
			String password = properties.getProperty(MAIL_PASSWORD);
			return new PasswordAuthentication(username, password);
		}
	}

	// Set a file as an attachment. Uses JAF FileDataSource.
	/**
	 * This method attaches the file as attachment. It contains message as
	 * multipart content. The attached file contains an email message .
	 *
	 * @param msg
	 *            the msg contains a set of attributes and a "content". Messages
	 *            within a folder also have a set of flags that describe its
	 *            state within the folder.
	 * @param message
	 *            the message written with the mail.
	 *
	 * @throws MessagingException
	 *             the messaging exception will be thrown if any of the
	 *             following exceptions occur AuthenticationFailedException,
	 *             FolderClosedException, FolderNotFoundException,
	 *             IllegalWriteException, MessageRemovedException,
	 *             MethodNotSupportedException, NoSuchProviderException,
	 *             ParseException, ReadOnlyFolderException, SearchException,
	 *             SendFailedException, SMTPAddressSucceededException,
	 *             StoreClosedException
	 */
	public static void setFileAsAttachment(Message msg, String message)
			throws MessagingException {
		BodyPart mimeBodyPartText = new MimeBodyPart();

		String html = new StringBuffer(G4GConstants.MAILHELPER_START_TAG)
				.append(msg.getSubject()).append(
						G4GConstants.MAILHELPER_BODY_TAG).append(message)
				.append(G4GConstants.MAILHELPER_END_TAG).toString();

		mimeBodyPartText.setDataHandler(new DataHandler(
				new HTMLDataSource(html)));

		// Create the Multipart. Add BodyParts to it.
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPartText);

		// Set Multipart as the message's content
		msg.setContent(multipart);
	}

	/** The properties. */
	private final Properties properties = new Properties();

	/** The MAI l_ host. */
	private final String MAIL_HOST = G4GProperties
			.getProperty(PropertiesConstants.G4G_MAIL_HOST);

	/** The MAI l_ port. */
	private final String MAIL_PORT = G4GProperties
			.getProperty(PropertiesConstants.G4G_MAIL_PORT);

	/** The MAI l_ username. */
	private final String MAIL_USERNAME = G4GProperties
			.getProperty(PropertiesConstants.G4G_MAIL_USERNAME);

	/** The MAI l_ password. */
	private final String MAIL_PASSWORD = G4GProperties
			.getProperty(PropertiesConstants.G4G_MAIL_PASSWORD);

	/**
	 * Method Send mail is used to send the mail. It models email message using
	 * Message class, sets the SMTP host address in the properties file
	 * SimpleAuthenticator is used to do simple authentication when the SMTP
	 * server requires it. It extends Autenticator which represents an object
	 * that knows how to obtain authentication for a network connection.<br>
	 * It uses the transport class to send mail which provides many common
	 * methods for naming transports, connecting to transports, and listening to
	 * connection events.
	 *
	 * @param from
	 *            the name of the sender the message is send from.
	 * @param recipient
	 *            the name of the receiver the message is received by.
	 * @param subject
	 *            the subject of the mail.
	 * @param message
	 *            the body of the message.
	 * @throws MessagingException
	 *
	 *             the messaging exception will be thrown if any of the
	 *             following exceptions occur AuthenticationFailedException,
	 *             FolderClosedException, FolderNotFoundException,
	 *             IllegalWriteException, MessageRemovedException,
	 *             MethodNotSupportedException, NoSuchProviderException,
	 *             ParseException, ReadOnlyFolderException, SearchException,
	 *             SendFailedException, SMTPAddressSucceededException,
	 *             StoreClosedException
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void sendMail(String from, String recipient, String subject,
			String message) throws MessagingException, IOException {
		boolean debug = false;

		// Set the host smtp address
		Properties props = new Properties();
		props.put(G4GConstants.MAIL_SMTP + G4GConstants.HOST, MAIL_HOST);
		props.put(G4GConstants.MAIL_SMTP + G4GConstants.PORT, MAIL_PORT);
		props
				.put(G4GConstants.MAIL_SMTP + G4GConstants.AUTH,
						G4GConstants.TRUE);

		Authenticator auth = new SMTPAuthenticator();
		Session session = Session.getInstance(props, auth);
		session.setDebug(debug);

		try {
			// Get a Transport object to send e-mail
			Transport bus = session.getTransport(SMTP);

			// Connect only once here
			// Transport.send() disconnects after each send
			// Usually, no username and password is required for SMTP
			// bus.connect();

			bus.connect(MAIL_HOST, MAIL_USERNAME, MAIL_PASSWORD);
			// Instantiate a message
			Message msg = new MimeMessage(session);

			// Set message attributes
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(recipient) };
			msg.setRecipients(Message.RecipientType.TO, address);

			msg.setSubject(subject);
			msg.setSentDate(new Date());

			setFileAsAttachment(msg, message);
			msg.saveChanges();
			bus.sendMessage(msg, address);
			bus.close();
		} catch (MessagingException mex) {
			// Prints all nested (chained) exceptions as well
			AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, mex);
			// How to access nested exceptions
			while (mex.getNextException() != null) {
				// Get next exception in chain
				Exception ex = mex.getNextException();
				AuditUtil.getInstance().writeLog(AuditUtil.FILE_TYPE_G4G, mex);
				if ((ex instanceof MessagingException)) {
					mex = (MessagingException) ex;
				} else {
					break;
				}
			}
		}
	}


}