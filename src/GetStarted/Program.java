package GetStarted;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

/**
 * Simple application that shows how to use Azure Cosmos DB for MongoDB API in a Java application.
 *
 */
public class Program {
	
    public static void main(String[] args)
    {
	/*
	* Replace connection string from the Azure Cosmos DB Portal
        */
        MongoClientURI uri = new MongoClientURI("mongodb://azurecoosmosaccount:SdRXTfXkyl8inkCOZ1GaUBSga3oofnFiVYdHyaZsdFdwfbzQQrWGrST7BYSfIKIw8MnxrSwKADi0wq0Wiok1wA==@azurecoosmosaccount.documents.azure.com:10255/?ssl=true&replicaSet=globaldb");
		
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient(uri);        
            
            // Get database
            MongoDatabase database = mongoClient.getDatabase("azurecoosmosaccount");

            // Get collection
            MongoCollection<Document> collection = database.getCollection("provider");
            
         // Get collection
            MongoCollection<Document> collection_two = database.getCollection("provider_two");

            Document document1 = new Document("fruit", "apple");
            collection.insertOne(document1);        

            // Find fruits by name
            Document queryResult = collection.find(Filters.eq("fruit", "apple")).modifiers(new Document("$explain", true)) .first();
            System.out.println(queryResult.toJson());    	
        	
            System.out.println( "Completed successfully" );  
            
        } finally {
        	if (mongoClient != null) {
        		mongoClient.close();
        	}
        }
    }
}
