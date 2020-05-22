## Optimizing for performance

### OnStart code

The OnStart property is invaluable for those one-time calls that are needed to initialize your apps. It might be tempting to put your data initialization calls in this property too. However, while OnStart code is running, the user will continue to see the app splash screen and a &quot;Getting your data&quot; message, and the perceived load times will be longer.

For a better user experience, we recommend that you let the home screen be shown with placeholders for data, such as double dashes (--). Then fill in the data after it&#39;s retrieved. In this way, the user can begin to read content on the home screen or interact with controls that aren&#39;t data-dependent. For example, the user can open an **About** screen.

### Concurrent function

PowerApps runs your data source calls from top to bottom in a module. If you have several calls, this linear execution can negatively affect app performance. One workaround for this issue has been to use timer controls to concurrently fire data calls. However, this approach is difficult to maintain and debug, especially when some timers depend on other timers.

The [Concurrent function](https://docs.microsoft.com/en-us/powerapps/maker/canvas-apps/functions/function-concurrent) eliminates the need to use timer controls to make multiple data calls at the same time. The following code snippet replaces several API calls that used to reside in the OnTimerStart property of timer controls in the app. This approach is much easier to maintain.

![Image](.\images\image031.png )

To fire these calls, you can put them in the OnVisible property. Alternatively, if that approach becomes too messy, you can put the calls in a timer control, and set the variable referenced in the timer&#39;s Start property either in the OnVisible property or in the OnSelectproperty of a hidden control. You can also combine timers with other controls to show a loading message while the code in the OnVisible property run. This approach is a great way to let your users know that the app is doing something. For more information, see the [Finding the best place for your code](#_Finding_the_best) section earlier in this white paper.

**Note:** To make code easier to maintain, you might prefer to use the OnVisible property. However, if you use OnVisible, you won&#39;t be able to use the Navigate function.

To see the Concurrent function in action, see Todd Baginski&#39;s video, [HOW TO: Use the Concurrent Function To Make Your PowerApps Perform Better](https://www.youtube.com/watch?v=xHJvbMEj-so).

### Delegable calls vs. non-delegable calls

When you call your data sources, be aware that some functions are delegable, but others aren&#39;t. Delegable functions can be evaluated on the server and will be more performant. Non-delegable functions require that data be downloaded to the client and evaluated locally. This process is slower and more data-intensive than a delegable call.

For more information, see the [Understand Delegation in a Canvas App](https://docs.microsoft.com/en-us/powerapps/maker/canvas-apps/delegation-overview) article.

### Using local collections

For smaller datasets, especially when frequent access is an issue, consider loading the datasets into local collections first. You can then perform your functions on, or bind controls to, those collections. This approach will be especially beneficial if you make frequent non-delegable calls. Just be aware that there will be an initial effect on performance because the data must be retrieved and there are limits on the number of records that are returned. For more information, see Mehdi Slaoui Andaloussi&#39;s great blog post, [Performance considerations with PowerApps](https://powerapps.microsoft.com/en-us/blog/performance-considerations-with-powerapps/).

### SQL optimization

Your organization might be using Azure SQL Database for your data back end, to take advantage of its rich administration capabilities and interoperability. However, a poorly implemented design won&#39;t handle concurrency, and you might have to increase the database transaction unit (DTU) size and, therefore, the cost.

For example, Microsoft IT built the Thrive Conference app for an internal conference that had 1,700 attendees. The back end was a 100-DTU SQL Database instance. During performance testing, Microsoft asked 120 employees in its operations center to open the app simultaneously. The app stopped responding. Network traces showed that HTTP 500 errors were thrown from the PowerApps connection object. The SQL logs indicated that the server was fully utilized, and that calls were timing out.

Because there was no time to rewrite the app before the conference, Microsoft IT increased the scale to 4,000 DTU to meet the concurrency requirements. Therefore, the cost was significantly higher than the 100-DTU server that was originally budgeted. Since then, Microsoft IT has optimized the design by using the approach that&#39;s described here. Now, a 100-DTU server is more than enough to handle the load, and the SQL calls are substantially faster.

#### Delegable functions for SQL

After you&#39;ve read the previous section for an overview of delegation, see the [list of data sources and supported delegation](https://docs.microsoft.com/en-us/powerapps/maker/canvas-apps/delegation-list#list-of-data-sources-and-supported-delegation) to understand which top-level functions and which predicates of the Filter and Lookup functions are supported. This information will make a big difference in the performance of your PowerApps apps, especially on mobile devices, because the whole dataset doesn&#39;t have to be downloaded and evaluated on the client.

#### Using views instead of tables

Instead of traversing from table to table to get your data, expose views where the joins have been made for you. Provided that you&#39;ve correctly indexed your tables, this approach should be very fast, and it will be even faster if you limit the results by using a delegable function that runs on the server.

#### Using stored procedures through a flow for performance

***The biggest performance improvement that you can make for a PowerApps app that uses Microsoft SQL Server is to call stored procedures from an implementation of Microsoft Flow (that is, a flow).*** This approach has the added benefit of decoupling your database design from the PowerApps app. Therefore, you can change the underlying table structure without affecting your app. As we&#39;ll explain later, this approach is also more secure.

To use this approach in a PowerApps app that already uses the SQL Server connector, you must first completely remove the SQL Server connector from the app. Then create a new SQL Server connector that uses a SQL sign-in that&#39;s restricted to EXECUTE permissions on stored procedures in the database. Finally, in the flow, call the stored procedure, and pass in the parameters from the PowerApps app.

For a detailed explanation of how to create the flow and return its results to PowerApps, see Brian Dang&#39;s article, [Return an array from a SQL Stored Procedure to PowerApps (Split Method)](https://powerapps.microsoft.com/en-us/blog/return-an-array-from-a-sql-stored-procedure-to-powerapps-split-method/).

This approach has these performance benefits:

- The stored procedure will be optimized through a query execution plan. Therefore, data will be returned faster.
- Delegable calls become less of an issue, because your stored procedure will be optimized to read or write only pertinent data.
- Your optimized flow is now a reusable component. Therefore, it&#39;s available to other makers in the environment for common read and write scenarios.

### Expensive calls

Some of your data or API calls will be expensive or time-consuming. Long execution times will affect your perceived performance. Here are some tips:

- Don&#39;t make expensive calls before the next page is opened. Try to make the loading of the next page instantaneous. Then, on the next page, in the background, make the calls in the OnVisibleproperty.

- Use a loading message box or spinner to let the user know that progress is being made behind the scenes.
- The Concurrentfunction is a great way to make your calls to run in parallel. However, when this approach is used, long-running calls can block subsequent code from running.

Here&#39;s a bad example in an OnSelect property that goes to the next page.

![Image](.\images\image032.png )

Here&#39;s a better example. First, here&#39;s the code in the OnSelect property.

![Image](.\images\image033.png )

And here&#39;s the code in the OnVisible property in the next page.

![Image](.\images\image034.png )

### Limiting the package size

Although PowerApps does a lot to optimize app loading, you can take steps to reduce the footprint of your apps. A reduced footprint will be especially important for users of older devices, or users in locales where there&#39;s higher latency or reduced bandwidth.

- Evaluate the media that are embedded in your app. If something isn&#39;t used, delete it.
- Embedded images might be too big. Instead of PNG files, see whether you can use SVG images. However, be careful about using text in SVG images, because the font that&#39;s used will have to be installed on the client. A great workaround when you need to show text is to superimpose a text label over an image.
- Evaluate whether the resolution is appropriate for the form factor. The resolution for a mobile app doesn&#39;t need to be as high as the resolution for a desktop app. Experiment to get the right balance of image quality and size.
- If you have unused screens, delete them. Be careful not to delete any hidden screens that only app makers or administrators use.
- Evaluate whether you&#39;re trying to fit too many workflows into one app. For example, do you have both admin screens and client screens in the same app? If so, consider breaking them into individual apps. This approach will also make it easier for multiple people to work on the apps at the same time, and it will limit the &quot;blast radius&quot; (amount of testing) when app changes require a full test pass.

### Periodically republishing your apps

The PowerApps product team is continually optimizing the Power platform. Sometimes, for backwards compatibility, these optimizations will apply only to apps that are published by using a certain version or later. Therefore, we recommend that you periodically republish your apps to take advantage of these optimizations.

### Advanced settings

The PowerApps product group offers preview features that makers can optionally turn on for their applications. Some of these features can give your apps a significant performance boost. For example, the **Delayed load** feature turns on lazy loading for your application. Then, during the initial load, the runtime will load only the screens and code that are required to show the first screen.

_Use these features at your own risk, and be sure to test your apps thoroughly when you experiment with these features._

![Image](.\images\image035.jpg )