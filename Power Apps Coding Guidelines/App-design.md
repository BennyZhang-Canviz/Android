## App design

### Using parent/child relationships for relative styling

We recommend that you use one control&#39;s style as the basis for styling other controls. In general, use relative styling for the color, fill, x, y, width, and height properties.

![Image](.\images\image036.png )

### Galleries

Use galleries for almost everything that&#39;s repetitive and linear.

The &quot;brute force&quot; (manual placement of multiple controls) method might be faster initially, but it will be very time-consuming to modify later.

If you must present a series of information or controls that seem repetitive, always consider whether you might be able to create an internal collection by using a gallery.

​ ![Image](.\images\image037.jpg )

It&#39;s also useful to use a Gallery control as a view form, instead of using a Display Form control.

For example, you have a three-screen &quot;app from data&quot; app. There&#39;s a **Users** data source that contains the user&#39;s name, job title, and phone number.

The first screen, the **User List** screen, has a control that&#39;s named galUsers. This control lists all users.

The second screen, the **User Details** screen, just has a gallery control that&#39;s named galUserDetails. The Items property of this control is set as shown here.

Table(

        {Title: "User Name", Value: galUsers.Selected.DisplayName},

        {Title: "Job Title", Value: galUsers.Selected.JobTitle},

        {Title: "Phone Number", Value: galUsers.Selected.PhoneNumber}

)

This method is much faster than trying to modify three separate data cards in a display form.

### Forms

Use forms for repetitive data entry fields.

Forms also let you quickly group several fields instead of having to use several text boxes.

A form is much easier to work with than individual text boxes, because forms let you to take advantage of parent/child relationships to implement relative styling.

![Image](.\images\image038.jpg )

### Common Data Service for Apps

We recommend that you use a single screen to handle edit/insert operations.

If possible, use a CardGallery control to handle updates to data, instead of referencing individual controls in a Patch function.

When you name context variables, you should indicate which record they&#39;re associated with.

### Multiple form factors

When you make the same PowerApps app target both phone and tablet layouts, first create one version of the app, run it through testing, and finalize it. Then convert it to the other version before you modify the layout and screens. This approach helps guarantee that your expressions, controls, variables, data sources, and so on, all have the same names. Therefore, it&#39;s **much** easier to support the apps and develop them. To learn how to convert one form factor to another, see the [How to convert a PowerApp from one layout to another](http://toddbaginski.com/blog/how-to-convert-a-powerapp-from-one-layout-to-another/) article on Todd Baginski&#39;s blog.