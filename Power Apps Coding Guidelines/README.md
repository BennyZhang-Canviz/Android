![](.\images\image001.png 'image')
<br><br>
# PowerApps canvas app coding standards and guidelines

White paper

**Summary:** This technical white paper is aimed at Microsoft PowerApps makers in the enterprise. It contains standards for naming objects, collections, and variables, and guidelines for developing consistent, performant, and easily maintainable apps.

**Writers:** Todd Baginski, Pat Dunn

**Technical Contributors:** Mehdi Slaoui Andaloussi, Alex Belikov, Ian Davis, Rémi Delarboulas, Aniket J. Gaud, Nick Gill, Audrie Gordon, Erik Orum Hansen, Eric Mckinney, Santhosh Sudhakaran, Hubert Sui, Vanessa Welgemoed, Keith Whatling


## Introduction

Microsoft PowerApps is a high-productivity application development platform from Microsoft. Microsoft uses this platform to build first-party applications in Microsoft Dynamics 365 for Sales, Microsoft Dynamics 365 for Service, Microsoft Dynamics 365 for Field Service, Microsoft Dynamics 365 for Marketing, and Microsoft Dynamics 365 for Talent. Enterprise customers can also use the same platform to build their own custom line-of-business applications. Individual users and teams within your organization can also build personal or team productivity applications without having to write very much code (or any).

### Purpose of this white paper

This white paper is targeted at the enterprise application maker (developer) who is responsible for designing, building, testing, deploying, and maintaining PowerApps apps in a corporate or government environment. This white paper was developed as a collaboration between the Microsoft PowerApps team, Microsoft IT, and industry professionals. Of course, enterprise customers are free to develop their own standards and practices. However, we feel that adherence to these guidelines will help developers in these areas:

- Simplicity
- Readability
- Supportability
- Ease of deployment and administration
- Performance
- Accessibility

### Scope of this white paper

Except where specifically noted, all features that are mentioned in this white paper are available as of December 2018. The following topics are out of scope for this white paper:

- PowerApps fundamentals for building applications. This white paper assumes that the reader has a working knowledge, but not necessarily expert knowledge, of how to build a PowerApps app. For blogs, tutorials, training resources, and community support, visit [https://docs.microsoft.com/en-us/powerapps/index](https://docs.microsoft.com/en-us/powerapps/index).
- Microsoft Power BI and other parts of the broader Microsoft Power platform.
- Code outside PowerApps, such as code in Microsoft Azure App Service and Function App.
- General governance and Application Lifecycle Management (ALM).
- Environment administration. To learn about this topic, we recommend that you read the [Administering a PowerApps enterprise deployment](https://docs.microsoft.com/en-us/powerapps/administrator/admin-powerapps-enterprise-deployment) white paper.

### This is a living document

This white paper is intended to be a living document. As Microsoft Power platform capabilities and industry standards change, so will this white paper.

Microsoft is listening to customer feedback and is constantly evolving the Power platform to help you build better apps for your users. As a result, today&#39;s best practice might become obsolete as new features change the most efficient approaches. Check back periodically to see the latest standards and guidelines.

***Thank you***  to all the professionals who have contributed to this white paper by sharing your collective guidance and experience. Now, on to the guidance.

- [General naming conventions](General-naming-conventions.md)
- [Object naming conventions](Object-naming-conventions.md)
- [Code naming conventions](Code-naming-conventions.md)
- [Organizing your objects and code](Organizing-your-objects-and-code.md)
- [General coding guidelines](General-coding-guidelines.md)
- [Optimizing for performance](Optimizing-for-performance.md)
- [App design](App-design.md)
- [Configuration values](Configuration-values.md)
- [Error handling debugging](Error-handling-debugging.md)
- [Documentation](Documentation.md)