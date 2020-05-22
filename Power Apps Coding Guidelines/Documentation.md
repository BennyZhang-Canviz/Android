## Documentation

### Code comments

As of June 2018, you can add comments to your code. As you write code in your application, be sure to heavily comment it. Comments will help you when you return to your application months later, and the next developer who works on the app will thank you for them.

There are two kinds of comments:

- **Line comments:** If a double forward slash (//) is added to any line of code, PowerApps will treat the rest of the line (including the //) as a comment. Use line comments to explain what happens next. You can also use them to temporarily disable a line of code without deleting it (therefore, they&#39;re useful for testing).
- **Block comments:** Any text that&#39;s wrapped inside /\* and \*/ will be treated as a comment. Whereas line comments comment out only a single line, block comments can comment out multiple lines. Therefore, they&#39;re useful for multiline comments (such as a code module header). You can also use them to temporarily disable multiple lines of code while you&#39;re testing or debugging.

We recommend that you add comments **after** you use the **Format text** feature, especially if your comments precede a code block. The **Format text** feature uses the following logic for existing comments:

1. If a property begins with a block comment, the next line of code will be appended to it.
2. If a property begins with a line comment, the next line of code won&#39;t be appended to it. Otherwise, the code will be commented out.
3. Line and block comments everywhere else in the property will be appended to the previous line of code.

Don&#39;t worry about adding too many comments or comments that are too long. All comments will be stripped out when PowerApps creates the client app package. Therefore, they won&#39;t affect the package size or slow down the app download or loading times.

### Documentation screens

We recommend that you create screens to document the collections and variables that are used in the PowerApps app. Don&#39;t link to these screens from the other screens in your app. These screens are seen only when the app is open in edit mode.

Here&#39;s an example from the Microsoft PowerApps Company Pulse sample template.

![Image](.\images\image044.png )

© 2018 Microsoft Corporation. All rights reserved.

This document is provided &quot;as-is.&quot; Information and views expressed in this document, including URL and other Internet Web site references, may change without notice. You bear the risk of using it.

Some examples are for illustration only and are fictitious. No real association is intended or inferred.

This document does not provide you with any legal rights to any intellectual property in any Microsoft product. You may copy and use this document for your internal, reference purposes.