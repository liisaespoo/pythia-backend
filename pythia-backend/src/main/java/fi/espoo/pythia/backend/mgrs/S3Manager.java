package fi.espoo.pythia.backend.mgrs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import fi.espoo.pythia.backend.converters.FileConverter;

@Component
public class S3Manager {

	public String createPlanMultipartFile(String key, String bucketName, MultipartFile mfile) throws IOException{

		// tarkista ettei ole null
//		if (json64base.isEmpty() || json64base == null) {
//			return null;
//		}

		AmazonS3 s3client = authenticate();
		File file = FileConverter.multipartFileToFile(mfile);
		
		key = file.getName();
		
		s3client.putObject(bucketName, key, file);
		// encode base64 to InputStream
//		S3ObjectInputStream imageStream = EncoderBase64.base64String2InputStream(json64base);		
//
//		File file = FileConverter.inputStreamToVirtualFile(imageStream);
		
		String url = uploadObject(s3client, file, key, bucketName);
		return url;
		
	}
	
	

	public String getPlanFile(String nimi) {
		// palauta
		return "";
	}

	// -----------------------AUTHENTICATION WITH ENVIRONMENTAL VARIABLES

	public AmazonS3 authenticate() {
		String publicKey = "";
		String privateKey = "";

		Map<String, String> env = System.getenv();

		Iterator it = env.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (pair.getKey().equals("s3public")) {
				publicKey = (String) pair.getValue();
				//System.out.println(pair.getKey() + " = " + pair.getValue());
			} else if (pair.getKey().equals("s3private")) {
				privateKey = (String) pair.getValue();
				//System.out.println(pair.getKey() + " = " + pair.getValue());
			}
			System.out.println(pair.getKey() + " = " + pair.getValue());
			// it.remove(); // avoids a ConcurrentModificationException
		}

		// First, we need to create a client connection to access Amazon S3 web
		// service. We’ll use AmazonS3 interface for this purpose:

		// https://console.aws.amazon.com/iam/home?region=us-east-1#/users/s3pythia?section=security_credentials
		AWSCredentials credentials = new BasicAWSCredentials(publicKey, privateKey);

		// And then configure the client:
		// http://docs.aws.amazon.com/general/latest/gr/rande.html
		// EU Ireland Eu_WEST_1

		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.EU_WEST_1).build();

		return s3client;
	}

	// --------------------AMAZON S3 METHODS
	// ----------------------------------

	/**
	 * upload inputStream to S3 and return the url of the object
	 * 
	 * @return
	 */
	public String uploadObject(AmazonS3 s3client, File file, String key, String bucketName) {
		
		s3client.putObject(bucketName, key, file);
		URL url = s3client.getUrl(bucketName, key);
		return url.toString();
	}

	/**
	 * 
	 * @param s3client
	 * @param uploadFile
	 * @return
	 */
	public S3ObjectInputStream downloadObject(AmazonS3 s3client, String downloadFile) {
		String bucketName = "kirapythia-example-bucket";
		S3Object s3object = s3client.getObject(bucketName, downloadFile);
		S3ObjectInputStream inputStream = s3object.getObjectContent();
		return inputStream;
	}

	private void listBuckets(AmazonS3 s3client) {
		List<Bucket> buckets = s3client.listBuckets();
		for (Bucket bucket : buckets) {
			System.out.println(bucket.getName());
		}
	}

	private void createBucket(String bucketName, AmazonS3 s3client) {

		if (s3client.doesBucketExist(bucketName)) {
			System.out.println("Bucket name is not available." + " Try again with a different Bucket name.");
			return;
		}

		s3client.createBucket(bucketName);

	}

	private void deleteBucket(AmazonS3 s3client) {
		// DELETE BUCKET
		try {
			s3client.deleteBucket("saara");
		} catch (AmazonServiceException e) {
			System.err.println(e.getErrorMessage());
			return;
		}

	}

	// -----------------------ENCODING ----------------------------------



	
	
}





