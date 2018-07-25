package com.valentine;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import java.net.InetAddress;
import java.util.Date;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class ElasticSdk {


    public ElasticSdk() throws Exception {
    }


    Settings settings = Settings.builder().put("cluster.name", "elasticSearch").build();
    TransportClient client = new PreBuiltTransportClient(settings).
        addTransportAddress(new TransportAddress(InetAddress.getByName("host1"), 9300)).
        addTransportAddress(new TransportAddress(InetAddress.getByName("host2"), 9300));
    //client.close();


    XContentBuilder builder = jsonBuilder().
        startObject().field("user", "kimchy").
        field("postDate", new Date()).field("message", "trying out Elasticsearch")
        .endObject();

     // if u need to see the generated json for test purposes may be
    // String json = Strings.toString(builder);


    IndexResponse response = client.prepareIndex("twitter", "tweet", "1").
        setSource(jsonBuilder().startObject().array("user", "kimchy", "valik") //array testing
        .field("postDate", new Date()).field("message", "trying out Elasticsearch").
                endObject()).get();

    // Index name
    String _index = response.getIndex();
    // Type name
    String _type = response.getType();
    // Document ID (generated or not)
    String _id = response.getId();
    // Version (if it's the first time you index this document, you will get: 1)
    long _version = response.getVersion();
    // status has stored current instance statement.
    RestStatus status = response.status();

    GetResponse getResponse = client.prepareGet("twitter", "tweet", "1").get();


    /**
     * This method deletes all persons with job = accounting, it first searches all specified indices
     * , gets all persons that match query then delete
     *
     * this deletes in bulk.
     */
    public void deleteAllAccountants() {
        BulkByScrollResponse    response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
            .filter(QueryBuilders.matchQuery("job", "accounting")).
            //here we can list as many indices as posible to search from
                source("Person", "","").
                get("time up");
        long deleted = response.getDeleted();

    }

    /**
     *
     * @return
     */
    public String deleteInBulkAsync() {

        // do async
        DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
            .filter(QueryBuilders.matchQuery("gender", "male"))
            .source("persons")
            .execute(new ActionListener<BulkByScrollResponse>() {

                @Override
                public void onResponse(BulkByScrollResponse response) {
                    long deleted = response.getDeleted();
                }
                @Override
                public void onFailure(Exception e) {
                    // Handle the exception
                }
            });
        return null;
    }


}


