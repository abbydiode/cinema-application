# Cinema Application

## Usage

1. Run the application with `mvn javafx:run`.
2. Sign in with one of the [credentials](#user-credentials) listed below.
3. Purchase a ticket by clicking on a showing, selecting the amount of seats you want to reserve and entering a name, then click <kbd>Purchase</kbd>.
4. Sign out by clicking <kbd>Sign Out</kbd> in the top menu bar and then again in the pop-out, then sign in with an administrator account.
5. Click <kbd>Admin</kbd> and then <kbd>Manage Showings</kbd>.
6. Select a showing to use as a base, edit any fields necessary, then click <kbd>Add Showing</kbd>. Note that a showing cannot overlap an existing showing.
7. Click <kbd>Admin</kbd>> and then <kbd>Manage Movies</kbd>.
8. Either create a new movie or select a movie to use as a base, edit any fields necessary, then click <kbd>Add Movie</kbd>.

## User Credentials

| Username | Password | Role          | Permissions     |
|----------|----------|---------------|-----------------|
| admin    | admin    | Administrator | All             |
| user     | user     | User          | Only Purchasing |
