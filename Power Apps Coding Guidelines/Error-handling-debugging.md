## Error handling/debugging

### Toggle controls for error handling

In the [OnTimerStart property](#_OnTimerStart_pProperty) section, we demonstrated one pattern that you can use to handle errors with Timer controls. Another pattern that you can use to handle errors involves a Toggle control.

The following illustration shows this approach.

![Image](.\images\image042.png )

In this approach, logic for validation and/or error handling can be encapsulated inside a single control. The Toggle control can evaluate complex conditions and issue a true or false value. Other controls can then reference that value to show/hide error messages, change font or border colors, make buttons unavailable, log to Application Insights, and much more. If you make this control visible and editable, the app maker can switch the error condition on and off to watch the user interface (UI) react. This approach can save time and effort when you develop or debug the app.

### Using a canvas control as a debug panel

As you&#39;re developing your app and testing it, you can use a canvas control to create a semi-transparent debugging panel that appears over the top of your screens. This panel can then have editable fields, toggles, or other controls, as needed, so that you can change your variables while the app is in play mode.

For a step-by-step instructional video, see [PowerApps - Best Practices: Debug Panel](https://www.youtube.com/watch?v=8f4vxZ_B7Qk) by Brian Dang.

### Showing debug controls to app makers

You don&#39;t want to show your debug controls to all your users. Therefore, you must either manually toggle the Visible property of your debug control (in PowerApps Studio) or automatically toggle control visibility for certain users.

One elegant approach is to add the PowerApps for Makers connector. Despite the name, this connector can be used by non-makers for read-only calls. Then call the [GetAppRoleAssignments function](https://docs.microsoft.com/en-us/connectors/powerappsforappmakers/#get-app-role-assignments) to determine whether the signed-in user is a maker for the current app.

![Image](.\images\image043.jpg )

For this example, you&#39;ll then set the Visible property of your debug controls to gloIsMaker, so that those controls appear only to app users who have maker permissions.

The benefit of this approach is that you won&#39;t need to use a configuration table to specify special debug permissions.

You can also show and hide debug controls for just app makers or admins by checking their email address (for example, check User().Email) or their AAD group membership.