package fi.espoo.pythia.backend.mgrs;

import org.springframework.stereotype.Component;

import java.io.IOException;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

@Component
public class SESManager {

	public void newVersion(String project, String projectId, String plan) {

		// Replace sender@example.com with your "From" address.
		// This address must be verified with Amazon SES.
		final String FROM = "kakedigibot@gmail.com";

		// Replace recipient@example.com with a "To" address. If your account
		// is still in the sandbox, this address must be verified.
		final String TO = "kakedigi@gmail.com";

		// The configuration set to use for this email. If you do not want to
		// use a
		// configuration set, comment the next line and line 60.
		// static final String CONFIGSET = "ConfigSet";

		// The subject line for the email.
		final String SUBJECT = "New Version in Project " + project;

		// The HTML body for the email.
		final String HTMLBODY = "<h1>New version in project " + project + "</h1>" + "<BR>New version for plan " + plan
				+ "<BR>http://localhost:3000/project/" + projectId;

		// The email body for recipients with non-HTML email clients.
		final String TEXTBODY = "New version in project " + project + "\nNew version for plan " + plan
				+ "\nhttp://localhost:3000/project/" + projectId;

		AmazonSimpleEmailService client = authenticate();

		sendEmailRequest(client, TO, FROM, SUBJECT, HTMLBODY, TEXTBODY);
	}

	public void planApproved() {

	}

	public void commentApproved() {

	}

	public void projectFinished() {

	}

	// -----------------------AUTHENTICATION WITH ENVIRONMENTAL VARIABLES

	public AmazonSimpleEmailService authenticate() {
		try {
			AWSCredentialsProvider awsCProv = new EnvironmentVariableCredentialsProvider();
			awsCProv.getCredentials();
			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().withCredentials(awsCProv)
					// Replace US_WEST_2 with the AWS Region you're using for
					// Amazon SES.
					.withRegion(Regions.EU_WEST_1).build();

			return client;
		} catch (Exception ex) {
			System.out.println("Cannot authenticate: " + ex.getMessage());
			return null;
		}
	}

	public boolean sendEmailRequest(AmazonSimpleEmailService client, String TO, String FROM, String SUBJECT,
			String HTMLBODY, String TEXTBODY) {
		try {
			SendEmailRequest request = new SendEmailRequest()
					.withDestination(
							new Destination().withToAddresses(TO))
					.withMessage(new Message()
							.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(HTMLBODY))
									.withText(new Content().withCharset("UTF-8").withData(TEXTBODY)))
							.withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)))
					.withSource(FROM);
			client.sendEmail(request);
			System.out.println("Email sent!");
			return true;
		} catch (Exception ex) {
			System.out.println("The email was not sent. Error message: " + ex.getMessage());
			return false;
		}

	}
}
