MoesifApi Lib for Java
======================

[![](https://jitpack.io/v/com.moesif/moesifapi-java.svg)](https://jitpack.io/#com.moesif/moesifapi-java)


__Check out Moesif's
[Java developer documentation](https://www.moesif.com/developer-documentation) to learn more__

How To Configure:
=================
The generated client class accepts the configuration parameters in its constructors.

The generated code uses a java library namely UniRest. The reference to this
library is already added as a maven dependency in the generated pom.xml
file. Therefore, you will need internet access to resolve this dependency.

How to Install:
===============

    Step 1. Add the JitPack repository to your build file

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>


    Step 2. Add the dependency
    <dependency>
  	    <groupId>com.moesif</groupId>
  	    <artifactId>moesifapi-java</artifactId>
  	    <version>1.0.2</version>
  	</dependency>

How to Use:
===========
    (See ApiControllerTest for usage examples)

```java
///////////////////////
// Create the Client //
///////////////////////
    MoesifAPIClient client = new MoesifAPIClient("my_application_id");
    ApiController controller = getClient().getApi();


/////////////////////////////////
// Create the API Event Model: //
/////////////////////////////////

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

        // Parameters for the API call
        Object reqHeaders = APIHelper.deserialize("{" +
                    "\"Host\": \"api.acmeinc.com\"," +
                    "\"Accept\": \"*/*\"," +
                    "\"Connection\": \"Keep-Alive\"," +
                    "\"User-Agent\": \"Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)\"," +
                    "\"Content-Type\": \"application/json\"," +
                    "\"Content-Length\": \"126\"," +
                    "\"Accept-Encoding\": \"gzip\"" +
                "}");

        Object reqBody = APIHelper.deserialize("{" +
                    "\"items\": [" +
                        "{" +
                            "\"type\": 1," +
                            "\"id\": \"fwfrf\"" +
                        "}," +
                        "{" +
                            "\"type\": 2," +
                             "\"id\": \"d43d3f\"" +
                         "}" +
                    "]" +
                "}");

        Object rspHeaders = APIHelper.deserialize("{" +
                    "\"Date\": \"Tue, 23 Aug 2016 23:46:49 GMT\"," +
                    "\"Vary\": \"Accept-Encoding\"," +
                    "\"Pragma\": \"no-cache\"," +
                    "\"Expires\": \"-1\"," +
                    "\"Content-Type\": \"application/json; charset=utf-8\"," +
                    "\"Cache-Control\": \"no-cache\"" +
                "}");

        Object rspBody = APIHelper.deserialize("{" +
                    "\"Error\": \"InvalidArgumentException\"," +
                    "\"Message\": \"Missing field field_a\"" +
                "}");


        EventRequestModel eventReq = new EventRequestModel();

        eventReq.setTime(dateFormat.parse("2016-09-09T04:45:42.914"));
        eventReq.setUri("https://api.acmeinc.com/items/reviews/");
        eventReq.setVerb("PATCH");
        eventReq.setApiVersion("1.1.0");
        eventReq.setIpAddress("61.48.220.123");
        eventReq.setHeaders(reqHeaders);
        eventReq.setBody(reqBody);


        EventResponseModel eventRsp = new EventResponseModel();

        eventRsp.setTime(dateFormat.parse("2016-09-09T04:45:42.914"));
        eventRsp.setStatus(500);
        eventRsp.setHeaders(rspHeaders);
        eventRsp.setBody(rspBody);

        EventModel eventModel = new EventModel();
        eventModel.setRequest(eventReq);
        eventModel.setResponse(eventRsp);
        eventModel.setUserId("my_user_id");
        eventModel.setSessionToken("23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f");


///////////////////////////////////////////
// Synchronous Call to create new event: //
///////////////////////////////////////////

    controller.createEvent(eventModel);

////////////////////////////////////////////
// Asynchronous Call to create new event: //
////////////////////////////////////////////

    APICallBack<Object> callBack = new APICallBack<Object>() {
        public void onSuccess(HttpContext context, Object response) {
            assertEquals("Status is not 201",
                    201, context.getResponse().getStatusCode());
            lock.countDown();
        }

        public void onFailure(HttpContext context, Throwable error) {
            fail();
        }
    };

    controller.createEventAsync(eventModel, callBack);

```

How to build and install manually (Advanced users):
=============

    1. Extract the zip file to a new folder named JavaSDK.
    2. Open a command prompt and navigate to the JavaSDK/MoesifApi folder.
    3. Execute "mvn install", this will install dependencies and also add the generated JAR in your local maven repository.

    4. The invoked process will automatically run the JUnit tests and show the results in the console.
    5.In your own maven application, add the following lines which will refer to newly installed SDK:
        <dependency>
            <groupId>MoesifApi</groupId>
            <artifactId>MoesifApi</artifactId>
            <version>1.0.0</version>
            <scope>compile</scope>
        </dependency>

How to build via Eclipse:
=============

For build process do the following:

    1. Open Eclipse and click on the "Import" option in "File" menu.
    2. Select "General -> Existing Projects into Workspace" option from the tree list.
    3. In "Select root directory", provide path to the unzipped archive for the generated code.
    4. Click "Finish" and ensure that "Project -> Build Automatically" option is enabled in the menu.

How to Test via Eclipse:
===========
The generated code and the server can be tested using automatically generated test cases.
Junit is used as the testing framework and test runner.

For test process do the following:

    1. Select the project MoesifApi from the package explorer.
    2. Select "Run -> Run as -> Junit Test" or use "Alt + Shift + X" followed by "T" to run the Tests.

How to Export jar:
===========

Export the compiled classes as a java libray (jar). The exported jar can be used as library.
See the following links for more information on this topic.

Exporting JARs:
    1. Click on the "Export" option in "File" menu.
    2. Select "Java -> JAR file" and click on "Next".
    3. Check the box beside "MoesifApi" and click on "Finish".

For further details on exporting JARs follow up on the following link.
http://help.eclipse.org/mars/topic/org.eclipse.jdt.doc.user/tasks/tasks-33.htm

Using JARs:
http://help.eclipse.org/juno/topic/org.eclipse.jst.j2ee.doc.user/topics/tjimpapp.html
