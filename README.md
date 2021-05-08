# MoesifApi Lib for Java

[![Built For][ico-built-for]][link-built-for]
[![Latest Version][ico-version]][link-package]
[![Software License][ico-license]][link-license]
[![Source Code][ico-source]][link-source]

__Check out Moesif's [Developer Documentation](https://www.moesif.com/docs) and [Java API Reference](https://www.moesif.com/docs/api?java) to learn more__

## How to Install:

#### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>com.moesif.api</groupId>
    <artifactId>moesifapi</artifactId>
    <version>1.6.10</version>
</dependency>
```

#### Gradle users

Add this dependency to your project's build file:

```gradle
compile 'com.moesif.api:moesifapi:1.6.10'
```

## How to Use:

(See src/test/java/com/moesif/api/controllers/APIControllerTest.java for more usage examples)

### Create a single API event

There are two ways to create an event: _synchronously_ or _asynchronously_ on a background thread. Unless you require synchronous behavior, we recommend the async versions.

#### 1. Generate the event model

```java
Map<String, String> reqHeaders = new HashMap<String, String>();
reqHeaders.put("Host", "api.acmeinc.com");
reqHeaders.put("Accept", "*/*");
reqHeaders.put("Connection", "Keep-Alive");
reqHeaders.put("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)");
reqHeaders.put("Content-Type", "application/json");
reqHeaders.put("Content-Length", "126");
reqHeaders.put("Accept-Encoding", "gzip");

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

Map<String, String> rspHeaders = new HashMap<String, String>();
rspHeaders.put("Date", "Tue, 23 Feb 2019 23:46:49 GMT");
rspHeaders.put("Vary", "Accept-Encoding");
rspHeaders.put("Pragma", "no-cache");
rspHeaders.put("Expires", "-1");
rspHeaders.put("Content-Type", "application/json; charset=utf-8");
rspHeaders.put("Cache-Control","no-cache");

Object rspBody = APIHelper.deserialize("{" +
		"\"Error\": \"InvalidArgumentException\"," +
		"\"Message\": \"Missing field field_a\"" +
	"}");


EventRequestModel eventReq = new EventRequestBuilder()
				.time(new Date())
				.uri("https://api.acmeinc.com/items/reviews/")
				.verb("PATCH")
				.apiVersion("1.1.0")
				.ipAddress("61.48.220.123")
				.headers(reqHeaders)
				.body(reqBody)
				.build();


EventResponseModel eventRsp = new EventResponseBuilder()
				.time(new Date(System.currentTimeMillis() + 1000))
				.status(500)
				.headers(rspHeaders)
				.body(rspBody)
				.build();

EventModel eventModel = new EventBuilder()
				.request(eventReq)
				.response(eventRsp)
				.userId("my_user_id")
				.companyId("my_company_id")
				.sessionToken("23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f")
				.build();
#### 2.a Send the event asynchronously

```java
MoesifAPIClient client = new MoesifAPIClient("your_moesif_application_id");
APIController api = client.getAPI();

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

api.createEventAsync(eventModel, callBack);
```

#### 2.b Send the event synchronously

```java
MoesifAPIClient client = new MoesifAPIClient("your_moesif_application_id");
APIController api = client.getAPI();

api.createEvent(eventModel, callBack);
```

### Create a batch of API events

You can also create a batch of events at once by sending a list of events.
Similar to the single event API, there are two ways to create an event: _synchronously_ or _asynchronously_ on a background thread. Unless you require synchronous behavior, we recommend the async versions.

#### 1. Generate the list of events

```java
Map<String, String> reqHeaders = new HashMap<String, String>();
reqHeaders.put("Host", "api.acmeinc.com");
reqHeaders.put("Accept", "*/*");
reqHeaders.put("Connection", "Keep-Alive");
reqHeaders.put("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 5.0.2; C6906 Build/14.5.A.0.242)");
reqHeaders.put("Content-Type", "application/json");
reqHeaders.put("Content-Length", "126");
reqHeaders.put("Accept-Encoding", "gzip");

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

Map<String, String> rspHeaders = new HashMap<String, String>();
rspHeaders.put("Date", "Tue, 23 Feb 2019 23:46:49 GMT");
rspHeaders.put("Vary", "Accept-Encoding");
rspHeaders.put("Pragma", "no-cache");
rspHeaders.put("Expires", "-1");
rspHeaders.put("Content-Type", "application/json; charset=utf-8");
rspHeaders.put("Cache-Control","no-cache");

Object rspBody = APIHelper.deserialize("{" +
		"\"Error\": \"InvalidArgumentException\"," +
		"\"Message\": \"Missing field field_a\"" +
	"}");


EventRequestModel eventReq = new EventRequestBuilder()
				.time(new Date())
				.uri("https://api.acmeinc.com/items/reviews/")
				.verb("PATCH")
				.apiVersion("1.1.0")
				.ipAddress("61.48.220.123")
				.headers(reqHeaders)
				.body(reqBody)
				.build();


EventResponseModel eventRsp = new EventResponseBuilder()
				.time(new Date(System.currentTimeMillis() + 1000))
				.status(500)
				.headers(rspHeaders)
				.body(rspBody)
				.build();

EventModel eventModel = new EventBuilder()
				.request(eventReq)
				.response(eventRsp)
				.userId("12345")
				.companyId("67890")
				.sessionToken("23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f")
				.build();

List<EventModel> events = new ArrayList<EventModel>();
events.add(eventModel);
events.add(eventModel);
events.add(eventModel);
events.add(eventModel);
```

#### 2.a Send the events batch asynchronously

```java
MoesifAPIClient client = new MoesifAPIClient("your_moesif_application_id");
APIController api = client.getAPI();

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

api.createEventsBatchAsync(events, callBack);
```

#### 2.b Send the events batch synchronously

```java
MoesifAPIClient client = new MoesifAPIClient("your_moesif_application_id");
APIController api = getClient().getAPI();

api.createEventsBatch(events, callBack);
```

## Update a Single User

Create or update a user profile in Moesif.
The metadata field can be any customer demographic or other info you want to store.
Only the `userId` field is required.
For details, visit the [Java API Reference](https://www.moesif.com/docs/api?java#update-a-user).

```java
MoesifAPIClient apiClient = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID");

// Campaign object is optional, but useful if you want to track ROI of acquisition channels
// See https://www.moesif.com/docs/api#users for campaign schema
CampaignModel campaign = new CampaignBuilder()
        .utmSource("google")
        .utmCampaign("cpc")
        .utmMedium("adwords")
        .utmTerm("api+tooling")
        .utmContent("landing")
        .build();

// Only userId is required
// metadata can be any custom object
UserModel user = new UserBuilder()
    .userId("12345")
    .companyId("67890") // If set, associate user with a company object
    .campaign(campaign)
    .metadata(APIHelper.deserialize("{" +
        "\"email\": \"johndoe@acmeinc.com\"," +
        "\"first_name\": \"John\"," +
        "\"last_name\": \"Doe\"," +
        "\"title\": \"Software Engineer\"," +
        "\"sales_info\": {" +
            "\"stage\": \"Customer\"," +
            "\"lifetime_value\": 24000," +
            "\"account_owner\": \"mary@contoso.com\"" +
          "}" +
        "}"))
    .build();
```

### Update the user asynchronously

```java
APICallBack<Object> callBack = new APICallBack<Object>() {
    public void onSuccess(HttpContext context, Object response) {
      // Do something
    }

    public void onFailure(HttpContext context, Throwable error) {
      // Do something else
    }
};

apiClient.updateUserAsync(user, callBack);
```

### Update the user synchronously

```java
apiClient.updateUser(user);
```

## Update Users in Batch

Similar to UpdateUser, but used to update a list of users in one batch. 
Only the `userId` field is required.
For details, visit the [Java API Reference](https://www.moesif.com/docs/api?java#update-users-in-batch).

You can update users _synchronously_ or _asynchronously_ on a background thread. Unless you require synchronous behavior, we recommend the async versions.

```java
MoesifAPIClient apiClient = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID");

List<UserModel> users = new ArrayList<UserModel>();

UserModel userA = new UserBuilder()
        .userId("12345")
        .companyId("67890")
        .campaign(campaign)
        .metadata(APIHelper.deserialize("{" +
            "\"email\": \"johndoe@acmeinc.com\"," +
            "\"first_name\": \"John\"," +
            "\"last_name\": \"Doe\"," +
            "\"title\": \"Software Engineer\"," +
            "\"sales_info\": {" +
                "\"stage\": \"Customer\"," +
                "\"lifetime_value\": 24000," +
                "\"account_owner\": \"mary@contoso.com\"" +
              "}" +
            "}"))
        .build();
users.add(userA);

UserModel userB = new UserBuilder()
        .userId("54321")
        .companyId("67890")
        .campaign(campaign)
        .metadata(APIHelper.deserialize("{" +
            "\"email\": \"johndoe@acmeinc.com\"," +
            "\"first_name\": \"John\"," +
            "\"last_name\": \"Doe\"," +
            "\"title\": \"Software Engineer\"," +
            "\"sales_info\": {" +
                "\"stage\": \"Customer\"," +
                "\"lifetime_value\": 24000," +
                "\"account_owner\": \"mary@contoso.com\"" +
              "}" +
            "}"))
        .build();
users.add(userB);
```

### Update the users asynchronously


```java
APICallBack<Object> callBack = new APICallBack<Object>() {
    public void onSuccess(HttpContext context, Object response) {
      // Do something
    }

    public void onFailure(HttpContext context, Throwable error) {
      // Do something else
    }
};

// Asynchronous call to update users
apiClient.updateUsersBatchAsync(users, callBack);

```

### Update the users synchronously

```java
apiClient.updateUsersBatch(users, callBack);
```

## Update a Single Company

Create or update a company profile in Moesif.
The metadata field can be any company demographic or other info you want to store.
Only the `companyId` field is required.
For details, visit the [Java API Reference](https://www.moesif.com/docs/api?java#update-a-company).

```java
MoesifAPIClient apiClient = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID").Api;

// Campaign object is optional, but useful if you want to track ROI of acquisition channels
// See https://www.moesif.com/docs/api#update-a-company for campaign schema
CampaignModel campaign = new CampaignBuilder()
        .utmSource("google")
        .utmCampaign("cpc")
        .utmMedium("adwords")
        .utmTerm("api+tooling")
        .utmContent("landing")
        .build();

// Only companyId is required
// metadata can be any custom object
CompanyModel company = new CompanyBuilder()
    .companyId("67890")
    .companyDomain("acmeinc.com") // If set, Moesif will enrich your profiles with publicly available info 
    .campaign(campaign) 
    .metadata(APIHelper.deserialize("{" +
        "\"org_name\": \"Acme, Inc\"," +
        "\"plan_name\": \"Free\"," +
        "\"deal_stage\": \"Lead\"," +
        "\"mrr\": 24000," +
        "\"demographics\": {" +
            "\"alexa_ranking\": 500000," +
            "\"employee_count\": 47" +
          "}" +
        "}"))
    .build();
```

### Update the company asynchronously

```java
MoesifAPIClient client = new MoesifAPIClient("your_moesif_application_id");
APIController api = client.getAPI();

APICallBack<Object> callBack = new APICallBack<Object>() {
    public void onSuccess(HttpContext context, Object response) {
      // Do something
    }

    public void onFailure(HttpContext context, Throwable error) {
      // Do something else
    }
};

apiClient.updateCompanyAsync(company, callBack);
```

### Update the company synchronously

```java
apiClient.updateCompany(company);
```

## Update Companies in Batch

Similar to updateCompany, but used to update a list of companies in one batch. 
Only the `companyId` field is required.
For details, visit the [Java API Reference](https://www.moesif.com/docs/api?java#update-companies-in-batch).


You can update users _synchronously_ or _asynchronously_ on a background thread. Unless you require synchronous behavior, we recommend the async versions.

```java
MoesifAPIClient apiClient = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID").Api;

// Campaign object is optional, but useful if you want to track ROI of acquisition channels
// See https://www.moesif.com/docs/api#update-a-company for campaign schema
CampaignModel campaign = new CampaignBuilder()
        .utmSource("google")
        .utmCampaign("cpc")
        .utmMedium("adwords")
        .utmTerm("api+tooling")
        .utmContent("landing")
        .build();

// Only companyId is required
// metadata can be any custom object
CompanyModel company = new CompanyBuilder()
    .companyId("67890")
    .companyDomain("acmeinc.com") // If set, Moesif will enrich your profiles with publicly available info 
    .campaign(campaign) 
    .metadata(APIHelper.deserialize("{" +
        "\"org_name\": \"Acme, Inc\"," +
        "\"plan_name\": \"Free\"," +
        "\"deal_stage\": \"Lead\"," +
        "\"mrr\": 24000," +
        "\"demographics\": {" +
            "\"alexa_ranking\": 500000," +
            "\"employee_count\": 47" +
          "}" +
        "}"))
    .build();
```

### Update the companies asynchronously

```java
APICallBack<Object> callBack = new APICallBack<Object>() {
    public void onSuccess(HttpContext context, Object response) {
      // Do something
    }

    public void onFailure(HttpContext context, Throwable error) {
      // Do something else
    }
};

apiClient.updateCompaniesBatchAsync(companies, callBack);
```

### Update the companies synchronously

```java
apiClient.updateCompaniesBatch(companies);
```

## How to build and install manually (Advanced users):


    1. Extract the zip file to a new folder named JavaSDK.
    2. Open a command prompt and navigate to the JavaSDK/MoesifApi folder.
    3. Execute "mvn install", this will install dependencies and also add the generated JAR in your local maven repository.

    4. The invoked process will automatically run the JUnit tests and show the results in the console.

## How to build via Eclipse:


For build process do the following:

    1. Open Eclipse and click on the "Import" option in "File" menu.
    2. Select "General -> Existing Projects into Workspace" option from the tree list.
    3. In "Select root directory", provide path to the unzipped archive for the generated code.
    4. Click "Finish" and ensure that "Project -> Build Automatically" option is enabled in the menu.

## How to Test via Eclipse:

The generated code and the server can be tested using automatically generated test cases.
Junit is used as the testing framework and test runner.

For test process do the following:

    1. Edit the '/src/test/java/com/moesif/api/controllers/ControllerTestBase.java' to change the ApplicationId to your ApplicationId obtained from Moesif.
    2. Select the project MoesifApi from the package explorer.
    3. Select "Run -> Run as -> Junit Test" or use "Alt + Shift + X" followed by "T" to run the Tests.
    4. Data will be captured in the corresponding Moesif account of the ApplicationId.

## How to Export jar:

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

[ico-built-for]: https://img.shields.io/badge/built%20for-java-blue.svg
[ico-version]: https://api.bintray.com/packages/moesif/maven/moesifapi/images/download.svg
[ico-license]: https://img.shields.io/badge/License-Apache%202.0-green.svg
[ico-source]: https://img.shields.io/github/last-commit/moesif/moesifapi-java.svg?style=social

[link-built-for]: https://www.java.com/en/
[link-package]: https://bintray.com/moesif/maven/moesifapi/_latestVersion
[link-license]: https://raw.githubusercontent.com/Moesif/moesifapi-java/master/LICENSE
[link-source]: https://github.com/moesif/moesifapi-java
