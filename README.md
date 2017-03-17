# MoesifApi Lib for Java


[![](https://jitpack.io/v/com.moesif/moesifapi-java.svg)](https://jitpack.io/#com.moesif/moesifapi-java)


__Check out Moesif's [Developer Documentation](https://www.moesif.com/docs) and [Java API Reference](https://www.moesif.com/docs/api?java) to learn more__

## How to Install:

```xml
<!-- Step 1. Add the JitPack repository to your build file -->

<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>


<!-- Step 2. Add the dependency -->

<dependency>
    <groupId>com.moesif</groupId>
    <artifactId>moesifapi-java</artifactId>
    <version>1.2.0</version>
</dependency>
```

The code uses a Java library namely UniRest. The reference to this
library is already added as a maven dependency in the pom.xml
file. Therefore, you will need internet access to resolve this dependency.

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
rspHeaders.put("Date", "Tue, 23 Feb 2017 23:46:49 GMT");
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
				.sessionToken("23jdf0owekfmcn4u3qypxg09w4d8ayrcdx8nu2ng]s98y18cx98q3yhwmnhcfx43f")
				.build();
#### 2.a Send the event asynchronously

```java
MoesifAPIClient client = new MoesifAPIClient("my_application_id");
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
MoesifAPIClient client = new MoesifAPIClient("my_application_id");
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
rspHeaders.put("Date", "Tue, 23 Feb 2017 23:46:49 GMT");
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
MoesifAPIClient client = new MoesifAPIClient("my_application_id");
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
MoesifAPIClient client = new MoesifAPIClient("my_application_id");
APIController api = getClient().getAPI();

api.createEventsBatch(events, callBack);
```

### Update an end user

Updating an end user will create one if it does not exist,
also know as [upsert](https://en.wikipedia.org/wiki/Merge_(SQL))
The only __required__ field is `user_id`.

You can update a user _synchronously_ or _asynchronously_ on a background thread. Unless you require synchronous behavior, we recommend the async versions.

#### 1. Generate the user model

```java
UserModel user = new UserBuilder()
		.userId("12345")
		.modifiedTime(new Date())
		.ipAddress("29.80.250.240")
		.sessionToken("di3hd982h3fubv3yfd94egf")
		.userAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
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

#### 2.a Update the user asynchronously

```java
MoesifAPIClient client = new MoesifAPIClient("my_application_id");
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

api.updateUserAsync(user, callBack);
```

#### 2.b Update the user synchronously

```java
MoesifAPIClient client = new MoesifAPIClient("my_application_id");
APIController api = getClient().getAPI();

api.updateUser(user, callBack);
```

### Update a batch of end users
Will update all users in a single batch, useful for saving from offline sources like CSV.
Any user that does not exist will be created, also known as [upsert](https://en.wikipedia.org/wiki/Merge_(SQL))
The only __required__ field is `user_id`.

You can update users _synchronously_ or _asynchronously_ on a background thread. Unless you require synchronous behavior, we recommend the async versions.

#### 1. Generate the list of users

```java
List<UserModel> users = new ArrayList<UserModel>();

UserModel userA = new UserBuilder()
		.userId("12345")
		.modifiedTime(new Date())
		.ipAddress("29.80.250.240")
		.sessionToken("di3hd982h3fubv3yfd94egf")
		.userAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
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
users.add(userA);

UserModel userB = new UserBuilder()
		.userId("56789")
		.modifiedTime(new Date())
		.ipAddress("21.80.11.242")
		.sessionToken("zceadckekvsfgfpsakvnbfouavsdvds")
		.userAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
		.metadata(APIHelper.deserialize("{" +
				"\"email\": \"maryjane@acmeinc.com\"," +
				"\"string_field\": \"value_1\"," +
				"\"number_field\": 1," +
				"\"object_field\": {" +
					"\"field_1\": \"value_1\"," +
					"\"field_2\": \"value_2\"" +
				"}" +
			"}"))
		.build();
users.add(userB);
```

#### 2.a Update the users asynchronously


```java
MoesifAPIClient client = new MoesifAPIClient("my_application_id");
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

api.updateUsersBatchAsync(users, callBack);
```

#### 2.b Update the users synchronously

```java
MoesifAPIClient client = new MoesifAPIClient("my_application_id");
APIController api = getClient().getAPI();

api.updateUsersBatch(users, callBack);
```


## How to build and install manually (Advanced users):


    1. Extract the zip file to a new folder named JavaSDK.
    2. Open a command prompt and navigate to the JavaSDK/MoesifApi folder.
    3. Execute "mvn install", this will install dependencies and also add the generated JAR in your local maven repository.

    4. The invoked process will automatically run the JUnit tests and show the results in the console.
    5.In your own maven application, add the following lines which will refer to newly installed SDK:
        <dependency>
            <groupId>MoesifApi</groupId>
            <artifactId>MoesifApi</artifactId>
            <version>1.2.0</version>
            <scope>compile</scope>
        </dependency>

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

    1. Select the project MoesifApi from the package explorer.
    2. Select "Run -> Run as -> Junit Test" or use "Alt + Shift + X" followed by "T" to run the Tests.

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
