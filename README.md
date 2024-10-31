# MoesifApi Lib for Java

Base Java Lib to send API events, users, companies, and subscription data to Moesif API Observability service.

> For logging API traffic at scale, most customers should integrate with one of Moesif's API monitoring agents which instrument your API automatically and handle batching.

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
    <version>1.8.3</version>
</dependency>
```

#### Gradle users

Add this dependency to your project's build file:

```gradle
compile 'com.moesif.api:moesifapi:1.8.3'
```

## How to Use:

(See src/test/java/com/moesif/api/controllers/APIControllerTest.java for more usage examples)

### Create a single API event

There are two ways to create an event: _synchronously_ or _asynchronously_ on a background thread. Unless you require synchronous behavior, we recommend the async versions.

#### 1. Generate the event model

```java
String reqBody = "{" +
  "\"items\": [" +
    "{" +
      "\"type\": 1," +
      "\"id\": \"hello\"" +,
    "}" +
  "]" +
  "}";

String rspBody = "{" +
    "\"Error\": \"InvalidArgumentException\"," +
    "\"Message\": \"Missing field field_a\"" +
  "}";

// Generate the event
Map<String, String> reqHeaders = new HashMap<String, String>();
reqHeaders.put("Host", "api.acmeinc.com");
reqHeaders.put("Content-Type", "application/json");
reqHeaders.put("Accept-Encoding", "gzip");

Map<String, String> rspHeaders = new HashMap<String, String>();
rspHeaders.put("Content-Type", "application/json; charset=utf-8");

BodyParser.BodyWrapper reqBodyWrapper = BodyParser.parseBody(reqHeaders, reqBody);
BodyParser.BodyWrapper rspBodyWrapper = BodyParser.parseBody(rspHeaders, rspBody);

EventRequestModel eventReq = new EventRequestBuilder()
        .time(new Date())
        .uri("https://api.acmeinc.com/items/reviews/")
        .verb("PATCH")
        .apiVersion("1.1.0")
        .ipAddress("61.48.220.123")
        .headers(reqHeaders)
        .body(reqBodyWrapper.body)
        .transferEncoding(reqBodyWrapper.transferEncoding);
        .build();

EventResponseModel eventRsp = new EventResponseBuilder()
        .time(new Date(System.currentTimeMillis() + 1000))
        .status(500)
        .headers(rspHeaders)
        .body(rspBodyWrapper.body)
        .transferEncoding(rspBodyWrapper.transferEncoding);
        .build();

EventModel eventModel = new EventBuilder()
        .request(eventReq)
        .response(eventRsp)
        .userId("12345")
        .companyId("67890")
        .build();
```
#### 2.a Send the event asynchronously

```java
MoesifAPIClient client = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID");

APICallBack<Object> callBack = new APICallBack<Object>() {
    public void onSuccess(HttpContext context, Object response) {
        assertEquals("Status is not 201",
                201, context.getResponse().getStatusCode());
        // Sent successfully!
    }

    public void onFailure(HttpContext context, Throwable error) {
        // Log there was a failure
    }
};

client.getAPI().createEventAsync(eventModel, callBack);
```

#### 2.b Send the event synchronously

```java
MoesifAPIClient client = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID");
client.getAPI().createEvent(eventModel);
```

### Create a batch of API events

You can also create a batch of events at once by sending a list of events.
Similar to the single event API, there are two ways to create an event: _synchronously_ or _asynchronously_ on a background thread. Unless you require synchronous behavior, we recommend the async versions.

#### 1. Generate the list of events

```java
String reqBody = "{" +
  "\"items\": [" +
    "{" +
      "\"type\": 1," +
      "\"id\": \"hello\"" +,
    "}" +
  "]" +
  "}";

String rspBody = "{" +
    "\"Error\": \"InvalidArgumentException\"," +
    "\"Message\": \"Missing field field_a\"" +
  "}";

// Generate the event
Map<String, String> reqHeaders = new HashMap<String, String>();
reqHeaders.put("Host", "api.acmeinc.com");
reqHeaders.put("Content-Type", "application/json");
reqHeaders.put("Accept-Encoding", "gzip");

Map<String, String> rspHeaders = new HashMap<String, String>();
rspHeaders.put("Content-Type", "application/json; charset=utf-8");

BodyParser.BodyWrapper reqBodyWrapper = BodyParser.parseBody(reqHeaders, reqBody);
BodyParser.BodyWrapper rspBodyWrapper = BodyParser.parseBody(rspHeaders, rspBody);

EventRequestModel eventReq = new EventRequestBuilder()
        .time(new Date())
        .uri("https://api.acmeinc.com/items/reviews/")
        .verb("PATCH")
        .apiVersion("1.1.0")
        .ipAddress("61.48.220.123")
        .headers(reqHeaders)
        .body(reqBodyWrapper.body)
        .transferEncoding(reqBodyWrapper.transferEncoding);
        .build();

EventResponseModel eventRsp = new EventResponseBuilder()
        .time(new Date(System.currentTimeMillis() + 1000))
        .status(500)
        .headers(rspHeaders)
        .body(rspBodyWrapper.body)
        .transferEncoding(rspBodyWrapper.transferEncoding);
        .build();

EventModel eventModel = new EventBuilder()
        .request(eventReq)
        .response(eventRsp)
        .userId("12345")
        .companyId("67890")
        .build();

// Create a batch of events
List<EventModel> events = new ArrayList<EventModel>();
events.add(eventModel);
```

#### 2.a Send the events batch asynchronously

```java
MoesifAPIClient client = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID");

APICallBack<Object> callBack = new APICallBack<Object>() {
    public void onSuccess(HttpContext context, Object response) {
        assertEquals("Status is not 201",
                201, context.getResponse().getStatusCode());
        // Sent successfully!
    }

    public void onFailure(HttpContext context, Throwable error) {
        // Log there was a failure
    }
};

client.getAPI().createEventsBatchAsync(events, callBack);
```

#### 2.b Send the events batch synchronously

```java
MoesifAPIClient client = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID");
client.getAPI().createEventsBatch(events);
```

## Update a Single User

Create or update a user profile in Moesif.
The metadata field can be any customer demographic or other info you want to store.
Only the `userId` field is required.
For details, visit the [Java API Reference](https://www.moesif.com/docs/api?java#update-a-user).

```java
// Only userId is required
// metadata can be any custom object
UserModel user = new UserBuilder()
    .userId("12345")
    .companyId("67890") // If set, associate user with a company object
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
MoesifAPIClient client = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID");

APICallBack<Object> callBack = new APICallBack<Object>() {
    public void onSuccess(HttpContext context, Object response) {
      // Do something
    }

    public void onFailure(HttpContext context, Throwable error) {
      // Do something else
    }
};

client.getAPI().updateUserAsync(user, callBack);
```

### Update the user synchronously

```java
MoesifAPIClient client = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID");
client.getAPI().updateUser(user);
```

## Update Users in Batch

Similar to UpdateUser, but used to update a list of users in one batch. 
Only the `userId` field is required.
For details, visit the [Java API Reference](https://www.moesif.com/docs/api?java#update-users-in-batch).

You can update users _synchronously_ or _asynchronously_ on a background thread. Unless you require synchronous behavior, we recommend the async versions.

```java
List<UserModel> users = new ArrayList<UserModel>();

UserModel userA = new UserBuilder()
        .userId("12345")
        .companyId("67890")
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
MoesifAPIClient client = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID");

APICallBack<Object> callBack = new APICallBack<Object>() {
    public void onSuccess(HttpContext context, Object response) {
      // Do something
    }

    public void onFailure(HttpContext context, Throwable error) {
      // Do something else
    }
};

// Asynchronous call to update users
client.getAPI().updateUsersBatchAsync(users, callBack);

```

### Update the users synchronously

```java
MoesifAPIClient client = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID");
client.getAPI().updateUsersBatch(users, callBack);
```

## Update a Single Company

Create or update a company profile in Moesif.
The metadata field can be any company demographic or other info you want to store.
Only the `companyId` field is required.
For details, visit the [Java API Reference](https://www.moesif.com/docs/api?java#update-a-company).

```java
// Only companyId is required
// metadata can be any custom object
CompanyModel company = new CompanyBuilder()
    .companyId("67890")
    .companyDomain("acmeinc.com") // If set, Moesif will enrich your profiles with publicly available info 
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
MoesifAPIClient client = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID");
APIController api = client.getAPI();

APICallBack<Object> callBack = new APICallBack<Object>() {
    public void onSuccess(HttpContext context, Object response) {
      // Do something
    }

    public void onFailure(HttpContext context, Throwable error) {
      // Do something else
    }
};

client.getAPI().updateCompanyAsync(company, callBack);
```

### Update the company synchronously

```java
MoesifAPIClient client = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID");
client.getAPI().updateCompany(company);
```

## Update Companies in Batch

Similar to updateCompany, but used to update a list of companies in one batch. 
Only the `companyId` field is required.
For details, visit the [Java API Reference](https://www.moesif.com/docs/api?java#update-companies-in-batch).


You can update users _synchronously_ or _asynchronously_ on a background thread. Unless you require synchronous behavior, we recommend the async versions.

```java
// Only companyId is required
// metadata can be any custom object
CompanyModel company = new CompanyBuilder()
    .companyId("67890")
    .companyDomain("acmeinc.com") // If set, Moesif will enrich your profiles with publicly available info 
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

// Create a batch of companies
List<EventModel> events = new ArrayList<CompanyModel>();
events.add(company);
```

### Update the companies asynchronously

```java
MoesifAPIClient client = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID");

APICallBack<Object> callBack = new APICallBack<Object>() {
    public void onSuccess(HttpContext context, Object response) {
      // Do something
    }

    public void onFailure(HttpContext context, Throwable error) {
      // Do something else
    }
};

client.getAPI().updateCompaniesBatchAsync(companies, callBack);
```

### Update the companies synchronously

```java
MoesifAPIClient client = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID");
client.getAPI().updateCompaniesBatch(companies);
```

## Update a Single Subscription

Create or update a subscription in Moesif. The metadata field can store any subscription-related information you want to keep. The `subscriptionId`, `companyId`, and `status` fields are required. For more details, visit the [Java API Reference](https://www.moesif.com/docs/api?java#update-a-subscription).

```java
// Create a subscription model with required and optional fields
SubscriptionModel subscription = new SubscriptionBuilder()
    .subscriptionId("sub_12345")
    .companyId("67890")
    .currentPeriodStart(new Date())
    .currentPeriodEnd(new Date())
    .status("active")
    .metadata(APIHelper.deserialize("{" +
            "\"email\": \"johndoe@acmeinc.com\"," +
            "\"string_field\": \"value_1\"," +
            "\"number_field\": 0," +
            "\"object_field\": {" +
            "\"field_1\": \"value_1\"," +
            "\"field_2\": \"value_2\"" +
            "}" +
            "}"))
    .build();
```

### Update the Subscription Asynchronously

```java
MoesifAPIClient client = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID");

APICallBack<HttpResponse> callBack = new APICallBack<HttpResponse>() {
    public void onSuccess(HttpContext context, HttpResponse response) {
        // Handle success
    }

    public void onFailure(HttpContext context, Throwable error) {
        // Handle failure
    }
};

client.getAPI().updateSubscriptionAsync(subscription, callBack);
```

### Update the Subscription Synchronously

```java
MoesifAPIClient client = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID");
client.getAPI().updateSubscription(subscription);
```

## Update Subscriptions in Batch

Similar to updating a single subscription, but used to update a list of subscriptions in one batch. The `subscriptionId`, `companyId`, and `status` fields are required for each subscription in the list.

### Update the Subscriptions Asynchronously

```java
MoesifAPIClient client = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID");

List<SubscriptionModel> subscriptions = new ArrayList<>();
subscriptions.add(subscription); // Assuming 'subscription' is previously defined

APICallBack<HttpResponse> callBack = new APICallBack<HttpResponse>() {
    public void onSuccess(HttpContext context, HttpResponse response) {
        // Handle success
    }

    public void onFailure(HttpContext context, Throwable error) {
        // Handle failure
    }
};

client.getAPI().updateSubscriptionsBatchAsync(subscriptions, callBack);
```

### Update the Subscriptions Synchronously

```java
MoesifAPIClient client = new MoesifAPIClient("YOUR_COLLECTOR_APPLICATION_ID");
client.getAPI().updateSubscriptionsBatch(subscriptions);
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

## How to run JUnit tests for this SDK using `mvn` command

To execute JUnit tests using `mvn` command, the environment variable `MOESIF_APPLICATION_ID` needs to be set. You'll also need to set the `MOESIF_BASE_URI`. Below is the URI for testing against Moesif production, if testing against a local API, change accordingly.
```
export MOESIF_APPLICATION_ID="<Set your Moesif Application Id here>"
export MOESIF_BASE_URI="https://api.moesif.net"
mvn test
```

[ico-built-for]: https://img.shields.io/badge/built%20for-java-blue.svg
[ico-version]: https://img.shields.io/maven-central/v/com.moesif.api/moesifapi
[ico-license]: https://img.shields.io/badge/License-Apache%202.0-green.svg
[ico-source]: https://img.shields.io/github/last-commit/moesif/moesifapi-java.svg?style=social

[link-built-for]: https://www.java.com/en/
[link-package]: https://search.maven.org/artifact/com.moesif.api/moesifapi
[link-license]: https://raw.githubusercontent.com/Moesif/moesifapi-java/master/LICENSE
[link-source]: https://github.com/moesif/moesifapi-java
