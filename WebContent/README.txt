Group Members:
  - Patrick Galicia (ID# 61968926)
  - Raj Robin (ID# 30492300)

URL: http://centaurus-2.ics.uci.edu:8927/ UPDATE -----------------------------------------------------------------------

Navigation:
  When you access the homepage, you see three links above: the logo, HOME button, and ABOUT button. Both the logo
and the HOME button will lead to the homepage, while ABOUT will lead users to our ABOUT page. Scrolling down will lead
users to our list of products, each products are clickable and when pressed it will lead you to the product's
corresponding product page where you have the option to buy the product. In the product page, when you click BUY, 
text fields will appear where users can input their personal information to buy the product. Completing the order will 
bring the user to a confirmation page.
  
Requirements: 
1. Generate Webpage: The product information in the home page is dynamically generated. The website pulls all the information
		     on the database and displays them in the homepage. Furthermore, we only have one product template page, which
		     will be filled with the product information depending on which product the user chooses in the homepage.
2. Submitting a Form: After filling out the text fields in the product page, the website will check if the information is valid,
		      meaning that if you follow the right email format, there is no number in the name text field, ect,. If it
		      went through, it will save the user information and purchase in our database that the website will use
	  	      for the confirmation page.
3. Confirmation Page: In the product page, after users filled out the information and press "Continue", it will lead them in
		      the confirmation page where it will display their purchase and the inputted information on the product 
		      page.
4. Utilize Ajax
	- Arrival Date:
		- The "Estimated Arrival Date" will initially start as "Unknown" and will continuously update depending 
		  on which shipping type the user chooses. For example, when users pick FREE Shipping which has an arrival
		  date of 8 - 15 days, "Estimated Arrival Date" will change to display the correct information.
	- Total Price:
		- The "Total Price" will initially just display the price of the chosen product and it will change depending
		  on which shipping type a user choses. For example, when a user pick Express Shipping, it will add $10.00 to
		  their Total Price.
          
NOTE: There will already be some data filled into our 'purchase' table(around 80+ rows) due to testing purposes. Any new purchase will insert all the information into the database at the very bottom of the table.