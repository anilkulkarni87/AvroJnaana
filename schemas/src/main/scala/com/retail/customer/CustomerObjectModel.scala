/** MACHINE-GENERATED FROM AVRO SCHEMA. DO NOT EDIT DIRECTLY */
package com.retail.customer

import com.retail.CustomerObject.{CustomerIdentity, CustomerAddress, CustomerContactPreference, CustomerContentPreference, CustomerMemberShip, CustomerSource, ContentPreference, MarketingFrequency, Programs}

/**
 * **Retail Customer Object Model**
 * 
 * 	We should look at the Customer journey, attributes being collected at the source and then look into how we should model it. For now as the primary team responsible for Customer data, we should be able to
 * 	Understand what is our source for each of these elements and if we are doing any audits around them.
 * 
 * 	**Questions:**
 * 
 * 	1. What are our source teams?
 * 
 * 	2. How are we getting the data?
 * 
 * 	3. Are we auditing it?
 * 
 * 	4. How do each of these datasets impact business?
 * 
 * 	5. What other datasets we have not tapped into?
 * 
 * 	**ENROLL** - First touchpoint of Customer data.
 * 
 * 	**EARN** - Retail Store is earning revenue and Customer’s trust during a sale transaction. We should talk about the transaction data model separately.
 * 
 * 	**MANAGE** - Any Customer interaction with Customer service if exists. This could result in change of communication preferences through web forms.
 * 
 * 	**LEVEL/RANKING** - Customer can reach different tiers based on his purchases.
 * 
 * 	**ENGAGEMENT with BRAND** - Information about events he might attend, This will also survey data, Product reviews, clickstream
 * 
 * 	**CLOSE** - Customer stops engaging with Retail store
 * 
 * 
 * 	**Example:**
 * 
 * 	1. **Enrollment** - Customer enrolls into Retail store system
 * 
 * 		1. Customer can sign up online
 * 
 * 		2. Customer can sign up at store
 * 
 * 		3. Customer can sign up and buy at store - (ENROLL and EARN together)
 * 
 * 	2. **EARN** - All purchases. What Customer information do we want to tap from a sale transaction and how should we models it.
 * 
 * 		1. Digital
 * 
 * 		2. Phygital
 * 
 * 	3. **MANAGE** - Any Customer account level changes like preferences.
 * 
 * 	4. **LEVEL/RANKING** - This helps identifying high value Customers.
 * 
 * 	5. **ENAGAGEMENT** - How do we tap into events, reviews and survey and stitch that with profile.  This will also include Clickstream data.
 * 
 * 		1. Helps in providing elevated Customer experience.
 * 
 * 	6. **CLOSE** - Why did the Customer stop engaging with the brand?
 * 
 * 	What details do we need for a **Customer**:
 * @param CustomerIdentity Id assigned by Retail store to Customer What is the SOURCE:
 * @param CustomerAddresses Customer Addresses What is the SOURCE:
 * @param CustomerContactPreference Customer Contact Preference What is the SOURCE:
 * @param CustomerContentPreference Customer Content Preference What is the SOURCE:
 * @param CustomerMemberShip Customer membership What is the SOURCE:
 */
final case class CustomerObjectModel(CustomerIdentity: CustomerIdentity, CustomerAddresses: Seq[CustomerAddress] = Seq(), CustomerContactPreference: CustomerContactPreference, CustomerContentPreference: CustomerContentPreference, CustomerMemberShip: CustomerMemberShip)