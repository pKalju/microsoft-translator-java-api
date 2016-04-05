# Troubleshooting FAQ #

Most issues with using the API are caused by setting incorrect values for Client Id and or Client Secret


# Finding your Client Id and Client Secret #

Assuming you have already [subscribed](http://msdn.microsoft.com/en-us/library/hh454950.aspx) to the Microsoft Translator Service (free up to 2,000,000 characters/month)
  * Visit your [Azure Datamarket Developer Page](https://datamarket.azure.com/developer/applications/)
  * Click "Edit" next to the name of your registered application
  * There you should find the two values "Client ID" and "Client Secret"
  * Set these values in your code using the setClientId() and setClientSecret() methods respectively.