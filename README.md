# YBS Android Developer Task

# Overview

- Develop a native mobile app which uses the Flickr API to display a list of photos.
- The look and feel of the app is up to you, but it should adhere to normal expectations for a user
  of that platform.
- The app should display a list of photos, along with the poster's userid and user icon. The app
  should display alongside the photo a list of any tags associated with the photo.
- Tapping on the photo should take the user to a separate page wherein they can see more detail
  about the photo (e.g. Title, date take, any content description, etc.),
- Tapping on a user id/photo should produce a list of photos by that user.
- There should be a default search of "Yorkshire" on first load and safe_search must be set to safe.
- The app should be able to search for Photos by tag or lists of tags, and provide whether a photo
  should contain all or some tags
- The app could allow for searching for a user's photos by their username
- Any other additional features you might be able to think of using the data available (be creative)
- The app should not require the use of authenticated API call.

## Requirements

### Required

- [x] Users the Flickr API to display a list of photos.
- [x] "Expected" Design for a photo viewing app,
- [x] Should display a list of photos, with a UserID and User Icon, and a list of tags.
- [x] Tapping the photo should take you to a detail view of that photo.
- [x] Tapping the user id or photo should take you to a list of that user's photos.
- [x] Default search of "Yorkshire" on load.
- [x] Safe Search always true.
- [x] Search by list of tags.
- [x] Select whether the Tag Mode should be ALL or ANY.

### Optional/Additional

- [x] Search by Username.
- [x] Search by Text Search.
- [x] No Authenticated API calls.

## App Navigation/Screens

Every page contains a standard TopNav bar, that currently allows for back navigation using the Back
Stack. Having a TopNav Bar would allow for additional common functionality, including but not
limited to a branding logo that takes the user home on click, and a hamburger style menu to the
right that allows for quick operations.

### Home

#### Main Content

- The Main content here is a LazyColumn list that displays individual ListCard components for each
  Photo returned in the API response.
- Clicking an Image navigates to the Detail view for that image.
- Clicking in the User section of the image navigates to the User Image List view for that user.

#### Search/Filtering

- An expandable Filters tab is present pinned to the bottom of the screen as a bottom bar. This
  allows for filtering if the user desires, but avoids taking up too much screen real estate when
  not in use.
- Filters currently only work on one field at a time, aside from Tag Searches, which is a
  combination of the Tag Search field and the Tag Mode checkbox.
- Searching by username performs a full text search on the entered username and fetches the nsid for
  that user, before navigating to the User Image List view page with that nsid if the user exists.

### User

- Re-Uses the image list cards with a user_id filter to display a list of that user's images.
- Has the same click functionality to get more image details as the Home Screen.

### Detail

- Displays the details for the selected Image.

# Decisions Made

## Technologies/Frameworks/Services

1. I decided to use Jetpack Compose for this project. I did this in order to keep the codebase
   smaller and to avoid having to make multiple XML layouts for every single component and page. It
   also allowed me to perform state management for searches, ease of navigation between screens, and
   would allow for easier UI Testing as it keeps components in small easily testable chunks. This
   also allowed me to quickly build re-usable components rather than having a bunch of re-used code
   for common elements.
2. I used MVVM as my chosen architecture for this project. The benefits of MVVM and Separation of
   Concerns were apparent for this project, separating all API/Network functions, business logic and
   utilities and models into their own chunks. This leaves the app in a place where it would be
   easier in the long term to scale. Jetpack Compose could utilise the data streams and states that
   are available using ViewModels. The app also responds to orientation changes from the VM in
   Jetpack.
3. Coil for image loading, to handle image loading asynchronously.

# With More Time...

- I would have liked to have expanded on the Search and Filtering functionality:
    - Filtering by multiple search terms, for example a combination of Text and Tag search queries.
    - "Pilling" the individual tags when separated by a comma in the tag search field, including
      allowing for deletion of individual tags once they are "pilled".
    - Allow for easy clearing of search field inputs.
- Data persistence. When navigating from the User or Detail Screens, it does not persist any search
  terms. Data persistence for searches is important so I would add that functionality in the
  Navigation flow.
- Nicer design! The design of the app is incredibly basic at the moment and could be refined
  massively, but I wanted to focus on functionality for this task.
- Pagination. As mentioned in the Decisions Made section, I set the per_page value to 10 as a
  constant in order to avoid making too many API calls for testing. Given more time, I would have
  included either infinite scrolling where the "getInfo" call fires off as items populate the
  scrolling list rather than on load to mitigate these excessive API calls for larger datasets, or
  paginated the results.
- An additional feature I would like to add with more time would be Location searching - some photos
  have locations attached and you can pass lat long values into the Flickr Photo Search API.
  Something like a feature to "find photos taken near me" that utilises the Native Location GPS data
  to find photos taken near the users location, or to select on a map!
- Cleaner Navigation Animations
- Cleaner handling of loading stages to avoid displaying old data or empty data whilst data loads
  in.
- Error handling for UI. The API requests are handled with errors, but UI navigation could be
  handled with exception and error handling for non-existent data for example. This also would
  include displaying an error and placeholders if there is no Internet Connection to make API calls.
- UI Testing - using Espresso.
- Unit Testing - I started to mock some tests and utilised Postman and logging with a Retrofit
  interceptor to check API requests, but fell short on writing some solid unit tests. 
