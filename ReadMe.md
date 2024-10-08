![Screenshot 2024-08-17 at 18 36 49](https://github.com/user-attachments/assets/db995056-b360-47dd-9453-9a9cbf9456b6)

  

## Challenges

### 1. Navigating between screens. Creating the navigation host

- Add the compose navigation dependency
- Create an Enum class with a route string for each screen
- Create a nav host file
- The navHost composable is used with a navHostController
- Add the composable() function for each screen within the navHost

### 2. Passing data between screens

- Add a enum class with a route that has an id e.g. "details/{id}"
- Add arguments to the composable function of the screen that will receive the data
- Within the composable use backStackEntry in order to get the arguments
- Use the navHostController to navigate to the screen with the arguments
- At the navigation site replace the id with the actual data on the route e.g "
  Screen.route.replace("{id}", idValue)

### 3. Adding some icons and images

- drag and drop images into the drawable folder
- For png icons add it as an imageAsset using action bar and tab icons option
- For big images use coil asyncImage to load the image

### 4. Adding a bottom navigation bar

- Create an enum with a route, label and icon for each screen
- Add a navigation bar within the BottomAppBar slot within a scaffold
- Add a navigation item for each screen

### 5. Making a screen scrollable

- Add a lazyColumn within the scaffold content slot
- Add each section of the screen within an item{} of the lazyColumn
- Make sure every item's height can be measured. You might have to add fixed height to some items
