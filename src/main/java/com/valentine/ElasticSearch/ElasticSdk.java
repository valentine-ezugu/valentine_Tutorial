package com.valentine.ElasticSearch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.document.DocumentField;
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
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class ElasticSdk {

    private static final Logger LOGGER = LogManager.getLogger();

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


    /**
     * This is a simple method that gets response from an index when request is made.
     */
    public void getDocument() {
        try {
            GetResponse getResponse = client.prepareGet("twitter", "tweet", "1")
                .get();

            if (getResponse != null) {
                Map<String, DocumentField> fields  =   getResponse.getFields();
                LOGGER.info("Response Data : " + fields.toString());
            }
        } catch (Exception ex) {
            LOGGER.error("Exception occurred while get Document : " + ex, ex);
        }
    }


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

    //get multiple doc with one request
    public String returnResponseAsString() {

        MultiGetResponse multiGetItemResponses = client.prepareMultiGet().
            add("twitter", "tweet", "1").add("twitter", "tweet", "2", "3", "4").
            add("another", "type", "foo").get();

        for (MultiGetItemResponse itemResponse : multiGetItemResponses) {
            GetResponse response = itemResponse.getResponse();
            if (response.isExists()) {
                String json = response.getSourceAsString();
                return json;
            }
        }
        return null;
    }


    //how search is made
    SearchResponse response11= client.prepareSearch("index1", "index2")
        .setTypes("type1", "type2")
        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
        .setQuery(QueryBuilders.termQuery("multi", "test"))                 // Query
        .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
        .setFrom(0).setSize(60).setExplain(true)
        .get();


    public void multiSearch() {
        //multiple search api
        SearchRequestBuilder srb1 = client
            .prepareSearch().setQuery(QueryBuilders.queryStringQuery("elasticsearch")).setSize(1);
        SearchRequestBuilder srb2 = client
            .prepareSearch().setQuery(QueryBuilders.matchQuery("name", "kimchy")).setSize(1);

        MultiSearchResponse sr = client.prepareMultiSearch()
            .add(srb1)
            .add(srb2)
            .get();

        // You will get all individual responses from MultiSearchResponse#getResponses()
        long nbHits = 0;
        for (MultiSearchResponse.Item item : sr.getResponses()) {
            SearchResponse response = item.getResponse();
            nbHits += response.getHits().getTotalHits();
        }

     }


}


