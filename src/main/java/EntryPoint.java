import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class EntryPoint {
    public static void main(String[] args) throws JsonProcessingException {

        String jsonBody2 = "{\n" +
                "   \"resources\":{\n" +
                "      \"core\":{\n" +
                "         \"limit\":60,\n" +
                "         \"remaining\":51,\n" +
                "         \"reset\":1638809791,\n" +
                "         \"used\":9,\n" +
                "         \"resource\":\"core\"\n" +
                "      },\n" +
                "      \"graphql\":{\n" +
                "         \"limit\":0,\n" +
                "         \"remaining\":0,\n" +
                "         \"reset\":1638810209,\n" +
                "         \"used\":0,\n" +
                "         \"resource\":\"graphql\"\n" +
                "      },\n" +
                "      \"integration_manifest\":{\n" +
                "         \"limit\":5000,\n" +
                "         \"remaining\":5000,\n" +
                "         \"reset\":1638810209,\n" +
                "         \"used\":0,\n" +
                "         \"resource\":\"integration_manifest\"\n" +
                "      },\n" +
                "      \"search\":{\n" +
                "         \"limit\":10,\n" +
                "         \"remaining\":10,\n" +
                "         \"reset\":1638806669,\n" +
                "         \"used\":0,\n" +
                "         \"resource\":\"search\"\n" +
                "      }\n" +
                "   },\n" +
                "   \"rate\":{\n" +
                "      \"limit\":60,\n" +
                "      \"remaining\":51,\n" +
                "      \"reset\":1638809791,\n" +
                "      \"used\":9,\n" +
                "      \"resource\":\"core\"\n" +
                "   }\n" +
                "}";

        String jsonBody = "{\n" +
                "  \"name\" : \"mkyong\",\n" +
                "  \"age\" : 33,\n" +
                "  \"messages\" : [ \"hello jackson 1\", \"hello jackson 2\", \"hello jackson 3\" ]\n" +
                "}";

        String jsonBody3 = "{\n" +
                "  \"id\": \"957c43f2-fa2e-42f9-bf75-6e3d5bb6960a\",\n" +
                "  \"name\": \"The Best Product\",\n" +
                "  \"objects\": [\n" +
                "    \"One\",\n" +
                "    \"Two\",\n" +
                "    \"Three\"\n" +
                "  ],\n" +
                "  \"defects\": [\n" +
                "    {\n" +
                "      \"name\": \"defect_1\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"defect_2\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"defect_3\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"brand\": {\n" +
                "    \"id\": \"9bcd817d-0141-42e6-8f04-e5aaab0980b6\",\n" +
                "    \"name\": \"ACME Products\",\n" +
                "    \"owner\": {\n" +
                "      \"id\": \"b21a80b1-0c09-4be3-9ebd-ea3653511c13\",\n" +
                "      \"name\": \"Ultimate Corp, Inc.\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        try{
            User user = mapper.readValue(jsonBody, User.class);
            System.out.println(user.getMessages());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonNode productNode = new ObjectMapper().readTree(jsonBody3);
        Product product = new Product();
        //product.setId(productNode.get("id").textValue());
        //product.setName(productNode.get("name").textValue());
        //product.setBrandName(productNode.get("brand").get("name").textValue());
        //product.setOwnerName(productNode.get("brand").get("owner").get("name").textValue());

        String id = productNode.get("id").textValue();
        String name = productNode.get("name").textValue();
        String brandName = productNode.get("brand").get("name").textValue();
        String ownerName = productNode.get("brand").get("owner").get("name").textValue();
        System.out.println(id);
        System.out.println(name);
        System.out.println(brandName);
        System.out.println(ownerName);

        JsonNode objs = productNode.get("objects");
        for(JsonNode items : objs){
            System.out.println(items);
        }

        String firstDefect = productNode.get("defects").get(0).get("name").asText();
        System.out.println(firstDefect);

       
        for(JsonNode defectChild : productNode.get("defects")){
            System.out.println(defectChild.fields().next().getValue().asText());
        }




    }
}
