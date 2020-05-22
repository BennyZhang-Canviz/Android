## Code naming conventions

As you add code to your PowerApps apps, it becomes increasingly important to use consistent naming conventions for variables and collections. If variables are named correctly, you should be able to quickly discern the _type_, _purpose_, and _scope_ of each.

We found lots of variance in the code naming and object naming conventions of different organizations. For example, one team uses data type prefixes for its variables (such as strUserName to indicate a string), whereas another team prefixes all its variables with an underscore (\_) to group them in IntelliSense. There are differences in the way that teams denote global variables versus context variables.

The same guidance applies here: _Have a pattern for your team, and be consistent in its usage_.

### Variable names

- Be descriptive of the variable&#39;s function. Think about what the variable is bound to and how it&#39;s used, and then name it accordingly.
- Prefix your global and context variables differently.

***Be smart!*** PowerApps lets context variables and global variables share the same names. This can cause confusion, because, by default, your formulas use context variables unless the [disambiguation operator](https://docs.microsoft.com/en-us/powerapps/maker/canvas-apps/functions/operators#disambiguation-operator) is used. Avoid this situation by following these conventions:

- Prefix context variables with loc.
- Prefix global variables with gbl.
- The name after the prefix should indicate the intent/purpose of the variable. Multiple words can be used and don&#39;t have to be separated by any special characters (for example, spaces or underscores), provided that the first letter of each word is capitalized.
- Use Camel casing. Begin your variable names with a prefix in lowercase letters, and then capitalize the first letter of each word in the name (that is, lowerUppperUpper).

Here are some good examples:

- **Global variable:** gblFocusedBorderColor
- **Context variable:** locSuccessMessage

Here are some bad examples:

- dSub
- rstFlds
- hideNxtBtn
- ttlOppCt
- cFV
- cQId

Avoid short and cryptic variable names such as EID. Use EmployeeId instead.

**Note:** When there are many variables in an app, you can just type the prefix in the formula bar to see a list of the available variables. If you follow these guidelines to name your variables, you&#39;ll be able to find them very easily tin the formula bar as you develop your app. Ultimately, this approach leads to quicker app development.

### Collection names

- Be descriptive of the collection&#39;s contents. Think about what the collection contains and/or how it&#39;s used, and then name it accordingly.
- Collections should be prefixed with col.
- The name after the prefix should indicate the intent or purpose of the collection. Multiple words can be used and don&#39;t have to be separated by spaces or underscores, provided that the first letter of each word is capitalized.
- Use Camel casing. Begin your collection names with a lowercase col prefix, and then capitalize the first letter of each word in the name (that is, colUpperUpper).

Here are some good examples:

- colMenuItems
- colThriveApps

Here are some bad examples:

- orderscoll
- tempCollection

**Note:** When there are many collections in the app, you can just type the prefix in the formula bar to see a list the available collections. As for variables, if you follow these guidelines to name your collections, you&#39;ll be able to find them very easily in the formula bar as you develop your app. Ultimately, this approach leads to quicker app development.