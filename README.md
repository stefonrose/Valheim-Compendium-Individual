# Valheim Compendium

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
A companion app for the popular online game Valheim. Valheim is a game with many different biomes, mechanics creatures and items. This app provides valuable information about these features that can be useful to those new to the game as well as seasoned veterans.

### App Evaluation

- **Category:** Gaming / Utilities
- **Mobile:** We are making this app specifically to only be used on a mobile device. There are already websites for this, but considering this is an online PC game we feel it would be best to access this information on a mobile device.
- **Story:** Takes all the information about the game and displays it in different categories. User can click on a category and look at all the information that pertains to that category.
- **Market:** This app is made for anybody that plays or wants to play the game Valheim. It will be a great resource to have to ensure they have a wonderful experience playing the game. 
- **Habit:** User can use it as they see fit. If they want certain parts of the game to be a surprise then they can stay away from that category. They can also use it to look for certain items or to travel to certain biomes. 
- **Scope:** We want the Valheim community to have an easily accessible app where they can find all the information that pertains to the game. Considering there is so much to do and so much to find, the app can help them make sure that they get the full experience and locate everything there is in the world of Valheim. 

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can see an overview of the game and major game elements on the home page.
* User can select on one of the major game elements to navigate to a page with more details.
* User can browse from an alphabetical list of all items in the app.
* User can also utilize a search feature to directly find the information that they are looking for.

**Optional Nice-to-have Stories**

* User can see posts from the Valheim subreddit in-app.
* User can press a button to be taken to a random page in-app.

### 2. Screen Archetypes

* Home Page
   * This page lists some basic information about the game followed by a grid containing some major game features that can be clicked into to further expand upon them.

* Feature Page
   * This page gives details on the selected game feature that the user selected. Things that we consider features are ***Biomes***, ***Creatures***, ***Mechanics***, & ***Items***. These pages may have a brief description at the top but mainly consist of links to the entries that fall under each feature.

* Entry Page
   * These are in-depth explanations of individual features and items in the game. The entry page will vary depending on if the entry is a creature vs if the entry is an item, however, there should be a consistent layout.

* Search Page
   * This page contains a search bar so that the user can search directly for something if they know the name.

* Index Page
   * This page contains an alphabetical list of all entries and features so that the user can browse them.

### 3. Navigation

**Tab Navigation** (Tab to Screen)

*Currently, our application does not utilize tabs. This may change in the future and if it does we'll be sure to update this.*

**Flow Navigation** (Screen to Screen)

![](https://i.imgur.com/WuN2i7M.png)


## Wireframes
![](https://i.imgur.com/8GA6epB.jpg)

## Schema 
### Models
#### Feature
| Property Name | Type    | Description             |
| ------------- | ------- | ----------------------- |
| name          | String  | unique name for feature |
| image         | File    | picture to show feature |
| overview      | String  | describes the feature   |
| id            | Integer | unique identifier       |


#### Biome Entry
| Property Name | Type               | Description                           |
| ------------- | ------------------ | ------------------------------------- |
| id            | Integer            | unique identifier for biome           |
| name          | String             | unique name of biome                  |
| overview      | String             | description of biome                  |
| creatures     | Array of Creatures | profile picture for user              |
| items         | Array of Items     | genres that user indicated they liked |
| image         | File               | picture of biome                      |


#### Creature Entry
| Property Name | Type            | Description                        |
| ------------- | --------------- | ---------------------------------- |
| id            | Integer         | unique identifier for biome        |
| name          | String          | unique name of creature            |
| overview      | String          | description of creature            |
| category      | String          | describes if passive or aggressive |
| spawn         | Array of Biomes | locations of creature              |
| image         | File            | picture of creature                |


#### Boss Entry
| Property Name | Type            | Description                      |
| ------------- | --------------- | -------------------------------- |
| id            | Integer         | unique identifier for boss       |
| name          | String          | unique name of boss              |
| overview      | String          | description of boss              |
| requirement   | String          | describes items needed to summon |
| spawn         | Array of Biomes | locations of boss                |
| image         | File            | picture of boss                  |
| tip           | String          | suggestions on how to kill       |


#### Item Entry
| Property Name | Type           | Description                             |
| ------------- | -------------- | --------------------------------------- |
| id            | Integer        | unique identifier for item              |
| name          | String         | unique name of item                     |
| overview      | String         | description of item                     |
| category      | String         | describes type of item                  |
| resources     | Array of Items | describes what is needed to create item |
| image         | File           | picture of item                         |


#### Concept Entry
| Property Name | Type    | Description                 |
| ------------- | ------- | --------------------------- |
| id            | Integer | unique identifier for biome |
| name          | String  | unique name of concept      |
| overview      | String  | description of concept      |

### Networking
- Home Screen
	- (Get) Display features to be selected
	```java
		ParseQuery<Feature> query = ParseQuery.getQuery(Feature.class);
		query.findInBackground(new FindCallback<Feature>() {
			
			@Override
			public void done(List<Feature> features, ParseException e) {
				allFeatures.addAll(features);
			}
		});
	```

-  Feature Pages
	- (Get) Display a list of items that correspond to the selected feature
	```java
		ParseQuery<Items> query = ParseQuery.getQuery(Items.class);
		query.include(Items.FEATURE_ID);
		query.findInBackground(new FindCallback<Item>() {
			@Override
			public void done(List<Item> items, ParseException e) {
				allItems.addAll(items);
			}
		});
	```

- Entry Pages
	- (Get) Display information about the selected item
	```java
		ParseQuery<Items> query = ParseQuery.getQuery(Items.class);
		query.include(Items.ITEM_ID);
		query.findInBackground(new FindCallback<Item>() {
			@Override
				String curItem = item;
			}
		});
	```

- Valheim Subreddit: https://www.reddit.com/r/valheim/.json
  
	```java=
		URL url = new URL("https://www.reddit.com/r/valheim/.json");
		URLConnection request = url.openConnection();
		request.setRequestProperty("Content-Type", "application/json; utf-8");
		request.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		request.connect();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuilder response = new StringBuilder();
		String inputLine;
		
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		
		JsonObject jsonResponse = new JsonParser().parse(response.toString()).getAsJsonObject();
	```

## Milestones

### Milestone 1

- [x] **Gather Data for Parse Server**

    We need to gather information on all of the items in-game in order to populate our parse server.

    **Our Data Sources:**

    - [Official Wiki](https://valheim.fandom.com/wiki/Valheim_Wiki)
    - Playing the game
    - Browsing game files

    **Data Tables**

    - [x] **Creatures**
    - [x] **Biomes**
        - [ ] (Optional) Recommended gear to explore with.
    - [x] **Items**
    - [x] **Bosses**
        - [ ]  (Optional) Recommended gear to fight with.

- [x] **Setup Parse Server**

	- [x] Set up parse server that will store the Valheim data and be able to GET the data from.

![Milestone 1 Walkthrough](milestone1-parse-data.gif)

### Milestone 2

- [x] **Implement the app homepage**

    Implement the hompage so that the user can see an overview of the ggame along with the main game elements when they load the app.

    **Initial Implementation Idea**

    - [ ] GridView to display the different game elements

    **Current Implementation**

    - [x] Proof of concept with hardcoded data
    - [ ] Using recycler view to display the game elements instead of GridView

	**Issues with Milestone 2**
	
	- Our initial idea was to use a GridView on the bottom half of our page to display the features that we needed to. However, despite looking at 4 different tutorials we could not get it to work. We are currently changing to using a recycler view and hoping that we can change it to give a similar effect to grid view.

![Issues with homepage](issues-with-homepage.png)
