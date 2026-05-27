# java-address-book
A desktop address book application developed with Java Swing for course project, implementing complete contact management functions with GUI interaction and local file data persistence.

# Features
Contact management: add, view, edit, delete, navigate records
Sort contacts by name alphabetically
Pagination navigation (first/previous/next/last)
Local text file storage for data persistence
Friendly graphical user interface with prompt dialogs

# Tech Stack
Java 8+
Swing (GUI development)
File I/O (data read/write)
AWT (event handling & layout)
ArrayList & Collections (data operation)

# Project Structure
plaintext
/
├── AddressBook.java       Main interface & entry
├── add.java               Add contact window
├── navigator.java         Core logic & file operation
├── GBLHelper.java         Layout component helper
├── MyActListener1.java    Read file listener
├── MyActListener2.java    Write file listener
├── AudioPlayer.java       Audio prompt module
└── readme.txt             Data storage file

# How to Run
Clone or download this repository
Open with IDE (IntelliJ IDEA / Eclipse)
Compile all .java files
Run AddressBook.java to launch the application
The data file readme.txt will auto-generate on first run
Notes
All data stored locally in readme.txt
No database or external dependencies required
Supports Windows / macOS / Linux cross-platform
